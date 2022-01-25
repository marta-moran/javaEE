

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cabecera
 */
@WebServlet("/Cabecera")
public class Cabecera extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cabecera() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//especifico formato de respuesta
		PrintWriter salida = response.getWriter();
		String nombreNavegador = request.getHeader("User-Agent"); //para recoger la cabecera 
		salida.println("<html><body>");
		if(nombreNavegador.contains("Chrome")) { //si contiene chrome
			salida.println("<h1 style='color:pink;'>Estás usando Chrome</h1>");
		} else {
			salida.println("Estás usando un navegador distinto a Chrome"); //si no contiene chrome
		}
		salida.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
