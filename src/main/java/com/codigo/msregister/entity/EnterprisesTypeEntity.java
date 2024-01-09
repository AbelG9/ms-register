package com.codigo.msregister.entity;

import com.codigo.msregister.aggregates.model.Audit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "enterprises_type")
public class EnterprisesTypeEntity extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_enterprises_type")
    private int idEnterprisesType;
    @Column(name = "cod_type", length = 45, nullable = false)
    private String codType;
    @Column(name = "desc_type", length = 45, nullable = false)
    private String descType;
    @Column(name = "status", nullable = false)
    private int status;
}
