package edu.zju.cst.w3.common;

import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Utils {
    public List<String[]> readCsv(String path) {
        List<String[]> list = new ArrayList() {
        };
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

    public static Date convertStringToDate(String dateString) {
        return new Date(dateString);
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

}
