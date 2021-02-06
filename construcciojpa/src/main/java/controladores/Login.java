package controladores;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.beans.Cliente;
import modelo.daos.ClienteDAO;
import modelo.daos.ClienteDAOImpl;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cliente = null;
		ClienteDAO cdao = new ClienteDAOImpl();
		switch(request.getParameter("opcion")) {
		case "validar":
			
			cliente = cdao.autenticar(request.getParameter("usuario"), 
										request.getParameter("password"));
			if (cliente != null) {
				if (cliente.getTipoCliente().equals("normal")) {
					HttpSession misesion = request.getSession();
					misesion.setAttribute("cliente", cliente);
					request.getRequestDispatcher("MenuClientes.jsp").forward(request, response);
				}else
					request.getRequestDispatcher("MenuAdministrador.jsp").forward(request, response);
			}else {
				request.setAttribute("mensajeLogin", "usuario o password incorrecto, intentalo de nuevo");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			
			break;
		case "registrar":
			cliente = new Cliente();
			cliente.setUsuario(request.getParameter("usuario"));
			cliente.setPassword(request.getParameter("password"));
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellidos(request.getParameter("apellidos"));
			cliente.setDireccion(request.getParameter("direccion"));
			
			cliente.setTipoCliente("normal");
			cliente.setFechaAlta(new Date());
			
			if (cdao.insert(cliente) == 1) {
				request.setAttribute("mensajeLogin", "cliente inserado por favor loguese");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				request.setAttribute("mensajeLogin", "usuario ya existe");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			break;
			
		case "registrarBean":
			cliente = (Cliente) request.getAttribute("micliente");
			cliente.setTipoCliente("normal");
			cliente.setFechaAlta(new Date());
			if (cdao.insert(cliente) == 1) {
				request.setAttribute("mensajeLogin", "cliente inserado por favor loguese");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else {
				request.setAttribute("mensajeLogin", "usuario ya existe");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
				
			
			break;
			
		default:
			System.out.println("opcion en Login erronea : " + request.getParameter("opcion"));
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
