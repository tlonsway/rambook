import javax.swing.*;
import java.awt.*;
public class draw extends JPanel
{
    public void drawing()
    {
        repaint();
    }   
    public void paintComponet(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(10,10,100,100);
        g.drawRect(200, 50, 20, 10);
    }
}