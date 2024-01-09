package com.codigo.msregister.repository;

import com.codigo.msregister.entity.EnterprisesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EnterprisesRepository extends JpaRepository<EnterprisesEntity, Integer> {
}
