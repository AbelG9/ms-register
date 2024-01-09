package com.codigo.msregister.aggregates.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Audit {
    @Column(name="user_create", length = 45, nullable = false)
    private String userCreate;
    @Column(name="date_create", nullable = false)
    private Timestamp dateCreate;
    @Column(name="user_modif",length = 45, nullable = true)
    private String userModif;
    @Column(name="date_modif", nullable = true)
    private Timestamp dateModif;
}
