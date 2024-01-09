package com.codigo.msregister.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "documents_type")
public class DocumentsTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documents_type")
    private int inEnterprises;
    @Column(name = "cod_type", length = 45, nullable = false)
    private String codType;
    @Column(name = "desc_type", length = 45, nullable = false)
    private String descType;
    @Column(name = "status", nullable = false)
    private int status;
}
