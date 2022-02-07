package controladores;

import modelo.AccesoDatos;
import modelo.Producto;


import java.io.IOException;

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
		 Producto prod = new Producto();
		 request.setAttribute("orden", "Nuevo");
		 request.setAttribute("prod", prod);
		 request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);
	}
	 void accionBorrar ( String producto_no ){
		 AccesoDatos db = AccesoDatos.initModelo();
		 db.borrarUsuario(producto_no);	
	}
	void accionModificar (String producto_no) throws ServletException, IOException {
		 AccesoDatos db = AccesoDatos.initModelo();
		 	Producto prod = db.getProducto(producto_no); //revisar el parametro q se pasa, creo q es producto_no
		    request.setAttribute("orden", "Modificar");
		    request.setAttribute("prod", prod);
			request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);

	}
	 void accionDetalles  (String producto_no ) throws ServletException, IOException {
		    AccesoDatos db = AccesoDatos.initModelo();
		    Producto prod = db.getProducto(producto_no);
		    request.setAttribute("orden", "Detalles");
		    request.setAttribute("prod", prod);
			request.getRequestDispatcher("/WEB-INF/layout/formulario.jsp").forward(request, response);
	}
    void accionTerminar() {
    	System.out.println("->>> accionTerminar   \n");
    }
    
    void accionPostAlta() {
      // Habrá que controlar los datos de recibido <<<<<<<
    Producto prod = new Producto();
   	  prod.setProducto_no(Integer.parseInt(request.getParameter("producto_no")));
   	  prod.setDescripcion(request.getParameter("descripcion"));
   	  prod.setPrecio_actual(Float.parseFloat(request.getParameter("precio_actual")));
   	  prod.setStock_disponible(Integer.parseInt(request.getParameter("stock_disponible")));
   	  AccesoDatos db = AccesoDatos.initModelo();
   	  db.addUsuario(prod);
    }
    void accionPostModificar() {
    	// Habrá que controlar los datos de recibido <<<<<<<
     	  Producto prod = new Producto();
    	  prod.setProducto_no(Integer.parseInt(request.getParameter("producto_no")));
    	  prod.setDescripcion(request.getParameter("nombre"));
    	  prod.setPrecio_actual(Float.parseFloat(request.getParameter("precio_actual")));
    	  prod.setStock_disponible(Integer.parseInt(request.getParameter("stock_disponible")));
    	  AccesoDatos db = AccesoDatos.initModelo();
    	  db.modProducto(prod);
 	
    }
}
