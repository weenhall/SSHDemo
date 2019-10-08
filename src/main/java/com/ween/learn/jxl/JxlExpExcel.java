package com.ween.learn.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlExpExcel {

	/*
	 * JXL创建Excel文件
	 */
	public static void main(String[] args) {
		String[] title={"id","name","sex"};
		//创建Excel文件
		File file=new File("c:/jxl_test.xls");
		try {
			file.createNewFile();
			//创建工作簿
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			WritableSheet sheet=workbook.createSheet("sheet1",0);
			Label label=null;
			//第一行设置列名
			for(int i=0;i<title.length;i++){
				label=new Label(i,0,title[i]);
				sheet.addCell(label);
			}
			//给每列添加数据
			for(int j=1;j<10;j++){
				label =new Label(0,j,"a"+j);
				sheet.addCell(label);
				label =new Label(1,j,"user"+j);
				sheet.addCell(label);
				label =new Label(2,j,"male");
				sheet.addCell(label);
			}
			//写入数据
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
