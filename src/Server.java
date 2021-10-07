import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(4000)){
            System.out.println("Server Started");

            int counter = 0;
            Scanner reader = new Scanner(System.in);

            Map<String,Integer> dictionary = new HashMap<String,Integer>();

            File file = new File ("Ip.txt");

            /*FileReader fr = new FileReader(file);
            BufferedReader file_reader = new BufferedReader(fr);

            String line = "";

            do{
                line = file_reader.readLine();

                if (line != null){
                    dictionary.put(line, 0);
                    System.out.println(line);
                }
            }
            while (line != null);*/

            while(true){
                try (Connector connector = new Connector(server)){

                    System.out.println("Client ID: " + counter);

                    counter++;

                    if (dictionary.containsKey(connector.getSocket().getInetAddress().toString())){
                        dictionary.put(connector.getSocket().getInetAddress().toString(), dictionary.get(connector.getSocket().getInetAddress().toString())+1);
                    }
                    else {
                        dictionary.put(connector.getSocket().getInetAddress().toString(), 1);
                    }

                    if(dictionary.get(connector.getSocket().getInetAddress().toString()) > 3){
                        connector.writeLine("IT IS BUN!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        System.out.println("BUN");
                        System.out.println(connector.getSocket().getInetAddress());
                        connector.close();
                        continue;
                    }
                    else {
                        connector.writeLine("Hello!");
                        System.out.println("Hello!");
                        connector.writeLine(connector.getSocket().getInetAddress().toString());
                        System.out.println(connector.getSocket().getInetAddress());
                    }

                    FileWriter fos = new FileWriter("Ip.txt", false);

                    String ip = dictionary.toString() + "\n";

                    fos.write(ip);
                    fos.flush();
                    connector.close();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}