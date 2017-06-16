package Controllers;

/**
 * Created by Jose Luis Rodriguez on 27/5/2017.
 */

import Code.conector;
import com.jfoenix.controls.JFXButton;
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

public class VentanaPuertoIPController {

    @FXML
    private JFXTextField txfIP;

    @FXML
    private JFXTextField txfPuerto;

    @FXML
    private JFXButton btnConectar;

    static conector c;

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void ConectarServer(ActionEvent event) throws IOException {
        if(!Objects.equals(txfPuerto.getText(), "") && !Objects.equals(txfIP.getText(), "")) {
            int port = Integer.parseInt(txfPuerto.getText().trim());
            c = new conector(port, txfIP.getText().trim());
            if (c.conectar()) {
                c.iniciar();
                Parent VentanaLogin_Parent = FXMLLoader.load(getClass().getResource("../FXMLs/Login.fxml"));
                Scene VentanaLogin_scene = new Scene(VentanaLogin_Parent);
                Stage App_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                App_Stage.close();
                App_Stage.setScene(VentanaLogin_scene);
                App_Stage.setTitle("LogIn");
                App_Stage.setResizable(false);
                App_Stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error al conectar");
                alert.setHeaderText("Se produjo un error al conectar el servidor");
                alert.setContentText("Por favor ingrese los datos e intente nuevamente, si el error persiste comuniquese" +
                        " con el encargado del servidor");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Faltan datos");
            alert.setHeaderText("Todos los datos son requeridos");
            alert.setContentText("Asegurese de ingresar todos los datos solicitados e intente nuevamente");
            alert.showAndWait();
        }

    }

}
