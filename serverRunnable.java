import java.io.*;
import java.net.*;

public class serverRunnable implements Runnable{
    BufferedReader din;
    Socket client;
    PrintStream ps;
    public serverRunnable(Socket clientsocket) {
        client = clientsocket;
        try {
            ps = new PrintStream(client.getOutputStream());     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        String line = "";
        try {
            din = new BufferedReader(new InputStreamReader(client.getInputStream()));               
            line = din.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (line.substring(0,1).indexOf("g") != -1) { 
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
            try {
                ps.println(new String());
                ps.println(ret);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } //Decoding get requests, grabbing data, and replying
        
        if (line.substring(0,1).indexOf("a") != -1) {
            //format - a:u:username:name:age:bio
            String input = line;
            if (line.substring(2,3).indexOf("u") != -1) {
                String action = input.substring(0,input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String type = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String username = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String name = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String age = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String bio = input;
                try {
                    addUser(username, name, Integer.parseInt(age), bio);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }   
        } //Decoding post tranmissions
        
        if (line.substring(0,1).indexOf("p") != -1) {
            //format - p:username:hash
            String input = line.substring(line.indexOf(":")+1);
            String username = input.substring(0, input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            String hash = input;
            String ret = "";
            try {
                ret = checkPassword(username, hash);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.println(ret);
        } //Decode and reply to password checks
    }
    public String getData(String name, String type) throws Exception{
        FileReader fr = new FileReader("users.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        int amt;
        while(!br.readLine().equals(name)) {
        }
        if (type.equals("s")) {
            amt = 0;
        } else 
        if (type.equals("n")) {
            amt = 1;
        } else
        if (type.equals("f")) {
            amt = 2;
        } else
        if (type.equals("a")) {
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
    public String checkPassword(String name, String hash) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("loginlist.txt"));
        String currentLine;
        String user;
        String pass;
        while ((currentLine = br.readLine()) != null) {
            if (currentLine.substring(0, currentLine.indexOf(":")).equals(name)) {
                if (currentLine.substring(currentLine.indexOf(":")+1).equals(hash)) {
                    return "true"; //returns true if the given user:pass is valid
                }
            }
        }
        return "false"; //returns false if the given user:pass is invalid
    }
    public void addUser(String username, String name, int age, String bio) throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
        bw.append(username);
        bw.append("\nstatus:offline");
        bw.append("\nname:" + name);
        bw.append("\nfriends:0");
        bw.append("\nage:" + age);
        bw.append("\nbio:" + bio);
        bw.append("\n}");
        bw.close();
    }
    public void setStatus() {
        
    }
}