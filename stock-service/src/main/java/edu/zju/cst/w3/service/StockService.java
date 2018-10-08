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

    public double getStockClosingPrice(String stockId, Date date) {
        return stockDAO.getStockClosingPrice(stockId, date);
    }

    public void insertStockClosingPrice(String stockId, Date date, double closingPrice) {
        stockDAO.insertStockClosingPrice(stockId, date, closingPrice);
    }

    public String getStockName(String id) {
        return stockDAO.getStockName(id);
    }

    public List<String> getStockIdList() {
        return stockDAO.getStockIdList();
    }

    public String getBestStock(Date startDate, Date endDate) {

        List<String> stockIdList = stockDAO.getStockIdList();
        List<Stock> stockList = Utils2.queryAllByDateRange(stockDAO.db.getStockList(), startDate, endDate);
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
