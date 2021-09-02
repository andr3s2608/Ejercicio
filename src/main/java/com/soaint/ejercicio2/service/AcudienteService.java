package com.soaint.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import com.soaint.ejercicio2.entities.Acudiente;
import com.soaint.ejercicio2.entities.Estudiante;

public interface AcudienteService {
	public Iterable<Acudiente> FindAll();
	public Optional<Acudiente> FindById(Long id);
	public Acudiente save(Acudiente acudiente);
	public void deleteByid(Long id);
}
