import java.io.*;
import java.net.*;
import cs1.*;
public class client {
    public static void main(String[] args) throws Exception {
        client client = new client();
        while(true)
        client.run();
    }
    public void run() throws Exception {
        Socket sock = new Socket("127.0.0.1", 5555);        
        PrintStream ps = new PrintStream(sock.getOutputStream());
        ps.println("Hello to SERVER from CLIENT");
        String message;
        while(true) {
            message = Keyboard.readString();
            ps.println(message);
        }
        
        
        
        //InputStreamReader ir = new InputStreamReader(sock.getInputStream());
        //BufferedReader br = new BufferedReader(ir);
        
        //String message = br.readLine();
        //System.out.println(message);
        
    }
}
        