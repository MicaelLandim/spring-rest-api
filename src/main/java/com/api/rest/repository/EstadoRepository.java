package com.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.rest.model.EstadoModel;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {

}
