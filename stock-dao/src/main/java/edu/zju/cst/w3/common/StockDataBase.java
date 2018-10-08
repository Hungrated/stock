package edu.zju.cst.w3.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockDataBase {
    private static String CSV_DIR = "src/main/resources/stock.csv";
    private List<String[]> stockList;

    public StockDataBase() {
        stockList = new ArrayList<String[]>(){};
        read();
    }

    private void read() {
        Utils utils = new Utils();
        stockList = utils.readCsv(CSV_DIR);
    }

    public List<String[]> getStockList () {
        return stockList;
    }

    public void print() {
        for (String[] aStockList : stockList) {
            System.out.println(Arrays.deepToString(aStockList));
        }
    }

    public void save(String[] line, List<String[]> list) {
        Utils.writeFile(CSV_DIR, line, list);
    }


}
