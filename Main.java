 import java.util.Scanner; 
 class Main {
  public static void main(String[] args) {
    displayInstruction();
    int level = 1;
    char[] board = new char[100];
    int cap = 9;
    setBoard(board,level+2);
    displayBoard(board, level+2);  

    runGame(board, level, cap);

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
  
  public static boolean decipherInput(String input1,String input2, char[] board, int level){
    if(input1.charAt(0)=='q' || input1.charAt(0)=='Q' ||input2.charAt(0)=='q' || input2.charAt(0)=='Q' ){
      System.out.println("You've quit the game. Guess it was unflippable after all!");
      return true;
    }
    else if (input1.charAt(0) == 'S' || input1.charAt(0) == 's'|| input2.charAt(0) == 'S' || input2.charAt(0) == 's'){
              System.out.println("Scrambling the board.\n");
              setBoard(board, level+2);
    }
    else if(input1.charAt(0)<58 && input1.charAt(0)>47 && input2.charAt(0)<58 && input2.charAt(0)>47 ){
      int row = input1.charAt(0)-48;
      int column = input2.charAt(0)-48;
      if(row-1>level || row < 1 || column-1 > level || column < 1){
              System.out.println("Invalid dimensions. Try again");
      }
      else
       flip(row,column, board, level);
        
      
    }
    else{
      System.out.println("Invalid input. Try again");
    }

    return false;

  }
  public static void runGame(char[] board, int level, int cap){
    int row,column;
    int tokenCount = 2;
    int count = 0;
    String input1,input2;
    Scanner sc= new Scanner(System.in);

    while(level<cap){
      while(!completeBoard(board,level)){
        System.out.print("Which tile to flip? ");
        input1 = sc.next();
        input2 = sc.next();
        if(decipherInput(input1,input2, board, level))
            return;
        System.out.println();
        displayBoard(board, level+2);  
      }
      System.out.println("Good job! You beat level " +  level + "! Now onto the next level!\n");
      level++;
      if(level!=cap){
        setBoard(board, level+2);
        displayBoard(board, level+2);
      }
    }

    System.out.println("Congratz! You beat all the levels! Guess it wasn't unflipable after all!");
  }

  public static void displayInstruction(){
      System.out.println("Thank you for playing flip! The objective of the game is to have the whole board be all X's or O's. When you flip a tile, the tiles directly above, below, left, and right of it also flip. You input which tile to flip in a row column format. (Example: row column) The game will always assume 2 inputs of row and column. If either of them is an 's' the board will scramble. If either of them is a 'q' the game will quit. Good Luck!");
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