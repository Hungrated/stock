package edu.zju.cst.w3.common;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.zju.cst.w3.model.Stock;

public class StockDataBase {
    private static String CSV_DIR = "src/main/resources/stock.csv";
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

    public void print() {
        for (Stock stock : stockList) {
            stock.print();
        }
    }

    public void save(Stock stock, List<Stock> list) {
        List<String[]> stringArrayList = Utils.parseStringArrayList(list);
        String[] line = {stock.getId(), stock.getName(),
                Double.toString(stock.getClosingPrice()), Utils.convertDateToString(stock.getDate())};
        Utils.writeFile(CSV_DIR, line, stringArrayList);
    }

    public List<Stock> queryAllById(String stockId) {
        List<Stock> res = new ArrayList<Stock>();
        for (Stock stock : stockList) {
            if (stock.getId() == stockId) {
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

    public Stock queryLastById(String stockId) {
        List<Stock> res = new ArrayList<Stock>();
        for (Stock stock : stockList) {
            if (stock.getId() == stockId) {
                res.add(stock);
            }
        }
        return res.get(res.size() - 1);
    }
}
