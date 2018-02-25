import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("window");
        frame.setVisible(true);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display window = new display();
        frame.add(window);
        //String view = "sign up";
        String view = "login";
        window.setView(view);
        JTextField UsernameInput = new JTextField();
        JTextField Password = new JTextField();
        JTextField checkPassword = new JTextField();
        JTextField age = new JTextField();
        JTextField bio = new JTextField();
        JTextField name = new JTextField();
        JTextField messageUser = new JTextField();
        JTextField searchfield = new JTextField();

        JButton enter = new JButton("ENTER");
        JButton SignUp = new JButton("Create Account");

        JButton search = new JButton("Search");

        JButton signout = new JButton("Sign out");

        JButton messaging = new JButton("Message");

        frame.add(enter);
        frame.add(checkPassword);
        frame.add(SignUp);
        frame.add(Password);
        frame.add(UsernameInput);
        frame.add(messaging);

        frame.add(age);
        frame.add(bio);
        frame.add(name);

        frame.add(search);
        frame.add(searchfield);

        frame.add(messageUser);

        frame.add(signout);

        window.setTextField(UsernameInput, 1);
        window.setTextField(Password, 2);
        window.setTextField(checkPassword, 3);

        window.setTextField(age, 4);
        window.setTextField(bio, 5);
        window.setTextField(name, 6);

        window.setTextField(searchfield, 7);
        
        window.setTextField(messageUser, 8);
        
        window.setEnterButton(enter);
        window.setSignUpButton(SignUp);
        window.setSignOutButton(signout);
        window.setSearchButton(search);
        window.setMessageButton(messaging);

        String Usernamewindow = "";
        login account = new login();
        window.setUserinfo("true", "Tristan","I have mad hax. idhhfgfg dfewbftfreyrvbrhdfyevf", 50);
        User currentUser;
        //client Client = new client();
        enter.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try {

                        if(window.view.equals("login"))
                        {
                            System.out.println((new client()).checkPassword(window.getUsername(), (window.getPassword())));
                            if((new client()).checkPassword(window.getUsername(), window.getPassword()) == true)
                            {
                                window.setView("home screen");
                                String currentUser = window.getUsername();
                                window.setUserinfo(((new client()).getData(currentUser,"status", 0)), (currentUser), ((new client()).getData(currentUser,"bio", 0)), (Integer.parseInt((new client()).getData(currentUser, "friends", 0))));

                            }
                            else if((new client()).checkPassword(window.getUsername(), (window.getPassword())) == false)
                            {
                                window.setFailedLogin(true);
                            }
                            window.drawing();
                        }
                        else if(window.view.equals("sign up"))
                        {
                            if((new client()).checkUserExist(UsernameInput.getText()) == true)
                                window.setSignUpValid(false, true);
                            else 
                            {
                                window.setSignUpValid(true, true);
                                if(Password.getText().equals(checkPassword.getText()))
                                    window.setPasswordMatch(true);
                                else
                                    window.setPasswordMatch(false);
                                if(window.passwordsMatch == true)
                                {
                                    String uname = UsernameInput.getText();
                                    (new client()).addUser(uname, name.getText(), Integer.parseInt(age.getText()), bio.getText(), (Password.getText()));
                                    window.setView("login");
                                }
                            }
                            window.drawing();
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } );
        SignUp.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try {
                        if(window.view.equals("login"))
                        {
                            window.setView("sign up");
                            window.drawing();
                        }
                        else if(window.view.equals("sign up"))
                        {
                            window.setView("login");
                            window.drawing();
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } );
        window.drawing();

        search.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try{
                        window.setView("home screen");
                        String currentUser = searchfield.getText();
                        window.setUserinfo(((new client()).getData(currentUser,"status", 0)), (currentUser), ((new client()).getData(currentUser,"bio", 0)), (Integer.parseInt((new client()).getData(currentUser, "friends", 0))));
                        if((new client()).checkUserExist(window.getSearchfieldText()) == false)
                            window.InvalidSearch = true;
                        else if((new client()).checkUserExist(window.getSearchfieldText()) == true)
                            window.InvalidSearch = false;
                        window.drawing();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            } );
        signout.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    window.setView("login");
                    window.drawing();
                }
            } );

        messaging.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try{
                        if(new client().checkUserExist(window.getUserChoice()) == true && new client().getData(window.getUserChoice(), "status", 0).equals("online"))
                        {
                            //JFrame messagingFrame = new JFrame("Messaging");
                            
                        }
                        window.drawing();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            } );
    }
}