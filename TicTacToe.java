import java.util.Scanner;
public class TicTacToe {
    private static char[] board=new char[9];
    public static void main(String[] args) {
        populateBoard();
        displayBoard();
        char currPLayer='x';
        boolean gamePlay=true;
        Scanner input=new Scanner(System.in);
        int i=0;
        while(true){
            System.out.println("Enter a move:");
            i=input.nextInt();
            insertMove(i,currPLayer);
            gamePlay=gameOver(currPLayer);
            display();
            if(!gamePlay){
                currPLayer=(currPLayer=='x')?'o':'x';
            }
            else{
                System.out.println("winner is "+currPLayer);
                break;
            }
        }
    }

    private static void display() {
        for(int i=0;i<9;i++){
            System.out.print(board[i]);
            if((i+1)%3==0){
                System.out.println();
            }
        }
        System.out.println("-------------");
    }

    private static boolean gameOver(char curr){
        int[][] winning={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int[] win:winning){
            if(curr==board[win[0]] && board[win[1]]==curr && board[win[2]]==curr){
                return true;
            }
        }
        return false;
    }

    private static void insertMove(int idx,char c){
         board[idx]=c;
    }
    private static void displayBoard(){
        System.out.println(board);
    }

    private static void populateBoard() {
        for(int i=0;i<board.length;i++){
            board[i]='-';
        }
    }
}
