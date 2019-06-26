package Principal;



import Controller.AgregarController;
import Controller.ListarController;
import java.awt.Graphics;
import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;





public class Principal extends Application {
    private final String PAQUETE_VISTA = "/Views/";
    private Stage escenarioPrincipal;
    private Scene escena;

    @Override
    public void start(Stage escenarioPrincipal) throws Exception{
        this.escenarioPrincipal = escenarioPrincipal;
        escenarioPrincipal.setTitle("AplicacionJava");
        escenarioPrincipal.setMaximized(true);
        Image image = new Image("/Images/Logo.PNG");
        escenarioPrincipal.getIcons().add(image);
        ventanaAgregar();
        escenarioPrincipal.show();
        
             
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public void ventanaAgregar (){
        try{
            AgregarController agregar = (AgregarController)cambiarEscena ("Agregar.fxml",1362,900);
            agregar.setEscenarioPrincipal(this);
        }catch (Exception e){
                        e.printStackTrace();

        }
    }
    
        public void ventanaListarAgregados (){
        try{
            ListarController listar = (ListarController)cambiarEscena ("ListarAgregados.fxml",1362,900);
            listar.setEscenarioPrincipal(this);
        }catch (Exception e){
                        e.printStackTrace();

        }
    }
   
         

    public Initializable cambiarEscena(String fxml,int ancho,int alto)throws Exception{
            Initializable resultado = null;
            FXMLLoader cargadorFXML = new FXMLLoader();
            InputStream archivo = Principal.class.getResourceAsStream(PAQUETE_VISTA+fxml);
            cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
            cargadorFXML.setLocation(Principal.class.getResource(PAQUETE_VISTA+fxml));
            escena = new Scene((BorderPane )cargadorFXML.load(archivo),ancho,alto);
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.sizeToScene();
            resultado = (Initializable)cargadorFXML.getController();
        return resultado;
    }

    

}
