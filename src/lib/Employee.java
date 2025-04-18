package lib;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;

	private LocalDate joinDate;
	private int monthWorkingInYear;

	private boolean isForeigner;

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;

	private Person spouse;
	private List<Person> children;

	// Konstanta untuk mengganti magic number
	private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;
	private static final int[] SALARY_GRADE = { 0, 3000000, 5000000, 7000000 };
	private static final int MAX_MONTH_WORKING = 12;

	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address,
			LocalDate joinDate, boolean isForeigner) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.joinDate = joinDate;
		this.isForeigner = isForeigner;

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
		// Mendelegasikan perhitungan pajak ke TaxFunction
		return TaxFunction.calculateAnnualTax(
				monthlySalary,
				otherMonthlyIncome,
				joinDate,
				annualDeductible,
				spouse != null, // hasSpouse (true jika memiliki pasangan)
				children.size());
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getJoinDate() {
		return joinDate;
	}
}
