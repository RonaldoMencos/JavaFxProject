/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Agregar;
import Principal.Principal;
import Reportes.GenerarReporte;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.mysql.jdbc.PreparedStatement;
import db.Conexion;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Casa
 */
public class ListarController implements Initializable{
     private Principal escenarioPrincipal;
     private ObservableList<Agregar>  listaAgregar;
    @FXML private JFXTextField txtNombre = null;
    @FXML private JFXTextField txtDireccion = null;
    @FXML private JFXDatePicker dteFecha = null;
    @FXML private TableView tblDatos;
    @FXML private TableColumn colNombre;
    @FXML private TableColumn colDireccion;
    @FXML private TableColumn colFecha;
    @FXML private TableColumn colCodigo;
    @FXML private ComboBox cmbCodigo;
    
          @Override
    public void initialize(URL location, ResourceBundle resources) {
     cargarDatos();

    }
    
     public void cargarDatos(){
        tblDatos.setItems(getAgregar());
        colCodigo.setCellValueFactory(new PropertyValueFactory<Agregar,Integer>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Agregar,String>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Agregar,String>("direccion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Agregar,String>("fecha"));
    }
    
    public ObservableList<Agregar>getAgregar(){
        ArrayList<Agregar> lista = new ArrayList<Agregar>();
        String sql = "SELECT agregar.id,agregar.nombre,agregar.direccion,agregar.fecha from agregar";
        try{
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstancia().getConexion().prepareStatement(sql);
            ResultSet resultado = procedimiento.executeQuery();
                System.out.println(resultado);

               while(resultado.next()){
                lista.add(new Agregar(resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("direccion"),
                        resultado.getString("fecha")));
            }
            
        }catch (SQLException e){
            e.printStackTrace();

        }catch (Exception e ){
            e.printStackTrace();
        }
        return listaAgregar = FXCollections.observableList(lista);
    }
    
    public void seleccionarElemento(){
        if (tblDatos.getSelectionModel().getSelectedItem() != null){;
        cmbCodigo.getSelectionModel().select(BuscarAgregar(((Agregar)tblDatos.getSelectionModel().getSelectedItem()).getId()));

    }  else{
            JOptionPane.showMessageDialog(null, "No hay registros");
    }
    }
        
    
    public Agregar BuscarAgregar(int id){
        Agregar resultado = null;
        String sql = "SELECT *FROM agregar WHERE id = ?";
        try{
            PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstancia().getConexion().prepareStatement(sql);
            procedimiento.setInt(1,id);
            ResultSet registro = procedimiento.executeQuery();
            while (registro.next()){
                resultado = new Agregar(registro.getInt("id"),registro.getString("nombre"),registro.getString("direccion"),registro.getString("fecha"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultado;
    }
    
    public void eliminar(){
        if(tblDatos.getSelectionModel().getSelectedItem() != null){
                    int respuesta = JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar el registro?","Eliminar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (respuesta == JOptionPane.YES_OPTION){
                        String sql = "delete from agregar where agregar.id = ?";
                       try{ 
                       PreparedStatement procedimiento = (PreparedStatement) Conexion.getInstancia().getConexion().prepareStatement(sql);
                       procedimiento.setInt (1,((Agregar)tblDatos.getSelectionModel().getSelectedItem()).getId());
                       procedimiento.execute();
                       listaAgregar.remove(tblDatos.getSelectionModel().getSelectedIndex());
                           
                       }catch(SQLException e){
                           e.printStackTrace();
                           
                       }
                    }
                }else {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una producto");
                    }
    }
    
    
        public void imprimirReporte(){
        Map parametros = new HashMap();
        parametros.put("id",null);
        GenerarReporte.mostrarReporte("Imprimir.jasper", "Reporte de Productos ", parametros);
    }
    
        public Principal getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Principal escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }
}
