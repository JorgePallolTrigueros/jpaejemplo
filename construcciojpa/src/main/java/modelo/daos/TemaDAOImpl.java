package modelo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.beans.Tema;

public class TemaDAOImpl implements TemaDAO{
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory("construcciojpa");
		em = emf.createEntityManager();
	}
	
	private String sql;
	private Query query;
	private EntityTransaction tx;
	
	public TemaDAOImpl() {
		tx = em.getTransaction();
	}
	

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tema> findAll() {
		query = em.createNamedQuery("Tema.findAll", Tema.class);
		return query.getResultList();
		
	}

	@Override
	public int insert(Tema tema) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tema findById(int clave) {
		return em.find(Tema.class, clave);
	}

}
