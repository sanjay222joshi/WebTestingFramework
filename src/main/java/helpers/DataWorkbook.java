package helpers;

import api.BrandPageData;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * Created by jorge on 22-12-2015.
 */
public class DataWorkbook {

    private final String location;

    private DataWorkbook(final String location) {
        this.location = location;
    }

    public static DataWorkbook getDefaultWorkbook() {
        return new DataWorkbook("src/test/resources/testData/TestData-seleniumframework.xlsx");
    }

    public DataWorkbook writeBrandPages(final Collection<BrandPageData> datas) throws IOException {
        File dataFile = new File(location);
        FileInputStream inputStream = new FileInputStream(dataFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("TestData");

        int row = 1;
        for (BrandPageData data : datas) {
            StringBuilder breadcrumbs = new StringBuilder();
            for (String link : data.getBreadcrumbs()){
                breadcrumbs.append(link);
                breadcrumbs.append(",");
            }
            if(breadcrumbs.lastIndexOf(",") > -1) {
                breadcrumbs.deleteCharAt(breadcrumbs.lastIndexOf(","));
            }
            XSSFRow brandRow = sheet.getRow(row);
            if(brandRow == null) {
                brandRow = sheet.createRow(row);
            }
            brandRow.createCell(0).setCellValue(data.getURL());
            brandRow.createCell(1).setCellValue(data.getCanonical());
            brandRow.createCell(2).setCellValue(data.getTitle());
            brandRow.createCell(3).setCellValue(data.getMetaDescription());
            brandRow.createCell(4).setCellValue(data.getHeader1());
            brandRow.createCell(5).setCellValue(data.getDescription());
            brandRow.createCell(6).setCellValue(data.getBreadcrumbsText());
            brandRow.createCell(7).setCellValue(breadcrumbs.toString());
            ++row;
        }

        FileOutputStream outputStream = new FileOutputStream(dataFile);
        workbook.write(outputStream);
        workbook.close();

        return this;
    }

    public Collection<BrandPageData> readBrands() throws IOException {
        ArrayList<BrandPageData> brands = new ArrayList<>();

        File dataFile = new File(location);
        FileInputStream inputStream = new FileInputStream(dataFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("TestData");
        for (int row = 1; row <= sheet.getLastRowNum(); ++row) {
            XSSFRow dataRow = sheet.getRow(row);
            String url = dataRow.getCell(0, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String canonical = dataRow.getCell(1, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String title = dataRow.getCell(2, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String metaDescription = dataRow.getCell(3, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String header1 = dataRow.getCell(4, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String description = dataRow.getCell(5, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String breadcrumbsText = dataRow.getCell(6, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String[] breadcrumbs = dataRow.getCell(7, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue().split(",");
            brands.add(DataFactory.createBrand(
                    DataFactory.createPage(
                            url, title, canonical,
                            metaDescription,
                            breadcrumbsText, Arrays.asList(breadcrumbs)),
                    header1,
                    description
            ));
        }
        return brands;
    }

}
