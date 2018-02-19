import java.io.*;
import java.net.*;
import cs1.*;
public class client {
    Socket sock;    
    PrintStream ps;  
    public static void main(String[] args) throws Exception {
        new client();
        //types:B:bio, F:#friends, S:status
        //
    }
    public client() {
        try {
            sock = new Socket("127.0.0.1", 8888);
            ps = new PrintStream(sock.getOutputStream());
            //ps.println("HELLO");
            //run(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getData(String name, String type, int number) {
        String t = 
        
        
        if (number==0) {
            
       
        } else {
     
        }
        
        

        
        
    }
    public String run(String input) throws Exception {
        String output = "";
        
        ps.println(input);
        

        
        return output;
    }
}
        