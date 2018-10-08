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

    /**
     * 读取csv文件
     *
     * @param path csv文件路径
     * @return list
     */
    public List<String[]> readCsv(String path) throws ParseException {
        List<String[]> list = new ArrayList();
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "utf-8"),
                    CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER,
                    CSVParser.DEFAULT_ESCAPE_CHARACTER, 0);
            String[] strings;
            while ((strings = csvReader.readNext()) != null) {
                list.add(strings);
            }
            csvReader.close();

        } catch (Exception e) {
            // empty
        }
        return list;
    }

    /**
     * 写csv文件
     *
     * @param path csv文件路径
     * @param list 信息列表
     */
    public static void writeFile(String path, List<String[]> list) {
        File csv = new File(path);
//        if (!csv.exists()) {
//            try {
//                csv.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        try {
            CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csv), "utf-8"),
                    CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER);
            writer.writeAll(list);
            writer.close();
        } catch (Exception e) {
            // empty
        }
    }

    /**
     * 将csv解析出的数据转换成Stock类型列表
     *
     * @param stockList 股票信息列表
     * @return res
     */
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

    /**
     * 将Stock类型列表转换为可写csv的列表格式
     *
     * @param stockList 股票信息列表
     * @return res
     */
    public static List<String[]> parseStringArrayList(List<Stock> stockList) {
        List<String[]> res = new ArrayList();
        for (Stock stock : stockList) {
            String[] stringArray = {stock.getId(), stock.getName(),
                    Double.toString(stock.getClosingPrice()), Utils.convertDateToString(stock.getDate())};
            res.add(stringArray);
        }
        return res;
    }

    /**
     * 字符串转为Date对象
     *
     * @param dateString 日期字符串
     * @return date
     */
    public static Date convertStringToDate(String dateString) throws ParseException {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e) {
            // empty
        }
        return date;
    }

    /**
     * Date对象转换为字符串
     *
     * @param date 日期
     * @return dateString
     */
    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

}
