/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Agregar;
import javafx.fxml.Initializable;
import Principal.Principal;
import com.jfoenix.controls.JFXDatePicker;
import javafx.fxml.FXML;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import db.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
/**
 *
 * @author Casa
 */
public class AgregarController implements Initializable {
    private Principal escenarioPrincipal;
    @FXML private JFXTextField txtNombre = null;
    @FXML private JFXTextField txtDireccion = null;
    @FXML private JFXDatePicker dteFecha = null;


      @Override
    public void initialize(URL location, ResourceBundle resources) {
     

    }
    
   
    
public void add(){
    Agregar registro = new Agregar();
    String nombre = txtNombre.getText();
    String direccion = txtDireccion.getText();
    LocalDate fecha = dteFecha.getValue();

        String sql = "INSERT INTO agregar(nombre,direccion,fecha) values (?,?,?) ";
        if( nombre.equals("") || direccion.equals("") || fecha == null ){
            JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos",
                "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
        }else{
    registro.setNombre(txtNombre.getText());
    registro.setDireccion(txtDireccion.getText());
    registro.setFecha(dteFecha.getValue().format(DateTimeFormatter.ISO_DATE));
        try{
               
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstancia().getConexion().prepareStatement(sql);
            procedimiento.setString(1,registro.getNombre());
            procedimiento.setString(2,registro.getDireccion());
            procedimiento.setString(3,registro.getFecha());
            procedimiento.execute();
            limpiar();
            JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente");
        }catch(SQLException e){
             e.printStackTrace();

        }
        }
}

public void limpiar(){
    txtNombre.setText("");
    txtDireccion.setText("");
    dteFecha.setValue(null);
}



    
    public void ventanaListarAgregados(){
      escenarioPrincipal.ventanaListarAgregados();

    }
    
    public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }







    

}
