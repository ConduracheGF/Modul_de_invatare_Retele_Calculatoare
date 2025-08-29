import java.io.*;
import java.net.*;

public class Client {
    private String adresaIP;
    private int nextPort;

    public Client(String adresaIP, int nextPort){
        this.adresaIP = adresaIP;
        this.nextPort = nextPort;
    }

    public void send(int value){
        try (Socket socket = new Socket(adresaIP, nextPort);
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
            output.println(value);
            System.out.println("Clientul a dat: " + value + " catre " + nextPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
