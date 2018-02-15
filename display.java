import javax.swing.*;
import java.awt.*;
public class display extends JPanel
{
    String view = "";
    public void drawing()
    {
        repaint();
    }
    public void setView(String v)
    {
        view = v;
    }
    public void draw(Graphics g)
    {
        super.paintComponent(g);
        if(view == "login")
        {
            g.setColor(Color.BLACK);
            g.drawRect(480,270,960,510);
            
        }
    }
}