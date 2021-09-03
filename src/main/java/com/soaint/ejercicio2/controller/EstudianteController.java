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

import com.soaint.ejercicio2.entities.Estudiante;
import com.soaint.ejercicio2.service.EstudianteService;


@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

	@Autowired
	EstudianteService estudianteService;
	
	/**
	 * metodo que crea un nuevo estudiante
	 * @param estudiante
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Estudiante estudiante){
		if(estudiante.getEdad()>=18)
		{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Estudiante invalido");
		}
		else
		{
			
			return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudiante));
		}
		
	}
	/**
	 * metodo que busca todos los estudiantes
	 * @return
	 */
	@GetMapping
	public List<Estudiante> buscarTodos()
	{
		List<Estudiante> estudiantes= StreamSupport
				.stream(estudianteService.FindAll().spliterator(), false)
				.collect(Collectors.toList());
		
		
		return estudiantes;
	}
	/**
	 * metodo que busca un estudiante por determinado id
	 * @param estudianteId
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId (@PathVariable(value= "id") Long estudianteId) throws Exception{
		Optional <Estudiante> estudianteBuscado = estudianteService.FindById(estudianteId);
		if(estudianteBuscado.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			 
		
			return ResponseEntity.ok(estudianteBuscado);
		}
	}
	
	/**
	 * metodo que busca por edad y estado ingresado
	 * @param pruebaEdad
	 * @param estudianteEstado
	 * @return
	 */
	@GetMapping("/{edad}/{estado}")
	public ResponseEntity<?> buscarPorEdad(@PathVariable(value="edad") int pruebaEdad,@PathVariable(value="estado")boolean estudianteEstado) throws Exception{
	
		List <Estudiante> estudianteBuscado= estudianteService.FindByEdadAndEstado(pruebaEdad,estudianteEstado);
		if(estudianteBuscado.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{			
			return ResponseEntity.ok(estudianteBuscado);
	
		}
	}
	
	/**
	 * metodo que modifica un estudiante 
	 * @param estudianteNuevo
	 * @param estudianteId
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> modificar (@RequestBody Estudiante estudianteNuevo,@PathVariable(value= "id") Long estudianteId){
		Optional <Estudiante> estudianteBuscado = estudianteService.FindById(estudianteId);
		if(estudianteBuscado.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			estudianteBuscado.get().setEdad(estudianteNuevo.getEdad());
			estudianteBuscado.get().setNombre(estudianteNuevo.getNombre());
			estudianteBuscado.get().setEmail(estudianteNuevo.getEmail());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(estudianteService.save(estudianteBuscado.get()));
		}
	}
	/**
	 * metodo que vuelve inactivo a un estudiante
	 * @param estudianteId
	 * @return
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<?> ponerInactivo(@PathVariable (value = "id") Long estudianteId){
		if(estudianteService.FindById(estudianteId).isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			estudianteService.deleteByid(estudianteId);
			return ResponseEntity.ok().build();
		}
		
	}
}
