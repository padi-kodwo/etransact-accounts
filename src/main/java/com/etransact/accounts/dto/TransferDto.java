package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TransferDto implements Serializable {
    private String originAccountNumber;
    private String destinationAccountNumber;
    private BigDecimal amount;
}
