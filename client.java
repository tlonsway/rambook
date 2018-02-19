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
    public String getData(String name, String type, int number) {
        String t = type.toLowerCase().substring(0, 1);
        String ret = "";
        try {
            if (number==0) {
                ret = run("g:" + name + ":" + type);
                
            } else {
                ret = run("g" + name + ":" + type + ":" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
    public String run(String input) throws Exception {
        String output = "";
        ps.println(input);
        

        
        return output;
    }
}
        