package testData;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    static FileInputStream fileInputStream = null ;

    public FileInputStream getFileInputStream() {
        String filePath = System.getProperty("user.dir")+"/src/test/java/testData/testData.xlsx";
        File srcFile = new File(filePath);

        try {
            fileInputStream = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("Test Data file not found. treminating Process !! : Check file path of TestData");
            System.exit(0);
        }
        return fileInputStream;
    }

    public Object[][] getExcelData(int sheetNumber) throws IOException {
        fileInputStream = getFileInputStream();
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        //sheet number index starts from 0
        XSSFSheet sheet = workbook.getSheetAt(sheetNumber);

        //get max number of rows
        int totalNumberOfRows = (sheet.getLastRowNum()+1);

        //get max number of columns
        // Initialize the maximum number of columns to 0
        int maxColumns = 0;
        // Iterate over all the rows in the sheet
        for (Row row : sheet) {
            // Get the number of cells in this row
            int numCells = row.getLastCellNum();

            // If this row has more cells than the current maximum, update the maximum
            if (numCells > maxColumns) {
                maxColumns = numCells;
            }
        }
        // The total number of columns is the maximum number of cells in any row
        int totalNumberOfColumns = maxColumns;

        String[][] arrayExcelData = new String[totalNumberOfRows][totalNumberOfColumns] ;

        for (int i = 0; i < totalNumberOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < totalNumberOfColumns; j++) {
                    if (row.getCell(j) != null) {
                        arrayExcelData[i][j] = row.getCell(j).toString();
                    }
                }
            }
        }

        workbook.close();
        return arrayExcelData;
    }
}