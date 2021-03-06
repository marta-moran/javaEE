

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormAvanzado
 */
@WebServlet("/FormAvanzado")
public class FormAvanzado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormAvanzado() {
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
		String apellidos = request.getParameter("apellidos");
		String edad = request.getParameter("edad");
		String sexo = request.getParameter("sexo");
		String[] hobbies = request.getParameterValues("hobbies"); //para arrays
		
		salida.println("<html><body>");
		salida.println("<h1>Bienvenido/a " + nombre + "</h1>");
		
		salida.println("<h2>Tus datos son: </h2>");
		salida.println("Nombre " + nombre + "<br>");
		salida.println("Apellidos " + apellidos + "<br>");
		salida.println("Tu franja de edad es " + edad + "<br>");

		if(edad.contains("Menor")) {
			salida.println("Aun eres menor de edad <br>");
		} else {
			salida.println("Eres mayor de edad <br>");
		}
		
		salida.println("Tus hobbies son: <br>");
		for (String hobby : hobbies) {
			salida.println("<ul>");
			salida.println("<li>" + hobby + "</li>");
			salida.println("</ul>");
		}
		
		//para poner las fotos asociadas a la selecci?n
		if(sexo.contains("M")) {
			salida.println("<img src='https://static1.mujerhoy.com/www/multimedia/201712/27/media/cortadas/marie-aristogatos-kU9H-U50474454186KrB-624x926@MujerHoy.jpg' width: 10%><br>");
		} else if (sexo.contains("H")) {
			salida.println("<img src='https://www.cineycine.com/archivos/2020/12/los-aristogatos-toulouse.jpg' width: 10%><br>");
		} else {
			salida.println("<img src='https://www.cineycine.com/archivos/2020/12/los-aristogatos-berlioz.jpg' width: 10%><br>");
		}
		
		salida.println("?Hasta Pronto!");
		response.setHeader("Refresh", "30; URL=formularioAvanzado.html");
		salida.println("</body></html>");
	}

}
