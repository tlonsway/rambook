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
                ret = run("g:" + t + ":" + name);
                System.out.println("Data sent - g:" + t + ":" + name);
            } else {
                ret = run("g" + t + ":" + name + ":" + number);
                System.out.println("Data sent - g:" + t + ":" + name + ":" + number);         
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            run(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String run(String input) throws Exception {
        String output = "";
        ps.println(input);
        return output;
    }
}
        