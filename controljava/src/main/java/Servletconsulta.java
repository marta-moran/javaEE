

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import modelo.AccesoDatos;
import modelo.Movimiento;

/**
 * Servlet implementation class Servletconsulta
 */
@WebServlet({ "/Servletconsulta", "/procesarconsulta" })
public class Servletconsulta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servletconsulta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String cod_cliente = request.getParameter("id");
		String importe_maxs = request.getParameter("importmax");
		String importe_mins = request.getParameter("importmin");
		
		int importemax = -1;  
		int importemin =  1;
		boolean error = false;
		try {
			importemax = Integer.parseInt(importe_maxs);
			importemin = Integer.parseInt(importe_mins);
		} catch ( NumberFormatException ex) {
			error = true;
		}
		AccesoDatos mimodelo = AccesoDatos.initModelo(); 
		
		String msg;
		
		if (error || importemax < importemin) { //si mete caracteres o si el importe mínimo es mayor que el máximo
			msg = "Los importes introducidos no son correctos ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}
		
		if (! mimodelo.hayMovimientos(cod_cliente) ) { //no existe el id (el array resultado no existe)
			msg = "El código de cliente " + cod_cliente + " no se encuentra en la base de datos ";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}
		
		ArrayList <Movimiento> listmov = mimodelo.obtenerListaMovimientos(cod_cliente, importemax, importemin);
		
		if ( listmov.size() == 0) { //existe el array resultado pero está vació. Existe cliente pero no tiene movimientos
			msg = "No se han encontrado movimientos del cliente " + cod_cliente + " con los importes seleccionados";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/infoerror.jsp").forward(request, response);
			return;
		}
		
		request.setAttribute("listmovimientos", listmov);
		request.getRequestDispatcher("/WEB-INF/informovimiento.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
