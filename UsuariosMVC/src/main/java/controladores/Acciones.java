package controladores;

import modelo.AccesoDatos;
import modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Acciones {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	Acciones (HttpServletRequest request, HttpServletResponse response ){
		this.request = request;
		this.response = response;
	}
	
	void accionAlta() throws ServletException, IOException {
		 Usuario user = new Usuario();
		 request.setAttribute("orden", "Nuevo");
		 request.setAttribute("user", user);
		 request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);
	}
	 void accionBorrar ( String id ){
		 AccesoDatos db = AccesoDatos.initModelo();
		 db.borrarUsuario(id);	
	}
	 
	 void incrementarSaldo(String[] ids) throws ServletException, IOException {
		 AccesoDatos db = AccesoDatos.initModelo();
		 for(String id:ids){
			 Usuario user = db.getUsuario(id);
			 db.incrementaSaldo(user);
		}
		 response.sendRedirect("./indice");
	 }
	 
	 void accionBloqueo(String[] ids) throws ServletException, IOException {
		 AccesoDatos db = AccesoDatos.initModelo();
		 ArrayList <Usuario> usuarios = db.getUsuarios();
		 
		 for(Usuario user : usuarios) {
			 db.desbloquear(user.getLogin());
			for(String id : ids) {
				if(user.getLogin().equals(id)) {
					db.bloquear(id);
				} 
			}	 
		}
		 response.sendRedirect("./indice"); 
	 }
	 
	void accionModificar (String id ) throws ServletException, IOException {
		 AccesoDatos db = AccesoDatos.initModelo();
		    Usuario user = db.getUsuario(id);
		    request.setAttribute("orden", "Modificar");
		    request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);

	}
	 void accionDetalles  (String id ) throws ServletException, IOException {
		    AccesoDatos db = AccesoDatos.initModelo();
		    Usuario user = db.getUsuario(id);
		    request.setAttribute("orden", "Detalles");
		    request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);
	}
    void accionTerminar() {
    	System.out.println("->>> accionTerminar   \n");
    }
    
    void accionPostAlta() {
      // Habría que controlar los datos de recibido <<<<<<<
      Usuario user = new Usuario();
   	  user.setLogin( request.getParameter("login"));
   	  user.setNombre(request.getParameter("nombre"));
   	  user.setPassword(request.getParameter("password"));
   	  user.setComentario(request.getParameter("comentario"));
   	  AccesoDatos db = AccesoDatos.initModelo();
   	  db.addUsuario(user);
    }
    void accionPostModificar() {
    	// Habría que controlar los datos de recibido <<<<<<<
     	  Usuario user = new Usuario();
    	  user.setLogin( request.getParameter("login"));
    	  user.setNombre(request.getParameter("nombre"));
    	  user.setPassword(request.getParameter("password"));
    	  user.setComentario(request.getParameter("comentario"));
    	  AccesoDatos db = AccesoDatos.initModelo();
    	  db.modUsuario(user);
 	
    }
}
