import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try (Connector connector = new Connector("10.2.124.19",4000)){
            System.out.println("Connected to server");
            System.out.println(connector.readLine());

            System.out.println("Ip: " + connector.readLine());

            System.out.println(connector.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}