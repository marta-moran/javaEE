package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AccesoDatos {

	private static AccesoDatos modelo;
	private static Connection conexion = null;
	private PreparedStatement stmt_consulta = null;

	public static synchronized AccesoDatos initModelo() {
		if (modelo == null) {
			modelo = new AccesoDatos();
		}
		return modelo;
	}

	private AccesoDatos() {
		try {

			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection("jdbc:mysql://localhost/telefonia", "root", "root");

			this.stmt_consulta = conexion.prepareStatement("select * from clientes where puntos >= ?");

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	//método para obtener la lista de clientes que tegan igual o más puntos que los introducidos por el formulario
	public ArrayList<Cliente> getClientes(int puntos) {
    	
    	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

        ResultSet rs; 
        try {
        	this.stmt_consulta.setInt(1, puntos);
			rs =  this.stmt_consulta.executeQuery();
			while ( rs.next()) { //si no pongo el while no recorre todos los registros
				  //Cliente client = null;
				  Cliente client = new Cliente();
				  client.setTelefono(rs.getInt("telefono"));
				  client.setNombre(rs.getString("nombre"));
				  client.setPuntos(rs.getInt("puntos"));

				  clientes.add(client); //añado el cliente al array list que muestro en el jsp
			  }
		        	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          	return clientes; //devuelvo el arrayList
    }
	

	public static void closeModelo() {
		if (modelo != null) {
			try {
				conexion.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public AccesoDatos clone() {
		try {
			throw new CloneNotSupportedException();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
