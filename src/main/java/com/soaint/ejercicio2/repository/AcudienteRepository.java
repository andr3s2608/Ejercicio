package com.soaint.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.ejercicio2.entities.Acudiente;

@Repository
public interface AcudienteRepository extends JpaRepository<Acudiente, Long>{

}
