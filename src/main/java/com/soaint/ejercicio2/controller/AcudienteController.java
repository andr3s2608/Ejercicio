package com.soaint.ejercicio2.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soaint.ejercicio2.entities.Acudiente;
import com.soaint.ejercicio2.entities.Estudiante;
import com.soaint.ejercicio2.service.AcudienteService;
import com.soaint.ejercicio2.service.EstudianteService;


@RestController
@RequestMapping("/acudiente")
public class AcudienteController {
	@Autowired
	AcudienteService acudienteeService;
	EstudianteService estudianteService;
	
	/**
	 * metodo para crear un nuevo acudiente
	 * @param acudiente
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Acudiente acudiente){

		return ResponseEntity.status(HttpStatus.CREATED).body(acudienteeService.save(acudiente));
		
	}
	/**
	 * metodo para buscar todos los acudientes
	 * @return
	 */
	@GetMapping
	public List<Acudiente> buscarTodos()
	{
		List<Acudiente> acudientes= StreamSupport
				.stream(acudienteeService.FindAll().spliterator(), false)
				.collect(Collectors.toList());
		
		
		return acudientes;
	}
	/**
	 * metodo para buscar un acudiente por determinado id
	 * @param acudienteId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId (@PathVariable(value= "id") Long acudienteId){
		Optional <Acudiente> acudienteBuscado = acudienteeService.FindById(acudienteId);
		if(acudienteBuscado.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok(acudienteBuscado);
		}
	}
	
	/**
	 * metodo que modifica un acudiente
	 * @param acudienteNuevo
	 * @param acudienteId
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar (@RequestBody Acudiente acudienteNuevo,@PathVariable(value= "id") Long acudienteId){
		Optional <Acudiente> acudienteBuscado = acudienteeService.FindById(acudienteId);
		if(acudienteBuscado.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			acudienteBuscado.get().setNombre(acudienteNuevo.getNombre());		
			acudienteBuscado.get().setTelefono(acudienteNuevo.getTelefono());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(acudienteeService.save(acudienteBuscado.get()));
		}
	}
	
	/**
	 * metodo que elimina un acudiente
	 * @param acudienteId
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> eliminar(@PathVariable (value = "id") Long acudienteId){
		if(acudienteeService.FindById(acudienteId).isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			acudienteeService.deleteByid(acudienteId);
			return ResponseEntity.ok().build();
		}
		
	}
}
