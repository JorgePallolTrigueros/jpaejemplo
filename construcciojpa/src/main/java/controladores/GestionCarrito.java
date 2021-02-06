package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Cliente;
import modelo.beans.Libro;
import modelo.beans.LineasPedido;
import modelo.beans.Pedido;
import modelo.beans.Tema;
import modelo.daos.LibroDAO;
import modelo.daos.LibroDAOImpl;
import modelo.daos.LineasPedidoDAO;
import modelo.daos.LineasPedidoDAOImpl;
import modelo.daos.PedidoDAO;
import modelo.daos.PedidoDAOImpl;

/**
 * Servlet implementation class GestionCarrito
 */
//tomas.escudero.ted@gmail.com
//  69996575757

@WebServlet("/GestionCarrito")
public class GestionCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession misesion = request.getSession();
		
		List<LineasPedido> lps = (List<LineasPedido>)misesion.getAttribute("carrito");
		
		PedidoDAO pdao = new PedidoDAOImpl();
		LineasPedidoDAO lpdao = new LineasPedidoDAOImpl();
		
		switch(request.getParameter("opcion")) {
		case "vaciar":
			if(lps != null) 
				lps.clear();
				
			request.getRequestDispatcher("VerCarrito_jstl.jsp").forward(request, response);;
			
			break;
		case "eliminar":
		//	System.out.println(request.getParameter("isbn"));
			LineasPedido lp = new LineasPedido();
			LibroDAO  ldao = new LibroDAOImpl();
			 
			 
			
			lp.setLibro(ldao.findById(Long.parseLong(request.getParameter("isbn"))));
			
			lps.remove(lp);
			
			request.getRequestDispatcher("VerCarrito_jstl.jsp").forward(request, response);;
			
			
			break;
		case "eliminar2":
			System.out.println(request.getParameter("posicion"));
			
			lps.remove(Integer.parseInt(request.getParameter("posicion")));
			
			request.getRequestDispatcher("VerCarrito_jstl_v2.jsp").forward(request, response);;
			
			
			break;
		case "comprar":
			
			
			Pedido p1 = new Pedido();
			p1.setCliente((Cliente)misesion.getAttribute("cliente"));
			p1.setEstado("comprado");
			p1.setFechaAlta(new Date());
			p1.setDomicilioEntrega(p1.getCliente().getDireccion());
			System.out.println(p1);
			pdao.insert(p1);
			System.out.println(p1);
			for (LineasPedido ele: lps) {
				ele.setPedido(p1);
			}
			int filas = lpdao.insertLineas(lps);
			System.out.println(filas);
			
			
			request.getRequestDispatcher("GestionLibros?opcion=cerrarSesion").forward(request, response);
			
			
			
				
			
			break;
			
		case "comprar2":
			// 1.- configuramos el pedido menos el idPedido 
			Pedido p2 = new Pedido();
			p2.setCliente((Cliente)misesion.getAttribute("cliente"));
			p2.setEstado("comprado");
			p2.setFechaAlta(new Date());
			p2.setDomicilioEntrega(p2.getCliente().getDireccion());
			
			//2.- asignamos el pedido a las lineas de pedido del carrito
			
			for(LineasPedido ele: lps)
				ele.setPedido(p2);
			 // 3.- asignamos las lps al pedido
			p2.setLineasPedidos(lps);
			// 4.- antes de insertar tenomos que modificar el bean Pedido cascade = CascadeType.PERSIST
			pdao.insert(p2);
			
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
