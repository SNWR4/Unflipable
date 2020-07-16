 import java.util.Scanner; 
 class Main {
  public static void main(String[] args) {
    displayInstruction();
    int level = 1;
    char[] board = new char[100];
    setBoard(board,level+2);
    displayBoard(board, level+2);  

    runGame(board, level);

  }

  public static boolean completeBoard(char[] board,int level){
    char boardSymbol = board[level+3];
    for(int i = level+4; i < (level+2)*(level+2); i++){
      if(board[i]!=boardSymbol && i%(level+2)!=0)
        return false;
    }
    return true;
  }

  public static void flip(int row, int column, char[] board, int level){
    int index = row*(level+2)+column;
    flipHelper(board, index);

       //upper
       if(row!=1){
          flipHelper(board, index-level-2);
       }
       if(row!=level+1)
          flipHelper(board, index+level+2);
       if(column!=1)
           flipHelper(board, index-1);
       if(column!=level+1)
           flipHelper(board, index+1);
  }

  public static void flipHelper(char[] board, int index){
     if(board[index]=='X')
         board[index] = 'O';
      else
       board[index] = 'X';
  }
  
  public static void runGame(char[] board, int level){
    int row,column;
    Scanner sc= new Scanner(System.in);

    while(level<9){
      while(!completeBoard(board,level)){
        System.out.print("Which tile to flip? ");
        row = sc.nextInt(); 
        column = sc.nextInt();
        System.out.println();

        if(row-1>level || row < 1 || column-1 > level || column < 1){
          System.out.println("Invalid move. Try again \n");
          displayBoard(board, level+2);  
          continue;
        }

        System.out.println();

        flip(row,column, board, level);
        displayBoard(board, level+2);  

      }
      System.out.println("Good job! You beat level " +  level + "! Now onto the next level!\n");
      level++;
      setBoard(board, level+2);
      displayBoard(board, level+2);
    }

    System.out.println("Congratz! You beat all the levels! Guess it wasn't unflipable after all!");
  }

  public static void displayInstruction(){
      System.out.println("Thank you for playing flip! The objective of the game is to have the whole board be all X's or O's. When you flip a tile, the tiles directly above, below, left, and right of it also flip. You input which tile to flip in a row column format. Good Luck!");
      System.out.println();
  }

  public static char setRandomPiece()
  {
      // Randomly choose a piece to be placed (2 or 4)
      char pieceToPlace = 'X';
      if( Math.random()*2%2 < 1) 
          pieceToPlace = 'O';
      
      return pieceToPlace;
  }

  public static void setBoard(char board[], int squaresPerSide){
      int currentDim = squaresPerSide*squaresPerSide;
      for(int i = 0; i < currentDim;i++){
        if(i<squaresPerSide)
           board[i] = (char)(i+48);
        else if(i%(squaresPerSide)==0)
            board[i] = (char)((i/(squaresPerSide))+48);
        else
            board[i] = setRandomPiece();
      }
      if(completeBoard(board,squaresPerSide-2)){
        int rand = (int) (Math.random()*currentDim);
        while(rand<squaresPerSide || rand%squaresPerSide==0){
          rand = (int)(Math.random()*currentDim);
        }
        if(board[rand]=='O')
          board[rand] = 'X';
        else
          board[rand] = 'O';
      }
        

  }

  public static void displayBoard(char board[], int squaresPerSide){
    int level = squaresPerSide-2;
    for(int i = 0; i<squaresPerSide;i++){
      for(int j = 0; j < squaresPerSide; j++){
        System.out.print(board[i*squaresPerSide+j]+" ");
      }
      System.out.println("");
    }

    System.out.println("\nLevel: " + level + "\n");
  }

}  