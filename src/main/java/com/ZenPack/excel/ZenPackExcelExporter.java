package com.ZenPack.excel;

import com.ZenPack.model.ZenPack;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ZenPackExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<ZenPack> listZenPack;


    public ZenPackExcelExporter(List<ZenPack> listZenPack) {
        this.listZenPack=listZenPack;
        workbook = new XSSFWorkbook();

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell=row.createCell(columnCount);
        if(value instanceof Long) {
            cell.setCellValue((Long) value);
        }else if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void writeHeaderLine() {
        sheet=workbook.createSheet("ZenPack");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        createCell(row,0,"ZenPack Information",style);
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
        font.setFontHeightInPoints((short)(10));

        row=sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "ZenPack Id", style);
        createCell(row, 1, "Created Date", style);
        createCell(row, 2, "Created By", style);
        createCell(row, 3, "Updated Time", style);
        createCell(row, 4, "Updated By", style);

    }

    private void writeDataLines() {
        int rowCount=2;

        CellStyle style=workbook.createCellStyle();
        XSSFFont font=workbook.createFont();
        font.setFontHeight(16);
        style.setFont(font);

        for(ZenPack zen:listZenPack) {
            Row row=sheet.createRow(rowCount++);
            int columnCount=0;
            createCell(row, columnCount++, zen.getZenPackId(), style);
            createCell(row, columnCount++, zen.getCreatedDate(), style);
            createCell(row, columnCount++, zen.getCreatedBy(), style);
            createCell(row, columnCount++, zen.getUpdatedTime(), style);
            createCell(row, columnCount++, zen.getUpdatedBy(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
