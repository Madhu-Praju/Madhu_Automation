package com.qea.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcelData {
	 public FileInputStream fis = null;
	    public XSSFWorkbook workbook = null;
	    public XSSFSheet sheet = null;
	    public XSSFRow row = null;
	    public XSSFCell cell = null;
	
	public ArrayList readExcel(String filePath,String fileName,int sheetindex, int columindex) throws IOException {
		
		//this is for obtaining input bytes from file
		ArrayList data = new ArrayList();
		FileInputStream inputStream = new FileInputStream(new File(filePath+"\\"+fileName));
		//creating workbook instance
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet =wb.getSheetAt(sheetindex);
		int rownum=sheet.getLastRowNum();
		
		//System.out.println("Print Row Number of sheet : " + rownum);
//		
//			for(int j=0;j<=rownum;j++) {
//				row=sheet.getRow(j);
//			cell =row.getCell(columindex);
//			System.out.println("Print Cell Data"+ cell.getStringCellValue());			
//		}
		 Iterator rows = sheet.rowIterator();
	        while (rows.hasNext())
	        {
	            row=(XSSFRow) rows.next();
	        Iterator cells = row.cellIterator();
	        while (cells.hasNext()) {
	        	for(int j=0;j<=rownum;j++) {
	        		row=sheet.getRow(j);
	        		cell =row.getCell(columindex);
	        		switch(cell.getCellType()) {
	        		case STRING:
	        			
	        			if(cell.getStringCellValue()!=null) {
	        			data.add(cell.getStringCellValue());
	        			//System.out.println("Print Cell Data"+ cell.getStringCellValue());	
	        			break;
	        			}
	        			else {break;}
	        					
			
	        			
	        		case NUMERIC:
	        			data.add(cell.getNumericCellValue());
	        			break;
	        			
	        		case BOOLEAN:
	        			data.add(cell.getBooleanCellValue());
	        			break;
	        			
	        		case BLANK:
	        			data.add("");
	        			break;
	        			
	        		default:
	        			data.add("");
	        			break;
	        			}
	        		}
	        	break;
	        	}
	        break;
	        
	        }			
		//System.out.println("Data from List"+ data);
		inputStream.close(); 
		
		return data;
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		readExcelData rs = new readExcelData();
		rs.readExcel("C:\\Users\\ReemaSingh\\OneDrive - FACCTUM IT SOLUTIONS INDIA PRIVATE LIMITED\\Desktop\\IBL_Testing", "Test_Sheet.xlsx", 2,0);
		
	}
}
	
	
	
	
	


