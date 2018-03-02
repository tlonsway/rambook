import javax.swing.*;
import java.awt.*;
import org.apache.commons.text.*;
import org.apache.commons.lang3.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.util.*;
public class display extends JPanel
{
    public display(){
        super();
        setLayout(null);
    }
    String view = "";
    JTextField UserName;
    JTextField password;
    JTextField checkpassword;

    JTextField age;
    JTextField bio;
    JTextField name;

    JTextField UserChoice;

    JTextField searchfield;

    JTextField subjectPost;
    JTextField contentPost;

    JButton enter;
    JButton signUp;
    JButton search;
    JButton signout;
    JButton message;
    JButton addapost;
    JButton settings;
    JButton addfriend;
    JButton removefriend;
    
    String online = "";
    String Name = "";
    String aboutMe = "";
    String UserSignedIn = "";
    String userView = "";
    String UsernameSignedIn = "";
    String currentUser = "";
    int numberOfFriends = 0;
    boolean isValidUsername = false;
    boolean enterClicked = false;
    boolean passwordsMatch = true;
    boolean failedLogin = false;
    boolean InvalidSearch = false;
    boolean addPost = false;
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
            UserName = U;
        else if(i == 2)
            password = U;
        else if(i == 3)
            checkpassword = U;
        else if(i == 4)
            age = U;
        else if(i == 5)
            bio = U;
        else if(i == 6)
            name = U;
        else if(i == 7)
            searchfield = U;
        else if(i == 8)
            UserChoice = U;
        else if(i == 9)
            subjectPost = U;
        else if(i == 10)
            contentPost = U;
    }
    
    public void setSettingsButton(JButton u)
    {
        settings = u;
    }
    
    public void setAddFriendButton(JButton u)
    {
        addfriend = u;
    }
    
    public void setAddPostButton(JButton u)
    {
        addapost = u;
    }

    public void setAddPost(boolean i)
    {
        addPost = i;
    }

    public void setUserSignedIn(String s)
    {
        UserSignedIn = s;
    }

    public String getUserSignedIn()
    {
        return UserSignedIn;
    }
    
    public void setRemoveFriendButton(JButton u)
    {
        removefriend = u;
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

    public void setUserView(String s)
    {
        userView = s;
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
    
    public void setCurrentUser(String s)
    {
        currentUser = s;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawRect(50, 50, 50, 50);
        if(view.equals("login"))
        {
            
            g.setColor(Color.RED);
            g.fillRect(0,0,1920,1020);
            g.setColor(Color.WHITE);
            g.fillRect(480,255,960,510);
            g.setColor(Color.BLACK);
            g.drawRect(480,255,960,510);
            g.setColor(Color.BLACK);
            //JLabel Wordz = new JLabel();
            Font f = new Font("Username: ", Font.BOLD, 20);
            //Wordz.setSize(10,20);
            g.setFont(f);
            g.drawString(f.getName(), 850, 350);
            g.drawString("Login",500, 310);
            g.drawString("Password: ", 850, 550);

            g.drawRect(850,400,100,20);
            g.drawRect(850,600,100,20);
            g.setColor(Color.WHITE);
            g.drawString("RAMBOOK", 900, 200);

            
            g.setColor(Color.BLACK);
            g.drawRect(1000,475,100,50);
            g.drawRect(1000,575,100,50);

            if(failedLogin == true)
            {
                g.setColor(Color.RED);
                g.drawString("Incorrect Username or Password",500, 500);
            }
            Font x = new Font("",Font.PLAIN, 15);
            g.setFont(x);

            g.drawString("SignUp",1015,600);
        }
        else if(view.equals("home screen"))
        {
            
            //signout.setVisible(true);
            //searchfield.setVisible(true);
            //search.setVisible(true);
            
            g.setColor(new Color(239,100,50));
            g.fillRect(0,0, 1920, 1020);
            g.setColor(Color.BLACK);
            //g.fillRect(10,10,200,200);
            
            BufferedImage profile;
            try {
                //profile = ImageIO.read(new File("default.png"));
                System.out.println("preparing to get profile image for " + Name);
                profile = (new client()).getProfile(Name);      
                System.out.println("Grabbed " + Name + "'s profile");
                g.drawImage(resize(profile, 200, 200), 10, 10, null);
            } catch (Exception e) {
                drawing();
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
            g.drawLine(960,0, 960, 1020);
            g.drawString("Number Of Friends:", 250, 60);
            String temp = "" + numberOfFriends;
            g.drawString(temp, 440, 60);
            g.drawString("Current Status: ", 10, 250);
            g.drawString(online, 175, 250);
            g.setColor(Color.WHITE);
            //g.drawLine(960, 0, 960, 1020);
            Font x = new Font("", Font.PLAIN, 20);
            int numPosts = 0;
            try {
                numPosts = new client().getNumPosts(userView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String post = "";
            String date;
            String location;
            String subject; 
            String content;
            int index;
            g.setFont(x);
            int X = 1000;
            int Y = 50;
            g.setColor(Color.BLACK);
            int modNumPosts = numPosts-6;
            if (modNumPosts<1) {
                modNumPosts = 1;
            }
            
            for(int i = numPosts; i >= (modNumPosts); i--)
            {
                try {
                    post = new client().getPost(Name, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Post received: " + post);
                
                String in = post.substring(post.indexOf(":")+1);
                date = in.substring(0, in.indexOf(":"));
                in = in.substring(in.indexOf(":")+1);
                location = in.substring(0, in.indexOf(":"));
                in = in.substring(in.indexOf(":")+1);
                subject = in.substring(0, in.indexOf(":"));
                in = in.substring(in.indexOf(":")+1);
                content = in;

                g.drawString("Subject: " + subject,X,Y);
                Y += 25;
                g.drawString("Content: " + content,X,Y);
                Y += 25;
                g.drawString("Post Date: " + date, X, Y);
                Y += 25;
                g.drawString("Post Location: " + location, X, Y);
                Y += 25;
                g.setColor(Color.WHITE);
                g.drawLine(X,Y, 1920, Y);
                g.setColor(Color.BLACK);
                Y += 25;
            }
            //---- Add post
            
            g.setColor(Color.WHITE);
            g.drawString("Post Topic", 10, 500);
            g.drawString("Post Content",10,570);
            
            g.setColor(Color.BLACK);
            
            g.drawString("View another user's page",10,375);
            g.drawString("Message another user",10,275);
        }
        else if(view.equals("sign up"))
        {
            g.setColor(Color.RED);
            g.fillRect(0,0,1920, 1020);
            g.setColor(Color.WHITE);
            g.fillRect(480,255,960,510);
            g.setColor(Color.BLACK);
            g.drawRect(480,255,960,510);
            Font f = new Font("", Font.BOLD, 20);
            g.setFont(f);
            g.drawString("Sign Up",500, 310);
            //JLabel Wordz = new JLabel();
            //Wordz.setSize(10,20);
            g.drawString("Username: ", 850, 350);
            g.drawString("Password: ", 800, 550);
            g.drawString("Retype Password: ", 1000 , 550);

            
            g.drawString("Name: ", 600, 375);
            g.drawString("Age: ", 600, 475);
            g.drawString("Bio: ", 600, 625);

            g.drawRect(850,400,100,20);
            g.drawRect(800,600,100,20);
            g.drawRect(1000,600,100,20);

            g.drawRect(600,400,100,20);
            g.drawRect(600,500,100,20);
            g.drawRect(600,650,200,40);

            
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
        else if(view.equals("settings"))
        {
            g.setColor(Color.RED);
            g.fillRect(0,0,1920,1080);
            g.setColor(Color.WHITE);
            g.fillRect(480,270,960,510);
            g.setColor(Color.BLACK);
            g.drawRect(480,270,960,510);   
            Font f = new Font("", Font.BOLD, 20);
            g.setFont(f);
            g.drawString("Settings",500,310);
            g.drawString("Current Name",550,375);
            g.drawString("Current bio",550,475);
            g.drawString("Current age",670,375);
        }
    }
    public void setButtons()
    {
        if(view.equals("login"))
        {
            checkpassword.setVisible(false);
            age.setVisible(false);
            addapost.setVisible(false);
            search.setVisible(false);
            searchfield.setVisible(false);
            signout.setVisible(false);
            bio.setVisible(false);
            name.setVisible(false);
            message.setVisible(false);
            UserChoice.setVisible(false);
            subjectPost.setVisible(false);
            contentPost.setVisible(false);
            settings.setVisible(false);
            addfriend.setVisible(false);
            
            signUp.setBounds(1000, 575, 100,50);  
            signUp.setText("Sign Up");
            
            enter.setBounds(1000, 475, 100,50);
            enter.setText("Enter");
            
            password.setBounds(850,600,100,20);
            password.setText("");
            password.setEditable(true);
            
            UserName.setBounds(850,400,100,20);
            UserName.setText("");
            UserName.setEditable(true);
            
            signUp.setVisible(true);
            enter.setVisible(true);
            password.setVisible(true);
            UserName.setVisible(true);
        }
        else if(view.equals("sign up"))
        {
            message.setVisible(false);
            UserChoice.setVisible(false);
            addapost.setVisible(false);
            searchfield.setVisible(false);
            subjectPost.setVisible(false);
            contentPost.setVisible(false);
            settings.setVisible(false);
            addfriend.setVisible(false);
            
            UserName.setBounds(850, 400, 100, 20);
            UserName.setText("");
            UserName.setEditable(true);
            
            password.setBounds(800, 600, 100, 20);
            password.setText("");
            password.setEditable(true);
            
            checkpassword.setBounds(1000, 600, 100, 20);
            checkpassword.setText("");
            checkpassword.setEditable(true);
            
            signUp.setLocation(1000, 400);
            signUp.setText("Back");
            
            enter.setLocation(1000, 475);
            enter.setText("Enter");
            
            name.setBounds(600,400,100,20);
            name.setText("");
            name.setEditable(true);
            
            bio.setBounds(600,650,200,40);
            bio.setText("");
            bio.setEditable(true);
            
            age.setBounds(600,500,100,20);
            age.setText("");
            age.setEditable(true);
            
            UserName.setVisible(true);
            password.setVisible(true);
            checkpassword.setVisible(true);
            enter.setVisible(true);
            name.setVisible(true);
            age.setVisible(true);
            bio.setVisible(true);

        }
        else if(view.equals("home screen"))
        {
            UserName.setVisible(false);
            password.setVisible(false);
            contentPost.setVisible(false);
            subjectPost.setVisible(false);
            signUp.setVisible(false);
            enter.setVisible(false);
            addfriend.setVisible(false);
            if(!UsernameSignedIn.equals(userView)/* && new client().isFriend(UsernameSignedIn,userView) == false*/)
            {
                addfriend.setBounds(500,10,100,50);
                addfriend.setText("Add Friend");
                addfriend.setVisible(true);
            }
            else if(!UsernameSignedIn.equals(userView) /*&& new client().isFriend(UsernameSignedIn,userView) == true*/)
            {
                removefriend.setBounds(500,10,100,50);
                removefriend.setText("Remove Friend");
                removefriend.setVisible(true);
            }
            subjectPost.setBounds(10, 525, 100, 20);
            subjectPost.setEditable(true);
            subjectPost.setVisible(true);
            
            contentPost.setBounds(10,595, 100, 20);
            contentPost.setEditable(true);
            contentPost.setVisible(true);
            
            addapost.setBounds(10,620,100,50);
            addapost.setVisible(true);
            
            search.setBounds(10,400,100,50);
            search.setVisible(true);
            
            searchfield.setBounds(120,425,100,20);
            searchfield.setEditable(true);
            searchfield.setVisible(true);
            
            signout.setBounds(1810,10,100,50);
            signout.setText("Sign out");
            signout.setVisible(true);

            message.setBounds(10,300, 100, 50);
            message.setText("Message");
            message.setVisible(true);
            
            settings.setBounds(1750,10,50,50);
            settings.setText("");
            try {
                BufferedImage im = ImageIO.read(new File("settings.png"));
                BufferedImage pic = resize(im, 50, 50);
                settings.setIcon(new ImageIcon(pic));
            } catch (Exception e) {
                e.printStackTrace();
                }
            //settings.setIcon("");
            settings.setVisible(true);
            
            UserChoice.setBounds(120, 325, 100, 20);
            UserChoice.setVisible(true);
            UserChoice.setEditable(true);
        }
        else if(view.equals("settings"))
        {
            checkpassword.setVisible(false);
            age.setVisible(false);
            addapost.setVisible(false);
            search.setVisible(false);
            searchfield.setVisible(false);
            bio.setVisible(false);
            name.setVisible(false);
            message.setVisible(false);
            UserChoice.setVisible(false);
            subjectPost.setVisible(false);
            contentPost.setVisible(false);
            settings.setVisible(false);
            addfriend.setVisible(false);
            
            signout.setBounds(1810,10,100,50);
            signout.setText("Sign out");
            signout.setVisible(true);

            signUp.setBounds(1690,10,100,50);
            signUp.setText("Back");
            signUp.setVisible(true);
            
            enter.setBounds(1100,310,100,50);
            enter.setText("Enter");
            enter.setVisible(true);
            
            name.setBounds(550,400,100,20);
            name.setEditable(true);
            name.setText(new client().getData(UsernameSignedIn,"name",0));
            
            bio.setBounds(550,500,100,20);
            bio.setEditable(true);
            bio.setText(new client().getData(UsernameSignedIn,"bio",0));
            bio.setVisible(true);
            
            age.setBounds(670,400,100,20);
            age.setEditable(true);
            age.setText(new client().getData(UsernameSignedIn,"age",0));
            age.setVisible(true);
        }
    }
    public String getSubjectText()
    {
        return subjectPost.getText();
    }

    public String getContentText()
    {
        return contentPost.getText();
    }

    public String getSearchfieldText()
    {
        return searchfield.getText();
    }

    public void setUsernameSignedIn(String s)
    {
        UsernameSignedIn = s;
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