package edu.zju.cst.w3.common;

import java.text.ParseException;

import edu.zju.cst.w3.model.Stock;

public class EntitiesHelper {

    public static Stock baseStock;

    static {
        try {
            baseStock = new Stock("000000", "aaa",
                        0.0, Utils.convertStringToDate("2018-01-03"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

