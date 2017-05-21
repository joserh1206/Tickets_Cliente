package Controllers;

/**
 * Created by Jose Luis Rodriguez on 20/5/2017.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class VentanaEmpleadoController {

    @FXML
    private JFXButton btnActualizar;

    @FXML
    private JFXTreeTableView<?> ttvTickets;

    @FXML
    private JFXButton btnDesconectar;

    @FXML
    private Label lblNombreEmpleado;

    @FXML
    private JFXButton btnReporte;

    @FXML
    private JFXTextField txfTicketID;

    @FXML
    private JFXButton btnAtender;


    @FXML
    void ActualizarTickets(ActionEvent event) {
        System.out.println("Sirve");
    }

    @FXML
    void AtenderTicket(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLs/VentanaTicket.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Ticket");
        stage.setResizable(false);
        stage.show();
    }
}
