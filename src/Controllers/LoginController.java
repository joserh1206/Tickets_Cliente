package Controllers;

import Code.conector;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public String usuario, contrasenia;

    @FXML
    private JFXTextField txfUsuario;

    @FXML
    private JFXPasswordField pwfContraseña;

    @FXML
    private JFXButton bntIngresar;

    @FXML
    void IngresoEmpleado(ActionEvent e) throws IOException {

        usuario = txfUsuario.getText();
        contrasenia = pwfContraseña.getText();
        String datos = usuario+";"+contrasenia;

        //System.out.println("TAMANIOS");
        //System.out.println(usuario.length());
        //System.out.println(contrasenia.length());

        conector c = new conector();
        String mensaje[] = c.inicio(datos);
        if(!Objects.equals(mensaje[1], "0")) {
            if(Objects.equals(mensaje[1], "ROJO")){
                CrearVentana("../FXMLs/VentanaEmpleadoRojo.fxml", e,"Empleado Categoria Roja");
            }
            else if(Objects.equals(mensaje[1], "VERDE")){
                CrearVentana("../FXMLs/VentanaEmpleadoVerde.fxml", e,"Empleado Categoria Verde");
            }
            else {
                CrearVentana("../FXMLs/VentanaEmpleadoAmarillo.fxml", e,"Empleado Categoria Amarilla");
            }
        }
        else{
            //System.out.println("Error");
        }
    }
    void CrearVentana(String ruta, ActionEvent event, String tiulo) throws IOException {
        Parent VentanaEmpleado_parent = FXMLLoader.load(getClass().getResource(ruta));
        Scene VentanaEmpleado_scene = new Scene(VentanaEmpleado_parent);
        Stage App_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_Stage.close();
        App_Stage.setScene(VentanaEmpleado_scene);
        App_Stage.setTitle(tiulo);
        App_Stage.setResizable(false);
        App_Stage.show();
    }
}
