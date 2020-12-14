package org.guilhermefaleiros.presentation.resources;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.guilhermefaleiros.domain.dto.CreateEmployeeDTO;
import org.guilhermefaleiros.domain.models.Employee;
import org.guilhermefaleiros.main.CreateEmployeeService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employee")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreateEmployeeResource {

    @Inject
    CreateEmployeeService createEmployeeService;

    @POST
    public Employee handle(@RequestBody CreateEmployeeDTO createEmployeeDTO) {
        return createEmployeeService.handle(createEmployeeDTO);
    }
}