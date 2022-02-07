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
	private PreparedStatement stmt_productos = null;
    private PreparedStatement stmt_producto  = null;
    private PreparedStatement stmt_borproducto  = null;
    private PreparedStatement stmt_modproducto  = null;
    private PreparedStatement stmt_creaproducto = null;
	
	public static synchronized  AccesoDatos initModelo(){
		if (modelo == null){
			modelo = new AccesoDatos();
		}
		return modelo;
	}
	
	private AccesoDatos (){
		try {
			
			Class.forName("com.mysql.jdbc.Driver");

			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost/EMPRESA", "root", "root");

			
			 this.stmt_productos  = conexion.prepareStatement("select * from PRODUCTOS");
		     this.stmt_producto   = conexion.prepareStatement("select * from PRODUCTOS where PRUDUCTO_NO =?");
		     this.stmt_borproducto   = conexion.prepareStatement("delete from PRODUCTOS where PRODUCTO_NO =?");
		     this.stmt_modproducto   = conexion.prepareStatement("update PRODUCTOS set DESCRIPCION=?, PRECIO_ACTUAL=?, STOCK_DISPONIBLE=? where PRODUCTO_NO=?");
		     this.stmt_creaproducto  = conexion.prepareStatement("insert into PRODUCTOS (PRODUCTO_NO,DESCRIPCION,PRECIO_ACTUAL,STOCK_DISPONIBLE) Values(?,?,?,?)");
			

		} catch (Exception ex) {
			System.err.println(" Error - En la base de datos.");
			ex.printStackTrace();
		}
	}
	
	
	 public static void closeModelo(){
	        if (modelo != null){
	           try {
				conexion.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	    }

    // Devuelvo la lista de Usuarios
    public ArrayList<Producto> getProductos () {
        ArrayList <Producto> tproductos = new ArrayList<Producto>();
        
        ResultSet rs; 
        try {
			rs =  this.stmt_productos.executeQuery();
			  while ( rs.next()) {
		        	Producto p = new Producto();
		        	p.setProducto_no(rs.getInt("PRODUCTO_NO"));
		        	p.setDescripcion(rs.getString("DESCRIPCION"));
		        	p.setPrecio_actual(rs.getFloat("PRECIO_ACTUAL"));
                    p.setStock_disponible(rs.getInt("STOCK_DISPONIBLE"));
                    tproductos.add(p);
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
    
        return tproductos;
    }
	
    // revisar id. Sería producto_no
    public Producto getProducto(String producto_no) {
    	Producto p = null;
    	
        ResultSet rs; 
        try {
        	this.stmt_producto.setString(1, producto_no);
			rs =  this.stmt_producto.executeQuery();
			  if  ( rs.next()) {
		        	p = new Producto();
		        	p.setProducto_no(rs.getInt("PRODUCTO_NO"));
		        	p.setDescripcion(rs.getString("DESCRIPCION"));
		        	p.setPrecio_actual(rs.getFloat("PRECIO_ACTUAL"));
                    p.setStock_disponible(rs.getInt("STOCK_DISPONIBLE"));
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          	return p;
    }
	
    // UPDATE
    public boolean modProducto(Producto prod){
      
    	boolean resu = false;
        try {
		
			stmt_modproducto.setString(1,prod.getDescripcion());
	        stmt_modproducto.setFloat(2,prod.getPrecio_actual());
	        stmt_modproducto.setInt(3,prod.getStock_disponible());
	    	stmt_modproducto.setInt(4,prod.getProducto_no());
	        resu = stmt_modproducto.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        return resu;
    }
    
    //INSERT
    public boolean addUsuario(Producto prod){
        boolean resu = false;
    	try {
    	stmt_creaproducto.setInt(1,prod.getProducto_no());
    	stmt_creaproducto.setString(2,prod.getDescripcion());
    	stmt_creaproducto.setFloat(3,prod.getPrecio_actual());
    	stmt_creaproducto.setInt(4,prod.getStock_disponible());
        resu = stmt_creaproducto.execute();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
        return resu;
    }
    
    
    // DELETE Elimino un usuario
    public boolean borrarUsuario(String producto_no)  {
        boolean resu = false;
    	
        try {
        	this.stmt_borproducto.setString (1, producto_no);
			resu = this.stmt_borproducto.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return resu; 
        
    }  
    
    
    
	
	// Evito que se pueda clonar el objeto.
 @Override
 public AccesoDatos clone(){
         try {
             throw new CloneNotSupportedException();
         } catch (CloneNotSupportedException ex) {
         	ex.printStackTrace();
         }
         return null; 
     }    
}

