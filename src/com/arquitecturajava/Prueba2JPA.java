package com.arquitecturajava;

import java.util.List;

import com.arquitecturajava.model.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Prueba2JPA {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersonas");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();

			// Consulta JPQL moderna
			List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();

			for (Persona p : personas) {
				System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad());
			}
			
			

			em.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
			emf.close();
		}
	}
}