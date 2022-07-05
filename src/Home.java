import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
    double rate = 1.2;
    Box boxH;
    Box boxV1;
    Box boxV2;
    Box boxV3;
    JButton gameStart;
    JButton gameOver;
    JMenuBar menuBar;
    JMenuItem item1;
    JMenuItem item2;
    JMenuItem item3;
    JMenuItem item4;
    int rank = 1; // 等级 
    GamePage gamePage;

    JButton rankList;
    

    public Home(){

        LogoPanel panel = new LogoPanel();
        add(panel, BorderLayout.NORTH);
        
        initionMenu();
        boxH = Box.createVerticalBox();
        boxV1 = Box.createHorizontalBox();
        boxV2 = Box.createHorizontalBox();
        boxV3 = Box.createHorizontalBox();
        gameStart = new JButton("开始游戏");
        gameStart.setBackground(Color.LIGHT_GRAY);
        gameStart.addActionListener(this);

        gameOver  = new JButton("退出游戏");
        gameOver.setBackground(Color.LIGHT_GRAY);
        gameOver.addActionListener(this);


        rankList = new JButton("  排行榜  ");
        rankList.setBackground(Color.LIGHT_GRAY);
        rankList.addActionListener(this);

        boxV1.add(gameStart);
        boxV2.add(gameOver);
        boxV3.add(rankList);
        boxH.add(Box.createVerticalStrut(20));
        boxH.add(boxV1);
        boxH.add(Box.createVerticalStrut(10));
        boxH.add(boxV2);
        boxH.add(Box.createVerticalStrut(10));
        boxH.add(boxV3);

        

        add(boxH, BorderLayout.CENTER);
        setTitle("扫雷");
        setBounds(50, 90, 400, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void initionMenu(){

        JMenu menu = new JMenu("难度", false);
        item1 = new JMenuItem("初级");
        item2 = new JMenuItem("中级");
        item3 = new JMenuItem("高级");
        item4 = new JMenuItem("顶级");


        item1.addActionListener(this);
        item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);

        menu.add(item1);
        menu.add(item2);
        menu.add(item3);
        menu.add(item4);

        menuBar = new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == gameStart){
            setVisible(false); // 设为不可见
            gamePage = new GamePage(rank, this); // 游戏结束后设为可见
        }
        else if(e.getSource() == gameOver) {
            int option = JOptionPane.showConfirmDialog(this, "是否退出游戏?", "提示",
            JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
        else if(e.getSource() == item1) {
            setItem1();
        }
        else if(e.getSource() == item2){
            setItem2();
        }
        else if(e.getSource() == item3){
            setItem3();
        }
        else if(e.getSource() == item4){
            setItem4();
        }
    }

    public void setItem1(){
        rank = 1;
    }
    public void setItem2(){ 
        rank = 2;
    }
    public void setItem3(){ 
        rank = 3;
    }
    public void setItem4(){
        rank = 4;
    }

}

