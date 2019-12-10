package com.tech.sse.excel.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelUtil {
	private POIFSFileSystem fs;
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;
	private HSSFCell cell;
	private Logger logger = Logger.getLogger(ExcelUtil.class);
/*
 * 打开excel
 */
	public void openExcel(String filePath) throws Exception {
		if (filePath == null) {
			logger.error("filePath error!");
		}
		InputStream fileNode = new FileInputStream(filePath);
		 fs = new POIFSFileSystem(fileNode);
		 wb = new HSSFWorkbook(fileNode);		
	}
	
	public ArrayList<String> readExcel() {
		ArrayList<String> list = new ArrayList();
		sheet = wb.getSheetAt(0);
		for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
			row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
				cell = row.getCell(cellNum);
				if (cell == null) {
					continue;
				}
			String cellContent = getCellValue(cell);
			list.add(cellContent);
				
			}
		}
		return list;
	}
	
	public void closeExcel() throws IOException {
		fs.close();
	}
	
	private static String getCellValue(HSSFCell cell) {
		if (cell.getCellType() == CellType.BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == CellType.NUMERIC ) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == CellType.STRING) {
			return String.valueOf(cell.getStringCellValue());
		} else {
			return "##";
		}
			
	}
}
