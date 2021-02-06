package modelo.daos;

import java.util.List;

import modelo.beans.Libro;

public interface LibroDAO {
	
	public Libro findById(long isbn);
	public List<Libro> librosByTema(int idTema);
	int modificar(Libro libro);

}
