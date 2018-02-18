import java.net.*;
import java.io.*;

public class Server {
    ServerSocket ss;
    Socket s;
    DataInputStream din;
    DataOutputStream dout;
    
    public static void main(String[] args) throws Exception {
        new Server();     
    }
    public Server() {
        try {
            ss = new ServerSocket(8888);
            s = ss.accept();
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            //listenForData();
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
    // public void listenForData() {
        // while (true) {
            // try {
                // while (din.available() == 0) {
                    // try {
                        // Thread.sleep(1);
                    // } catch (InterruptedException e) {
                        // e.printStackTrace();
                    // }
                // }
                    
                
            // }
        
        // }
    }

    
    
    
    
    
