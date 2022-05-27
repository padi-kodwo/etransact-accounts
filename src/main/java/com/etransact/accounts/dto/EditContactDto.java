package com.etransact.accounts.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EditContactDto implements Serializable {
    private String id;
    private String Type;
    private String contact;

}
