import java.util.*;

/*
*** Rules ***
At each step in time (tick), the following transitions occur:
1. Any live cell with fewer than two live neighbors dies, as if by loneliness.
2. Any live cell with more than three live neighbors dies, as if by overcrowding.
3. Any live cell with two or three live neighbors lives, unchanged, to the next generation.
4. Any dead cell with exactly three live neighbors comes to life.

*/

public class Coditation 
{
    // Value for live cell
    public static int live=1;
    
    // Value for Dead cell
    public static int dead=0;
    
    public static void main(String args[]) 
    {
        
        Scanner sc=new Scanner(System.in);
        
        // Initial Grid
        int[][] gridBoard = {{dead, dead, dead, dead, dead, dead, dead, dead},
                            {dead, dead, dead, live, dead, dead, dead, dead},
                            {dead, dead, live, live, dead, dead, dead, dead},
                            {dead, dead, dead, live, dead, dead, dead, dead},
                            {dead, dead, dead, live, dead, dead, dead, dead},
                            {dead, dead, dead, live, dead, dead, dead, dead},
                            {dead, dead, dead, live, dead, dead, dead, dead},
                            {dead, dead, dead, dead, dead, dead, dead, dead}};
                         
        String response="";
        
        do
        {
            System.out.println("Please enter your choice :");
            System.out.println("1. State of all cells after iterations");
            System.out.println("2. State of specific cell");
            
            System.out.println();
            int choice = sc.nextInt();
            System.out.println();
            
            Coditation coditation = new Coditation();
            switch(choice)
            {
                case 1:
                    System.out.println("Initial state of grid");
                    coditation.printBoard(gridBoard);
                    System.out.println("Enter Number of iterations");
                    int n = sc.nextInt();
                    gridBoard = coditation.getGridBoard(gridBoard, n);
                    System.out.println("Final Grid Board after " + n + " iterations:");
                    coditation.printFinalBoard(gridBoard);
                    break;
                    
                case 2:
                    System.out.println("Enter Cell number. eg(row 1 and column 2, you should enter 12 )");
                    int cellNumber = sc.nextInt();
                    coditation.getCellState(gridBoard, cellNumber);
                    break;
                
                default:
                     System.out.println("Please enter valid choice");
            }
            
            System.out.println();
            
            System.out.println("Do you want to continue?(YES or NO)");
            response= sc.next();
            
            System.out.println();
            
        }while(response.equalsIgnoreCase("YES"));
        
    }
    
    
    // Function to print final output grid in live or dead state
    private void printFinalBoard(int[][] board) 
    {
        for (int i = 0, rows = board.length ; i < rows ; i++) 
        {
            for (int j = 0, cols = board[i].length ; j < cols ; j++) {
                System.out.print((board[i][j] == 1? "Live" : "Dead") + ", ");
            } 
            System.out.println();
        }
        System.out.println();
    }
    
    // Function to print grid in 1 (Live) or 0 (dead) state
    private void printBoard(int[][] board) 
    {
        for (int i = 0, rows = board.length ; i < rows ; i++) 
        {
            for (int j = 0, cols = board[i].length ; j < cols ; j++) {
                System.out.print(board[i][j] + ",");
            } 
            System.out.println();
        }
        System.out.println();
    }
    
    // Function that gives grid after mentioned iterations
    private int[][] getGridBoard(int[][] board, int iterations)
    {
        for (int i = 0 ; i < iterations ; i++) 
        {
            System.out.println("Grid after iteration "+ (i+1) +":");
            board = getNextGridBoard(board);
            printBoard(board);
        }
        
        System.out.println();
        return board;
    }
    
    // Function that gives grid by calculating cells as it dies or live on
    public int[][] getNextGridBoard(int[][] board) 
    {
        int rows = board.length;
        int cols = board[0].length;
        int[][] tBoard = new int[rows][cols];
        
        if (rows == 0 || cols == 0) 
        {
            throw new IllegalArgumentException("Board should not be empty");
        }

        
        for (int row = 0 ; row < rows ; row++) {
	
            for (int col = 0 ; col < cols ; col++) {
                tBoard[row][col] = getNewStateOfCell(board[row][col], getCellLiveNeighbours(row, col, board));
            }
        }   
        return tBoard;
    }
    
    // Function that checks the cell exist in grid or not
    private Boolean checkCellExists(int[][] board, int row,int col)
    {
        int rows = board.length;
        int cols = board[0].length;
        
        if((row<0) || (row>rows-1) || (col<0) || (col>cols-1))
        {
            return false;
        }
        else
            return true;
    }
    
    // Function that gives number of live neighbors
    private int getCellLiveNeighbours(int row, int col, int[][] board)
    {
        int liveNeighbours=0;
        
        for(int i = row-1; i <= row+1; i++)
        {
            for(int j = col-1; j <= col+1; j++)
            {
                if(checkCellExists(board,i,j))
                {
                    if(!(i==row && j==col) && board[i][j]==live)
                    {
                        liveNeighbours++;
                    }
                }
            }
        }
        
        return liveNeighbours;
    }
    
    // Function that gives new state of cell 
    private int getNewStateOfCell(int currentState, int liveNeighbours) 
    {
        int newState=currentState;
        
        if(currentState == live)
        {
            if (liveNeighbours < 2) 
            {
                newState = dead;
            }

            else if (liveNeighbours == 2 || liveNeighbours == 3) 
            {
                newState = live;
            }

            else if (liveNeighbours > 3) 
            {
                newState = dead;
            }
        }
        
        else if (currentState == dead)
        {
            if(liveNeighbours == 3)
            {
                newState = live;
            }
        }
        
        return newState;
    }
    
    // Function that gives current cell state
    private void getCellState(int[][] board, int cellNumber)
    {
        int row = (cellNumber/10) - 1;
        int col = (cellNumber%10) - 1;
        String cellState = "";
        
        if(checkCellExists(board,row,col))
        {
            if(board[row][col] == live)
            {
                cellState = "Live";
            }
            
            else if(board[row][col] == dead)
            {
                cellState = "Dead";
            }
            
            System.out.println();
            System.out.println("Cell Number " + cellNumber + " : " + cellState);
            System.out.println();
        }
        else
        {
            System.out.println();
            System.out.println("Please Enter Valid Cell Number");
            System.out.println();
        }
    }
}

