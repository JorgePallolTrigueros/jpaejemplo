package modelo.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.beans.Pedido;

public class PedidoDAOImpl implements PedidoDAO{

	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory("construcciojpa");
		em = emf.createEntityManager();
	}
	
	private String sql;
	private Query query;
	private EntityTransaction tx;
	
	public PedidoDAOImpl() {
		tx = em.getTransaction();
	}
	
	@Override
	public int insert(Pedido pedido) {
		tx.begin();
		try {
			em.persist(pedido);
			tx.commit();
			return 1;
			
		}catch(Exception e) {
			System.out.println("al insertar pedido");
			e.printStackTrace();
			return 0;
		}
		 
	}

	@Override
	public Pedido findById(int idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

}
