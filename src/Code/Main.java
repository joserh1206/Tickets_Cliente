package Code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXMLs/VentanaPuertoIP.fxml"));
        primaryStage.setTitle("Conectar con el servidor");
        primaryStage.setScene(new Scene(root, 343, 322));
        primaryStage.show();
    }


    public static void main(String[] args) {
        System.out.println("Esperando conexion del servidor...");
        launch(args);
    }
}
