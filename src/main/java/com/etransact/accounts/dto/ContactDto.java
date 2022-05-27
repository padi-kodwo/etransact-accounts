package com.etransact.accounts.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class ContactDto implements Serializable {
    private String Type;
    private String contact;

}
