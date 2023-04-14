package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;

	private String lastName;
	private String idNumber;

	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private enum jeniskelamin {
		lakilaki,
		perempuan
	}

	private jeniskelamin gender;
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String lastName, String idNumber, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, jeniskelamin gender) {
		this.employeeId = employeeId;

		this.lastName = lastName;
		this.idNumber = idNumber;

		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	

	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = 6000000;
			if (isForeigner) {
				monthlySalary = (int) (6000000 * 1.5);
			}
		}else if (grade == 2) {
			monthlySalary = 7000000;
			if (isForeigner) {
				monthlySalary = (int) (6000000 * 1.5);
			}
		}else if (grade == 3) {
			monthlySalary = 8000000;
			if (isForeigner) {
				monthlySalary = (int) (6000000 * 1.5);
			}
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
