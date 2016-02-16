package com.famous_smoke.automation.data;

import com.famous_smoke.automation.util.TestConfigReader;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>Handles the interaction with the TestData Workbook.</p>
 */
public final class DataWorkbook {

    /**
     * The Singleton representing the TestData Workbook
     * configured in the TestConfig.propertiesfile.
     */
    private static final DataWorkbook TESTDATA_WORKBOOK  = new DataWorkbook(TestConfigReader.getTestDataWorkbookPath());

    /**
     * The name of the sheet with the BrandPageData
     * information.
     *
     * This sheet has to exist in the XLSX even if
     * it's blank.
     */
    private static final String BRANDPAGEDATA_SHEET_NAME = "BrandPageData";

    /**
     * The header row information for the BrandPage
     * Data Sheet.
     */
    private static final int    BRAND_HEADER_ROW               = 0;
    private static final String BRAND_URL_HEADER               = BrandPageData.URL_FIELD_NAME;
    private static final String BRAND_CANONICAL_HEADER         = BrandPageData.CANONICAL_FIELD_NAME;
    private static final String BRAND_TITLE_HEADER             = BrandPageData.TITLE_FIELD_NAME;
    private static final String BRAND_META_DESCRIPTION_HEADER  = BrandPageData.METADESCRIPTION_FIELD_NAME;
    private static final String BRAND_HEADER1_HEADER           = BrandPageData.HEADER1_FIELD_NAME;
    private static final String BRAND_DESCRIPTION_HEADER       = BrandPageData.DESCRIPTION_FIELD_NAME;
    private static final String BRAND_BREADCRUMBS_TEXT_HEADER  = BrandPageData.BREADCRUMBS_TEXT_FIELD_NAME;
    private static final String BRAND_BREADCRUMBS_LINKS_HEADER = BrandPageData.BREADCRUMBS_LINKS_FIELD_NAME;
    private static final String BRAND_IDENTIFIED_HEADER        = BrandPageData.IDENTIFIED_FIELD_NAME;
    /**
     * The column placements of the BrandPageData elements
     * in the XLSX sheet.
     */
    private static final int BRAND_URL_COLUMN               = 0;
    private static final int BRAND_CANONICAL_COLUMN         = 1;
    private static final int BRAND_TITLE_COLUMN             = 2;
    private static final int BRAND_META_DESCRIPTION_COLUMN  = 3;
    private static final int BRAND_HEADER1_COLUMN           = 4;
    private static final int BRAND_DESCRIPTION_COLUMN       = 5;
    private static final int BRAND_BREADCRUMBS_TEXT_COLUMN  = 6;
    private static final int BRAND_BREADCRUMBS_LINKS_COLUMN = 7;
    private static final int BRAND_IDENTIFIED_COLUMN        = 8;

    /**
     * The location of the XLSX File.
     */
    private final String location;

    /**
     * This constructor is only accessed privately
     * by the Singleton.
     * @param location the XLSX File location.
     */
    private DataWorkbook(final String location) {
        this.location = location;
    }

    /**
     * The static accesor to the TestData Workbook
     * Singleton.
     * @return the DataWorkbook singleton.
     */
    public static DataWorkbook getTestDataWorkbook() {
        return TESTDATA_WORKBOOK;
    }

    /**
     * Writes the Collection of BrandPageData in the
     * BrandPageData as a table.
     *
     * The elements of the BrandPageData objects are
     * placed in specific columns of the table, this
     * is determined by a DataMap which pairs each
     * element with a column.
     *
     * If the BrandPageData Sheet already exists, it
     * is overwritten with only the values passed as
     * parameters.
     * @param datas the Collection of BrandPageData
     *              to write.
     * @return the current DataWorkbook object,
     * this allows chained calls.
     * @throws IOException
     */
    public DataWorkbook writeBrandPages(final Collection<BrandPageData> datas) throws IOException {
        Workbook workbook = openWorkBook(location);
        Sheet sheet = createBrandPageDataSheet(workbook);

        int row = 1;
        for (BrandPageData data : datas) {
            Row brandRow = sheet.createRow(row);

            CellStyle dataStyle = createDataStyle(workbook);
            getBrandDataMap(data).forEach((column, value) -> {
                Cell cell = createCell(brandRow, column, dataStyle);
                cell.setCellValue(value);
                sheet.autoSizeColumn(cell.getColumnIndex());
            });

            ++row;
        }

        writeWorkBook(workbook, location);
        workbook.close();
        return this;
    }

    /**
     * Reads the BrandPageData table in the BrandPageData
     * Sheet, and returns the values in a Collection.
     * @return the Collection with the BrandPageData
     * in the Sheet.
     * @throws IOException
     */
    public Collection<BrandPageData> readBrandPages() throws IOException {
        HashSet<BrandPageData> brands = new HashSet<>();

        Workbook workbook = openWorkBook(location);

        Sheet sheet = getBrandPageDataSheet(workbook);
        for (int row = 1; row <= sheet.getLastRowNum(); ++row) {
            Row dataRow = sheet.getRow(row);

            String url = getCellValue(dataRow, BRAND_URL_COLUMN);
            String canonical = getCellValue(dataRow, BRAND_CANONICAL_COLUMN);
            String title = getCellValue(dataRow, BRAND_TITLE_COLUMN);
            String metaDescription = getCellValue(dataRow, BRAND_META_DESCRIPTION_COLUMN);
            String header1 = getCellValue(dataRow, BRAND_HEADER1_COLUMN);
            String description = getCellValue(dataRow, BRAND_DESCRIPTION_COLUMN);
            String breadcrumbsText = getCellValue(dataRow, BRAND_BREADCRUMBS_TEXT_COLUMN);
            String breadcrumbsLinks = getCellValue(dataRow, BRAND_BREADCRUMBS_LINKS_COLUMN);
            String identified = getCellValue(dataRow, BRAND_IDENTIFIED_COLUMN);

            brands.add(DataFactory.createBrandPage(
                    DataFactory.createBasePage(
                            url, title, canonical,
                            metaDescription, breadcrumbsText,
                            Arrays.asList(breadcrumbsLinks.split(BasePageData.BREADCRUMBS_LINKS_SEPARATOR))
                    ),
                    header1,
                    description,
                    Boolean.valueOf(identified)));
        }
        return brands;
    }

    /**
     * Opens the XLSX Workbook specified in the location.
     * @param location the location of the Workbook.
     * @return the Workbook as an Apache POI object.
     * @throws IOException
     */
    private static Workbook openWorkBook(final String location) throws IOException {
        File dataFile = new File(location);
        FileInputStream inputStream = new FileInputStream(dataFile);
        return new XSSFWorkbook(inputStream);
    }

    /**
     * Writes the changes made in the workbook
     * in the specified location.
     * @param workbook the modified Workbook.
     * @param location the location of the XLSX file.
     * @return the written Workbook.
     * @throws IOException
     */
    private static Workbook writeWorkBook(final Workbook workbook,
                                   final String location) throws IOException {
        File dataFile = new File(location);
        FileOutputStream outputStream = new FileOutputStream(dataFile);
        workbook.write(outputStream);
        return workbook;
    }

    /**
     * Retrieves the BrandPageData Sheet of the given
     * Workbook.
     * @param workbook The Workbook with the information.
     * @return the BrandPageDataSheet as an Apache POI
     * object.
     */
    private static Sheet getBrandPageDataSheet(final Workbook workbook) {
        return workbook.getSheet(BRANDPAGEDATA_SHEET_NAME);
    }

    /**
     * Creates a new BrandPageData Sheet in the Workbook,
     * if there is already a sheet, it is deleted in order
     * to create a new one.
     *
     * The Sheet is created with a Header row with the
     * identifiers of the BrandPageData columns.
     * @param workbook the Workbook where the Sheet will
     *                 be created.
     * @return the new BrandPageData Sheet as an Apache
     * POI object.
     */
    private static Sheet createBrandPageDataSheet(final Workbook workbook) {
        if (getBrandPageDataSheet(workbook) != null) {
            deleteSheet(workbook, getBrandPageDataSheet(workbook));
        }
        Sheet sheet = workbook.createSheet(BRANDPAGEDATA_SHEET_NAME);
        Row header = sheet.createRow(BRAND_HEADER_ROW);
        CellStyle style = createHeaderStyle(workbook);

        getBrandHeaderMap().forEach((column, value) -> {
            Cell cell = createCell(header, column, style);
            cell.setCellValue(value);
            sheet.autoSizeColumn(cell.getColumnIndex());
        });

        return sheet;
    }

    /**
     * Deletes a Sheet from a Workbook.
     * @param workbook the Workbook.
     * @param sheet the Sheet to delete.
     */
    private static void deleteSheet(final Workbook workbook,
                                    final Sheet sheet) {
        workbook.removeSheetAt(workbook.getSheetIndex(sheet));
    }

    /**
     * Creates a map of the BrandPageData Sheet
     * table headers specifying in which column
     * goes each header.
     * @return the Map with the Column and Header.
     */
    private static Map<Integer, String> getBrandHeaderMap() {
        return Arrays.stream(new Object[][] {
                {BRAND_URL_COLUMN, BRAND_URL_HEADER},
                {BRAND_CANONICAL_COLUMN, BRAND_CANONICAL_HEADER},
                {BRAND_TITLE_COLUMN, BRAND_TITLE_HEADER},
                {BRAND_META_DESCRIPTION_COLUMN, BRAND_META_DESCRIPTION_HEADER},
                {BRAND_HEADER1_COLUMN, BRAND_HEADER1_HEADER},
                {BRAND_DESCRIPTION_COLUMN, BRAND_DESCRIPTION_HEADER},
                {BRAND_BREADCRUMBS_TEXT_COLUMN, BRAND_BREADCRUMBS_TEXT_HEADER},
                {BRAND_BREADCRUMBS_LINKS_COLUMN, BRAND_BREADCRUMBS_LINKS_HEADER},
                {BRAND_IDENTIFIED_COLUMN, BRAND_IDENTIFIED_HEADER}
        }).collect(Collectors.toMap(kv -> (Integer) kv[0], kv -> (String) kv[1]));
    }

    /**
     * Creates a map of the BrandPageData Sheet
     * table rows specifying in which column
     * goes each BrandPageData field.
     * @param data the BrandPageData instance
     * @return the Map with the Column and
     * BrandPageData fields.
     */
    private static Map<Integer, String> getBrandDataMap(final BrandPageData data) {
        return Arrays.stream(new Object[][] {
                {BRAND_URL_COLUMN, data.getURL()},
                {BRAND_CANONICAL_COLUMN, data.getCanonical()},
                {BRAND_TITLE_COLUMN, data.getTitle()},
                {BRAND_META_DESCRIPTION_COLUMN, data.getMetaDescription()},
                {BRAND_HEADER1_COLUMN, data.getHeader1()},
                {BRAND_DESCRIPTION_COLUMN, data.getDescription()},
                {BRAND_BREADCRUMBS_TEXT_COLUMN, data.getBreadcrumbsText()},
                {BRAND_BREADCRUMBS_LINKS_COLUMN, getBreadcrumbsLinksAsString(data.getBreadcrumbsLinks())},
                {BRAND_IDENTIFIED_COLUMN, data.getIdentified().toString()}
        }).collect(Collectors.toMap(kv -> (Integer) kv[0], kv -> (String) kv[1]));
    }

    /**
     * Creates the Cell Style to be applied
     * to the Header cells.
     * @param workbook the Workbook in which
     *                 the Style is going to
     *                 be created.
     * @return the Cell Style as an Apache
     * POI object.
     */
    private static CellStyle createHeaderStyle(final Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(HSSFColor.AQUA.index);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        return style;
    }

    /**
     * Creates the Cell Style to be applied
     * to the Data cells.
     * @param workbook the Workbook in which
     *                 the Style is going to
     *                 be created.
     * @return the Cell Style as an Apache
     * POI object.
     */
    private static CellStyle createDataStyle(final Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setWrapText(true);
        return style;
    }

    /**
     * Creates a new Cell in the given Row.
     * @param row the Row where the Cell is
     *            going to be placed.
     * @param column the Column where the Cell
     *               will be created.
     * @param style the Style to be applied to
     *              the Cell.
     * @return the new Cell as an Apache POI
     * object.
     */
    private static Cell createCell(final Row row, final int column, final CellStyle style){
        Cell cell = row.createCell(column);
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * Retrieves the value of a Cell
     * as a String object.
     * @param row the Row where the Cell
     *            is located.
     * @param column the column of the Cell.
     * @return the String value of the Cell.
     */
    private static String getCellValue(final Row row, final int column) {
        return row.getCell(column, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
    }

    /**
     * Transforms the Collection of Strings
     * representing the BreadcrumbsLinks as
     * a Single String
     * @param breadcrumbsLinks the String Collection.
     * @return the reduced String with all
     * the Breadcrumbs Links.
     */
    private static String getBreadcrumbsLinksAsString(final Collection<String> breadcrumbsLinks) {
        return breadcrumbsLinks
                .stream()
                .reduce("", (a, b) -> a + BrandPageData.BREADCRUMBS_LINKS_SEPARATOR + b)
                .replaceFirst(BrandPageData.BREADCRUMBS_LINKS_SEPARATOR, "");
    }

}
