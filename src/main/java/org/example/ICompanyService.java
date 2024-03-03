package org.example;

/*
    @author igormakovijcuk
    @project TDD_Companies
    @class ICompanyService
    @version 1.0.0
    @since 02.03.2024 - 21.54
*/

import java.util.List;

public interface ICompanyService {
    Company getTopLevelParent(Company child);
    long getEmployeeCountForCompanyAndChildren(Company company, List<Company> companies);
}
