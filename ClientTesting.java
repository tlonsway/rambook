import java.io.*;
import java.net.*;
import java.util.*;

public class ClientTesting {
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    public static void main(String[] args) {
        new ClientTesting();
    }
    public ClientTesting() {
        try {
            s = new Socket("127.0.0.1", 8888);
            din = new DataInputStream(s.getInputStream());
           dout = new DataOutputStream(s.getOutputStream());
            listenForInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void listenForInput() {
        Scanner console = new Scanner(System.in);
        while(true) {
            while(console.hasNextLine()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String input = console.nextLine();
            if (input.toLowerCase().equals("end")) {
                break;
            }
            try {
                dout.writeUTF(input);
                while (din.available() == 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String reply = din.readUTF();
                System.out.println(reply);
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            din.close();
            dout.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}