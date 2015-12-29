package helpers;

import api.BrandPageData;
import api.PageData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static api.PageData.BREADCRUMBS_SEPARATOR;


/**
 * Created by jorge on 22-12-2015.
 */
public final class DataWorkbook {

    private static final int URL_COLUMN              = 0;
    private static final int CANONICAL_COLUMN        = 1;
    private static final int TITLE_COLUMN            = 2;
    private static final int META_DESCRIPTION_COLUMN = 3;
    private static final int HEADER1_COLUMN          = 4;
    private static final int DESCRIPTION_COLUMN      = 5;
    private static final int BREADCRUMBS_TEXT_COLUMN = 6;
    private static final int BREADCRUMBS_COLUMN      = 7;

    private final String location;

    private DataWorkbook(final String location) {
        this.location = location;
    }

    public static DataWorkbook getDefaultWorkbook() {
        return new DataWorkbook("src/test/resources/testData/TestData-seleniumframework.xlsx");
    }

    public DataWorkbook writeBrandPages(final Collection<BrandPageData> datas) throws IOException {
        Workbook workbook = openWorkBook(location);
        Sheet sheet = workbook.getSheet("TestData");

        int row = 1;
        for (BrandPageData data : datas) {
            Row brandRow = sheet.getRow(row);
            if(brandRow == null) {
                brandRow = sheet.createRow(row);
            }

            CellStyle dataStyle = createDataStyle(workbook);
            Cell urlCell = createCell(brandRow, URL_COLUMN, dataStyle);
            Cell canonicalCell = createCell(brandRow, CANONICAL_COLUMN, dataStyle);
            Cell titleCell = createCell(brandRow, TITLE_COLUMN, dataStyle);
            Cell metaDescriptionCell = createCell(brandRow, META_DESCRIPTION_COLUMN, dataStyle);
            Cell header1Cell = createCell(brandRow, HEADER1_COLUMN, dataStyle);
            Cell descriptionCell = createCell(brandRow, DESCRIPTION_COLUMN, dataStyle);
            Cell breadcrumbsTextCell = createCell(brandRow, BREADCRUMBS_TEXT_COLUMN, dataStyle);
            Cell breadrumbsCell = createCell(brandRow, BREADCRUMBS_COLUMN, dataStyle);

            urlCell.setCellValue(data.getURL());
            canonicalCell.setCellValue(data.getCanonical());
            titleCell.setCellValue(data.getTitle());
            metaDescriptionCell.setCellValue(data.getMetaDescription());
            header1Cell.setCellValue(data.getHeader1());
            descriptionCell.setCellValue(data.getDescription());
            breadcrumbsTextCell.setCellValue(data.getBreadcrumbsText());
            breadrumbsCell.setCellValue(getBreadcrumbs(data.getBreadcrumbs()));
            ++row;
        }

        writeWorkBook(workbook, location);
        workbook.close();
        return this;
    }



    public Collection<BrandPageData> readBrands() throws IOException {
        ArrayList<BrandPageData> brands = new ArrayList<>();

        Workbook workbook = openWorkBook(location);

        Sheet sheet = workbook.getSheet("TestData");
        for (int row = 1; row <= sheet.getLastRowNum(); ++row) {
            Row dataRow = sheet.getRow(row);

            String url = getCellValue(dataRow, URL_COLUMN);
            String canonical = getCellValue(dataRow, CANONICAL_COLUMN);
            String title = getCellValue(dataRow, TITLE_COLUMN);
            String metaDescription = getCellValue(dataRow, META_DESCRIPTION_COLUMN);
            String header1 = getCellValue(dataRow, HEADER1_COLUMN);
            String description = getCellValue(dataRow, DESCRIPTION_COLUMN);
            String breadcrumbsText = getCellValue(dataRow, BREADCRUMBS_TEXT_COLUMN);
            String[] breadcrumbs = getCellValue(dataRow, BREADCRUMBS_COLUMN).split(BREADCRUMBS_SEPARATOR);

            brands.add(DataFactory.createBrand(
                    DataFactory.createPage(
                            url, title, canonical,
                            metaDescription, breadcrumbsText,
                            Arrays.asList(breadcrumbs)),
                    header1,
                    description
            ));
        }
        return brands;
    }

    private Workbook openWorkBook(final String location) throws IOException {
        File dataFile = new File(location);
        FileInputStream inputStream = new FileInputStream(dataFile);
        return new XSSFWorkbook(inputStream);
    }

    private Workbook writeWorkBook(final Workbook workbook,
                                   final String location) throws IOException {
        File dataFile = new File(location);
        FileOutputStream outputStream = new FileOutputStream(dataFile);
        workbook.write(outputStream);
        return workbook;
    }

    private CellStyle createDataStyle(final Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setWrapText(true);
        return style;
    }

    private Cell createCell(final Row row, final int column, final CellStyle style){
        Cell cell = row.createCell(column);
        cell.setCellStyle(style);
        return cell;
    }

    private String getBreadcrumbs(final Collection<String> breadcrumbs) {
        StringBuilder builder = new StringBuilder();
        if (!breadcrumbs.isEmpty()) {
            for (String link : breadcrumbs){
                builder.append(link);
                builder.append(BREADCRUMBS_SEPARATOR);
            }
            builder.deleteCharAt(builder.lastIndexOf(BREADCRUMBS_SEPARATOR));
        }
        return builder.toString();
    }

    private String getCellValue(final Row row, final int column) {
        return row.getCell(column, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
    }

}
