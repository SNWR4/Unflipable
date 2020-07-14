 class Main {
  public static void main(String[] args) {
    displayInstruction();
    int squaresPerSide=9;
    squaresPerSide+=1;
    char[] board = new char[(squaresPerSide)*(squaresPerSide)];
    setBoard(board,squaresPerSide);
    displayBoard(board, squaresPerSide);

    //Scanner sc= new Scanner(System.in);
    //System.out.println("");

  }
  public static void displayInstruction(){
      System.out.println("Thank you for playing flip! To objective of the game is to have the whole board be all X's or O's. When you flip a tile, the tiles directly above, below, left, and right of it also flip. Good Luck!");
  }

  public static char setRandomPiece()
  {
      // Randomly choose a piece to be placed (2 or 4)
      char pieceToPlace = 'X';
      if( Math.random()*2%2 < 1) {
          pieceToPlace = 'O';
      }
      return pieceToPlace;
  }

  public static void setBoard(char board[], int squaresPerSide){
      for(int i = 0; i < board.length;i++){
        if(i<squaresPerSide)
           board[i] = (char)(i+48);
        else if(i%(squaresPerSide)==0)
            board[i] = (char)((i/(squaresPerSide))+48);
        else
            board[i] = setRandomPiece();
      }

  }

  public static void displayBoard(char board[], int squaresPerSide){

    for(int i = 0; i<squaresPerSide;i++){
      for(int j = 0; j < squaresPerSide; j++){
        System.out.print(board[i*squaresPerSide+j]+" ");
      }
      System.out.println("");
    }

  }
}  