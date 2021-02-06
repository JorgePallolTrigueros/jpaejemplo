package modelo.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.beans.Cliente;

public class ClienteDAOImpl implements ClienteDAO{
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory("construcciojpa");
		em = emf.createEntityManager();
	}
	
	private String sql;
	private Query query;
	private EntityTransaction tx;
	
	public ClienteDAOImpl() {
		tx = em.getTransaction();
	}

	@Override
	public int insert(Cliente cliente) {
		tx.begin();
		try {
		em.persist(cliente);
		tx.commit();
		return 1;
		}catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Cliente findById(String usuario) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, usuario);
	}

	@Override
	public Cliente autenticar(String usuario, String password) {
		sql = "select c from Cliente c where c.usuario = :usu and c.password = :pwd";
		try
		{
		query = em.createQuery(sql);
		query.setParameter("usu", usuario);
		query.setParameter("pwd", password);
		
		
		return (Cliente) query.getSingleResult();
		}
		catch(javax.persistence.NoResultException e) {
			return null;
		}
		catch(Exception e) {
			System.out.println("error capturado");
			e.printStackTrace();
			return null;
		}
	}

}
