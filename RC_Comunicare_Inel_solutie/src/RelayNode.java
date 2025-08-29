import java.io.*;
import java.net.*;

public class RelayNode {
    private Server server;
    private Client client;

    public RelayNode(int currentPort, String adresaIP, int nextPort){
        this.server = new Server(currentPort, this);
        this.client = new Client(adresaIP, nextPort);
    }

    public void start() {
        server.start();
    }

    public void onReceive(int value){
        if(value<100){
            value++;
            client.send(value);
        } else {
            System.out.println("RelayNode: valoarea finala este: " + value);
        }
    }
}