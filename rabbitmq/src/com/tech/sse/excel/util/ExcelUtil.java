package com.tech.sse.excel.util;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelUtil {
	private Workbook wb;
	private Sheet sheet;
	private Row row;
	private Logger logger = Logger.getLogger(ExcelUtil.class);
	
	public void openExcel(String filePath) {
		if (filePath == null) {
			logger.error("filePath error!");
		}
		InputStream fileNode = new FileInputStream(filePath);
		wb = new HSSFWorkbook(fileNode);
		
	}
}
