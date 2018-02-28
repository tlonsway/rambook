import javax.swing.*;
import java.awt.*;
public class drawFrame extends JFrame 
{
    public drawFrame(){
        super();
    }

    public void paint(Graphics g){
        //g.drawLine(10,10,150,150);
        //g.setColor(Color.RED);
        //g.fillRect(300,10,50,50);
    }
    
    public void drawing()
    {
        repaint();
    }
}