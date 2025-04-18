package lib;

import java.time.LocalDate;

public class Person {
    private String name;
    private String idNumber;
    private LocalDate birthDate;

    public Person(String name, String idNumber, LocalDate birthDate) {
        this.name = name;
        this.idNumber = idNumber;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}