import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class controller {
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
    controller(){
       buttons=new Button[]{btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8};
       gamer g=new gamer();
       updateBoard();
    }
    private void updateBoard() {
        char[] board=g.board;
        for(int i=0;i<board.length;i++){
            buttons[i].setText(String.valueOf(board[i]));
        }
    }
}
