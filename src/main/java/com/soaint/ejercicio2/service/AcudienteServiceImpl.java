package com.soaint.ejercicio2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soaint.ejercicio2.entities.Acudiente;
import com.soaint.ejercicio2.entities.Estudiante;
import com.soaint.ejercicio2.repository.AcudienteRepository;
import com.soaint.ejercicio2.repository.EstudianteRepository;

@Service
public class AcudienteServiceImpl implements AcudienteService{

	@Autowired
	AcudienteRepository acudienteRepository;
	
	@Autowired
	EstudianteRepository estudianteRepository;
	
	@Override
	@Transactional
	public Iterable<Acudiente> FindAll() {
		
		return acudienteRepository.findAll();
	}

	@Override
	@Transactional
	public Optional<Acudiente> FindById(Long id) {
		
		return acudienteRepository.findById(id);
	}

	@Override
	@Transactional
	public Acudiente save(Acudiente acudiente) {
		Optional<Estudiante> estudiante= estudianteRepository.findById(acudiente.getTipoid());
		if(estudiante.isPresent())
		{
			acudiente.setEstudiante(estudiante.get());
		}
		
		return acudienteRepository.save(acudiente);
	}

	@Override
	@Transactional
	public void deleteByid(Long id) {
		
		acudienteRepository.deleteById(id);
	}

}
