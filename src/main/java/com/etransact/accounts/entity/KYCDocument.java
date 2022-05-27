package com.etransact.accounts.entity;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "kyc", indexes = {
        @Index(name = "index_kyc_on_type", columnList = "type"),
})
@Data
public class KYCDocument implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @NotNull
    @Column(name = "card_id")
    private String cardId;

    @NotNull
    @Column(name = "type")
    private String Type;

    @NotNull
    @Column(name = "image")
    private String image;

    @NotNull
    @Column(name = "user_image")
    private String user_image;

    @CreationTimestamp
    @Column(name = "created")
    private Timestamp created;

    @UpdateTimestamp
    @Column(name = "updated")
    private Timestamp updated;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
