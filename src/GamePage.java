import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GamePage extends JFrame implements WindowListener{
    TopBar topBar;
    Timer timer;
    int row;
    int col;
    int bombs;
    GamePanel gamePanel;
    Home home;

    public GamePage(int rank, Home home){
        setHome(home);
        setParameter(rank);
        inition();

        setBounds(50, 90, 400, 400);
        setVisible(true);
        setTitle("扫雷");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        validate();
    }

    public void setHome(Home home){
        this.home = home;
    }

    public void setParameter(int rank){ // 设置参数
        switch(rank){
            case 1:
                row = 9;
                col = 9;
                bombs = 10;
            break;
            case 2:
                row = 16;
                col = 16;
                bombs = 40;
            break;
            case 3:
                row = 16;
                col = 30;
                bombs = 99;
            break;
            case 4:
                row = 27;
                col = 60;
                bombs = 334;
            break;
        }
    }

    public void gameOver(int status) { // 游戏结束
        timer.stop();; // 计时结束

        if(status == 0){ // 输了
            JOptionPane.showMessageDialog(null, 
            "很遗憾,你踩到💣了!");
            this.dispose(); // 回收窗体
            home.setVisible(true); // 恢复界面
        }
        else {
            JOptionPane.showMessageDialog(null, 
            "恭喜你,完成排雷🎉" + " 花费时长 " + 
            String.format("%02d : %02d", topBar.timer / 60,topBar.timer % 60));
            this.dispose(); // 回收窗体
            home.setVisible(true); // 恢复界面
        }
    }

    public void inition(){
        // 顶部栏初始化
        topBar = new TopBar(row * col, bombs);
        topBar.setGamePage(this);
        add(topBar, BorderLayout.NORTH);

        addWindowListener(this);

        timer = new Timer(1000, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                topBar.countTimer();
            }
            
        });
        timer.start();

        // 游戏Panel初始化
        gamePanel = new GamePanel(row, col, bombs);
        gamePanel.setTopBar(topBar);
        gamePanel.setGamePage(this);
        add(gamePanel, BorderLayout.CENTER);

    }

    @Override
    public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        int option = JOptionPane.showConfirmDialog(this, "是否退出游戏?", "提示",
        JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION){
            if (e.getWindow() == this) {
                this.dispose();
                home.setVisible(true);
            }
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
    }
}
