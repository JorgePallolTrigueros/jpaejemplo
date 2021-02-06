package modelo.daos;

import modelo.beans.Cliente;

public interface ClienteDAO {
	
	public int insert(Cliente cliente);
	public Cliente findById(String usuario);
	public Cliente autenticar(String usuario, String password);

}
