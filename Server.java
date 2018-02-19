import java.net.*;
import java.io.*;

public class Server {
    ServerSocket ss;
    Socket s;
    BufferedReader din;
    DataOutputStream dout;
    
    public static void main(String[] args) throws Exception {
        new Server();
    }
    public Server() {
        try {
            ss = new ServerSocket(8888);
            s = ss.accept();
            din = new BufferedReader(new InputStreamReader(s.getInputStream()));
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    public void run() throws IOException{
        while(true) {
            String input = (din.readLine());
            int number = -9999;
            String name;
            String action = input.substring(0,input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            String type = input.substring(0, input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            if (input.indexOf(":") != -1) {
                name = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                number = Integer.parseInt(input);
            } else {
                name = input;
            }
            System.out.println("action: " + action + "\ntype: " + type);
            if (number != -9999) {
                System.out.println("number: " + number);
            }
            System.out.println("name: " + name);
            }
    }
}
