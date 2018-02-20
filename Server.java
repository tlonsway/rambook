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
    public String run() throws IOException{
        while(true) {
            String line = din.readLine();
            if (line.indexOf(":") != -1) { 
                System.out.println("Message recieved: " + line + "|");
                String input = line;
                int number = -9999;
                String name;
                
                String action = input.substring(0,input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String type = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                if (input.indexOf(":") != -1) {
                    name = input.substring(0, input.indexOf(":"));
                    input = input.substring(input.indexOf(":")+1);
                    number = Integer.parseInt(input);
                } else {
                    name = input;
                }
                System.out.println("action: " + action + "\ntype: " + type);
                if (number != -9999) {
                    System.out.println("number: " + number);
                }
                System.out.println("name: " + name);
                String ret = "";
                if (action.equals("g")) {
                    try {
                        ret = getData(name, type);
                        System.out.println(ret);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                s = ss.accept();
                din = new BufferedReader(new InputStreamReader(s.getInputStream()));                
                return ret;
            }
        }
    }
    public String getData(String name, String type) throws Exception{
	FileReader fr = new FileReader("users.txt");
	BufferedReader br = new BufferedReader(fr);
        String line;
	int amt;
        while(!br.readLine().equals(name)) {
	}
	if (type.equals("status")) {
	    amt = 0;
	} else 
	if (type.equals("name")) {
	    amt = 1;
	} else
	if (type.equals("friends")) {
	    amt = 2;
	} else
	if (type.equals("age")) {
	    amt = 3;
	} else {
	    amt = 4;
	}
	for (int i=0; i<=amt-1; i++) {
	    br.readLine();
	}
	line = br.readLine();
	System.out.println("line: " + line);
	String ret = line.substring(line.indexOf(":")+1);
	return ret;
    }
}
