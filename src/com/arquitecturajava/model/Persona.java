package com.arquitecturajava.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Persona {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	private String nombre;
	private int edad;
	
	@OneToMany(cascade = CascadeType.ALL)
	Set<Email> emails = new HashSet<>();
	
	public void addEmail(String email) {
		emails.add(new Email(email));
	}
	public void addEmail(Email email) {
		emails.add(email);
	} 

	
	public boolean delEmail(String strEmail) {
		emails.add(new Email(strEmail));
		return emails.remove(strEmail);
	}

	public Persona(Long id, String nombre, int edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
	} 


	
	

	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", edad=" + edad + "]";
	}

	public Persona() {
		super();

	}

	public Persona(String nombre, int edad) {
		super();
		setEdad(edad);
		setNombre(nombre);
	}

	public Set<Email> getEmails() {
		return emails;
	}

	
	public String getNombre() {

		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public int getEdad() {

		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;

	}

	@Override
	public int hashCode() {
//simplificacion
		return nombre.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
//simplificacion
		Persona nueva = (Persona) obj;
		return nueva.getNombre().equals(this.getNombre());

	}

}