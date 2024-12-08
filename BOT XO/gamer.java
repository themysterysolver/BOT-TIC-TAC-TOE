public class gamer {
    char[] board=new char[9];
    boolean isDraw(int count) {
        return count==9;
    }

    void display() {
        for(int i=0;i<9;i++){
            System.out.print(board[i]);
            if((i+1)%3==0){
                System.out.println();
            }
        }
        System.out.println("-------------");
    }

    boolean gameOver(char curr){
        int[][] winning={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        for(int[] win:winning){
            if(curr==board[win[0]] && board[win[1]]==curr && board[win[2]]==curr){
                return true;
            }
        }
        return false;
    }

    void insertMove(int idx,char c){
        board[idx]=c;
    }
    void displayBoard(){

        System.out.println(board);
    }

    void populateBoard() {
        for(int i=0;i<board.length;i++){
            board[i]='-';
        }
    }
    //main bot logic!
    public void botMove() {
        //PRIORITY-1:if my win exist,I play it!
        if(winningMove('o'))return;
        //PRIORITY-2:if he is going to win,I should block it!
        if(winningMove('x'))return;
        //PRIORITY-3:if I can fork,I play the fork,so I can win next!
        if(playFork('o'))return;
        //PRIORITY-4:if he is going to fork me,I should stop it!
        if(playFork('x'))return;
        //PRIORITY-5:Aim to get consecutive next 2 vacant spaces+centers+corners(over mids)!!
        optimalplay();
    }

    private void optimalplay() {
        if(board[4]=='-') {
            insertMove(4, 'o');
            return;
        }
        int[] corners={0,2,6,8};
        for (int i : corners) {
            if (board[i] == '-') {
                insertMove(i, 'o');
                return;
            }
        }
    }



    private boolean playFork(char c) {
        for(int i=0;i<9;i++){
            if(board[i]=='-') {
                insertMove(i,c);
                if(noOfWinning(c) > 1) {
                    removeMove(i);
                    insertMove(i,'o');
                    return true;
                }
                removeMove(i);
            }
        }
        return false;
    }

    private int noOfWinning(char c) {
        int count=0;
        for(int i=0;i<9;i++){
            if(board[i]=='-'){
                insertMove(i,c);
                if(gameOver(c)){
                    count++;
                }
                removeMove(i);
            }
        }
        return count;
    }

    private boolean winningMove(char c){
        for(int i=0;i<9;i++){
            if(board[i]=='-') {
                insertMove(i, c);
                if (gameOver(c)){
                    removeMove(i);
                    insertMove(i,'o');
                    return true;
                }
                removeMove(i);
            }
        }
        return false;
    }

    private void removeMove(int i) {
        board[i]='-';
    }
}
