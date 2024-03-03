package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    @author igormakovijcuk
    @project TDD_Companies
    @class CompanyServiceTest
    @version 1.0.0
    @since 02.03.2024 - 22.01
*/
class CompanyServiceTest {

    private Company parentCompany;
    private Company childCompany1;
    private Company childCompany2;
    private Company grandchildCompany1;
    private Company grandchildCompany2;
    private Company grandchildCompany3;
    private List<Company> companies;
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        parentCompany = new Company("Parent Company", null, 1000);
        childCompany1 = new Company("Child Company 1", parentCompany, 500);
        childCompany2 = new Company("Child Company 2", parentCompany, 300);
        grandchildCompany1 = new Company("Grandchild Company 1", childCompany1, 200);
        grandchildCompany2 = new Company("Grandchild Company 2", childCompany2, 100);
        grandchildCompany3 = new Company("Grandchild Company 3", null, 50);

        companies = new ArrayList<>();
        companies.add(parentCompany);
        companies.add(childCompany1);
        companies.add(childCompany2);
        companies.add(grandchildCompany1);
        companies.add(grandchildCompany2);
        companies.add(grandchildCompany3);

        companyService = new CompanyService();
    }

    @Test
    void whenCompanyIsNullThenNull() {
        Company result = companyService.getTopLevelParent(null);
        Assertions.assertNull(result);
    }

    @Test
    void whenCompanyHasNoParentItIsOnTop() {
        Company result = companyService.getTopLevelParent(parentCompany);
        Assertions.assertEquals(parentCompany, result);
    }

    @Test
    void whenCompanyIsSingleItIsOnTop() {
        Company result = companyService.getTopLevelParent(grandchildCompany3);
        Assertions.assertEquals(grandchildCompany3, result);
    }

    @Test
    void whenCompanyHasOneStepToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(grandchildCompany1);
        Assertions.assertEquals(parentCompany, result);
    }

    @Test
    void whenCompanyHasTwoStepsToTheTopThenFindTop() {
        Company result = companyService.getTopLevelParent(grandchildCompany2);
        Assertions.assertEquals(parentCompany, result);
    }

    @Test
    void whenListOfCompanyIsNullThenZero() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(parentCompany, null);
        Assertions.assertEquals(0, result);
    }

    @Test
    void whenCompanyIsNullThenZero() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(null, companies);
        Assertions.assertEquals(0, result);
    }

    @Test
    void whenCompanyIsParentThenEmployeeCountIs1000() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(parentCompany, companies);
        Assertions.assertEquals(2100, result);
    }

    @Test
    void whenCompanyIsSingleThenEmployeeCountIs200() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(grandchildCompany1, companies);
        Assertions.assertEquals(200, result);
    }

    @Test
    void whenCompanyIsSingleThenEmployeeCountIs100() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(grandchildCompany2, companies);
        Assertions.assertEquals(100, result);
    }

    @Test
    void whenCompanyIsChild1ThenEmployeeCountIs700() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(childCompany1, companies);
        Assertions.assertEquals(700, result); // 500 (Child Company 1) + 200 (Grandchild Company 1)
    }

    @Test
    void whenCompanyIsChild2ThenEmployeeCountIs400() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(childCompany2, companies);
        Assertions.assertEquals(400, result); // 300 (Child Company 2) + 100 (Grandchild Company 2)
    }

    @Test
    void whenListOfCompanyEmptyThenZero() {
        long result = companyService.getEmployeeCountForCompanyAndChildren(grandchildCompany1, new ArrayList<>());
        Assertions.assertEquals(0, result);
    }
}