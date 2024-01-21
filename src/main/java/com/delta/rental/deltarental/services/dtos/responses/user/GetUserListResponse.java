package com.delta.rental.deltarental.services.dtos.responses.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserListResponse {

    private int id;

    private String name;

    private String surname;

    private String gsm;

    private String email;
}
