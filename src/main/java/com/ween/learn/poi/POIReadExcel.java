package com.ween.learn.poi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class POIReadExcel {
	public static void main(String[] args) {
        List<Person> person=new ArrayList<Person>();
		//������Ҫ������Excel�ļ�
		File file=new File("d:/poi_test.xls");
		try {
			//����Excel,��ȡ�ļ�����
			HSSFWorkbook workbook=new HSSFWorkbook(FileUtils.openInputStream(file));
			//��ȡ��һ��������workbook,workbook.getSheet("Sheet0"),Ҳ���Բ�������Ķ�ȡ������
			//HSSFSheet sheet=workbook.getSheet("Sheet0");
			//��ȡĬ�ϵ�һ��������sheet
			HSSFSheet sheet=workbook.getSheetAt(0);
			//��ȡsheet�����һ���к�
			int lastRowNum=sheet.getLastRowNum();
			for (int i = 1; i <= lastRowNum; i++) {//������
				HSSFRow row=sheet.getRow(i);
				//��ȡ��ǰ�����Ԫ���к�
				Person pp=new Person();
				pp.setId(row.getCell(0).getStringCellValue());
				pp.setName(row.getCell(1).getStringCellValue());
				pp.setSex(row.getCell(2).getStringCellValue());
				person.add(pp);
			}
			System.out.println(person.get(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
