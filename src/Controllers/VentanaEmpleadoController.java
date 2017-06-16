package Controllers;

/**
 * Created by Jose Luis Rodriguez on 20/5/2017.
 */

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

import static Code.conector.entrada;
import static Code.conector.salida;

public class VentanaEmpleadoController {

    @FXML
    private JFXButton btnActualizar;

    @FXML
    private JFXTreeTableView<TicketsRecibidos> ttvTickets;

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

    String mensaje;
    public String Ticket[];
    public String datosTicket[];
    ObservableList<TicketsRecibidos> ticketsRecibidos = FXCollections.observableArrayList();

    /**
     * @param event
     */
    @FXML
    void ActualizarTickets(ActionEvent event) throws IOException {
        ticketsRecibidos.clear();
        salida.writeUTF(LoginController.mensaje[1]+";Actualizar");
        mensaje = entrada.readUTF();
//        System.out.println(mensaje+" Recibido");
        Ticket = mensaje.split(";");
        for(int i=0; i<Ticket.length; i++){
//            System.out.println(Ticket[0]+" 0");
            datosTicket = Ticket[i].split(":");
//            System.out.println(datosTicket[0]+" 0:");
//            System.out.println(datosTicket[1]+" 1:");
//            System.out.println(datosTicket[2]+" 2:");
            TicketsRecibidos t = new TicketsRecibidos(datosTicket[0], datosTicket[1], datosTicket[2]);
            ticketsRecibidos.add(t);
        }
        mostrarTabla();

    }

    /**
     * @param event
     * @throws IOException
     */
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

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void SolicitarReporte(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXMLs/VentanaEstadisticas.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Estadisticas");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    void Desconectar(ActionEvent event) throws IOException {
        String msj = LoginController.mensaje[2]+";Desconectar";
        salida.writeUTF(msj);
        entrada.close();
        salida.close();
        Stage App_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        App_Stage.close();
    }
    public void mostrarTabla(){
        ttvTickets.refresh();
        JFXTreeTableColumn<TicketsRecibidos, String> colAsunto = new JFXTreeTableColumn<>("Asunto");
        colAsunto.setPrefWidth(150);
        colAsunto.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TicketsRecibidos, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TicketsRecibidos, String> param) {
                return param.getValue().getValue().Asunto;
            }
        });
        JFXTreeTableColumn<TicketsRecibidos, String> colIDCliente = new JFXTreeTableColumn<>("ID CLiente");
        colIDCliente.setPrefWidth(150);
        colIDCliente.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TicketsRecibidos, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TicketsRecibidos, String> param) {
                return param.getValue().getValue().IDCliente;
            }
        });
        JFXTreeTableColumn<TicketsRecibidos, String> colIDTicket = new JFXTreeTableColumn<>("ID Ticket");
        colIDTicket.setPrefWidth(150);
        colIDTicket.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<TicketsRecibidos, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<TicketsRecibidos, String> param) {
                return param.getValue().getValue().IDTicket;
            }
        });

        final TreeItem<TicketsRecibidos> root = new RecursiveTreeItem<TicketsRecibidos>(ticketsRecibidos,
                RecursiveTreeObject::getChildren);

        ttvTickets.getColumns().setAll(colAsunto, colIDCliente, colIDTicket);
        ttvTickets.setRoot(root);
        ttvTickets.setShowRoot(false);
        ttvTickets.autosize();
    }
}

class TicketsRecibidos extends RecursiveTreeObject<TicketsRecibidos> {

    public StringProperty Asunto;
    public StringProperty IDCliente;
    public StringProperty IDTicket;

    public TicketsRecibidos (String asunto, String idcliente, String idticket){
        this.Asunto = new SimpleStringProperty(asunto);
        this.IDCliente = new SimpleStringProperty(idcliente);
        this.IDTicket = new SimpleStringProperty(idticket);
    }

}
