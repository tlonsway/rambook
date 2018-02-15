import java.util.*;
import javax.swing.*;
import java.awt.*;
public class drawDisplay {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setVisible(true);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display login = new display();
        frame.add(login);
        login.setView("login");
        JTextField UsernameInput = new JTextField();
        frame.add(UsernameInput);
        login.setUserTextField(UsernameInput);
        login.drawing();
    }
}