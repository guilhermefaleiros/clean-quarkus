package org.guilhermefaleiros.data.usecases;

import org.guilhermefaleiros.data.interfaces.EmployeeRepository;
import org.guilhermefaleiros.data.interfaces.Encrypter;
import org.guilhermefaleiros.domain.dto.CreateEmployeeDTO;
import org.guilhermefaleiros.domain.models.Employee;
import org.guilhermefaleiros.domain.usecases.CreateEmployee;

import javax.transaction.Transactional;

public class CreateEmployeeImpl implements CreateEmployee {

    private EmployeeRepository employeeRepository;
    private Encrypter encrypter;

    public CreateEmployeeImpl(EmployeeRepository employeeRepository, Encrypter encrypter) {
        this.employeeRepository = employeeRepository;
        this.encrypter = encrypter;
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employeeDTO) {
        Employee employee = this.employeeRepository.findByEmail(employeeDTO.email);
        if(employee != null) {
            throw new RuntimeException("Já existe um funcionário com este email");
        }
        employeeDTO.password = this.encrypter.hash(employeeDTO.password);
        return this.employeeRepository.create(employeeDTO);
    }
}
