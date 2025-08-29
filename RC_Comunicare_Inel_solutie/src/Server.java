import java.io.*;
import java.net.*;

public class Server {
    private int currentPort;
    private RelayNode parinte;

    public Server(int currentPort, RelayNode parinte){
        this.currentPort = currentPort;
        this.parinte = parinte;
    }

    public void start() {
        new Thread( () -> {
            try(ServerSocket serverSocket = new ServerSocket(currentPort)){
                System.out.println("Serverul curent este: " + currentPort);
                while (true){
                    try(Socket clientSocket = serverSocket.accept();
                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
                        String linie = input.readLine();
                        int value = Integer.parseInt(linie);
                        System.out.println("Server pe portul" + currentPort + " este receptionat " + value);
                        parinte.onReceive(value);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}