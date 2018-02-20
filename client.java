import java.io.*;
import java.net.*;
import cs1.*;
public class client {
    Socket sock;    
    PrintStream ps;
    BufferedReader din;    
    public static void main(String[] args) throws Exception {
        new client();
        //types:B:bio, F:#friends, S:status
        //
    }
    public client() {
        try {
            sock = new Socket("192.168.1.17", 8888);
            ps = new PrintStream(sock.getOutputStream());
            din = new BufferedReader(new InputStreamReader(sock.getInputStream()));
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
        String get = "";
        try {
            get = run(ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("data recieved: " + get);
        return get;
    }
    public String run(String input) throws Exception {
        String output = "";
        ps.println(input);
        String line = "";
        boolean temp = false;
        while(temp == false) {
            line = din.readLine();
            if (line != null) 
                temp = true;
        }
        //String line = din.readLine(); 
        return line;
    }
}
        