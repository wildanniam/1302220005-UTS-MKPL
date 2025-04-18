package lib;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas untuk mengelola informasi keluarga karyawan
 */
public class FamilyInfo {
    private Person spouse;
    private List<Person> children;

    public FamilyInfo() {
        this.children = new ArrayList<>();
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
    }

    public Person getSpouse() {
        return spouse;
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public List<Person> getChildren() {
        return children;
    }

    public int getNumberOfChildren() {
        return children.size();
    }

    public boolean hasSpouse() {
        return spouse != null;
    }
}