package modelo.daos;

import modelo.beans.Pedido;

public interface PedidoDAO {
	
	public int insert(Pedido pedido);
	public Pedido findById(int idPedido);
	

}
