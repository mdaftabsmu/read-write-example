package com.excel.exmaple.model;

import java.util.Date;

public class Employee {
		private Integer id;
		private String name;
		private Double salary;
		private String country;
		private Date joiningDate;
		private Long mobile;
		
		public Employee() {
			// TODO Auto-generated constructor stub
		}

		public Employee(Integer id, String name, Double salary, String country, Date joiningDate, Long mobile) {
			super();
			this.id = id;
			this.name = name;
			this.salary = salary;
			this.country = country;
			this.joiningDate = joiningDate;
			this.mobile = mobile;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getSalary() {
			return salary;
		}

		public void setSalary(Double salary) {
			this.salary = salary;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public Date getJoiningDate() {
			return joiningDate;
		}

		public void setJoiningDate(Date joiningDate) {
			this.joiningDate = joiningDate;
		}

		public Long getMobile() {
			return mobile;
		}

		public void setMobile(Long mobile) {
			this.mobile = mobile;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", country=" + country
					+ ", joiningDate=" + joiningDate + ", mobile=" + mobile + "]";
		}
		
		
		
	
}
