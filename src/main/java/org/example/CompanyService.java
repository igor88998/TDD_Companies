package org.example;

/*
    @author igormakovijcuk
    @project TDD_Companies
    @class CompanyService
    @version 1.0.0
    @since 02.03.2024 - 21.55
*/

import java.util.List;
import java.util.HashSet;
import java.util.Set;

class CompanyService implements ICompanyService {

    @Override
    public Company getTopLevelParent(Company child) {

        if (child == null) {
            return null;
        }

        while (child.getParent() != null) {
            child = child.getParent();
        }
        return child;
    }

    @Override
    public long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies) {
        Set<Company> visited = new HashSet<>();
        return getEmployeeCountForCompanyAndChildrenHelper(company, companies, visited);
    }

    private long getEmployeeCountForCompanyAndChildrenHelper(Company company, List<Company> companies, Set<Company> visited) {
        if (visited.contains(company)) {
            return 0; // Prevent infinite loop
        }

        if (company == null || companies == null || companies.isEmpty()) {
            return 0;
        }

        visited.add(company);
        long totalEmployeeCount = company.getEmployeeCount();

        for (Company child : companies) {
            if (isChildCompany(company, child)) {
                totalEmployeeCount += getEmployeeCountForCompanyAndChildrenHelper(child, companies, visited);
            }
        }

        return totalEmployeeCount;
    }

    private boolean isChildCompany(Company parent, Company child) {
        while (child != null) {
            if (child == parent) {
                return true;
            }
            child = child.getParent();
        }
        return false;
    }
}

