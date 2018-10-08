package edu.zju.cst.w3.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.zju.cst.w3.model.Stock;

public class Utils2 {
    public List<Stock> queryAllByDateRange(List<Stock> stockList, Date startDate, Date endDate) {
        List<Stock> res = new ArrayList<Stock>();
        for (Stock stock : stockList) {
            if ((stock.getDate().getTime() >= startDate.getTime())
                    && (stock.getDate().getTime() <= endDate.getTime())) {
                res.add(stock);
            }
        }
        return res;
    }
}
