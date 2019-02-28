package com.backbase.datafactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class  DataFactory {
	public final static String testid1="TC_001";
	public final static String testid2="TC_002";
	public final static String testid3="TC_003";
	public final static String testid4="TC_004";
	public final static String testid5="TC_005";
	public final static String testid6="TC_006";
	public final static String testid7="TC_007";
	public final static String testid8="TC_008";
	public final static String testid9="TC_009";
	public final static String testid10="TC_010";
	public final static String testid11="TC_011";
	public final static String testid12="TC_012";
	final static String workingDir = System.getProperty("user.dir");
	final  static String filePath = workingDir+ "\\Files\\heroukapp.xls";
       public static String rtnVal = null;
	public static  String getData(String sheetName,String caseID) throws NumberFormatException {

		int rownum = 0;

		try {
			InputStream input = new FileInputStream(filePath);
			HSSFWorkbook wb=new HSSFWorkbook(input);
			HSSFSheet sh=wb.getSheet(sheetName);
			rownum= findRow(sh,caseID);
			HSSFRow rc=sh.getRow(rownum);
			HSSFRichTextString s = rc.getCell(0).getRichStringCellValue();
			HSSFRichTextString s2 = rc.getCell(1).getRichStringCellValue();
			HSSFRichTextString s3 = new HSSFRichTextString(s.getString()+ "-->  " +s2.getString());
			rtnVal= s3.getString();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(NumberFormatException ex){ // handle your exception
			ex.printStackTrace();
		}
		return rtnVal;
	}
		public static int findRow(HSSFSheet sheet, String cellContent) {
			for (Row row : sheet) {
				for (Cell cell : row) {
					if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
						if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
							return row.getRowNum();
						}
					}
				}
			}
			return 0;
		}
		public static  String getData(String sheetName,int rowNum,int cellNum ) throws NumberFormatException {

			try {
				InputStream input = new FileInputStream(filePath);
				HSSFWorkbook wb=new HSSFWorkbook(input);
				HSSFSheet sh=wb.getSheet(sheetName);
				HSSFRow rc=sh.getRow(rowNum);
				HSSFRichTextString s = rc.getCell(cellNum).getRichStringCellValue();
				rtnVal= s.toString();
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}catch(NumberFormatException ex){ 
				ex.printStackTrace();
			}
			return rtnVal;
		}

}


