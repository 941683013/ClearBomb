import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{
    // 游戏界面板块
    Card[][] cards;
    int col;
    int row;
    int flag;
    Random random; // 随机数
    TopBar topBar;
    int bombs;
    GamePage gamePage;

    public GamePanel(int row, int col, int bombs){
        this.bombs = bombs;
        this.row = row;
        this.col = col;
        flag = bombs;
        inition();
    }

    public void setGamePage(GamePage gamePage){
        this.gamePage = gamePage;
    }

    public int getBombs(){
        return bombs;
    }

    public void setTopBar(TopBar topBar){
        this.topBar = topBar;
    }

    public void inition(){

        random = new Random();

        GridLayout layout = new GridLayout(row, col);
        setLayout(layout);

        cards = new Card[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int x = i, y = j;
                cards[x][y] = new Card(x, y);
                cards[x][y].setGamePanel(this);
                add(cards[x][y]);
            }
        }

        // 生成地雷
        for(int i = 0; i < bombs; i++) {
            int x = random.nextInt(row);
            int y = random.nextInt(col);
            if(cards[x][y].isBomb()){
                i--;
            }
            else {
                cards[x][y].beBomb();
            }
        }
    }

    final int dirx[] = {0, 1, 0, -1, 1, 1, -1, -1};
    final int diry[] = {1, 0, -1, 0, 1, -1, 1, -1};

    public void dfs(int x, int y){
        // System.out.println(x + "  " + y);
        // 如果是炸弹 || 被翻开 || 插了旗 || aroundBombs != 0
        if(cards[x][y].isBomb() || cards[x][y].getStatus() != 0 || cards[x][y].getAroundBombs() != 0){ 
            if(cards[x][y].getAroundBombs() != 0 && cards[x][y].getStatus() == 0){
                flipCard(x, y);
            }
            return;
        }

        flipCard(x, y);

        for(int i = 0; i < 8; i++){
            int px = x + dirx[i];
            int py = y + diry[i];

            if(px >= 0 && px < row && py >= 0 && py < col){ // 坐标有意义
                dfs(px, py);
            }
        }
   }
   
   public void flipCard(int x, int y){
       topBar.flipCard();
       cards[x][y].flip_card();
   }

   public void flipAllBombs(){ // 游戏结束翻开所有💣
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                cards[i][j].flipBomb();
            }
        }
   }

   public boolean submitFlag(){ // 提交🚩
        flipAllBombs();
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(cards[i][j].getStatus() == 2 && !cards[i][j].isBomb()){
                    return false;
                }
            }
        }
        return true;
   }

}
