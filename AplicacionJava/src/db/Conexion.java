package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

private static Connection conexion;
private static final String driver = "com.mysql.jdbc.Driver";
private static final String user = "root";
private static final String password = "";
private static final String url= "jdbc:mysql://localhost/aplicacionjava";
private  static Conexion instancia;


    public Conexion() {
        conexion = null;
        try{
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(url,user,password);
            if(conexion != null){
                System.out.println("Conexion exitosa");
            }
         }catch (ClassNotFoundException e){
            e.printStackTrace();
         
        }catch(InstantiationException e){
           e.printStackTrace();
           
        }catch(IllegalAccessException e){
             e.printStackTrace();
             
        }catch(SQLException e){
             e.printStackTrace();
    }
        }
    
    public static Conexion getInstancia(){
        if(instancia == null){
            instancia = new Conexion();
            
        }
        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }


}
