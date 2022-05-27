package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class EditUserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 8857975249274367170L;

    private String id;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String otherNames;
    private boolean loggedIn;
    private String created;
    private String updated;
}
