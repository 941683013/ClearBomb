import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JButton implements MouseListener {
    int x, y; // 坐标
    boolean isbomb; // 
    int status; // 状态(初始0) 1(被翻开), 2(插上旗)
    GamePanel gamePanel;
    int aroundBombs; // 周围的炸弹数
    ImageIcon imageIcon;
    Color color = Color.LIGHT_GRAY;
    Color flagColor = Color.RED;


    public Card(int x, int y){
        this.x = x;
        this.y = y;
        setMargin(new Insets(0, 0, 0, 0));
        setOpaque(true);

        setBackground(color);
        this.addMouseListener(this); // 加入监听
    }

    public int getStatus(){
        return status;
    }

    public void flipCard(){ // 翻牌函数
        
        gamePanel.dfs(x, y);
    }

    public void flip_card(){ // 翻卡片
        status = 1;
        if(aroundBombs != 0){
            setText("" + aroundBombs);
        }
        setBackground(Color.WHITE);
        if(gamePanel.topBar.getCards() == gamePanel.getBombs()){ // 赢了游戏结束
            System.out.println("赢了  Card.flip_card()");
            gameEnding(1);
        }
    }

    public int getAroundBombs(){
        return aroundBombs;
    }

    public boolean isBomb(){
        return isbomb;
    }

    final int dirx[] = {0, 1, 0, -1, 1, 1, -1, -1};
    final int diry[] = {1, 0, -1, 0, 1, -1, 1, -1};

    public void beBomb(){
        isbomb = true;
        for(int i = 0; i < 8; i++){
            int px = x + dirx[i];
            int py = y + diry[i];

            if(px >= 0 && px < gamePanel.row && py >= 0 && py < gamePanel.col){ // 坐标有意义
                gamePanel.cards[px][py].aroundBombs++;
            }
        }
        
    }

    public void plantFlag(){
        if(gamePanel.topBar.flag == 0) return;
        status = 2;
        gamePanel.topBar.plantFlag();
        setBackground(Color.RED);
        setText("🚩");
    }

    public void cancelFlag(){
        status = 0;
        gamePanel.topBar.cancelFlag();
        setText("");
        setBackground(color);
    }

    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void gameEnding(int t){ // 游戏结束...
        // 给一个提示框，此处做一些收尾工作
        gamePanel.flipAllBombs();
        System.out.println("游戏结束");

        if(t == 0){ // 输了
            gamePanel.gamePage.gameOver(0);
        }
        else { // 赢了
            gamePanel.gamePage.gameOver(1);
        }

        
    }

    public void flipBomb(){
        if(isBomb()){ // 是炸弹
            setText("💣");
            setBackground(flagColor);
        }
    }





    // 实现监听效果  鼠标左键BUTTON1(翻卡片), 鼠标滑动BUTTON2, 鼠标右键BUTTON3(插旗)
    @Override
    public void mouseClicked(MouseEvent e) { // 单击监听
        // TODO Auto-generated method stub

        switch(e.getButton()){
            case MouseEvent.BUTTON1: // 翻牌
                if(getStatus() == 0){
                    if(isBomb()){
                        gameEnding(0);
                    }
                    else if(getStatus() == 0){
                        flipCard();
                    }
                }
            break;

            case MouseEvent.BUTTON3: // 插旗 或 拔旗
                if(getStatus() == 0){
                    plantFlag();
                }
                else if(getStatus() == 2){
                    cancelFlag();
                }
            break;
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
