package controladores;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.beans.Libro;
import modelo.daos.LibroDAO;
import modelo.daos.LibroDAOImpl;
import modelo.daos.TemaDAO;
import modelo.daos.TemaDAOImpl;

/**
 * Servlet implementation class GestionCRUD
 */
@WebServlet("/GestionCRUD")
public class GestionCRUD extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCRUD() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LibroDAO ldao = new LibroDAOImpl();
		TemaDAO tdao = new TemaDAOImpl();
	//	request.getSession().setAttribute("temas", tdao.findAll());
		
		switch(request.getParameter("crud")) {
		case "Alta":
			
			break;
			
		case "Eliminar":
			
			break;
			
		case "Modificar":
			Libro libro = new Libro();
			libro.setIsbn(Long.parseLong(request.getParameter("isbn")));
			libro.setAutor(request.getParameter("autor"));
			libro.setTitulo(request.getParameter("titulo"));
			libro.setPaginas(Integer.parseInt(request.getParameter("paginas")));
			libro.setPrecio(BigDecimal.valueOf(Double.parseDouble(request.getParameter("precio"))));
	 		libro.setTema(tdao.findById(Integer.parseInt(request.getParameter("idTema"))));
	//		libro.setTema(tdao.findById(1));
			
			if (ldao.modificar(libro) == 1)
				request.setAttribute("mensajeCRUD", "libro modificado");
			else
				request.setAttribute("mensajeCRUD", "libro NOOOO modificado");
			
			request.getRequestDispatcher("CrudLibro.jsp").forward(request, response);
			
			break;
			
		case "Consultar":
			request.getSession().setAttribute("libro", ldao.findById(Long.parseLong(request.getParameter("isbn"))));
			request.getRequestDispatcher("CrudLibro.jsp").forward(request, response);
			
			break;
			
			default:
				System.out.println("opc erronea GestionCrudLibro : " + request.getParameter("crud"));
			
		}
	}

}
