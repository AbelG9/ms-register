package com.codigo.msregister.entity;

import com.codigo.msregister.aggregates.model.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "enterprises")
public class EnterpriseEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enterprises")
    private int inEnterprises;
    @Column(name = "num_document", length = 15, nullable = false)
    private String numDocument;
    @Column(name = "business_name", length = 150, nullable = false)
    private String businessName;
    @Column(name = "tradename", length = 150, nullable = false)
    private String tradeName;
    @Column(name = "status", nullable = false)
    private int status;
}
