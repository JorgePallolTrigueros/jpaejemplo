package modelo.daos;

import java.util.List;

import modelo.beans.Tema;

public interface TemaDAO {
	public List<Tema> findAll();
	public int insert(Tema tema);
	public Tema findById(int clave);

}
