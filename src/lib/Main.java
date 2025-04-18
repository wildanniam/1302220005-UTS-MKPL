package lib;

public class Main {
    public static void main(String[] args) {

        Employee employee = new Employee(
                "EMP001",
                "John",
                "Doe",
                "123456789",
                "123 Main St",
                2020,
                1,
                15,
                false,
                true // gender (true = Laki-laki)
        );

        employee.setMonthlySalary(2); // Grade 2: Rp 5.000.000

        employee.setAdditionalIncome(2000000); // Rp 2.000.000 per bulan
        employee.setAnnualDeductible(10000000); // Rp 10.000.000

        employee.setSpouse("Jane Doe", "987654321");
        employee.addChild("Child 1", "CH001");
        employee.addChild("Child 2", "CH002");

        // Menghitung pajak tahunan
        int annualTax = employee.getAnnualIncomeTax();

        System.out.println(
                "Pajak tahunan untuk " + employee.getFirstName() + " " + employee.getLastName() + ": Rp " + annualTax);
    }
}