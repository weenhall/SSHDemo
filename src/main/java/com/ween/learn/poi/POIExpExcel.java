package com.ween.learn.poi;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIExpExcel {

	public static void main(String[] args) {
		String[] title = { "id", "name", "sex" };
		// 创建Excel工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 创建一个工作表
		HSSFSheet sheet = workbook.createSheet();
		// 创建第一行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = null;
		// 插入第一行数据id,name,sex
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		// 追加数据
		for (int j = 1; j <= 10; j++) {
			HSSFRow nextrow = sheet.createRow(j);
			HSSFCell cell2 = nextrow.createCell(0);
			cell2.setCellValue("SID" + j);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue("用户" + j);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue("男");
		}
		// 创建一个excel文件
		File file = new File("d:/poi_test.xls");
		try {
			file.createNewFile();
			FileOutputStream fos = FileUtils.openOutputStream(file,true);
			workbook.write(fos);
			workbook.close();
			fos.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
