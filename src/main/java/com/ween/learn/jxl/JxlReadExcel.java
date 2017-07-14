package com.ween.learn.jxl;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class JxlReadExcel {
	/*
	 * JXL����Excel
	 */
	public static void main(String[] args) {
		try {
			//����workbook
			Workbook workbook = Workbook.getWorkbook(new File("c:/jxl_test.xls"));
			//��ȡ��һ��������sheet
			Sheet sheet=workbook.getSheet(0);
			//��ȡ����
			for(int i=0;i<sheet.getRows();i++){
				for(int j=0;j<sheet.getColumns();j++){
					Cell cell=sheet.getCell(j,i);
					System.out.print(cell.getContents()+" ");
				}
				System.out.println();
			}
			workbook.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
