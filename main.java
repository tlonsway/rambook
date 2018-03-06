import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("RAMBOOK");
        frame.setVisible(true);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display window = new display();
        frame.add(window);
        File file = new File("icon.png");
        try {
            BufferedImage image = ImageIO.read(file); 
            frame.setIconImage(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //String view = "sign up";
        String view = "login";
        window.setView(view);
        JTextField UsernameInput = new JTextField();
        JPasswordField Password = new JPasswordField();
        JPasswordField checkPassword = new JPasswordField();
        JTextField age = new JTextField();
        JTextField bio = new JTextField();
        JTextField name = new JTextField();
        JTextField messageUser = new JTextField();
        JTextField searchfield = new JTextField();
        JTextField postSubject = new JTextField();
        JTextField postContent = new JTextField();
        JTextField homeTown = new JTextField();
        JTextField schools = new JTextField();

        JButton enter = new JButton("ENTER");
        JButton SignUp = new JButton("Create Account");

        JButton search = new JButton("Search");

        JButton signout = new JButton("Sign out");

        JButton messaging = new JButton("Message");

        JButton addPost = new JButton("Add A Post");

        JButton addFriend = new JButton("Add Friend");
        JButton removeFriend = new JButton("Remove Friend");

        JButton settings = new JButton("");

        window.add(enter);
        window.add(checkPassword);
        window.add(SignUp);
        window.add(Password);
        window.add(UsernameInput);
        window.add(addFriend);
        window.add(removeFriend);
        window.add(homeTown);
        window.add(schools);

        window.add(postSubject);
        window.add(postContent);
        window.add(addPost);

        window.add(age);
        window.add(bio);
        window.add(name);

        window.add(search);
        window.add(searchfield);

        window.add(signout);
        window.add(settings);

        window.setTextField(UsernameInput, 1);
        window.setTextField(Password, 2);
        window.setTextField(checkPassword, 3);

        window.setTextField(postSubject, 9);
        window.setTextField(postContent, 10);
        window.setAddPostButton(addPost);

        window.setTextField(age, 4);
        window.setTextField(bio, 5);
        window.setTextField(name, 6);

        window.setTextField(searchfield, 7);

        window.setTextField(messageUser, 8);

        window.setTextField(homeTown,11);
        window.setTextField(schools,12);

        window.setEnterButton(enter);
        window.setSignUpButton(SignUp);
        window.setSignOutButton(signout);
        window.setSearchButton(search);
        window.setSettingsButton(settings);
        window.setRemoveFriendButton(removeFriend);
        window.setAddFriendButton(addFriend);

        String Usernamewindow = "";

        login account = new login();
        //window.setUserinfo("true", "Tristan","I have mad hax. idhhfgfg dfewbftfreyrvbrhdfyevf", 50);
        String UserSignedIn;
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
                                window.setUsernameSignedIn(currentUser);
                                window.setUserSignedIn(currentUser); 
                                window.setUserinfo(((new client()).getData(currentUser,"status", 0)), (currentUser), ((new client()).getData(currentUser,"bio", 0)), (Integer.parseInt((new client()).getData(currentUser, "friends", 0))),(new client().getData(currentUser,"age",0)),(new client().getData(currentUser,"name",0)),(new client().getData(currentUser,"hometown",0)),(new client().getData(currentUser,"cchoools",0)));
                                window.setUserView(currentUser);
                                System.out.println(window.view);
                                window.setFriends(new client().getFriends(currentUser));
                            }
                            else if((new client()).checkPassword(window.getUsername(), (window.getPassword())) == false)
                            {
                                window.setFailedLogin(true);
                            }
                            window.drawing();
                            window.setButtons();
                        }
                        else if(window.view.equals("sign up"))
                        {
                            if(window.invalidCharacters() == false)
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
                                        (new client()).addUser(uname, name.getText(), Integer.parseInt(age.getText()), bio.getText(), (Password.getText()), homeTown.getText(), schools.getText());
                                        window.setView("login");
                                    }
                                }
                            }
                            else
                                window.hasInvalidCharacters = true;
                            window.drawing();
                            window.setButtons();
                        }
                        else if(window.view.equals("settings"))
                        {
                            //if (window.getUserSignedIn() != null && window.name.getText() != null && window.bio.getText() != null && window.age.getText() != null) {
                            new client().setData(window.UserSignedIn, "name", window.name.getText());
                            Thread.sleep(100);
                            new client().setData(window.getUserSignedIn(), "bio", window.bio.getText());
                            Thread.sleep(100);
                            new client().setData(window.getUserSignedIn(), "age", window.age.getText());
                            Thread.sleep(100);
                            new client().setData(window.getUserSignedIn(), "hometown", window.homeTown.getText());
                            Thread.sleep(100);
                            new client().setData(window.getUserSignedIn(), "c", window.schools.getText());
                            Thread.sleep(100);
                            //}
                            window.setView("home screen");
                            window.drawing();
                            window.setButtons();
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
                            window.failedLogin = false;
                            UsernameInput.setText("");
                            Password.setText("");
                            window.drawing();
                            window.setButtons();
                        }
                        else if(window.view.equals("sign up"))
                        {
                            window.setView("login");
                            window.hasInvalidCharacters = false;
                            window.drawing();
                            window.setButtons();
                        }
                        else if(window.view.equals("home screen"))
                        {
                            window.setAddPost(false);
                            window.hasInvalidCharacters = false;
                            window.drawing();
                            window.setButtons();
                        }
                        else if(window.view.equals("settings"))
                        {
                            window.setView("home screen");
                            window.drawing();
                            window.setButtons();
                        }
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } );

        search.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try{
                        String currentUser = searchfield.getText();
                        System.out.println("current user is now " + currentUser);
                        if((new client()).checkUserExist(currentUser) == true)
                        {
                            window.setUserinfo(((new client()).getData(currentUser,"status", 0)), (currentUser), ((new client()).getData(currentUser,"bio", 0)), (Integer.parseInt((new client()).getData(currentUser, "friends", 0))),(new client().getData(currentUser,"age",0)),(new client().getData(currentUser,"name",0)),(new client().getData(currentUser,"hometown",0)),(new client().getData(currentUser,"cchoools",0)));
                            window.InvalidSearch = false;
                            window.setUserView(currentUser);
                            window.setFriends(new client().getFriends(currentUser));
                            System.out.print("It set the user view to: " + currentUser);
                        }
                        else if((new client()).checkUserExist(currentUser) == false)
                        {
                            window.InvalidSearch = true;
                        }
                        window.drawing();
                        window.setButtons();
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
                    (new client()).signout(window.UsernameSignedIn);
                    window.setView("login");
                    window.drawing();
                    window.setButtons();
                }
            } );
        addPost.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try{
                        new client().addPost(window.UsernameSignedIn,window.getSubjectText(),window.getContentText());
                        window.drawing();
                        window.setButtons();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            } );
        settings.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try{
                        window.setView("settings");
                        window.drawing();
                        window.setButtons();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            } );
        addFriend.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try{
                        new client().addFriend(window.UsernameSignedIn, window.userView);
                        window.drawing();
                        window.setButtons();
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            } );
        window.drawing();
        window.setButtons();
    }
}