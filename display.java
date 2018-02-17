import javax.swing.*;
import java.awt.*;

public class display extends JPanel
{
    String view = "";
    JTextField UserName;
    JTextField password;
    JButton enter;
    JButton signUp;
    boolean online = true;
    String Name = "";
    String aboutMe = "";
    int numberOfFriends = 0;
    boolean isValidUsername = false;
    boolean enterClicked = false;
    public void drawing()
    {
        repaint();
    }
    public void setUserinfo(boolean bop,String name,String bio, int numFriends )
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
    public void setSignUpValid(boolean valid, boolean clicked)
    {
        isValidUsername = valid;
        enterClicked = clicked;
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
            UserName.setBounds(850, 400, 100, 20);
            password.setBounds(850, 600, 100, 20);
            UserName.setEditable(true);
            password.setEditable(true);
            UserName.setVisible(true);
            password.setVisible(true);
            g.drawRect(850,400,100,20);
            g.drawRect(850,600,100,20);
            enter.setVisible(true);
            signUp.setVisible(true);
            enter.setBounds(1000, 475, 100,50);
            signUp.setBounds(1000, 575, 100,50);
            signUp.setText("Sign Up");
            enter.setText("ENTER");
            g.drawRect(1000,475,100,50);
            g.drawRect(1000,575,100,50);
        }
        else if(view.equals("home screen"))
        {
            UserName.setVisible(false);
            password.setVisible(false);
            signUp.setVisible(false);
            enter.setVisible(false);
            g.setColor(Color.RED);
            g.fillRect(0,0, 1920, 1020);
            g.setColor(Color.BLACK);
            g.fillRect(10,10,200,200);
            Font f = new Font("", Font.BOLD, 20);
            g.setFont(f);
            g.setColor(Color.WHITE);
            g.drawString("Name: ", 250, 25);
            g.drawString(Name, 325, 25);
            g.drawString("About Me: ", 250, 95);
            String editBio = "";
            if(aboutMe.length() > 20 )
            {
                editBio = aboutMe.substring(0, 21);
                editBio += "\n";
                editBio += aboutMe.substring(21);
            }
            aboutMe = editBio;
            g.drawString(aboutMe.substring(0,aboutMe.indexOf("\n")),250, 120);
            g.drawString(aboutMe.substring(aboutMe.indexOf("\n")),250,150);
            g.drawString("Number Of Friends:", 250, 60);
            String temp = "" + numberOfFriends;
            g.drawString(temp, 440, 60);
            g.drawString("Current Status: ", 10, 250);
            if(online == true)
            {
                g.drawString("Online", 175, 250);
            }
            else if(online == false)
            {
                g.drawString("Offline", 175, 250);
            }
            g.setColor(Color.WHITE);
            g.drawLine(960, 0, 960, 1020);
            
        }
        else if(view.equals("sign up"))
        {
            UserName.setVisible(true);
            password.setVisible(true);
            signUp.setVisible(false);
            enter.setVisible(true);
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
            g.drawString("Password: ", 850, 550);
            UserName.setBounds(850, 400, 100, 20);
            password.setBounds(850, 600, 100, 20);
            UserName.setEditable(true);
            password.setEditable(true);
            UserName.setVisible(true);
            password.setVisible(true);
            g.drawRect(850,400,100,20);
            g.drawRect(850,600,100,20);
            enter.setVisible(true);
            enter.setBounds(1000, 475, 100,50);
            enter.setText("ENTER");
            g.drawRect(1000,475,100,50);
            if(isValidUsername == false && enterClicked == true)
            {
                g.setColor(Color.RED);
                g.drawString("Username Already Taken",600,400);
            }
        }
    }
}