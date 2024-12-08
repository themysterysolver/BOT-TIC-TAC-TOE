import java.util.Scanner;
public class driver {
    public static void main(String[] args) {
        gamer g=new gamer();
        g.populateBoard();
        g.displayBoard();
        char currPLayer='x';
        boolean gamePlay=true;
        Scanner input=new Scanner(System.in);
        int i=0,count=0;
        while(true){
            System.out.println("Enter a move:");
            i=input.nextInt();
            if(i<0 || i>8|| g.board[i]!='-'){
                System.out.println("INVALID MOVE!");
                continue;
            }
            g.insertMove(i,currPLayer);
            gamePlay=g.gameOver(currPLayer);
            g.display();
            if(!gamePlay){
                //System.out.println("Bot is playing!!BOT MOVE!!");
                g.botMove();
                if(g.gameOver('o')){
                    System.out.println("Winner is O");
                    return;
                }
                g.display();
            }
            else{
                System.out.println("winner is "+currPLayer);
                break;
            }
            count++;
            if(g.isDraw(count)){
                System.out.println("It's a draw!");
                break;
            }
        }
    }


}
