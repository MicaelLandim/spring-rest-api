package com.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.CidadeModel;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {

}
