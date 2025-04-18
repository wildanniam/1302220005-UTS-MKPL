package lib;

/**
 * Kelas untuk mengelola informasi gaji karyawan
 */
public class SalaryInfo {
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    private boolean isForeigner;

    // Konstanta untuk mengganti magic number
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;
    private static final int[] SALARY_GRADE = { 0, 3000000, 5000000, 7000000 };

    public SalaryInfo(boolean isForeigner) {
        this.isForeigner = isForeigner;
        this.monthlySalary = 0;
        this.otherMonthlyIncome = 0;
        this.annualDeductible = 0;
    }

    /**
     * Menetapkan gaji bulanan berdasarkan grade
     * 
     * @param grade tingkat gaji (1-3)
     */
    public void setMonthlySalary(int grade) {
        if (grade >= 1 && grade <= 3) {
            this.monthlySalary = SALARY_GRADE[grade];
            if (isForeigner) {
                this.monthlySalary = (int) (monthlySalary * FOREIGNER_SALARY_MULTIPLIER);
            }
        }
    }

    /**
     * Menetapkan pendapatan tambahan bulanan
     * 
     * @param income pendapatan tambahan
     */
    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    /**
     * Menetapkan pengurangan tahunan
     * 
     * @param deductible jumlah pengurangan
     */
    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    /**
     * Mendapatkan gaji bulanan
     * 
     * @return gaji bulanan
     */
    public int getMonthlySalary() {
        return monthlySalary;
    }

    /**
     * Mendapatkan pendapatan tambahan bulanan
     * 
     * @return pendapatan tambahan
     */
    public int getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    /**
     * Mendapatkan pengurangan tahunan
     * 
     * @return pengurangan tahunan
     */
    public int getAnnualDeductible() {
        return annualDeductible;
    }

    /**
     * Mendapatkan total pendapatan bulanan
     * 
     * @return total pendapatan bulanan
     */
    public int getTotalMonthlyIncome() {
        return monthlySalary + otherMonthlyIncome;
    }
}