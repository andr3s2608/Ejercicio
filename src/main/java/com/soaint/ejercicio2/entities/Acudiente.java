package com.soaint.ejercicio2.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="acudiente")
public class Acudiente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(name="telefono",nullable=false)
	private int telefono;
	
	@Column(name="nombre",nullable=false)
	private String nombre;
	
	@Column(name="parentesco",nullable=false)
	private String parentesco;
	
	@Column(name="tipoid",nullable=false)
	private Long tipoid;

	
	@ManyToOne
	@JoinColumn(name = "FK_estudiante", nullable = false)
	private Estudiante estudiante;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		this.parentesco = parentesco;
	}

	
	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Long getTipoid() {
		return tipoid;
	}

	public void setTipoid(Long tipoid) {
		this.tipoid = tipoid;
	}
	
	
}
