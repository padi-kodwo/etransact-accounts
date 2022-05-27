package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class AccountDto implements Serializable {
    private String id;
    private String number;
    private String firstName;
    private String lastName;
    private String Type;
    private BigDecimal balance;
    private String branch;
    private String created;
    private String updated;
}
