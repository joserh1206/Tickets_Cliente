package Code;

/**
 * Created by Jose Luis Rodriguez on 21/5/2017.
 */

import javafx.scene.control.Alert;

import java.net.*;
import java.io.*;
import java.util.Objects;

/**
 * Clase que controla el socket
 */
public class conector {

    static Socket cliente;
    int puerto;
    String ip;
    public static DataInputStream entrada;
    public static DataOutputStream salida;

    /**
     * @constructor
     * @param puerto
     * @param ip
     */
    public conector(int puerto, String ip){
        this.puerto = puerto;
        this.ip = ip;
    }

    /**
     * @return
     */
    public boolean conectar(){
        try{
            cliente = new Socket(ip, puerto);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @throws IOException
     */
    public void iniciar() throws IOException {
        entrada = new DataInputStream(cliente.getInputStream());
        salida = new DataOutputStream(cliente.getOutputStream());
        System.out.println("Conexion establecida exitosamente!");
    }

    /**
     * @param datos
     * @return
     */
    public String[] inicio(String datos){
        try{
            String mensaje;
            salida.writeUTF(datos);
            mensaje = entrada.readUTF();

            /*entrada.close();
            salida.close();
            teclado.close();
            cliente.close();*/
            return mensaje.split(";");
        }catch (Exception e){
            String error[] = new String[0];
            System.out.println("Ocurrio un problema con la conexion");
            return error;
        }
    }

}

