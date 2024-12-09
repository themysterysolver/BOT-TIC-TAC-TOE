import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Controller {
    @FXML
    Button btn0;
    @FXML
    Button btn1;
    @FXML
    Button btn2;
    @FXML
    Button btn3;
    @FXML
    Button btn4;
    @FXML
    Button btn5;
    @FXML
    Button btn6;
    @FXML
    Button btn7;
    @FXML
    Button btn8;
    @FXML
    Button startMe;
    @FXML
    Button Draw,reset;
    private Button[] buttons;
    private gamer g;
    private int count=0;
    @FXML
    private void initialize(){
        buttons=new Button[]{btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
        g=new gamer();
        g.populateBoard();
        updateBoard();
        Draw.setVisible(false);
        reset.setVisible(false);
    }

    @FXML
    private void onClick(javafx.event.ActionEvent e){
        Button b=(Button)e.getSource();
        int idx=getIdx(b);
        System.out.println("Button clicked:"+idx);
        if(g.board[idx]=='-'){
            g.insertMove(idx,'x');
            if(g.gameOver('x')){
                applyGameOver('x');
            }
            g.botMove();
            count+=2;
            startMe.setVisible(false);
            if(g.gameOver('o')){
                applyGameOver('x');
            }
            updateBoard();
            System.out.println(count);
            if(count>9){
                declareDraw();
            }
        }
    }

    private void applyGameOver(char x) {
        reset.setVisible(true);
        highlightSquares();
        updateBoard();
    }

    private void declareDraw() {
        Draw.setVisible(true);
        reset.setVisible(true);
    }

    private void allDisbled() {
        char[] board=g.board;
        g.displayBoard();
        for(int i=0;i<board.length;i++){
                buttons[i].setDisable(true);
        }
    }
    private void highlightSquares() {
        int[] winningBlock=g.winBlock();
        System.out.println(winningBlock.length);
        for(int i=0;i<winningBlock.length;i++){
            System.out.println(winningBlock[i]);
            buttons[winningBlock[i]].setBackground(new Background(new BackgroundFill(Color.rgb(0, 176, 71), CornerRadii.EMPTY,null)));
        }
    }
    private int getIdx(Button b) {
        return Integer.parseInt(b.getId().substring(b.getId().length()-1));
    }

    private void updateBoard() {
        char[] board=g.board;
        g.displayBoard();
        for(int i=0;i<board.length;i++){
            if(board[i]!='-') {
                buttons[i].setText(String.valueOf(board[i]));
            }
        }
    }

    public void firstMove(ActionEvent e) {
        g.botMove();
        updateBoard();
        startMe.setVisible(false);
        count++;
    }

    public void setReset(ActionEvent actionEvent) {
        buttons=new Button[]{btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
        g=new gamer();
        g.populateBoard();
        updateBoard();
        for (Button button : buttons) {
            button.setText(""); // Clear text
            button.setDisable(false); // Enable button
            button.setBackground(null); // Reset background
        }
        count=0;
        startMe.setVisible(true);
        Draw.setVisible(false);
        reset.setVisible(false);
    }
}
