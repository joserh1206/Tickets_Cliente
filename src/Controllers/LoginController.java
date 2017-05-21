package Controllers;

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

public class LoginController {

    @FXML
    private JFXTextField txfUsuario;

    @FXML
    private JFXPasswordField pwfContraseña;

    @FXML
    private JFXButton bntIngresar;

    @FXML
    void IngresoEmpleado(ActionEvent event) throws IOException {
        Parent VentanaEmpleado_parent = FXMLLoader.load(getClass().getResource("../FXMLs/VentanaEmpleadoRojo.fxml"));
        Scene VentanaEmpleado_scene = new Scene(VentanaEmpleado_parent);
        Stage App_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_Stage.close();
        App_Stage.setScene(VentanaEmpleado_scene);
        App_Stage.setTitle("Empleado Categoria Roja");
        App_Stage.setResizable(false);
        App_Stage.show();
    }

}
