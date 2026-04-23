package utils;

import org.apache.poi.xssf.usermodel.*;
import java.io.*;

public class FeatureFileGenerator {

    public static void generateFeatureFile() {

        String excelPath = "src/test/resources/testdata/loginData.xlsx";
        String featurePath = "src/test/resources/features/generatedLogin.feature";

        try {
            File featureFile = new File(featurePath);
            featureFile.getParentFile().mkdirs();
            featureFile.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(featureFile));

            FileInputStream file = new FileInputStream(excelPath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            writer.write("Feature: Herokuapp Login\n\n");

            int rows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < rows; i++) {

                String username = sheet.getRow(i).getCell(0).toString();
                String password = sheet.getRow(i).getCell(1).toString();
                String result = sheet.getRow(i).getCell(2).toString();

                writer.write("Scenario: Login with user " + username + "\n");
                writer.write("  Given user is on login page\n");
                writer.write("  When user enters username \"" + username + "\" and password \"" + password + "\"\n");
                writer.write("  And clicks on login button\n");
                writer.write("  Then login result should be \"" + result + "\"\n\n");
            }

            writer.close();
            workbook.close();

            System.out.println("✅ Feature file generated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}