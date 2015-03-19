package br.com.prova.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory em=Persistence.createEntityManagerFactory("default");
	
	public static EntityManager getConexao(){
		
		return em.createEntityManager();
	}
	
}
