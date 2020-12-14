package org.guilhermefaleiros.data.interfaces;

import org.guilhermefaleiros.domain.dto.CreateEmployeeDTO;
import org.guilhermefaleiros.domain.models.Employee;

public interface EmployeeRepository {
    Employee findByEmail(String email);
    Employee create(CreateEmployeeDTO employee);
}
