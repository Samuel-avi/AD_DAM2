package com.arquitecturajava;

import com.arquitecturajava.model.Persona;
import com.arquitecturajava.model.Email;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Prueba1JPA {

	public static void main(String[] args) {

		Persona yo = new Persona("Maria", 22);
		Email ema = new Email("maria@gmail.com");
		yo.addEmail(ema);
		yo.addEmail("maria2@gmail.com");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadPersonas");
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(yo);
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