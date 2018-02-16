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
        window.setView("login");
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
        window.drawing();
        login account = new login();
        enter.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent arg0) //throws Exception
                {
                    //login.verify(window.getUsername(), window.getPassword());
                }
            } );
    }
}