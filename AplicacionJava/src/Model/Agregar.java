
package Model;

import java.sql.Date;
import java.time.LocalDate;

public class Agregar {
    private int id;
    private String nombre;
    private String direccion;
    private String fecha;

    public Agregar(int id, String nombre, String direccion, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    
        public Agregar() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
        public String toString(){
        return getId() + " | "+ getNombre();
    }




    
    
}
