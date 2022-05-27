package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WithdrawalDto implements Serializable {
    private String accountNumber;
    private BigDecimal amount;
}
