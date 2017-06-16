package Controllers;

/**
 * Created by Jose Luis Rodriguez on 20/5/2017.
 *
 */


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import java.util.Objects;

import static Controllers.VentanaTicketController.*;


/**
 * Clase que controla lo que ocurre en la ventana que muestra cada uno de los tickets
 */
public class VentanaTicketController {
    public static int msegundos = 0;
    public static int segundos = 0;
    public static int minutos = 0;
    public static int horas = 0;

    boolean cambiarColor = false;
    public static boolean detener = false;


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
    public JFXButton btnPausa;

    @FXML
    private Label lblCronometro;

    Cronometro c;

    /**
     * @param event
     * @throws InterruptedException
     */
    @FXML
    void Play(MouseEvent event) throws InterruptedException {

        c = new Cronometro();

        lblCronometro.textProperty().bind(c.messageProperty());

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

    /**
     * @param event
     * @throws InterruptedException
     */
    @FXML
    void PausarCronometro(ActionEvent event) throws InterruptedException {
//        detener = true;

        c.cancel(true);

    }

    /**
     * @param event
     */
    @FXML
    void Pendiente(ActionEvent event) {
        msegundos = 0;
        segundos = 0;
        minutos = 0;
        horas = 0;
        Stage App_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_Stage.close();
    }

    @FXML
    void Resuelto(ActionEvent event) {
        msegundos = 0;
        segundos = 0;
        minutos = 0;
        horas = 0;
        Stage App_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_Stage.close();
    }
}

/**
 * Clase que maneja el hilo para el cronometro de la ventana de los tickets
 */
class Cronometro extends Task<String> {
//    int hora, min, seg, cseg;
//
//    public Cronometro(int h, int m, int s, int cs){
//        this.hora = h;
//        this.min = m;
//        this.seg = s;
//        this.cseg = cs;
//    }


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
        while (true) {
            if (msegundos > 99) {
                msegundos = 0;
                segundos++;
                if (segundos > 59) {
                    segundos = 0;
                    minutos++;
                    if (minutos > 59) {
                        horas++;
                        minutos = 0;
                        if (horas > 90) {
                            return "1";
                        }
                    }
                }
            }
            actualizarCronometro(horas, minutos, segundos, msegundos);
            Thread.sleep(10);
            msegundos++;
        }
    }

    /**
     * @param horas
     * @param minutos
     * @param segundos
     * @param msegundos
     *
     * Actualiza el label en el que se muestra el cronometro
     */
    private void actualizarCronometro(int horas, int minutos, int segundos, int msegundos){
        String h, m, s, ms, colon = " : ", tiempo;
        h = String.valueOf(horas);
        m = String.valueOf(minutos);
        s = String.valueOf(segundos);
        ms = String.valueOf(msegundos);
        if(horas < 10){
            h = "0"+horas;
        }
        if(minutos < 10){
            m = "0"+minutos;
        }
        if(segundos < 10){
            s = "0"+segundos;
        }
        if(msegundos < 10){
            ms = "0"+msegundos;
        }
        tiempo = h + colon + m + colon + s + colon + ms;
        updateMessage(tiempo);
    }
}