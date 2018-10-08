package edu.zju.cst.w3.service;

import java.text.ParseException;
import java.util.*;

import edu.zju.cst.w3.common.Utils2;
import edu.zju.cst.w3.dao.StockDAO;
import edu.zju.cst.w3.model.Stock;

public class StockService implements IStockService {

    private StockDAO stockDAO;
    private static double MIN_CLOSING_PRICE_DIFF = -99999;

    public StockService() throws ParseException {
        stockDAO = new StockDAO();
    }

    /**
     * 根据id和日期查询股票收盘价
     *
     * @param stockId 股票id
     * @param date 日期
     * @return res
     */
    public double getStockClosingPrice(String stockId, Date date) {
        return stockDAO.getStockClosingPrice(stockId, date);
    }

    /**
     * 插入股票收盘价信息
     *
     * @param stockId 股票id
     * @param date 日期
     * @param closingPrice 收盘价
     */
    public void insertStockClosingPrice(String stockId, Date date, double closingPrice) {
        stockDAO.insertStockClosingPrice(stockId, date, closingPrice);
    }

    /**
     * 根据id和查询股票名称
     *
     * @param id 股票id
     * @return name
     */
    public String getStockName(String id) {
        return stockDAO.getStockName(id);
    }

    /**
     * 获取股票id列表
     *
     * @return stockIdList
     */
    public List<String> getStockIdList() {
        return stockDAO.getStockIdList();
    }


    /**
     * 获取指定时段内涨幅最大股票id
     *
     * @param startDate 起始日期
     * @param endDate 截止日期
     */
    public String getBestStock(Date startDate, Date endDate) {

        Utils2 utils2 = new Utils2();
        List<String> stockIdList = stockDAO.getStockIdList();
        List<Stock> stockList = utils2.queryAllByDateRange(stockDAO.db.getStockList(), startDate, endDate);
        Stock bestStock = null;
        double maxStockClosingPriceDiff = MIN_CLOSING_PRICE_DIFF;

        for (String stockId : stockIdList) {
            List<Stock> temp = new ArrayList<Stock>();
            for (Stock stock : stockList) {
                if (stock.getId().equals(stockId)) {
                    temp.add(stock);
                }
            }
            if (temp.size() <= 1) {
                break;
            } else {
                Collections.sort(temp, new Comparator<Stock>() {
                    public int compare(Stock o1, Stock o2) {
                        return Double.compare(o1.getClosingPrice(), o2.getClosingPrice());
                    }
                });
                double newStockClosingPriceDiff =
                        temp.get(temp.size() - 1).getClosingPrice() - temp.get(0).getClosingPrice();
                if (newStockClosingPriceDiff > maxStockClosingPriceDiff) {
                    maxStockClosingPriceDiff = newStockClosingPriceDiff;
                    bestStock = temp.get(0);
                }
            }
        }
        if (bestStock != null) {
            System.out.println("Best stock: " + bestStock.getId() + " " + bestStock.getName());
            return bestStock.getId();
        } else {
            return "null";
        }

    }
}
