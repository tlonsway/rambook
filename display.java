import javax.swing.*;
import java.awt.*;
public class display extends JPanel
{
    String view = "";
    JTextField UserName = new JTextField();
    public void drawing()
    {
        repaint();
    }
    public void setUserTextField(JTextField U)
    {
        UserName = U;
    }
    public void setView(String v)
    {
        view = v;
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(view.equals("login"))
        {
            g.setColor(Color.RED);
            g.fillRect(0,0,1920,1020);
            g.setColor(Color.WHITE);
            g.fillRect(480,255,960,510);
            g.setColor(Color.BLACK);
            g.drawRect(480,255,960,510);
            g.setColor(Color.BLACK);
            JLabel Wordz = new JLabel();
            Font f = new Font("Username: ", Font.BOLD, 20);
            Wordz.setSize(10,20);
            g.setFont(f);
            g.drawString(f.getName(), 850, 300);
            g.drawString("Password: ", 850, 500);
            UserName.setBounds(850, 400, 100, 20);
            UserName.setEditable(true);
            UserName.setVisible(true);
        }
    }
}