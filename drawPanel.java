import javax.swing.*;
import java.awt.*;
public class drawPanel extends JPanel
{
    JButton b1;
    JButton b2;
    public drawPanel()
    {
        super();
        
    }
    public void paint(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(10,10,100,100);
        //button 1 setting
        b1.setBounds(500,500, 100,50);
        b1.setText("test 1");
        
        //button  2 setting 
        b2.setBounds(650,500,100,50);
        b2.setText("test 2");
        //setting buttons visible
        b2.setVisible(true);
        b1.setVisible(true);
    }
    public void setButtons(int i, JButton a)
    {
        if(i==1)
            b1 = a;
        else if(i==2)
            b2 = a;
    }
    public void drawing()
    {
        repaint();
    }
}