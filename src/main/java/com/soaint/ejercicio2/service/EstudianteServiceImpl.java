package com.soaint.ejercicio2.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soaint.ejercicio2.entities.Estudiante;
import com.soaint.ejercicio2.repository.EstudianteRepository;

@Service
public class EstudianteServiceImpl implements EstudianteService{

	@Autowired
	EstudianteRepository estudianteRepository;
	
	
	@Override
	@Transactional
	public Iterable<Estudiante> FindAll() {
		
		return estudianteRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<Estudiante> FindById(Long id) {
		// TODO Auto-generated method stub
		return estudianteRepository.findById(id);
	}

	@Override
	@Transactional
	public Estudiante save(Estudiante estudiante) {
		// TODO Auto-generated method stub
		return estudianteRepository.save(estudiante);
	}

	@Override
	@Transactional
	public void deleteByid(Long id) {
		Optional <Estudiante> estudiante=estudianteRepository.findById(id);
		estudiante.get().setEstado(false);
		
	}

	@Override
	@Transactional
	public List<Estudiante> FindByEdadAndEstado(int edad,boolean estado) {
		List <Estudiante>estudiante =StreamSupport
				.stream(estudianteRepository.findAll().spliterator(),false)
				.filter(est1 -> est1.getEdad()==edad)
				.filter(est1 -> est1.isEstado()==estado)
				.collect(Collectors.toList());
		
		return estudiante;
	}

}
