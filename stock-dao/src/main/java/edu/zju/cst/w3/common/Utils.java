package edu.zju.cst.w3.common;

import java.io.*;
import java.security.spec.ECField;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import edu.zju.cst.w3.model.Stock;

public class Utils {
    public List<String[]> readCsv(String path) throws ParseException {
        List<String[]> list = new ArrayList();
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            // CSVReader csvReader = new CSVReader(new InputStreamReader(in, "GBK"));
            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "utf-8"),
                    CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
                    CSVParser.DEFAULT_ESCAPE_CHARACTER, 1);
            String[] strings;
            while ((strings = csvReader.readNext()) != null) {
                list.add(strings);
            }
            csvReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeFile(String path, String[] strArr, List<String[]> list) {
        File csv = new File(path);
        if (!csv.exists()) {
            try {
                csv.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv), "utf-8"),
                    CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER);
            writer.writeNext(strArr);
            writer.writeAll(list);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Stock> parseStockList(List<String[]> stockList) throws ParseException {
        List<Stock> res = new ArrayList();
        for (String[] stock : stockList) {
            try {
                res.add(new Stock(stock[0], stock[1],
                        Double.parseDouble(stock[2]), Utils.convertStringToDate(stock[3])));
            } catch (Exception e) {
                // empty
            }
        }
        return res;
    }

    public static List<String[]> parseStringArrayList(List<Stock> stockList) {
        List<String[]> res = new ArrayList();
        for (Stock stock : stockList) {
            String[] stringArray = {stock.getId(), stock.getName(),
                    Double.toString(stock.getClosingPrice()), Utils.convertDateToString(stock.getDate())};
            res.add(stringArray);
        }
        return res;
    }

    public static Date convertStringToDate(String dateString) throws ParseException {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            // empty
        }
        return date;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

}
