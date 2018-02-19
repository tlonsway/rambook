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
            //din = new BufferedReader(new InputStreamReader(s.getInputStream()));
            System.out.println(din.readLine());
        }
    }
}

    
    
    
    
    
