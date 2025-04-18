package lib;

import java.time.LocalDate;

/**
 * Kelas untuk mengelola informasi identitas karyawan
 */
public class EmployeeIdentity {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
    private LocalDate joinDate;
    private boolean isForeigner;

    public EmployeeIdentity(String employeeId, String firstName, String lastName,
            String idNumber, String address, LocalDate joinDate,
            boolean isForeigner) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joinDate = joinDate;
        this.isForeigner = isForeigner;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public boolean isForeigner() {
        return isForeigner;
    }
}