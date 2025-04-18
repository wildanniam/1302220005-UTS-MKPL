package lib;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Membuat objek Employee
        Employee employee = new Employee(
                "EMP001",
                "John",
                "Doe",
                "123456789",
                "123 Main St",
                LocalDate.of(2020, 1, 15),
                false, // isForeigner
                Gender.MALE // gender
        );

        // Mengatur gaji bulanan berdasarkan grade
        employee.setMonthlySalary(2); // Grade 2: Rp 5.000.000

        // Mengatur penghasilan tambahan dan deductible
        employee.setAdditionalIncome(2000000); // Rp 2.000.000 per bulan
        employee.setAnnualDeductible(10000000); // Rp 10.000.000

        // Mengatur status pernikahan dan anak
        employee.setSpouse(new Person("Jane Doe", "987654321", LocalDate.of(1990, 5, 20)));
        employee.addChild(new Person("Child 1", "CH001", LocalDate.of(2015, 3, 15)));
        employee.addChild(new Person("Child 2", "CH002", LocalDate.of(2018, 7, 10)));

        // Menghitung pajak tahunan
        int annualTax = employee.getAnnualIncomeTax();

        // Menampilkan hasil
        System.out.println(
                "Pajak tahunan untuk " + employee.getFirstName() + " " + employee.getLastName() + ": Rp " + annualTax);
    }
}