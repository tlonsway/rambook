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
        JButton enter = new JButton("ENTER");
        JButton SignUp = new JButton("Create Account");
        frame.add(enter);
        frame.add(SignUp);
        frame.add(Password);
        frame.add(UsernameInput);
        window.setTextField(UsernameInput, 1);
        window.setTextField(Password, 2);
        window.setEnterButton(enter);
        window.setSignUpButton(SignUp);
        String Usernamewindow = "";
        login account = new login();
        window.setUserinfo(false, "Tristan","I have mad hax.", 50);
        enter.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try {
                        
                        if(window.view.equals("login"))
                        {
                            System.out.println(login.verify(window.getUsername(), window.getPassword()));
                            window.drawing();
                        }
                        else if(window.view.equals("sign up"))
                        {
                           // if(login.userExist(UsernameInput.getText()) == true)
                              //  window.setSignUpValid(false, true);
                            login.addUser(UsernameInput.getText(), Password.getText());
                            window.setView("login");
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
                        window.setView("sign up");
                        window.drawing();
                        System.out.println(window.view);
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                }
            } );
        window.drawing();
    }
}