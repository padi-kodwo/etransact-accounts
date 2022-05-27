package com.etransact.accounts.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class CreateContactDto implements Serializable {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    private String Type;

    @NotNull
    @NotEmpty
    private String contact;

}
