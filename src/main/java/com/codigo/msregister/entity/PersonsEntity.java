package com.codigo.msregister.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "persons")
public class PersonsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persons")
    private int idPersons;
    @Column(name = "num_document", length = 15, nullable = false)
    private String numDocument;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "lastname", length = 100, nullable = false)
    private String lastname;
    @Column(name = "email", length = 100, nullable = false)
    private String email;
    @Column(name = "telephone", length = 15, nullable = false)
    private String telephone;
    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "documents_type_id_documents_type", nullable = false)
    private DocumentsTypeEntity documentsTypeEntity;
    @ManyToOne
    @JoinColumn(name = "enterprises_id_enterprises", nullable = false)
    private EnterprisesEntity enterprisesEntity;
}
