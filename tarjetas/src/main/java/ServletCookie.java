

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Tarjetas
 */
@WebServlet("/pagoc")
public class ServletCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCookie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter salida = response.getWriter();
		String nuevatarjeta = request.getParameter("nuevatarjeta"); 
		Cookie[] tarjetas = request.getCookies(); //array que almacena las cookies
		
		
		
		salida.println("<html><body>");
		salida.println("<div style= 'text-align: center'>");
		salida.println("<h2>Su forma de pago actual es</h2>");
		
		
		if(nuevatarjeta != null) {
			//creo la cookie
			Cookie cookieTarjeta = new Cookie("seleccion", nuevatarjeta);
			
			//vida de la cookie
			cookieTarjeta.setMaxAge(5*60*24); //se indica en segundos. Así serían 5 mins
			
			//enviar a Usuario
			response.addCookie(cookieTarjeta);
			
		}
		
		if (tarjetas != null) {
			for (Cookie cookie_temporal:tarjetas) {
				if ("seleccion".equals(cookie_temporal.getName())) {
					nuevatarjeta = cookie_temporal.getValue();
					break;
				}
			}
			salida.println("<img src='img/" + nuevatarjeta + ".gif'<br>"); //nombre de la tarjeta + .gif
		}
		
		
		
		
		
		
		salida.println("<h2>SELECCIONE UNA NUEVA TARJETA DE CREDITO </h2><br>\r\n");
		
		salida.println("<a href='pagoc?nuevatarjeta=cashu'><img src='img/cashu.gif'></a>&ensp;");
		salida.println("<a href='pagoc?nuevatarjeta=amex1'><img src='img/amex1.gif'></a>&ensp;");
		salida.println("<a href='pagoc?nuevatarjeta=cirrus1'><img src='img/cirrus1.gif'></a>&ensp;");
		salida.println("<a href='pagoc?nuevatarjeta=dinersclub'><img src='img/dinersclub.gif'></a>&ensp;");
		salida.println("<a href='pagoc?nuevatarjeta=paypal'><img src='img/paypal.gif'></a>&ensp;");
		salida.println("<a href='pagoc?nuevatarjeta=visa1'><img src='img/visa1.gif'></a>&ensp;");
		salida.println("<a href='pagoc?nuevatarjeta=visa_electron'><img src='img/visa_electron.gif'></a>&ensp;");
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
