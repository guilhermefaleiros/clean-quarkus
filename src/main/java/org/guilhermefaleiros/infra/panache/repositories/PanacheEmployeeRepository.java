package org.guilhermefaleiros.infra.panache.repositories;

import io.quarkus.panache.common.Parameters;
import org.guilhermefaleiros.data.interfaces.EmployeeRepository;
import org.guilhermefaleiros.domain.dto.CreateEmployeeDTO;
import org.guilhermefaleiros.domain.models.Employee;
import org.guilhermefaleiros.infra.panache.entities.PanacheEmployee;

import javax.transaction.Transactional;

public class PanacheEmployeeRepository implements EmployeeRepository {

    private final String HQL_SELECT_BY_EMAIL =
            "SELECT employee FROM MySqlEmployee as employee WHERE employee.email = :email";

    @Override
    @Transactional
    public Employee findByEmail(String email) {
        PanacheEmployee employee =
                PanacheEmployee.find(HQL_SELECT_BY_EMAIL, Parameters.with("email", email)).firstResult();
        return employee == null ? null : employee.toEmployee();
    }

    @Override
    @Transactional
    public Employee create(CreateEmployeeDTO employee) {
        PanacheEmployee panacheEmployee = new PanacheEmployee();
        panacheEmployee.email = employee.email;
        panacheEmployee.password = employee.password;
        panacheEmployee.name = employee.name;
        panacheEmployee.persist();

        return panacheEmployee.toEmployee();
    }
}
