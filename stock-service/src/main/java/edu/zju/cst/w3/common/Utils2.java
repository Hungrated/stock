package edu.zju.cst.w3.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.zju.cst.w3.model.Stock;

public class Utils2 {

    /**
     * 根据时间范围查询股票信息列表
     *
     * @param stockList 股票列表
     * @param startDate 起始日期
     * @param endDate 截止日期
     */
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
