import javax.swing.*;
import java.awt.*;
import javax.swing.JOptionPane;
import javax.swing.JComponent.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String.*;
import java.lang.Object.*;
public class display extends JPanel
{
    String view = "";
    JTextField UserName;
    JTextField password;
    JButton enter;
    public void drawing()
    {
        repaint();
    }

    public void setTextField(JTextField U, int i)
    {
        if(i == 1)
        {
            UserName = U;
        }
        else if(i == 2)
        {
            password = U;
        }
    }
    /*enter.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent arg0)
        {
            
        }
    } );*/
    public void setView(String v)
    {
        view = v;
    }
    public void setButton(JButton g)
    {
        enter = g;
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
            password.setBounds(850, 600, 100, 20);
            UserName.setEditable(true);
            password.setEditable(true);
            UserName.setVisible(true);
            password.setVisible(true);
            g.drawRect(850,400,100,20);
            g.drawRect(850,600,100,20);
            enter.setVisible(true);
            enter.setBounds(1000, 475, 100,50);
            enter.setText("ENTER");
            g.drawRect(1000,475,100,50);
        }
    }
}