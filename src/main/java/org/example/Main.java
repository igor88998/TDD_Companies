package org.example;

/*
    @author igormakovijcuk
    @project TDD_Companies
    @class Company
    @version 1.0.0
    @since 02.03.2024 - 21.51
*/

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Company parentCompany = new Company("Parent Company", null, 1000);
        Company childCompany1 = new Company("Child Company 1", parentCompany, 500);
        Company childCompany2 = new Company("Child Company 2", parentCompany, 300);
        Company grandchildCompany1 = new Company("Grandchild Company 1", childCompany1, 200);
        Company grandchildCompany2 = new Company("Grandchild Company 2", childCompany2, 100);

        // Construct list of all available companies
        List<Company> companies = new ArrayList<>();
        companies.add(parentCompany);
        companies.add(childCompany1);
        companies.add(childCompany2);
        companies.add(grandchildCompany1);
        companies.add(grandchildCompany2);

        // Create company service instance
        CompanyService companyService = new CompanyService();

        // Test methods
        System.out.println("Top Level Parent of Grandchild Company 1: " + companyService.getTopLevelParent(grandchildCompany1).getName());
        System.out.println("Employee Count for Parent Company and Children: " + companyService.getEmployeeCountForCompanyAndChildren(parentCompany, companies));
    }
}
