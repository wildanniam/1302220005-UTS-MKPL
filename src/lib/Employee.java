package lib;

import lib.TaxFunction;
import lib.Gender;
import lib.Person;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Employee {

	private String employeeId;
	String firstName;
	String lastName;
	private String idNumber;
	private String address;

	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;

	private boolean isForeigner;
	private Gender gender; // Replacing boolean with Gender enum

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private Person spouse;
	private List<Person> children;

	private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;
	private static final int[] SALARY_GRADE = { 0, 3000000, 5000000, 7000000 }; // Grade 0 is added to avoid confusion
																				// with array index

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

		this.children = new ArrayList<>();
	}

	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3:
	 * 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	public void setMonthlySalary(int grade) {
		if (grade >= 1 && grade <= 3) {
			monthlySalary = SALARY_GRADE[grade];
			if (isForeigner) {
				monthlySalary = (int) (monthlySalary * FOREIGNER_SALARY_MULTIPLIER);
			}
		}
	}

	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}

	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public void addChild(Person child) {
		this.children.add(child);
	}

	public Person getSpouse() {
		return spouse;
	}

	public List<Person> getChildren() {
		return children;
	}

	public int getAnnualIncomeTax() {

		// Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah
		// bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();

		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		} else {
			monthWorkingInYear = 12;
		}

		TaxFunction.TaxData taxData = new TaxFunction.TaxData(
				monthlySalary,
				otherMonthlyIncome,
				monthWorkingInYear,
				annualDeductible,
				spouse == null,
				children.size());
		return TaxFunction.calculateTax(taxData);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
