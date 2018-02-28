import java.awt.*;
import javax.swing.*;

public class test{

    
    public static void main(String[] arg){
        JFrame frame = new JFrame("test");
        frame.setSize(1000,1000);
        frame.setVisible(true);
        drawPanel window = new drawPanel();
        frame.add(window);
        JButton b1 = new JButton("test 1");
        JButton b2 = new JButton("test 2");
        frame.add(b1);
        frame.add(b2);
        window.setButtons(1,b1);
        window.setButtons(2,b2);
        
        window.drawing();
        //window.setButtons();
        window.setButtons(1,b1);
        window.setButtons(2,b2);
    }
}