package com.vacinaja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacinaja.model.Entitys;

public interface EntityRepository extends JpaRepository<Entitys, Long>{

}
