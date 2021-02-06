package main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.beans.Libro;
import modelo.beans.Cliente;
import modelo.beans.LineasPedido;
import modelo.beans.Pedido;
import modelo.beans.Tema;
/**
 * Ejemplo crear objetos en operación OneToOne
 * @author Alan
 */
public class Cread {

	public static void main(String[] args) {

		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("construcciojpa");
		EntityManager manager = managerFactory.createEntityManager();
		
		// Creamos nuevos objetos
		
		Tema tema = new Tema();
		tema.setAbreviatura("0DP");
		tema.setIdTema(2);
		tema.setDescripcion("Nuevotema");
		
		
		Libro libro = new Libro();
		libro.setAutor("Jorge Pallol");
		libro.setPaginas(34);
		libro.setTema(tema);
		libro.setTitulo("Hola Mundo");
		

		

		// Guardar nuevos usuarios (método persist())
		try{
			// Se inicia una transacción
			manager.getTransaction().begin();   
			// Se persisten los objetos
			manager.persist(libro); 
			// Se realiza commit para hacer efectivas las inserciones
			manager.getTransaction().commit();    
		}catch(Exception ex){
			ex.printStackTrace();
			manager.getTransaction().rollback();   
		}finally{
			manager.close();
			managerFactory.close();
		}
	}

}