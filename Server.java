import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        Server svr = new Server();
        svr.run();
    }
    public void run() throws Exception {
        ServerSocket serversocket = new ServerSocket(5555);
        Socket sock = serversocket.accept();
        InputStreamReader ir = new InputStreamReader(sock.getInputStream());
        BufferedReader br = new BufferedReader(ir);
        while(true) {
            ir = new InputStreamReader(sock.getInputStream());
            br = new BufferedReader(ir);
            System.out.println(br.readLine());
        }
        
        //String message = br.readLine();
        //System.out.println(message);
        //if (message != null) {
        //    PrintStream ps = new PrintStream(sock.getOutputStream());
        //    ps.println("MESSAGE RECEIVED");
        //}
    }
}