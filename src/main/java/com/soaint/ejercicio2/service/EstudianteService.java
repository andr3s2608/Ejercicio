package com.soaint.ejercicio2.service;

import java.util.List;
import java.util.Optional;

import com.soaint.ejercicio2.entities.Estudiante;



public interface EstudianteService {
	public Iterable<Estudiante> FindAll();
	public Optional<Estudiante> FindById(Long id);
	public Estudiante save(Estudiante estudiante);
	public List<Estudiante> FindByEdadAndEstado(int edad,boolean estado);
	public void deleteByid(Long id);
}
