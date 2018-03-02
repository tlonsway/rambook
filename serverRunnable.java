import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import javax.imageio.*;
import java.awt.Image;
import com.maxmind.db.Reader.FileMode;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.DatabaseReader.Builder;
import com.maxmind.geoip2.model.CityResponse;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.Integer;
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
            //g:type:name
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
            String user = line.substring(line.indexOf(":")+1);
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
            //System.out.println("request for the profile picture of " + n);
            if (line.substring(2,3).indexOf("p") != -1) {
                try {
                    System.out.println("request for the profile picture of " + n);
                    sendImage("profiles/" + n + ".png");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            //i:a:p/g:name:url
            if (line.substring(2,3).indexOf("a") != -1) {
                if (line.substring(4,5).indexOf("p") != -1) {
                    String in = line.substring(6);
                    String name = in.substring(0, in.indexOf(":"));
                    String url = in.substring(in.indexOf(":")+1);
                    System.out.println("user " + name + " adding image at " + url);
                    try {
                        addProfile(url, name);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } //Decode and reply to image transmissions
        
        if (line.substring(0,1).indexOf("u") != -1) {
            //u:g:name:number
            String input = (line.substring(line.indexOf(":")+1));
            input = input.substring(input.indexOf(":")+1);
            String name = input.substring(0, input.indexOf(":"));
            String number = input.substring(input.indexOf(":")+1);
            if (line.substring(2,3).indexOf("g") != -1) {
                try {
                    String ret = getPost(name, number);
                    ps.println(ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } //Processes getting a post
            
            if (line.substring(2,3).indexOf("c") != -1) {
                String ret = "";
                try {
                    ret += getNumPosts(name);
                    ps.println(ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } //Processes getting total number of posts
            //u:a:name:subject:content
            if (line.substring(2,3).indexOf("a") != -1) {
                String in = line.substring(4);
                String name1 = in.substring(0, in.indexOf(":"));
                in = in.substring(in.indexOf(":")+1);
                String subject = in.substring(0, in.indexOf(":"));
                in = in.substring(in.indexOf(":")+1);
                String content = in;
                try {
                    addPost(name1, subject, content);
                } catch (Exception e) {
                    e.printStackTrace(); 
                }
            } //Processes adding a post
        } //Processes data management for user posts, returns data per input
        
        if (line.substring(0,1).indexOf("s") != -1) {
            //s:type:content:name
            String input = line.substring(line.indexOf(":")+1);
            String type = input.substring(0, input.indexOf(":"));
            input = input.substring(input.indexOf(":")+1);
            String content = input.substring(0, input.indexOf(":"));
            String name = input.substring(input.indexOf(":")+1);
            try {
                if (type.equals("s")) {
                    System.out.println("setting the status of " + name + " to " + content);
                    setStatus(name, content);
                }
                if (type.equals("n")) {
                    System.out.println("setting the name of " + name + " to " + content);                    
                    setName(name, content);
                }
                if (type.equals("f")) {
                    System.out.println("setting the friends of " + name + " to " + content);   
                    setFriends(name, Integer.parseInt(content));
                }
                if (type.equals("a")) {
                    System.out.println("setting the age of " + name + " to " + content);   
                    setAge(name, Integer.parseInt(content));
                }
                if (type.equals("b")) {
                    System.out.println("setting the bio of " + name + " to " + content);   
                    setBio(name, content);
                }
                System.out.println("database setting complete");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } //Processes data modification for users database
        
        if (line.substring(0,1).indexOf("f") != -1) {
            
            //f:a:name:other
            if (line.substring(2,3).indexOf("a") != -1) {
                String input = line.substring(line.indexOf(":")+1);
                input = input.substring(input.indexOf(":")+1);
                String name = input.substring(0, input.indexOf(":"));
                String other = input.substring(input.indexOf(":")+1);
                try {
                    addFriend(name, other);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
        BufferedWriter po = new BufferedWriter(new FileWriter("posts.txt", true));
        bw.append("\n" + username);
        bw.append("\nstatus:offline");
        bw.append("\nname:" + name);
        bw.append("\nfriends:0");
        bw.append("\nage:" + age);
        bw.append("\nbio:" + bio);
        bw.append("\n}");
        pw.append("\n" + username + ":" + password);
        po.append("\n}" + username + "\n|");
        bw.close();
        pw.close();
        po.close();
        File file = new File("/friends/" + username + ".txt");
        file.createNewFile();
    }
    public void setStatus(String name, String status) throws Exception {
        System.out.println("changing status for user " + name);
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("users" + name + ".txt"));
        boolean b = false;
        String rline;
        while(b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            } else if (rline.equals(name)) {
                bw.write(name);
                bw.newLine();
                bw.write("status:" + status);
                bw.newLine();
                br.readLine();
            } else {
                bw.write(rline);
                bw.newLine();
            }
        }
        bw.close();
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader("users" + name + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
        boolean fo = false;
        boolean f = false;
        String line;
        while(f == false && fo == false) {
            line = reader.readLine();
            if (line == null) {
                f = true;
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();        
        System.out.println("completing file writing for " + name);        
        File fl = new File("users" + name + ".txt");
        fl.delete();
    }
    public void setName(String name, String oname) throws Exception{
        System.out.println("changing name for user " + name);
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("users" + name + ".txt"));
        boolean b = false;
        String rline;
        while(b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            } else if (rline.equals(name)) {
                bw.write(name);
                bw.newLine();
                bw.write(br.readLine()); //reading once to change the name
                bw.newLine();
                bw.write("name:" + oname);
                bw.newLine();
                br.readLine();
            } else {
                bw.write(rline);
                bw.newLine();
            }
        }
        bw.close();
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader("users" + name + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
        boolean fo = false;
        boolean f = false;
        String line;
        while(f == false && fo == false) {
            line = reader.readLine();
            if (line == null) {
                f = true;
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();        
        System.out.println("completing file writing for " + name);        
        File fl = new File("users" + name + ".txt");
        fl.delete();        
    }
    public void setFriends(String name, int friends) throws Exception{
        System.out.println("changing friend count for user " + name);
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("users" + name + ".txt"));
        boolean b = false;
        String rline;
        while(b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            } else if (rline.equals(name)) {
                bw.write(name);
                bw.newLine();
                bw.write(br.readLine()); //reading once to change the name
                bw.newLine();
                bw.write(br.readLine()); //reading twice to change the friend count
                bw.newLine();
                bw.write("friends:" + friends);
                bw.newLine();
                br.readLine();
            } else {
                bw.write(rline);
                bw.newLine();
            }
        }
        bw.close();
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader("users" + name + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
        boolean fo = false;
        boolean f = false;
        String line;
        while(f == false && fo == false) {
            line = reader.readLine();
            if (line == null) {
                f = true;
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();        
        System.out.println("completing file writing for " + name);        
        File fl = new File("users" + name + ".txt");
        fl.delete();          
    }
    public void setAge(String name, int age) throws Exception{
        System.out.println("changing age for user " + name);
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("users" + name + ".txt"));
        boolean b = false;
        String rline;
        while(b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            } else if (rline.equals(name)) {
                bw.write(name);
                bw.newLine();
                bw.write(br.readLine()); //reading once to change the name
                bw.newLine();
                bw.write(br.readLine()); //reading twice to change the friend count
                bw.newLine();
                bw.write(br.readLine()); //reading three times to change the age
                bw.newLine();
                bw.write("age:" + age);
                bw.newLine();
                br.readLine();
            } else if (!rline.equals(name)){
                bw.write(rline);
                bw.newLine();
            }
        }
        bw.close();
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader("users" + name + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
        boolean fo = false;
        boolean f = false;
        String line;
        while(f == false && fo == false) {
            line = reader.readLine();
            if (line == null) {
                f = true;
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();        
        System.out.println("completing file writing for " + name);        
        File fl = new File("users" + name + ".txt");
        fl.delete();                  
    }
    public void setBio(String name, String bio) throws Exception{
        System.out.println("changing bio for user " + name);
        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("users" + name + ".txt"));
        boolean b = false;
        String rline;
        while(b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            } else if (rline.equals(name)) {
                bw.write(name);
                bw.newLine();
                bw.write(br.readLine()); //reading once to change the name
                bw.newLine();
                bw.write(br.readLine()); //reading twice to change the friend count
                bw.newLine();
                bw.write(br.readLine()); //reading three times to change the age
                bw.newLine();
                bw.write(br.readLine()); //reading four times to change the bio
                bw.newLine();
                bw.write("bio:" + bio);
                bw.newLine();
                br.readLine();
            } else {
                bw.write(rline);
                bw.newLine();
            }
        }
        bw.close();
        br.close();
        BufferedReader reader = new BufferedReader(new FileReader("users" + name + ".txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
        boolean fo = false;
        boolean f = false;
        String line;
        while(f == false && fo == false) {
            line = reader.readLine();
            if (line == null) {
                f = true;
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();        
        System.out.println("completing file writing for " + name);        
        File fl = new File("users" + name + ".txt");
        fl.delete();          
    }
    public String userExist(String username) throws Exception{
        System.out.println("Checking if user " + username + " exists");
        BufferedReader br = new BufferedReader(new FileReader("loginlist.txt"));
        String exist = "false";
        String currentLine;
        boolean b = false;
        String rline;
        while (b == false) {
            rline = br.readLine();            
            if (rline == null) {
                b = true;
            }
            if (rline != null && rline.substring(0, rline.indexOf(":")).equals(username)) {
                exist = "true";
                System.out.println("user " + username + " exists");
            } 
        }
        return exist;         
    }
    public void sendImage(String location) throws Exception{
        File fl = new File(location);
        if (!fl.exists()) {
            location = "profiles/default.png";
        }
        File file = new File(location);
        BufferedImage image = ImageIO.read(file);   
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
        System.out.println("reading image from internet location " + url);
        Image image = ImageIO.read(site);
        File of = new File("profiles/" + name + ".png");
        if (of.exists()) {
            of.delete();
        }
        BufferedImage bf = (BufferedImage)(image);
        ImageIO.write(bf, "png", of);
    }
    public void addPost(String name, String subject, String content) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter("posts" + name + ".txt"));
        FileReader fr = new FileReader("posts.txt");
        BufferedReader br = new BufferedReader(fr);
        File database = new File("IPdb.mmdb");
        DatabaseReader dbReader = new DatabaseReader.Builder(database).build();
        String location = "";
        try {
            CityResponse response = dbReader.city(client.getInetAddress());
            String city = response.getCity().getName();
            String country = response.getCountry().getName();
            location = city + ", " + country;
        } catch (Exception e) {
            location = "Unknown";
        }
        boolean b = false;
        boolean bo = false;
        String rline;
        while(b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            }
            if (rline != null && rline.equals("}" + name)) {
                bw.write(rline);
                bw.newLine();
                while (bo == false)  {
                    rline = br.readLine();
                    if (rline.equals("|")) {
                        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
                        Date now = new Date();
                        String strDate = sdfDate.format(now);
                        bw.write("]p" + (getNumPosts(name)+1) + ":" + strDate + ":" + location + ":" + subject + ":" + content); 
                        bw.newLine();
                        bw.write("|");
                        bw.newLine();
                        bo = true;
                    } else {
                        bw.write(rline);
                        bw.newLine();
                    }            
                } 
            } else if(rline != null) {
                bw.write(rline);
                bw.newLine();
                System.out.println("writing " + rline);
            }
            //bw.write(rline);
        }
        System.out.println("Closing post write stream");
        br.close();
        bw.close();
        fr.close();
        //try {
        //    Thread.sleep(1000);
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}
        BufferedWriter writer = new BufferedWriter(new FileWriter("posts.txt"));
        BufferedReader reader = new BufferedReader(new FileReader("posts" + name + ".txt"));
        boolean f = false;
        boolean fo = false;
        String line;
        while(f == false && fo == false) {
            line = reader.readLine();
            if (line == null) {
                f = true;
            } else {
                writer.write(line);
                writer.newLine();
            }
        }
        writer.close();
        reader.close();
        System.out.println("completing file writing for " + name);
        File other = new File("posts" + name + ".txt");
        other.delete();
        //File newpost = new File("posts" + name + ".txt");
        //Paths.get("posts.txt");
        //Paths.get("posts" + name + ".txt");
    }
    public String getPost(String name, String number) throws Exception{
        //returns the entire line of the post
        System.out.println("Getting post number " + number + " from user " + name);
        BufferedReader br = new BufferedReader(new FileReader("posts.txt"));
        boolean b = false;
        boolean bo = false;
        boolean d = false;
        String rline;
        String ret = "";
        while (b == false) {
            rline = br.readLine();
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            }                
            if (rline != null && rline.equals("}" + name)) {
                d = true;
                while (bo == false) {
                    rline = br.readLine();
                    if (rline.equals("|")) {
                        ret = "false";
                        bo = true;
                        b = true;
                    }
                    if (rline.substring(0, rline.indexOf(":")).equals("]p" + number)) {
                        ret = rline;
                        bo = true;
                        b = true;
                    }
                }
            }
        }
        if (!d == true) {
            ret = "false";
        }
        return ret;
    }
    public int getNumPosts(String name) throws Exception{
        System.out.println("Getting the total post count for " + name);
        BufferedReader br = new BufferedReader(new FileReader("posts.txt"));
        String rline;
        String pline = "";
        boolean b = false;
        boolean bo = false;
        int count = 0;
        while(b == false) {
            rline = br.readLine();
            
            if (rline == null) {
                System.out.println("reading line is null");
                b = true;
            }
            if (rline != null && rline.equals("}" + name)) {
                while(bo == false) {
                    rline = br.readLine();
                    if (rline.equals("|")) {
                        if (pline.indexOf("]") == -1) {
                            count = 0;
                            bo = true;
                            b = true;
                        } else {
                            count = Integer.parseInt(pline.substring(pline.indexOf("p")+1, pline.indexOf(":")));
                            bo = true;
                            b = true;
                        }
                    }
                    pline = rline;
                }
            }
        }
        return count;
    }
    public void addFriend(String name, String other) throws Exception{
        File fl = new File("/friends/" + name + ".txt");
        if (!fl.exists()) {
            
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("/friends/" + name + ".txt", true));
        bw.append(other);
    }
    
}