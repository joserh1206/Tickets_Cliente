package Controllers;

import Code.conector;
import Controllers.VentanaPuertoIPController;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginController {

    public static String mensaje[];

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
        if(!Objects.equals(usuario, "") && !Objects.equals(contrasenia, "")) {
            String datos = usuario + ";" + contrasenia;
            mensaje = VentanaPuertoIPController.c.inicio(datos);
            if (!Objects.equals(mensaje[1], "0")) {
                if (Objects.equals(mensaje[1], "ROJO")) {
                    CrearVentana("../FXMLs/VentanaEmpleadoRojo.fxml", e, "Empleado Categoria Roja");
                } else if (Objects.equals(mensaje[1], "VERDE")) {
                    CrearVentana("../FXMLs/VentanaEmpleadoVerde.fxml", e, "Empleado Categoria Verde");
                } else {
                    CrearVentana("../FXMLs/VentanaEmpleadoAmarillo.fxml", e, "Empleado Categoria Amarilla");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error con los datos");
                alert.setHeaderText("Se produjo un error con los datos ingresados");
                alert.setContentText("Por favor ingrese los datos e intente nuevamente, si el error persiste comuniquese" +
                        " con el encargado del servidor");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Faltan datos");
            alert.setHeaderText("Se deben ingresar todos los datos solicitados");
            alert.setContentText("Por favor ingrese todos los datos solicitados e intente nuevamente");
            alert.showAndWait();
        }
    }

    private void CrearVentana(String ruta, ActionEvent event, String tiulo) throws IOException {
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
