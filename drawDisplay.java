import java.util.*;
import javax.swing.*;
public class drawDisplay {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setVisible(true);
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display login = new display();
        login.setView("login");
        login.drawing();
    }
    
}