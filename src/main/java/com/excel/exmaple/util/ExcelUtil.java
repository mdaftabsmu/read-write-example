package com.excel.exmaple.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.excel.exmaple.model.Employee;

public class ExcelUtil {

	public static Set<Employee> readExcel(String fileName) {
		try {
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			Set<Employee> set = new LinkedHashSet<Employee>();
			while (iterator.hasNext()) {
				XSSFRow row = (XSSFRow) iterator.next();
				if (row.getRowNum() == 0) {
					continue; // just skip the rows if row number is 0 or 1
				}
				Iterator<Cell> cellIterator = row.iterator();
				Employee emp = new Employee();
				while (cellIterator.hasNext()) {
					XSSFCell cell = (XSSFCell) cellIterator.next();
					switch (cell.getCellType()) {
					case XSSFCell.CELL_TYPE_NUMERIC: {
						if (DateUtil.isCellDateFormatted(cell)) {
							emp.setJoiningDate(cell.getDateCellValue());//dateFormat.format(cell.getDateCellValue()));
						}
						else {
							BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
							if(emp.getSalary()==null) {
								emp.setSalary(bigDecimal.doubleValue());
							}else if(emp.getMobile()==null)	{
								emp.setMobile(bigDecimal.longValue());
							}
						}
						break;
					}
					case HSSFCell.CELL_TYPE_STRING: {
						if(emp.getName()==null||emp.getName().isEmpty() ) {
							emp.setName(cell.getRichStringCellValue().getString());
						}else if(emp.getCountry()==null||emp.getCountry().isEmpty() ) {
							emp.setCountry(cell.getRichStringCellValue().getString());
						}
						break;
					}
					}
				}
				set.add(emp);
			}
			return set;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Boolean writeExcel(Set<Employee> emps) {
		Boolean flag = false;
		String[] columns = {"Name", "Salary", "Joining Date", "Mobile","Country"};
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet 1");
		Row headerRow = sheet.createRow(0);
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		for(int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		CellStyle dateCellStyle = workbook.createCellStyle();
		int rowNum = 1;
		for(Employee employee: emps) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(employee.getName());
			row.createCell(1).setCellValue(employee.getSalary());
			row.createCell(2).setCellValue(employee.getJoiningDate());
			row.createCell(3).setCellValue(employee.getMobile());
			row.createCell(4).setCellValue(employee.getCountry());
		}

		for(int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Md Aftab Alam\\Downloads\\"+new Random().nextInt(8)+".xlsx");
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}



}
