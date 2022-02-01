

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ArrayAleatorios
 */
@WebServlet("/ArrayAleatorios")

/*
 * Rellenar un array con 20 números aleatorios entre 1 y 10 y mostrar el 
 * contenido del array mediante una tabla de una fila en HMTL. 
 * Mostrar a continuación el valor máximo, el mínimo y el valor que mas veces se repite. 
 * (Nota definir funciones para cada caso)
 */
public class ArrayAleatorios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    int contador = 0;
    public ArrayAleatorios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		PrintWriter salida = response.getWriter();
		Random rd = new Random();
		int random[] = new int[20];
		
		salida.println("<html><body>");
		salida.println("<table border='1'><tr>");
		for(int i = 0; i<random.length; i++){
			random[i] = rd.nextInt(10)+1;
			salida.println("<td>" + random[i] + "</td>");
		}
		salida.println("</tr></table>");
		salida.println("Número máximo: " + numeroMax(random));
		salida.println("Número máximo: " + numeroMin(random));
		
	}
	
	
	public int numeroMax(int t[]) {
		int resu = t[0];
		for(int i=1; i<t.length; i++) { //recorre la tabla
			if (t[i] > resu) { 
				resu = t[i];
			}
		}
		return resu;
	}
	
	public int numeroMin(int t[]) {
		int resu = t[0];
		for(int i=1; i<t.length; i++) { //recorre la tabla
			if (t[i] < resu) { 
				resu = t[i];
			}
		}
		return resu;

	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
