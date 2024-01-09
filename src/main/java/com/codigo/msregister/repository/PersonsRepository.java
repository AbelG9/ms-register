package com.codigo.msregister.repository;

import com.codigo.msregister.entity.PersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PersonsRepository extends JpaRepository<PersonsEntity, Integer> {
}
