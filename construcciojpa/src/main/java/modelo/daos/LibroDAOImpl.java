package modelo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import modelo.beans.Libro;

public class LibroDAOImpl implements LibroDAO{

	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory("construcciojpa");
		em = emf.createEntityManager();
	}
	
	private String sql;
	private Query query;
	private EntityTransaction tx;
	
	public LibroDAOImpl() {
		tx = em.getTransaction();
	}
	@Override
	public Libro findById(long isbn) {
		// TODO Auto-generated method stub
		return em.find(Libro.class, isbn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Libro> librosByTema(int idTema) {
		sql = "select l from Libro l where l.tema.idTema = :id";
		query = em.createQuery(sql);
		query.setParameter("id", idTema);
		
		return query.getResultList();
	}
	@Override
	public int modificar(Libro libro) {
		Libro libroFila = null;
		if (libro == null)
			return 0;
		
		try {
			
			tx.begin();
 		 libroFila = em.find(Libro.class, libro.getIsbn());
//			if (libroFila == null)
//				return 0;
 			libroFila = libro;
			em.merge(libro);
			tx.commit();
			return 1;
		}catch(PersistenceException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
