//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        RelayNode nod1 = new RelayNode(5001, "127.0.0.1", 5002);
        RelayNode nod2 = new RelayNode(5002, "127.0.0.1", 5003);
        RelayNode nod3 = new RelayNode(5003, "127.0.0.1", 5001);

        nod1.start();
        nod2.start();
        nod3.start();

        Thread.sleep(2000);
        Client start = new Client("127.0.0.1", 5001);
        start.send(1);
    }
}