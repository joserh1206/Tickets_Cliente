package Controllers;

/**
 * Created by joser on 20/5/2017.
 */

import Controllers.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

class Cronometro extends Task<String> {

    /**
     * Invoked when the Task is executed, the call method must be overridden and
     * implemented by subclasses. The call method actually performs the
     * background thread logic. Only the updateProgress, updateMessage, updateValue and
     * updateTitle methods of Task may be called from code within this method.
     * Any other interaction with the Task from the background thread will result
     * in runtime exceptions.
     *
     * @return The result of the background work, if any.
     * @throws Exception an unhandled exception which occurred during the
     *                   background operation
     */
    @Override
    protected String call() throws Exception {
        int i = 0;
        while(i<60){
            actualizarCronometro(String.valueOf(i));
            Thread.sleep(100);
            i++;
            if(i==60){
                i = 0;
            }
        }
        return "1";
    }

    private void actualizarCronometro(String tiempo){
        updateMessage(tiempo);
    }
}

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
    void Play(MouseEvent event) throws InterruptedException {

        Cronometro c = new Cronometro();

        lblCsegundos.textProperty().bind(c.messageProperty());

        new Thread(c).start();

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