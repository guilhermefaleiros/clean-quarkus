package org.guilhermefaleiros.main;

import org.guilhermefaleiros.data.interfaces.EmployeeRepository;
import org.guilhermefaleiros.data.interfaces.Encrypter;
import org.guilhermefaleiros.data.usecases.CreateEmployeeImpl;
import org.guilhermefaleiros.domain.dto.CreateEmployeeDTO;
import org.guilhermefaleiros.domain.models.Employee;
import org.guilhermefaleiros.domain.usecases.CreateEmployee;
import org.guilhermefaleiros.infra.panache.repositories.PanacheEmployeeRepository;
import org.guilhermefaleiros.infra.util.BCryptAdapter;

import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class CreateEmployeeService {

    CreateEmployee createEmployee;

    public CreateEmployeeService() {
        Encrypter encrypter = new BCryptAdapter();
        EmployeeRepository employeeRepository = new PanacheEmployeeRepository();
        this.createEmployee = new CreateEmployeeImpl(employeeRepository, encrypter);
    }

    @Transactional
    public Employee handle(CreateEmployeeDTO createEmployeeDTO) {
        return this.createEmployee.create(createEmployeeDTO);
    }
}
