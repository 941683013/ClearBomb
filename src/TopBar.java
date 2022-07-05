import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBar extends JPanel implements ActionListener{ // 顶部栏
    // 我们需要一个计时器，一个方块计数器， 一个🚩计数器

    int timer; // 计时器
    int card;  // 卡片
    int flag;  // 旗帜

    int cards;
    int flags;

    JLabel timer_label;
    JLabel card_lable;
    JLabel flag_lable;

    JButton submit;
    GamePage gamePage;

    public TopBar(int card, int flag){
        
        inition(card, flag);
        showTopBar();
    }

    public void setGamePage(GamePage gamePage){
        this.gamePage = gamePage;
    }

    public void showTopBar(){   // 完成组件的初始化

        removeAll();

        timer_label = new JLabel("", JLabel.CENTER);
        card_lable  = new JLabel("", JLabel.CENTER);
        flag_lable  = new JLabel("", JLabel.CENTER);
        submit = new JButton("提前引爆");
        submit.setOpaque(false);
        submit.addActionListener(this);
        submit.setMargin(new Insets(0, 0, 0, 0));

        add(timer_label);
        add(submit);
        add(card_lable);
        add(flag_lable);
        

        String str = String.format("Timer %02d : %02d  ",timer / 60, timer % 60);

        timer_label.setText(str);
        timer_label.setOpaque(true);
        timer_label.setBackground(Color.ORANGE);
        card_lable.setOpaque(true);
        card_lable.setText(String.format("Card %d / %d ", card, cards));
        card_lable.setBackground(Color.CYAN);
        flag_lable.setText(String.format("Flag %d / %d ",flag, flags));
        flag_lable.setOpaque(true);
        flag_lable.setBackground(Color.CYAN);


        updateUI();
        // repaint();
    }

    public void inition(int card, int flag){      // 完成数据的初始化
        this.card = card;
        this.flag = flag;
        this.cards = card;
        this.flags = flag;
    }

    public void countTimer(){
        timer++;
        showTopBar();
    }

    public void flipCard(){
        card--;
        showTopBar();
    }

    public void plantFlag(){
        flag--;
        showTopBar();
    }

    public void cancelFlag(){
        flag++;
        showTopBar();
    }

    public int getCards(){
        return card;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            if(flag == 0) { // 🚩安放好
                if(gamePage.gamePanel.submitFlag()) {
                    gamePage.gameOver(1);
                }
                else gamePage.gameOver(0);
            }
            else {
                JOptionPane.showMessageDialog(null, 
            "安放好所有🚩后选择引爆");
            }
        }
        
    }
    
}
