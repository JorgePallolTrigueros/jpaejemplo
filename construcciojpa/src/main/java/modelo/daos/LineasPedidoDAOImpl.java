package modelo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import modelo.beans.LineasPedido;

public class LineasPedidoDAOImpl implements LineasPedidoDAO{


	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory("construcciojpa");
		em = emf.createEntityManager();
	}
	
	private String sql;
	private Query query;
	private EntityTransaction tx;
	
	public LineasPedidoDAOImpl() {
		tx = em.getTransaction();
	}
	
	@Override
	public int insertLineas(List<LineasPedido> lps) {
		if (lps == null)
			return 0;
		int filas=0;
		try {
			tx.begin();
			for(LineasPedido ele: lps) {
				em.persist(ele);
				filas++;
			}
			tx.commit();
			return filas;
			
		}catch(PersistenceException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
