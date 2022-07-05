import javax.swing.ImageIcon;

import javax.swing.*;

public class LogoPanel extends JPanel{

    private ImageIcon image = null;
    private JLabel label;

    public LogoPanel(){
        image = new ImageIcon("BombLogo.jpg");
        label = new JLabel(image);
        add(label);
        
    }
}
