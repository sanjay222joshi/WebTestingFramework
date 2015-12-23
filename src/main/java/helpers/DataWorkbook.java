package helpers;

import api.Brand;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pageobjects.BrandPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


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

    public DataWorkbook writeBrandPages(final Collection<BrandPage> pages) throws IOException {
        File dataFile = new File(location);
        FileInputStream inputStream = new FileInputStream(dataFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("TestData");

        int row = 1;
        for (BrandPage page : pages) {
            XSSFRow brandRow = sheet.getRow(row);
            if(brandRow == null) {
                brandRow = sheet.createRow(row);
            }
            brandRow.createCell(0).setCellValue(page.getURL());
            brandRow.createCell(1).setCellValue(page.getTitle());
            brandRow.createCell(2).setCellValue(page.getMetaDescription());
            brandRow.createCell(3).setCellValue(page.getHeader1());
            brandRow.createCell(4).setCellValue(page.getDescription());
            brandRow.createCell(5).setCellValue(page.getBreadCrumbsText());
            StringBuilder builder = new StringBuilder();
            for (String link : page.getBreadCrumbs()){
                builder.append(link);
                builder.append(",");
            }
            builder.deleteCharAt(builder.lastIndexOf(","));
            brandRow.createCell(6).setCellValue(builder.toString());
            ++row;
        }

        FileOutputStream outputStream = new FileOutputStream(dataFile);
        workbook.write(outputStream);
        workbook.close();

        return this;
    }

    public Collection<Brand> readBrands() throws IOException {
        ArrayList<Brand> brands = new ArrayList<>();

        File dataFile = new File(location);
        FileInputStream inputStream = new FileInputStream(dataFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheet("TestData");
        for (int row = 1; row <= sheet.getLastRowNum(); ++row) {
            XSSFRow dataRow = sheet.getRow(row);
            String url = dataRow.getCell(0, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String title = dataRow.getCell(1, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String metaDescription = dataRow.getCell(2, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String header1 = dataRow.getCell(3, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String description = dataRow.getCell(4, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String breadcrumbsText = dataRow.getCell(5, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue();
            String[] breadcrumbs = dataRow.getCell(6, XSSFRow.CREATE_NULL_AS_BLANK).getStringCellValue().split(",");
            brands.add( new BrandImpl(
                    url, title, metaDescription,
                    breadcrumbsText, breadcrumbs,
                    header1, description
            ));
        }
        return brands;
    }

    private static class BrandImpl implements Brand {

        private final String url;
        private final String title;
        private final String metaDescription;
        private final String breadcumbsText;
        private final List<String> breadcumbs;
        private final String header1;
        private final String description;

        BrandImpl(String url,
                          String title,
                          String metaDescription,
                          String breadcumbsText,
                          String[] breadcumbs,
                          String header1,
                          String description) {
            this.url = url;
            this.title = title;
            this.metaDescription = metaDescription;
            this.breadcumbsText = breadcumbsText;
            this.breadcumbs = Arrays.asList(breadcumbs);
            this.header1 = header1;
            this.description = description;
        }


        @Override
        public String getURL() {
            return null;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Override
        public String getMetaDescription() {
            return null;
        }

        @Override
        public String getBreadCrumbsText() {
            return null;
        }

        @Override
        public List<String> getBreadCrumbs() {
            return null;
        }

        @Override
        public String getHeader1() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }


    }


}
