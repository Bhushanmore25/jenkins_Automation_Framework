package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.FileInputStream;

public class ExcelReader {

    public static String getData(String path, String sheet, int row, int col) {
        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheet);

            String data = sh.getRow(row).getCell(col).toString();
            wb.close();
            return data;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getRowCount(String path, String sheet) {
        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            int rows = wb.getSheet(sheet).getLastRowNum();
            wb.close();
            return rows;
        } catch (Exception e) {
            return 0;
        }
    }
}