package com.ween.learn.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlExpExcel {

	/*
	 * JXL����Excel�ļ�
	 */
	public static void main(String[] args) {
		String[] title={"id","name","sex"};
		//����Excel�ļ�
		File file=new File("c:/jxl_test.xls");
		try {
			file.createNewFile();
			//����������
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			WritableSheet sheet=workbook.createSheet("sheet1",0);
			Label label=null;
			//��һ����������
			for(int i=0;i<title.length;i++){
				label=new Label(i,0,title[i]);
				sheet.addCell(label);
			}
			//��ÿ���������
			for(int j=1;j<10;j++){
				label =new Label(0,j,"a"+j);
				sheet.addCell(label);
				label =new Label(1,j,"user"+j);
				sheet.addCell(label);
				label =new Label(2,j,"male");
				sheet.addCell(label);
			}
			//д������
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
