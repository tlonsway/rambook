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
        frame.add(enter);
        frame.add(Password);
        frame.add(UsernameInput);
        window.setTextField(UsernameInput, 1);
        window.setTextField(Password, 2);
        window.setButton(enter);
        String Usernamewindow = "";
        login account = new login();
        window.setUserinfo(false, "Tristan","I have mad hax.", 50);
        enter.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) 
                {
                    try {
                        if(view.equals("login"))
                            System.out.println(login.verify(window.getUsername(), window.getPassword()));
                        else if(view.equals("sign up"))
                        {
                            window.setSignUpValid(false, true);
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