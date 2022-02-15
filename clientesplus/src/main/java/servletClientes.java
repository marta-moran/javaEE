

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.AccesoDatos;

/**
 * Servlet implementation class verPuntos
 */
@WebServlet({"/servletClientes", "/verPuntos"})
public class servletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	//esta función comprueba que no contenga letras
	public static boolean comprobarLetras(String puntos, String minusculas, String mayusculas) {
		boolean noCaracteres = true;
		for(int i = 0; i < minusculas.length(); i++) {
			for(int j = 0; j < mayusculas.length(); j++) {
				if(puntos.indexOf(minusculas) == -1 && puntos.indexOf(mayusculas) == -1) {
					noCaracteres = false;
				}
			}
		}
		return noCaracteres;
	}
	
	//esta funcion comprueba que no contenga caracteres especiales
	public static boolean comprobarEspeciales(String puntos, String cEspeciales) {
		boolean noEspeciales = true;
		for (int i = 0; i < cEspeciales.length(); i++) {
			if(puntos.indexOf(cEspeciales) == -1) {
				noEspeciales = false;
			}
		}
		return noEspeciales;
	}
	
	//esta funcion comprueba que haya numeros
	public static boolean comprobarNumeros(String puntos, String numeros) {
		boolean noNumeros = false; //para q este ok debe devolver false
		for(int i = 0; i < numeros.length(); i++) {
			if (puntos.indexOf(numeros) == -1) {
				noNumeros = true;
			}
		}
		return noNumeros;
	}
	
	public static boolean esNumero(String puntos) { 
		  try {  
		    Double.parseDouble(puntos);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletClientes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		AccesoDatos ac =  AccesoDatos.initModelo();
		
		
		String puntos = request.getParameter("puntos"); //lo que recojo del index.html. Cuando haga las comprobaciones lo parseo
		
		//metodos que hacen las comprobaciones
	
		if(esNumero(puntos)) {	
			//validado, parseo los puntos para que pueda llamar al arrayList
			int puntosParseados = Integer.parseInt(puntos);
			request.setAttribute("tablaClientes", ac.getClientes(puntosParseados));
			request.getRequestDispatcher("/WEB-INF/listaCliente.jsp").forward(request, response);
			return;
			
		
		} else {
		
			//debe llamar al archivo de error.jsp y mandar un mensaje
			String msg = "Los puntos introducidos no son correctos";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
			return;
		
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
