package org.guilhermefaleiros.domain.usecases;

import org.guilhermefaleiros.domain.dto.CreateEmployeeDTO;
import org.guilhermefaleiros.domain.models.Employee;

public interface CreateEmployee {
    Employee create(CreateEmployeeDTO employee);
}
