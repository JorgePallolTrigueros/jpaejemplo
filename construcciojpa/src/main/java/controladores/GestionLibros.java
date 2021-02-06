package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Libro;
import modelo.beans.LineasPedido;
import modelo.beans.Tema;
import modelo.daos.LibroDAO;
import modelo.daos.LibroDAOImpl;
import modelo.daos.TemaDAO;
import modelo.daos.TemaDAOImpl;

/**
 * Servlet implementation class GestionLibros
 */
@WebServlet("/GestionLibros")
public class GestionLibros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionLibros() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misesion = request.getSession();
		LineasPedido lp = null;
		LibroDAO ldao = new LibroDAOImpl();
		TemaDAO tdao =  new TemaDAOImpl();
		switch(request.getParameter("opcion")) {
		case "temas":
			 
			List<Tema> temas = tdao.findAll();
			request.setAttribute("temas", temas);
			request.getRequestDispatcher("VerTemas.jsp").forward(request, response);
	//		request.setAttribute("temas",tdao.findAll());
			
			break;
		case "libros":
			System.out.println(request.getParameter("idTema"));
			
			List<Libro> librosPorTema = ldao.librosByTema(Integer.parseInt(request.getParameter("idTema")));
			request.setAttribute("libros", librosPorTema);
			request.getRequestDispatcher("VerLibros.jsp").forward(request, response);
			
			break;
		case "addLibros":
				List<LineasPedido> lineas = (List<LineasPedido>) misesion.getAttribute("carrito");
				if (lineas == null) {
					lineas = new ArrayList<LineasPedido>();
				}
				for (String ele:request.getParameterValues("isbn")) {
					//1.-  crear el objeto Linea Pedido
					lp = new LineasPedido();
					//2.-  rellenar los campos: libro, cantidad, precio de venta.
					// el libro lo obtengo de ldao.findById
					lp.setCantidad(1);
					lp.setLibro(ldao.findById(Long.parseLong(ele)));
					lp.setPrecioVenta(lp.getLibro().getPrecio());
					
					//3.- add linea de pedido al carrito representado por lineas
					if (!lineas.contains(lp))
						lineas.add(lp);
				
				}
				System.out.println(lineas);
				misesion.setAttribute("carrito", lineas);
				request.getRequestDispatcher("MenuClientes.jsp").forward(request, response);
				
					 
			
			break;
		case "cerrarSesion":
			misesion.removeAttribute("cliente");
			misesion.removeAttribute("carrito");
			misesion.invalidate();
			request.getRequestDispatcher("index.jsp").forward(request, response);
			break;
			default:
				System.out.println("opcion erronea en GestionLibros" + request.getParameter("opcion"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
