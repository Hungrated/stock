package edu.zju.cst.w3.dao;

import java.util.Date;
import java.util.List;

public interface IStockDAO {
    public double getStockClosingPrice(String stockId, Date date);
    public void insertStockClosingPrice(String stockId, Date date);
    public String getStockName(String stockId);
    public List<String> getStockIdList();
}
