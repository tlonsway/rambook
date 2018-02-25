import javax.swing.*;
import java.awt.*;
import org.apache.commons.text.*;
import org.apache.commons.lang3.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
public class display extends JPanel
{
    String view = "";
    JTextField UserName;
    JTextField password;
    JTextField checkpassword;
    
    JTextField age;
    JTextField bio;
    JTextField name;
    
    JTextField UserChoice;
    
    JTextField searchfield;
    
    JButton enter;
    JButton signUp;
    JButton search;
    JButton signout;
    JButton message;
    
    String online = "";
    String Name = "";
    String aboutMe = "";
    int numberOfFriends = 0;
    boolean isValidUsername = false;
    boolean enterClicked = false;
    boolean passwordsMatch = true;
    boolean failedLogin = false;
    boolean InvalidSearch = false;
    public void drawing()
    {
        repaint();
    }
    public void setUserinfo(String bop,String name,String bio, int numFriends )
    {
        online = bop;
        Name = name;
        aboutMe = bio;
        numberOfFriends = numFriends;
    }
    public void setTextField(JTextField U, int i)
    {
        if(i == 1)
        {
            UserName = U;
        }
        else if(i == 2)
        {
            password = U;
        }
        else if(i == 3)
        {
            checkpassword = U;
        }
        else if(i == 4)
        {
            age = U;
        }
        else if(i == 5)
        {
            bio = U;
        }
        else if(i == 6)
        {
            name = U;
        }
        else if(i == 7)
        {
            searchfield = U;
        }
        else if(i == 8)
        {
            UserChoice = U;
        }
    }
    public void setPasswordMatch(boolean i)
    {
        passwordsMatch = i;
    }
    public void setFailedLogin(boolean i)
    {
        failedLogin = i;
    }
    public void setView(String v)
    {
        view = v;
    }
    public String getUsername()
    {
        return UserName.getText();
    }
    public String getPassword()
    {
        return password.getText();
    }
    public void setEnterButton(JButton g)
    {
        enter = g;
    }
    public void setSignUpButton(JButton g)
    {
        signUp = g;
    }
    public void setSearchButton(JButton g)
    {
        search = g;
    }
    public void setSignOutButton(JButton g)
    {
        signout = g;
    }
    public void setMessageButton(JButton g)
    {
        message = g;
    }
    public void setSignUpValid(boolean valid, boolean clicked)
    {
        isValidUsername = valid;
        enterClicked = clicked;
    }   
    public String getUserChoice()
    {
        return UserChoice.getText();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(view.equals("login"))
        {
            
            g.setColor(Color.RED);
            g.fillRect(0,0,1920,1020);
            g.setColor(Color.WHITE);
            g.fillRect(480,255,960,510);
            g.setColor(Color.BLACK);
            g.drawRect(480,255,960,510);
            g.setColor(Color.BLACK);
            JLabel Wordz = new JLabel();
            Font f = new Font("Username: ", Font.BOLD, 20);
            Wordz.setSize(10,20);
            g.setFont(f);
            g.drawString(f.getName(), 850, 350);
            g.drawString("Login",500, 310);
            g.drawString("Password: ", 850, 550);
            
            
            g.drawRect(850,400,100,20);
            g.drawRect(850,600,100,20);
            g.setColor(Color.WHITE);
            g.drawString("RAMBOOK", 900, 200);
            
            signUp.setText("Sign Up");
            enter.setText("ENTER");
            g.setColor(Color.BLACK);
            g.drawRect(1000,475,100,50);
            g.drawRect(1000,575,100,50);
            
            
            if(failedLogin == true)
            {
                g.setColor(Color.RED);
                g.drawString("Incorrect Username or Password",500, 500);
            }
            checkpassword.setVisible(false);
            age.setVisible(false);
            search.setVisible(false);
            searchfield.setVisible(false);
            signout.setVisible(false);
            bio.setVisible(false);
            name.setVisible(false);
            message.setVisible(false);
            UserChoice.setVisible(false);
            
            UserName.setBounds(850, 400, 100, 20);
            password.setBounds(850, 600, 100, 20);
            UserName.setEditable(true);
            password.setEditable(true);
            UserName.setVisible(true);
            password.setVisible(true);
            
            enter.setBounds(1000, 475, 100,50);
            signUp.setBounds(1000, 575, 100,50);            
            enter.setVisible(true);
            signUp.setVisible(true);
            
            Font x = new Font("",Font.PLAIN, 15);
            g.setFont(x);
            
            g.drawString("SignUp",1015,600);
        }
        else if(view.equals("home screen"))
        {
            UserName.setVisible(false);
            password.setVisible(false);
            //signout.setVisible(true);
            //searchfield.setVisible(true);
            //search.setVisible(true);
            searchfield.setEditable(true);
            signUp.setVisible(false);
            enter.setVisible(false);
            g.setColor(new Color(239,100,50));
            g.fillRect(0,0, 1920, 1020);
            g.setColor(Color.BLACK);
            //g.fillRect(10,10,200,200);
            BufferedImage profile;
            try {
                //profile = ImageIO.read(new File("default.png"));
                System.out.println("preparing to get profile image for " + Name);
                profile = (new client()).getProfile(Name);                
                g.drawImage(resize(profile, 200, 200), 10, 10, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Font f = new Font("", Font.BOLD, 20);
            Font biofont = new Font("", Font.ROMAN_BASELINE, 18);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString("Name: ", 250, 25);
            g.drawString(Name, 325, 25);
            g.drawString("About Me: ", 250, 95);
            String bio = WordUtils.wrap(aboutMe, 40);
            System.out.println(bio);
            int count = 120;
            int last = 0;
            bio += "\n";
            g.setFont(biofont);
            g.setColor(new Color(209, 193, 190));
            for (int i=0; i<bio.length(); i++) {
                if (bio.substring(i,i+1).indexOf("\n") != -1) {
                    g.drawString(bio.substring(last, i),250,count);          
                    count += 30;         
                    last = i;
                }
            }
            g.setFont(f);
            if(InvalidSearch == true)
            {
                g.setColor(Color.RED);
                g.drawString("User Doesn't Exist", 800, 170);
            }
            g.setColor(Color.WHITE);
            
            g.drawString("Number Of Friends:", 250, 60);
            String temp = "" + numberOfFriends;
            g.drawString(temp, 440, 60);
            g.drawString("Current Status: ", 10, 250);
            g.drawString(online, 175, 250);
            g.setColor(Color.WHITE);
            //g.drawLine(960, 0, 960, 1020);
            
            
            search.setBounds(800,150,100,50);
            g.fillRect(800,150,100,50);
            search.setVisible(true);
            g.drawString("View another user's page", 800,100);
            searchfield.setBounds(800,125,100,20);
            g.fillRect(800,125,100,20);
            searchfield.setVisible(true);
            g.fillRect(800,125,100,20);
            searchfield.setEditable(true);
            
            //g.drawRect(400,125,100,50);
    
            signout.setBounds(1200,100,100,50);
            signout.setText("Sign out");
            signout.setVisible(true);
            
            message.setBounds(10, 275, 100, 50);
            message.setText("Message");
            message.setVisible(true);
            
            UserChoice.setVisible(true);
            UserChoice.setEditable(true);
            
            g.fillRect(1200,100,100,50);
        }
        else if(view.equals("sign up"))
        {
            message.setVisible(false);
            UserChoice.setVisible(false);
            g.setColor(Color.RED);
            g.fillRect(0,0,1920, 1020);
            g.setColor(Color.WHITE);
            g.fillRect(480,255,960,510);
            g.setColor(Color.BLACK);
            g.drawRect(480,255,960,510);
            Font f = new Font("", Font.BOLD, 20);
            g.setFont(f);
            g.drawString("Sign Up",500, 310);
            JLabel Wordz = new JLabel();
            Wordz.setSize(10,20);
            g.drawString("Username: ", 850, 350);
            g.drawString("Password: ", 800, 550);
            g.drawString("Retype Password: ", 1000 , 550);
            
            UserName.setBounds(850, 400, 100, 20);
            password.setBounds(800, 600, 100, 20);
            checkpassword.setBounds(1000, 600, 100, 20);
            
            g.drawString("Name: ", 600, 375);
            g.drawString("Age: ", 600, 475);
            g.drawString("Bio: ", 600, 625);
            
            
            name.setBounds(600,400,100,20);
            age.setBounds(600,500,100,20);
            bio.setBounds(600,650,200,40);
            
            UserName.setEditable(true);
            password.setEditable(true);
            
            name.setEditable(true);
            bio.setEditable(true);
            age.setEditable(true);
            
            
            UserName.setVisible(true);
            password.setVisible(true);
            
            name.setVisible(true);
            bio.setVisible(true);
            age.setVisible(true);
            
            
            g.drawRect(850,400,100,20);
            g.drawRect(800,600,100,20);
            g.drawRect(1000,600,100,20);
            
            g.drawRect(600,400,100,20);
            g.drawRect(600,500,100,20);
            g.drawRect(600,650,200,40);
            
            
            enter.setBounds(1000, 475, 100,50);
            enter.setText("ENTER");
            signUp.setBounds(1000,400,100,50);
            signUp.setText("BACK");
            g.drawRect(1000,400,100,50);
            g.drawRect(1000,475,100,50);
            
            g.setColor(Color.WHITE);
            g.drawString("RAMBOOK", 900, 200);
            if(passwordsMatch == false)
            {
                g.setColor(Color.RED);
                g.drawString("Passwords Don't Match",550,620);
            }
            if(isValidUsername == false && enterClicked == true)
            {
                g.setColor(Color.RED);
                g.drawString("Username Already Taken",600,400);
            }
           
        }
    }
    public String getSearchfieldText()
    {
        return searchfield.getText();
    }
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }      
}