import java.util.*;

public class Board {

   Cell[][] board = new Cell [9][];
   private int bCells, filledCells;

   public Board()
   {
      int j = 5;
      for ( int i = 0; i < 5; i++)
      {
         board[i] = new Cell[j];
         j++;
      }

      j = 8;
      for ( int i = 5; i < 9; i++)
      {
         board[i] = new Cell[j];
         j--;
      }
      initialize();
   }

   public int getRows(){
      return board.length;
   }

   public int getColumn(int row){
      return board[row].length;
   }

   public void initialize()
   {
      for(int i = 0; i < board.length; i++){
         for(int j = 0; j < board[i].length; j++)
            board[i][j] = Cell.EMPTY;
      }
      board[3][3] = Cell.BLACK;
      board[3][4] = Cell.WHITE;
//      board[4][3] = Cell.WHITE;
      board[4][4] = Cell.WHITE;
      board[4][5] = Cell.BLACK;
//      board[5][3] = Cell.BLACK;
//      board[5][4] = Cell.WHITE;
      bCells = 2;
      filledCells = 4;
   }

   public boolean isValue(Cell c, int row, int columnn){
      return board[row][columnn] == c;
   }

   public boolean isBlack(int x, int y)
   {
      return board[x][y] == Cell.BLACK;
   }

   public int getNumBlack(){
      return bCells;
   }

   public int getNumWhite(){
      return filledCells - bCells;
   }

   public boolean isWhite(int x, int y)
   {
      return board[x][y] == Cell.WHITE;
   }

   public boolean isEmpty(int x, int y)
   {
      return board[x][y] == Cell.EMPTY;
   }

   public boolean isGameOver(){
      return (getMoves(Cell.BLACK).length == 0 && getMoves(Cell.WHITE).length == 0);
   }

   public void play(Cell c, int row, int column)
   {
      if(canMoveHere(c, row, column))
      {
         filledCells++;
         int changedCells = 0;
         board[row][column] = c;
         Cell otherColor;
         if(c == Cell.BLACK)
            otherColor = Cell.WHITE;
         else
            otherColor = Cell.BLACK;
         //check west direction
         if(column > 1 && board[row][column - 1] == otherColor){
            for(int i = column - 1; i >= 0; i--){
               if(board[row][i] == c){
                  for(int j = column - 1; j > i; j--){
                     board[row][j] = c;
                     changedCells++;
                  }
                  break;
               }
               else if(isEmpty(row, i))
                  break;
            }
            //check east direction
         }
         if(column < board[row].length - 2 && board[row][column + 1] == otherColor){
            for(int i = column + 1; i < board[row].length; i++){
               if(board[row][i] == c){
                  for(int j = column + 1; j < i; j++){
                     board[row][j] = c;
                     changedCells++;
                  }
                  break;
               }
               else if(isEmpty(row, i))
                  break;
            }
         }
         //NorthWest direction
         if(isValidLocation(row - 1, getNWColum(row, column)) &&
                 isValidLocation(row - 2, getNWColum(row - 1, getNWColum(row, column))) &&
                 board[row - 1][getNWColum(row, column)] == otherColor){
            int checkR = row - 2, checkC = getNWColum(row - 1, getNWColum(row, column));
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c){
                  int changeC = getNWColum(row, column);
                  for(int changeR = row - 1; changeR > checkR; changeR--){
                     board[changeR][changeC] = c;
                     changeC = getNWColum(changeR, changeC);
                     changedCells++;
                  }
                  break;
               }
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getNWColum(checkR, checkC);
               checkR--;
            }
            //northeast direction
         }
         if(isValidLocation(row - 1, getNWColum(row, column) + 1) &&
                 isValidLocation(row - 2, getNWColum(row - 1, getNWColum(row, column) + 1) + 1) &&
                 board[row - 1][getNWColum(row, column) + 1] == otherColor){
            int checkR = row - 2, checkC = getNWColum(row - 1, getNWColum(row, column) + 1) + 1;
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c){
                  int changeC = getNWColum(row,column) + 1;
                  for(int changeR = row - 1; changeR > checkR; changeR--){
                     board[changeR][changeC] = c;
                     changeC = getNWColum(changeR, changeC) + 1;
                     changedCells++;
                  }
                  break;
               }
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getNWColum(checkR, checkC) + 1;
               checkR--;
            }
            //southwest direction
         }
         if(isValidLocation(row + 1, getSWColum(row, column)) &&
                 isValidLocation(row + 2, getSWColum(row + 1, getSWColum(row, column))) &&
                 board[row + 1][getSWColum(row, column)] == otherColor){
            int checkR = row + 2, checkC = getSWColum(row + 1, getSWColum(row, column));
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c){
                  int changeC = getSWColum(row, column);
                  for(int changeR = row + 1; changeR < checkR; changeR++){
                     board[changeR][changeC] = c;
                     changeC = getSWColum(changeR, changeC);
                     changedCells++;
                  }
                  break;
               }
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getSWColum(checkR, checkC);
               checkR++;
            }
            //southeast direction
         }
         if(isValidLocation(row + 1, getSWColum(row, column) + 1) &&
                 isValidLocation(row + 2, getSWColum(row + 1, getSWColum(row, column) + 1) + 1) &&
                 board[row + 1][getSWColum(row, column) + 1] == otherColor){
            int checkR = row + 2, checkC = getSWColum(row + 1, getSWColum(row, column) + 1) + 1;
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c){
                  int changeC = getSWColum(row, column) + 1;
                  for(int changeR = row + 1; changeR < checkR; changeR++){
                     board[changeR][changeC] = c;
                     changeC = getSWColum(changeR, changeC) + 1;
                     changedCells++;
                  }
                  break;
               }
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getSWColum(checkR, checkC) + 1;
               checkR++;
            }
         }
         if(c == Cell.BLACK)
            bCells += changedCells + 1;
         else
            bCells -= changedCells;
      }
   }


   public int playRating(Cell c, int row, int column){
      int rating = 0;
      Cell otherColor;
      if(c == Cell.BLACK)
         otherColor = Cell.WHITE;
      else
         otherColor = Cell.BLACK;
      //check west direction
      if(column > 1 && board[row][column - 1] == otherColor){
         for(int i = column - 2; i >= 0; i--){
            if(board[row][i] == c){
               rating += (column - 1) - (i);
               break;
            }
            else if(isEmpty(row, i))
               break;
         }
         //check east direction
      }
      if(column < board[row].length - 2 && board[row][column + 1] == otherColor){
         for(int i = column + 2; i < board[row].length; i++){
            if(board[row][i] == c){
               rating += (i) - (column + 1);
               break;
            }
            else if(isEmpty(row, i))
               break;
         }
      }
      //NorthWest direction
      if(isValidLocation(row - 1, getNWColum(row, column)) &&
              isValidLocation(row - 2, getNWColum(row - 1, getNWColum(row, column))) &&
              board[row - 1][getNWColum(row, column)] == otherColor){
         int checkR = row - 2, checkC = getNWColum(row - 1, getNWColum(row, column));
         while(isValidLocation(checkR, checkC)){
            if(board[checkR][checkC] == c){
               rating += (row - 1) - (checkR);
               break;
            }
            else if(isEmpty(checkR, checkC))
               break;
            checkC = getNWColum(checkR, checkC);
            checkR--;
         }
         //northeast direction
      }
      if(isValidLocation(row - 1, getNWColum(row, column) + 1) &&
              isValidLocation(row - 2, getNWColum(row - 1, getNWColum(row, column) + 1) + 1) &&
              board[row - 1][getNWColum(row, column) + 1] == otherColor){
         int checkR = row - 2, checkC = getNWColum(row - 1, getNWColum(row, column) + 1) + 1;
         while(isValidLocation(checkR, checkC)){
            if(board[checkR][checkC] == c){
               rating += (row - 1) - (checkR);
               break;
            }
            else if(isEmpty(checkR, checkC))
               break;
            checkC = getNWColum(checkR, checkC) + 1;
            checkR--;
         }
         //southwest direction
      }
      if(isValidLocation(row + 1, getSWColum(row, column)) &&
              isValidLocation(row + 2, getSWColum(row + 1, getSWColum(row, column))) &&
              board[row + 1][getSWColum(row, column)] == otherColor){
         int checkR = row + 2, checkC = getSWColum(row + 1, getSWColum(row, column));
         while(isValidLocation(checkR, checkC)){
            if(board[checkR][checkC] == c){
               rating += (checkR - 1) - row;
               break;
            }
            else if(isEmpty(checkR, checkC))
               break;
            checkC = getSWColum(checkR, checkC);
            checkR++;
         }
         //southeast direction
      }
      if(isValidLocation(row + 1, getSWColum(row, column) + 1) &&
              isValidLocation(row + 2, getSWColum(row + 1, getSWColum(row, column) + 1) + 1) &&
              board[row + 1][getSWColum(row, column) + 1] == otherColor){
         int checkR = row + 2, checkC = getSWColum(row + 1, getSWColum(row, column) + 1) + 1;
         while(isValidLocation(checkR, checkC)){
            if(board[checkR][checkC] == c){
               rating += (checkR - 1) - row;
               break;
            }
            else if(isEmpty(checkR, checkC))
               break;
            checkC = getSWColum(checkR, checkC) + 1;
            checkR++;
         }
      }
      return rating;
   }


   /**
    * Gets all the locations where Black can move
    * The array will either be empty or have order pairs
    * indicating row and column.
    *
    * @return two-dimensional array
    */
   public int[][] getMoves(Cell c){
      ArrayList<Integer> rowMoves = new ArrayList<Integer>();
      ArrayList<Integer> columnMoves = new ArrayList<Integer>();
      if(c != Cell.EMPTY){
         for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
               if(canMoveHere(c, i, j)){
                  rowMoves.add(i);
                  columnMoves.add(j);
               }
            }
         }
      }
      int[][] moves = new int[rowMoves.size()][2];
      for(int i = 0; i < rowMoves.size(); i++){
         moves[i][0] = rowMoves.get(i).intValue();
         moves[i][1] = columnMoves.get(i).intValue();
      }
      return moves;
   }

   /**
    * Checks if black can place a token at the given location
    *
    * @param int indicating the row
    * @param int indicating the column
    * @return boolean
    */
   public boolean canMoveHere(Cell c, int row, int column){
      if(isValidLocation(row, column) && isEmpty(row, column) &&
              c != Cell.EMPTY){
         Cell otherColor;
         if(c == Cell.BLACK)
            otherColor = Cell.WHITE;
         else
            otherColor = Cell.BLACK;
         //check west direction
         if(column > 1 && board[row][column - 1] == otherColor){
            for(int i = column - 2; i >= 0; i--){
               if(board[row][i] == c)
                  return true;
               else if(isEmpty(row, i))
                  break;
            }
            //check east direction
         }
         if(column < board[row].length - 2 && board[row][column + 1] == otherColor){
            for(int i = column + 2; i < board[row].length; i++){
               if(board[row][i] == c)
                  return true;
               else if(isEmpty(row, i))
                  break;
            }
         }
         //NorthWest direction
         if(isValidLocation(row - 1, getNWColum(row, column)) &&
                 isValidLocation(row - 2, getNWColum(row - 1, getNWColum(row, column))) &&
                 board[row - 1][getNWColum(row, column)] == otherColor){
            int checkR = row - 2, checkC = getNWColum(row - 1, getNWColum(row, column));
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c)
                  return true;
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getNWColum(checkR, checkC);
               checkR--;
            }
            //northeast direction
         }
         if(isValidLocation(row - 1, getNWColum(row, column) + 1) &&
                 isValidLocation(row - 2, getNWColum(row - 1, getNWColum(row, column) + 1) + 1) &&
                 board[row - 1][getNWColum(row, column) + 1] == otherColor){
            int checkR = row - 2, checkC = getNWColum(row - 1, getNWColum(row, column) + 1) + 1;
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c)
                  return true;
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getNWColum(checkR, checkC) + 1;
               checkR--;
            }
            //southwest direction
         }
         if(isValidLocation(row + 1, getSWColum(row, column)) &&
                 isValidLocation(row + 2, getSWColum(row + 1, getSWColum(row, column))) &&
                 board[row + 1][getSWColum(row, column)] == otherColor){
            int checkR = row + 2, checkC = getSWColum(row + 1, getSWColum(row, column));
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c)
                  return true;
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getSWColum(checkR, checkC);
               checkR++;
            }
            //southeast direction
         }
         if(isValidLocation(row + 1, getSWColum(row, column) + 1) &&
                 isValidLocation(row + 2, getSWColum(row + 1, getSWColum(row, column) + 1) + 1) &&
                 board[row + 1][getSWColum(row, column) + 1] == otherColor){
            int checkR = row + 2, checkC = getSWColum(row + 1, getSWColum(row, column) + 1) + 1;
            while(isValidLocation(checkR, checkC)){
               if(board[checkR][checkC] == c)
                  return true;
               else if(isEmpty(checkR, checkC))
                  break;
               checkC = getSWColum(checkR, checkC) + 1;
               checkR++;
            }
         }
      }
      return false;
   }

   /**
    * Checks if the location given is on the board
    *
    * @param int depicting x location
    * @param int depicting y location
    * @return boolean
    */
   private boolean isValidLocation(int x, int y){
      return ((x >= 0 && x < board.length) &&
              (y >=0 && y < board[x].length));
   }

   /**
    * Gets the integer representing the column of the
    * location to the North West of the given location
    *
    * NOTE: In order to get North East add one to value returned
    *
    * @param int depicting x location
    * @param int depicting y location
    * @return boolean
    */
   private int getNWColum(int x, int y){
      if(x <= (board.length / 2))
         return y - 1;
      else
         return y;
   }


   /**
    * Gets the integer representing the column of the
    * location to the South West of the given location
    *
    * NOTE: In order to get South East add one to value returned
    *
    * @param int depicting x location
    * @param int depicting y location
    * @return boolean
    */
   private int getSWColum(int x, int y){
      if(x < (board.length / 2))
         return y;
      else
         return y - 1;
   }

   public String toString()
   {
      String display = "";
      for(int i = 0; i < board.length; i++){
         for(int spaces = 0; spaces < Math.abs((board.length / 2) - i); spaces++)
            display += "  ";
         for(int j = 0; j < board[i].length; j++)
            display += "[" + board[i][j] + "] ";
         display += "\n";
      }

      return display;

   }
}
