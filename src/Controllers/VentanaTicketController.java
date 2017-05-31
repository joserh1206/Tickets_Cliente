package Controllers;

/**
 * Created by joser on 20/5/2017.
 */

import Controllers.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

public class VentanaTicketController {

    boolean cambiarColor = false;


    @FXML
    private AnchorPane anpTicketWindow;

    @FXML
    private Label lblTicket;

    @FXML
    private JFXTextArea txaComentario;

    @FXML
    private JFXButton btnResuelto;

    @FXML
    private JFXButton btnPendiente;

    @FXML
    private ImageView btnPlay;

    @FXML
    private ImageView btnPausa;

    @FXML
    private Label lblCsegundos;

    @FXML
    private Label lblCminutos;

    @FXML
    private Label lblChoras;

    @FXML
    void Play(MouseEvent event) {
        if(!cambiarColor) {
            JFXFillTransition paint = new JFXFillTransition();
            paint.setDuration(Duration.seconds(5));
            paint.setRegion(anpTicketWindow);
            paint.setFromValue(Color.WHITE);
            if(Objects.equals(LoginController.mensaje[1], "ROJO")) {
                paint.setToValue(Color.RED);
                paint.play();
            }
            else if (Objects.equals(LoginController.mensaje[1], "VERDE")){
                paint.setToValue(Color.GREENYELLOW);
                paint.play();
            }
            else{
                paint.setToValue(Color.DARKORANGE);
                paint.play();
            }
            cambiarColor = true;
        }
        else{
            System.out.println("Ya se hizo el cambio de color");
        }
    }

}