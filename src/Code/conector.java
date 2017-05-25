package Code;

/**
 * Created by Jose Luis Rodriguez on 21/5/2017.
 */

import java.net.*;
import java.io.*;
import java.util.Objects;

public class conector {

    Socket cliente;
    int puerto = 9000;
    String ip = "127.0.0.1";
    DataInputStream entrada, teclado;
    DataOutputStream salida;

    public String[] inicio(String datos){
        try{
            cliente = new Socket(ip, puerto);
            entrada = new DataInputStream(cliente.getInputStream());
            //teclado = new BufferedReader(new InputStreamReader(System.in));
            //String tec = teclado.readLine();
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF(datos);
            String mensaje = entrada.readUTF();
            //System.out.println(mensaje);
            String mensajes[] = mensaje.split(";");
            if(Objects.equals(mensajes[0], "Exito"))
                return mensajes;
            else if (Objects.equals(mensajes[0], "Fail"))
                return mensajes;
            else
                System.out.println("No mando ni exito ni fail");
            entrada.close();
            salida.close();
            teclado.close();
            cliente.close();
            return mensajes;
        }catch (Exception e){
            String error[] = new String[0];
            System.out.println("Ocurrio un problema con la conexion");
            return error;
        }
    }

}

