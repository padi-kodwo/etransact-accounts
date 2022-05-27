package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class EditAccountDto implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String Type;
    private BigDecimal balance;
}
