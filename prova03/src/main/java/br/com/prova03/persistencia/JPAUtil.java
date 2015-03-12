package br.com.prova03.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf=Persistence.createEntityManagerFactory("default");
	
	public static EntityManager getConexao(){
		
		return emf.createEntityManager();
	}
	
	
}
