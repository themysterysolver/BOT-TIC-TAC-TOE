import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.awt.event.ActionEvent;
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
    private Button[] buttons;
    private gamer g;
    public Controller(){

    }
    @FXML
    private void initialize(){
        buttons=new Button[]{btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
        g=new gamer();
        g.populateBoard();
        updateBoard();
    }
    @FXML
    private void onClick(javafx.event.ActionEvent e){
        Button b=(Button)e.getSource();
        int idx=getIdx(b);
        System.out.println("Button clicked:"+idx);
        g.insertMove(idx,'x');
        if(g.gameOver('x')){
            highlightSquares();
            updateBoard();
        }
        g.botMove();
        if(g.gameOver('o')){
            highlightSquares();
            updateBoard();
        }
        updateBoard();
    }

    private void highlightSquares() {
        int[] winningBlock=g.winBlock();
        for(int i=0;i<winningBlock.length;i++){
            buttons[winningBlock[i]].setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY,null)));
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
            if(board[i]!='-') {
                buttons[i].setDisable(true);
            }
        }
    }
}
