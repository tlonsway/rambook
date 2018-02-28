import javax.swing.*;
public class test
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("test");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        JPanel screen = new JPanel();
        frame.add(screen);
        draw drawTest = new draw();
        frame.add(drawTest);
        drawTest.drawing();
    }
}