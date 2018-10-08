package edu.zju.cst.w3.dao;

import java.text.ParseException;
import java.util.*;

import edu.zju.cst.w3.common.StockDataBase;
import edu.zju.cst.w3.model.Stock;

public class StockDAO implements IStockDAO {

    public StockDataBase db;

    public StockDAO() throws ParseException {
        db = new StockDataBase();
    }

    public double getStockClosingPrice(String stockId, Date date) {
        List<Stock> stockList = db.queryAllById(stockId);
        double res = 0;
        for (Stock stock : stockList) {
            if (stock.getDate().getTime() == date.getTime()) {
                res = stock.getClosingPrice();
                break;
            }
        }
        return res;
    }

    public void insertStockClosingPrice(String stockId, Date date, double closingPrice) {
        String stockName = getStockName(stockId);
        List<Stock> stockList = db.getStockList();
        stockList.add(new Stock(stockId, stockName, closingPrice, date));
        db.save(stockList);
    }

    public String getStockName(String stockId) {
        Stock stock = db.queryFirstById(stockId);
        return stock.getName();
    }

    public List<String> getStockIdList() {
        List<Stock> stockList = db.getStockList();
        List<String> res = new ArrayList<String>();
        for (Stock stock : stockList) {
            res.add(stock.getId());
        }
        Set set = new HashSet(res);
        List<String> newRes = new ArrayList(set);
        Collections.sort(newRes, new Comparator<String>() {
            public int compare(String o1, String o2) {
                if (o1.compareTo(o2) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return newRes;
    }
}
