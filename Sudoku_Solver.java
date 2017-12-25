package sudokuSolver;
import java.util.Scanner;

public class SudokuSolver{
    private static Integer[][] grid = new Integer[9][9];

    public int[] findUnassigned(){
        int [] retArr = new int[2];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (grid[i][j] == 0){
                    retArr[0] = i;
                    retArr[1] = j;
                }
            }
        }
        return retArr;
    }

    public boolean safe(int row, int column, int num){
        for (int i = 0; i < 9; i++){
            if (grid[row][i] == num)
                return false;
        }
        for (int i = 0; i < 9; i++){
            if (grid[i][column] == num)
                return false;
        }
        int boxRow = row - row % 3;
        int boxCol = column - column % 3;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (grid[boxRow + i][boxCol + j] == num)
                    return false;
            }
        }
        return true;
    }

    public void readBoard(String board){
        int pos = 0;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board.charAt(pos) == '.')
                    grid[i][j] = 0;
                else
                    grid[i][j] = Character.getNumericValue(board.charAt(pos));
                pos++;
            }
        }
    }

    public void printGrid(){
        for (int i = 0; i < 9; i++){
            if (i % 3 == 0)
                System.out.print("\n");
            for (int j = 0; j < 9; j++){
                if (j % 3 == 0)
                    System.out.print(" ");
                if (grid[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print(grid[i][j].toString());
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public boolean solve(int square){
        if (square == 81)
            return true;
        int row = square / 9;
        int column = square % 9;
        if (grid[row][column] != 0){
        	return solve(square+1);
        }
        for (int num = 1; num <= 9; num++){
            if (safe(row, column, num)){
                grid[row][column] = num;
                if (solve(square + 1)){
                    return true;
                }
                grid[row][column] = 0;
            }
        }
        return false;
    }

    public static void main(String args[]){
        System.out.println("Input a board:");
        Scanner scanner = new Scanner(System.in);
        String board = scanner.nextLine();
        SudokuSolver sudoku = new SudokuSolver();
        sudoku.readBoard(board);
        if (sudoku.solve(0)){
            System.out.println("Solved:");
            sudoku.printGrid();
        }
        else 
            System.out.println("Cannot solve.");
        scanner.close();
    }
}

