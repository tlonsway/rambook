import java.io.*;
import java.net.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.imageio.*;
public class serverRunnable implements Runnable{
    BufferedReader din;
    Socket client;
    PrintStream ps;
    OutputStream os;
    public serverRunnable(Socket clientsocket) {
        client = clientsocket;
        try {
            ps = new PrintStream(client.getOutputStream());
            os = client.getOutputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        String line = "";
        try {
            din = new BufferedReader(new InputStreamReader(client.getInputStream()));               
            line = din.readLine();
            System.out.println("Message recieved: " + line + "|");            
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (line.substring(0,1).indexOf("g") != -1) { 
            //
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
            //System.out.println("got this far");
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
                String bio = input.substring(0, input.indexOf(":"));
                input = input.substring(input.indexOf(":")+1);
                String password = input;
                System.out.println("Adding user " + username);
                try {
                    addUser(username, name, Integer.parseInt(age), bio, password);
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
            System.out.println("received username: " + username);
            System.out.println("received password: " + hash);
            try {
                ret = checkPassword(username, hash);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.println(ret);
        } //Decode and reply to password checks
        
        if (line.substring(0,1).indexOf("e") != -1) {
            //format - e:username
            String user = line.substring(line.indexOf(":"));
            String ret = "";
            try {
                ret = userExist(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ps.println(ret);  
        } //Decode and reply to user checks
        
        if (line.substring(0,1).indexOf("i") != -1) {
            //i:type:name/number
            String input = line.substring(line.indexOf(":")+1);
            String type = input.substring(0, input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            String n = input;
            System.out.println("request for the profile picture of " + n);
            if (line.substring(2,3).indexOf("p") != -1) {
                try {
                    System.out.println("request for the profile picture of " + n);
                    sendImage("profiles/" + n + ".png");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } //Decode and reply to image requests
        
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
    public void addUser(String username, String name, int age, String bio, String password) throws Exception{
        BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
        BufferedWriter pw = new BufferedWriter(new FileWriter("loginlist.txt", true));
        bw.append("\n" + username);
        bw.append("\nstatus:offline");
        bw.append("\nname:" + name);
        bw.append("\nfriends:0");
        bw.append("\nage:" + age);
        bw.append("\nbio:" + bio);
        bw.append("\n}");
        pw.append("\n" + username + ":" + password);
        bw.close();
        pw.close();
    }
    public void setStatus() {
        
    }
    public String userExist(String username) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("loginlist.txt"));
        String exist = "false";
        String currentLine;
        while ((currentLine = br.readLine()) != null) {
            if (currentLine.substring(0, currentLine.indexOf(":")).equals(username))
                exist = "true";
        }
        return exist;         
    }
    public void sendImage(String location) throws Exception{
        BufferedImage image = ImageIO.read(new File(location));        
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();        
        ImageIO.write(image, "png", byteArrayOutputStream);
        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
        os.write(size);
        os.write(byteArrayOutputStream.toByteArray());
        os.flush();
        System.out.println("Flushed: " + System.currentTimeMillis());        
    }
    public void addProfile(String url, String name) throws Exception {
        URL site = new URL(url);
        BufferedImage image = ImageIO.read(site);        
        
    }
    
}