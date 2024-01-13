package com.codigo.msregister.repository;

import com.codigo.msregister.entity.DocumentsTypeEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsTypeRepository extends JpaRepository<DocumentsTypeEntity, Integer> {
    DocumentsTypeEntity findByCodType(@Param("codType") String codType);
}
