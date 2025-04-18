package lib;

import java.time.LocalDate;

public class TaxFunction {

	private static final int NON_TAXABLE_INCOME_SINGLE = 54000000;
	private static final int ADDITIONAL_INCOME_MARRIED = 4500000;
	private static final int ADDITIONAL_INCOME_PER_CHILD = 1500000;
	private static final double TAX_RATE = 0.05;
	private static final int MAX_CHILDREN_FOR_TAX_DEDUCTION = 3;
	private static final int MAX_MONTH_WORKING = 12;
	private static final int MINIMUM_TAX = 0;

	public static class TaxData {
		public int monthlySalary;
		public int otherMonthlyIncome;
		public int numberOfMonthWorking;
		public int deductible;
		public boolean isMarried;
		public int numberOfChildren;

		public TaxData(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible,
				boolean isMarried, int numberOfChildren) {
			this.monthlySalary = monthlySalary;
			this.otherMonthlyIncome = otherMonthlyIncome;
			this.numberOfMonthWorking = numberOfMonthWorking;
			this.deductible = deductible;
			this.isMarried = isMarried;
			this.numberOfChildren = numberOfChildren;
		}
	}

	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus
	 * dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan
	 * bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan)
	 * dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena
	 * pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah
	 * sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya
	 * ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */

	public static int calculateTax(TaxData taxData) {

		int tax = 0;

		if (taxData.numberOfMonthWorking > MAX_MONTH_WORKING) {
			System.err.println("More than 12 month working per year");
		}

		if (taxData.numberOfChildren > MAX_CHILDREN_FOR_TAX_DEDUCTION) {
			taxData.numberOfChildren = MAX_CHILDREN_FOR_TAX_DEDUCTION;
		}

		if (taxData.isMarried) {
			tax = (int) Math.round(
					TAX_RATE * (((taxData.monthlySalary + taxData.otherMonthlyIncome) * taxData.numberOfMonthWorking)
							- taxData.deductible - (NON_TAXABLE_INCOME_SINGLE + ADDITIONAL_INCOME_MARRIED
									+ (taxData.numberOfChildren * ADDITIONAL_INCOME_PER_CHILD))));
		} else {
			tax = (int) Math.round(
					TAX_RATE * (((taxData.monthlySalary + taxData.otherMonthlyIncome) * taxData.numberOfMonthWorking)
							- taxData.deductible - NON_TAXABLE_INCOME_SINGLE));
		}

		if (tax < MINIMUM_TAX) {
			return MINIMUM_TAX;
		} else {
			return tax;
		}

	}

	/**
	 * Menghitung jumlah bulan bekerja dalam satu tahun
	 * 
	 * @param joinDate tanggal bergabung karyawan
	 * @return jumlah bulan bekerja dalam tahun ini
	 */
	public static int calculateMonthsWorkedInYear(LocalDate joinDate) {
		LocalDate currentDate = LocalDate.now();
		
		if (currentDate.getYear() == joinDate.getYear()) {
			return currentDate.getMonthValue() - joinDate.getMonthValue();
		} else {
			return MAX_MONTH_WORKING;
		}
	}
	
	/**
	 * Menghitung pajak tahunan untuk karyawan
	 * 
	 * @param monthlySalary     gaji bulanan
	 * @param otherMonthlyIncome penghasilan lain per bulan
	 * @param joinDate          tanggal bergabung
	 * @param deductible        pengurangan pajak tahunan
	 * @param hasSpouse         true jika karyawan memiliki pasangan
	 * @param numberOfChildren  jumlah anak
	 * @return jumlah pajak tahunan
	 */
	public static int calculateAnnualTax(int monthlySalary, int otherMonthlyIncome, 
										LocalDate joinDate, int deductible, 
										boolean hasSpouse, int numberOfChildren) {
		
		int monthWorkingInYear = calculateMonthsWorkedInYear(joinDate);
		
		TaxData taxData = new TaxData(
				monthlySalary,
				otherMonthlyIncome,
				monthWorkingInYear,
				deductible,
				hasSpouse,
				numberOfChildren);
				
		return calculateTax(taxData);
	}
}
