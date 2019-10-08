package com.ween.learn.util.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/7/14.
 */
public class ExcelReadUtil {
    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * 取得Workbook对象(xls和xlsx对象不同,不过都是Workbook的实现类)
     * xls:HSSFWorkbook
     * xlsx：XSSFWorkbook
     */
    private Workbook getWorkbook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        InputStream in = file.getInputStream();
        if (file.getOriginalFilename().endsWith(XLS)) {
            workbook = new HSSFWorkbook(in);
        } else if (file.getOriginalFilename().endsWith(XLSX)) {
            workbook = new XSSFWorkbook(in);
        }
        return workbook;
    }

    /**
     * 检查文件合法
     */
    private void checkFile(MultipartFile file) throws FileNotFoundException, FileFormatException {
        if (file.isEmpty()) {
            throw new FileNotFoundException(file.getName() + "文件不存在");
        }
        if (!(file.getOriginalFilename().endsWith(XLS) || file.getOriginalFilename().endsWith(XLSX))) {
            throw new FileFormatException("上传的文件格式不是Excel");
        }
    }

    /**
     * Read Single CellValue
     */
    private String getCellValue(Cell cell) {
        DecimalFormat df=new DecimalFormat("0");
        if (cell == null) {
            return "";
        }
        //read by CellType
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return df.format(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * Read All CellValue
     */
    public List<Map<String, Object>> ExcelContentRead(MultipartFile file) throws FileNotFoundException, FileFormatException {
        this.checkFile(file);
        Workbook workbook = null;
        try {
            workbook = this.getWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);//获取工作簿
            int lastRowNum = sheet.getLastRowNum();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Map<String, Object> dataMap = null;
            Row rowHeader = sheet.getRow(0);//获取表头，一般为第一行
            for (int i = 1; i < lastRowNum; i++) {
                Row row = sheet.getRow(i);
                int lastCellNum = row.getLastCellNum();
                if (row.getCell(0) != null && StringUtils.isNotEmpty(row.getCell(0).getStringCellValue())) {
                    dataMap = new HashMap<String, Object>();
                    for (int j = 0; j < lastCellNum; j++) {
                        Cell cell = row.getCell(j);
                        dataMap.put(rowHeader.getCell(j).getStringCellValue(), this.getCellValue(cell));
                    }
                    list.add(dataMap);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
