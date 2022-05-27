package com.etransact.accounts.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class CreateKycDocumentDto {
    @NotBlank
    @NotEmpty
    private String userId;

    @NotBlank
    @NotEmpty
    private String countryCode;
    @NotBlank
    @NotEmpty
    private String name;
    @NotNull
    private String type;
    @NotNull
    private Map<String, Object> data;
}
