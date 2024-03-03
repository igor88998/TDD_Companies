package org.example;

/*
    @author igormakovijcuk
    @project TDD_Companies
    @class Company
    @version 1.0.0
    @since 02.03.2024 - 21.52
*/

public class Company {

    private String name;
    private Company parent;
    private int employeeCount;

    public Company(String name, Company parent, int employeeCount) {
        this.name = name;
        this.parent = parent;
        this.employeeCount = employeeCount;
    }

    public String getName() {
        return name;
    }

    public Company getParent() {
        return parent;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }
}
