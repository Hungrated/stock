package edu.zju.cst.w3.dao;

import java.text.ParseException;
import java.util.*;

import edu.zju.cst.w3.common.StockDataBase;
import edu.zju.cst.w3.common.Utils;
import edu.zju.cst.w3.model.Stock;

public class StockDAO implements IStockDAO {

    private StockDataBase db;

    public StockDAO() throws ParseException {
        db = new StockDataBase();
    }

    public double getStockClosingPrice(String stockId, Date date) {
        List<Stock> stockList = db.queryAllById(stockId);
        for (Stock stock : stockList) {
            if (stock.getDate().equals(Utils.convertDateToString(date))) {
                return stock.getClosingPrice();
            }
        }
        return 0;
    }

    public void insertStockClosingPrice(String stockId, Date date) {

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
        return newRes;
    }
}
