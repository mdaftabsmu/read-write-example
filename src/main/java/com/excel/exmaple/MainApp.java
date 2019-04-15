package com.excel.exmaple;

import java.util.Set;

import com.excel.exmaple.model.Employee;
import com.excel.exmaple.util.ExcelUtil;

public class MainApp {

	public static void main(String[] args) {
		Set<Employee> set = ExcelUtil.readExcel("C:\\Users\\Md Aftab Alam\\Downloads\\Financial Sample.xlsx");
		set.forEach(System.out::println);
		
		Boolean writeExcel = ExcelUtil.writeExcel(set);
		if(writeExcel) {
			System.out.println(" Keep Smile , Your excel file created! ");
		}else {
			System.out.println("Sorry , Try again!");
		}
	}
}
