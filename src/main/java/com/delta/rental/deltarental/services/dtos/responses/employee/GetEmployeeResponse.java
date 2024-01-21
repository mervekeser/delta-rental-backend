package com.delta.rental.deltarental.services.dtos.responses.employee;

import com.delta.rental.deltarental.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEmployeeResponse {
    private int id;

    private double salary;

    List<UserRole> userRoles;

}
