package lib;

import java.time.LocalDate;
import java.util.List;

/**
 * Kelas Employee yang direfaktor dengan menerapkan komposisi
 * untuk memisahkan tanggung jawab
 */
public class Employee {
	private EmployeeIdentity identity;
	private SalaryInfo salary;
	private FamilyInfo family;

	public Employee(String employeeId, String firstName, String lastName,
			String idNumber, String address, LocalDate joinDate,
			boolean isForeigner) {

		this.identity = new EmployeeIdentity(employeeId, firstName, lastName,
				idNumber, address, joinDate, isForeigner);
		this.salary = new SalaryInfo(isForeigner);
		this.family = new FamilyInfo();
	}

	// Metode delegasi untuk identitas
	public String getEmployeeId() {
		return identity.getEmployeeId();
	}

	public String getFirstName() {
		return identity.getFirstName();
	}

	public String getLastName() {
		return identity.getLastName();
	}

	public String getFullName() {
		return identity.getFullName();
	}

	public String getIdNumber() {
		return identity.getIdNumber();
	}

	public String getAddress() {
		return identity.getAddress();
	}

	public LocalDate getJoinDate() {
		return identity.getJoinDate();
	}

	public boolean isForeigner() {
		return identity.isForeigner();
	}

	// Metode delegasi untuk gaji
	public void setMonthlySalary(int grade) {
		salary.setMonthlySalary(grade);
	}

	public void setAdditionalIncome(int income) {
		salary.setAdditionalIncome(income);
	}

	public void setAnnualDeductible(int deductible) {
		salary.setAnnualDeductible(deductible);
	}

	public int getMonthlySalary() {
		return salary.getMonthlySalary();
	}

	public int getOtherMonthlyIncome() {
		return salary.getOtherMonthlyIncome();
	}

	public int getAnnualDeductible() {
		return salary.getAnnualDeductible();
	}

	// Metode delegasi untuk keluarga
	public void setSpouse(Person spouse) {
		family.setSpouse(spouse);
	}

	public Person getSpouse() {
		return family.getSpouse();
	}

	public boolean hasSpouse() {
		return family.hasSpouse();
	}

	public void addChild(Person child) {
		family.addChild(child);
	}

	public List<Person> getChildren() {
		return family.getChildren();
	}

	// Metode untuk perhitungan pajak
	public int getAnnualIncomeTax() {
		return TaxFunction.calculateAnnualTax(
				salary.getMonthlySalary(),
				salary.getOtherMonthlyIncome(),
				identity.getJoinDate(),
				salary.getAnnualDeductible(),
				family.hasSpouse(),
				family.getNumberOfChildren());
	}
}
