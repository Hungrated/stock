package edu.zju.cst.w3.model;

import java.util.Date;

/**
 * class Stock
 *
 * 股票信息
 */
public class Stock {

    private String id;
    private String name;
    private double closingPrice;
    private Date date;

    public Stock(String id, String name, double closingPrice, Date date) {
        this.id = id;
        this.name = name;
        this.closingPrice = closingPrice;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public Date getDate() {
        return date;
    }

}
