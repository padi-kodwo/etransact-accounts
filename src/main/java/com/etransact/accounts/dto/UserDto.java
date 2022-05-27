package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String otherNames;
    private String lastLogin;
    private String created;
    private String updated;
}
