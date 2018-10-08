package edu.zju.cst.w3.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import edu.zju.cst.w3.model.Stock;

/**
 * Class StockDataBase
 *
 * 模拟股票数据库和查询操作
 */
public class StockDataBase {
    private static String CSV_DIR = "../stock.csv";
    private List<Stock> stockList;

    public StockDataBase() throws ParseException {
        stockList = new ArrayList<Stock>();
        read();
    }

    private void read() throws ParseException {
        Utils utils = new Utils();
        List<String[]> listRaw = utils.readCsv(CSV_DIR);
        stockList = Utils.parseStockList(listRaw);
    }

    public List<Stock> getStockList() {
        return stockList;
    }

    public void save(List<Stock> list) {
        List<String[]> stringArrayList = Utils.parseStringArrayList(list);
        Utils.writeFile(CSV_DIR, stringArrayList);
    }

    public List<Stock> queryAllById(String stockId) {
        List<Stock> res = new ArrayList<Stock>();
        for (Stock stock : stockList) {
            if (stock.getId().equals(stockId)) {
                res.add(stock);
            }
        }
        return res;
    }

    public Stock queryFirstById(String stockId) {
        Stock res = null;
        for (Stock stock : stockList) {
            if (stock.getId().equals(stockId)) {
                res = stock;
                break;
            }
        }
        return res;
    }

}
