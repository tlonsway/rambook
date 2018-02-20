import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class drawDisplay {
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
        JButton enter = new JButton("ENTER");
        JButton SignUp = new JButton("Create Account");
        frame.add(enter);
        frame.add(checkPassword);
        frame.add(SignUp);
        frame.add(Password);
        frame.add(UsernameInput);
        window.setTextField(UsernameInput, 1);
        window.setTextField(Password, 2);
        window.setTextField(checkPassword, 3);
        window.setEnterButton(enter);
        window.setSignUpButton(SignUp);
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
                            System.out.println(login.verify(window.getUsername(), window.getPassword()));
                            if(login.verify(window.getUsername(), window.getPassword()) == true)
                            {
                                window.setView("home screen");
                                String currentUser = window.getUsername();
                                window.setUserinfo(((new client()).getData(currentUser,"status", 0)), (currentUser), ((new client()).getData(currentUser,"bio", 0)), (Integer.parseInt((new client()).getData(currentUser, "friends", 0))));
                                
                            }
                            else if(login.verify(window.getUsername(), window.getPassword()) == false)
                            {
                                window.setFailedLogin(true);
                            }
                            window.drawing();
                        }
                        else if(window.view.equals("sign up"))
                        {
                            if(login.userExist(UsernameInput.getText()) == true)
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
                                    login.addUser(UsernameInput.getText(), Password.getText());
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
    }
}