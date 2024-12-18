import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import static javafx.scene.paint.Color.rgb;

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
    @FXML
    Label result;
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
        result.setVisible(false);
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
                //popUp("X won the game!");
                return;
            }
            g.botMove();
            count+=2;
            startMe.setVisible(false);
            if(g.gameOver('o')){
                applyGameOver('o');
                //popUp("O won the game!");
                return;
            }
            updateBoard();
            System.out.println(count);
            if(count>=9){
                declareDraw();
            }
        }
    }

    private void popUp(String s) {
        Alert a=new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("GAME RESULT");
        a.setHeaderText(null);
        a.setContentText(s);
        a.showAndWait();
    }

    private void applyGameOver(char x) {
        reset.setVisible(true);
        highlightSquares();
        updateBoard();
        for(int i=0;i<g.board.length;i++){
            g.board[i]='z';
        }
        result.setText(x+" won the game!!");
        result.setVisible(true);
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
            System.out.println("winning blocks are:"+winningBlock[i]);
            buttons[winningBlock[i]].getStyleClass().add("winning-button");
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
            button.getStyleClass().remove("winning-button");
        }
        count=0;
        startMe.setVisible(true);
        Draw.setVisible(false);
        reset.setVisible(false);
        result.setVisible(false);
    }
}
