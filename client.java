import java.io.*;
import java.net.*;
import cs1.*;
public class client {
    public static void main(String[] args) throws Exception {
        client client = new client();
        while(true)
        client.run("a:b:tristan"); //a:add, g:get, d:del
                                   //b:bio, n:name, o:online, p:post
                                   //name of user
                                   //if applies, number of data
    }
    public void run(String input) throws Exception {
        Socket sock = new Socket("127.0.0.1", 5555);        
        PrintStream ps = new PrintStream(sock.getOutputStream());
        ps.println("Hello to SERVER from CLIENT");
        
        
        //String message;
        
        String action = input.substring(0,input.indexOf(":"));
        input = input.substring(input.indexOf(":")+1);
        String type = input.substring(0, input.indexOf(":"));
        input = input.substring(input.indexOf(":")+1);
        if (input.indexOf(":") != -1) {
            String name = input.substring(0, input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            int number = Integer.parseInt(input);
        } else {
            String name = input;
        }
        
        
        
        
        //while(true) {
        //    message = Keyboard.readString();
        //    ps.println(message);
        //}
        
        
        
        //InputStreamReader ir = new InputStreamReader(sock.getInputStream());
        //BufferedReader br = new BufferedReader(ir);
        
        //String message = br.readLine();
        //System.out.println(message);
        
    }
}
        