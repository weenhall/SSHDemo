package com.ween.learn.poi;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class POIExpExcel_XSSF {

	public static void main(String[] args) {
		String[] title = { "id", "name", "sex" };

		// ����Excel������
		XSSFWorkbook workbook = new XSSFWorkbook();
		// ����һ��������
		XSSFSheet sheet = workbook.createSheet();
		// ������һ��
		XSSFRow row = sheet.createRow(0);
		XSSFCell cell = null;
		// �����һ������id,name,sex
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		// ׷������
		for (int j = 1; j <= 10; j++) {
			XSSFRow nextrow = sheet.createRow(j);
			XSSFCell cell2 = nextrow.createCell(0);
			cell2.setCellValue("SID" + j);
			cell2 = nextrow.createCell(1);
			cell2.setCellValue("�û�" + j);
			cell2 = nextrow.createCell(2);
			cell2.setCellValue("��");
		}
		// ����һ��excel�ļ�
		File file = new File("d:/poi_test.xlsx");
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
