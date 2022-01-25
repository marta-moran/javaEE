

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormAlumnos
 */
@WebServlet("/FormAlumnos")
public class FormAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormAlumnos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		response.setContentType("text/html");
		PrintWriter salida = response.getWriter();
		String nombre = request.getParameter("nombre");
		String clave = request.getParameter("clave");
		
		salida.println("<html><body>");
		salida.println();
		if(nombre.equals("alumno") && clave.equals("alumno")) {
			salida.println("<h1>�Hola " + nombre + "!</h1>");
		} else {
			salida.println("<h1>Usuario o clave incorrecta, vuelva a intentarlo</h1>");
			response.setHeader("Refresh", "3; URL=acceso.html");
		}
		salida.println("</body></html>");
				
	}

}
