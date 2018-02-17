import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serversocket = new ServerSocket(4445);
        Server svr = new Server();
        while(true)
        svr.run();
    }
    public void run() throws Exception {
        ServerSocket serversocket = new ServerSocket(4445);
        Socket sock = serversocket.accept();
        InputStreamReader ir = new InputStreamReader(sock.getInputStream());
        BufferedReader br = new BufferedReader(ir);
        
        String message = br.readLine();
        System.out.println(message);
        if (message != null) {
            PrintStream ps = new PrintStream(sock.getOutputStream());
            ps.println("MESSAGE RECEIVED");
        }
    }
}