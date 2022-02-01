

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletSession
 */
@WebServlet("/pagos")
public class ServletSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSession() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter salida = response.getWriter();
		HttpSession session = request.getSession();
		String seleccion = request.getParameter("nuevatarjeta");
		String nuevatarjeta = (String) session.getAttribute("nuevatarjeta"); //hay que hacer casting (string != objeto)
		
		salida.println("<html><body>");
		salida.println("<div style= 'text-align: center'>");
		salida.println("<h2>Su forma de pago actual es</h2>");
		
		
			
		
		
		if (seleccion == null) {
			salida.println("wewe");
			nuevatarjeta = new String();
			session.setAttribute("nuevaSeleccion", nuevatarjeta);
		}
		
		String select = request.getParameter("nuevatarjeta");
		
		if (select != null) {
			nuevatarjeta = select;
			salida.println("<img src='img/" + nuevatarjeta + ".gif'<br>");
		}
			
		
		
		salida.println("<h2>SELECCIONE UNA NUEVA TARJETA DE CREDITO </h2><br>\r\n");
		
		salida.println("<a href='pagos?nuevatarjeta=cashu'><img src='img/cashu.gif'></a>&ensp;");
		salida.println("<a href='pagos?nuevatarjeta=amex1'><img src='img/amex1.gif'></a>&ensp;");
		salida.println("<a href='pagos?nuevatarjeta=cirrus1'><img src='img/cirrus1.gif'></a>&ensp;");
		salida.println("<a href='pagos?nuevatarjeta=dinersclub'><img src='img/dinersclub.gif'></a>&ensp;");
		salida.println("<a href='pagos?nuevatarjeta=paypal'><img src='img/paypal.gif'></a>&ensp;");
		salida.println("<a href='pagos?nuevatarjeta=visa1'><img src='img/visa1.gif'></a>&ensp;");
		salida.println("<a href='pagos?nuevatarjeta=visa_electron'><img src='img/visa_electron.gif'></a>&ensp;");
		
		salida.println("</div>");
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
