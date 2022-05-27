package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class CreateAccountDto implements Serializable {
    String userId;
    private String firstName;
    private String lastName;
    private String Type;
    private BigDecimal balance; // is the amount the user is initially putting into the account
    private String branch;
    private String created;
    private String updated;
}
