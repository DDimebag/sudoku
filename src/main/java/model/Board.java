package model;
/**
 * Represents a Sudoku board.
 *
 */
public class Board {
	
	public static final int boardSize = 9;
	public int boxSize = 3;
	private int[][] board = new int[boardSize][boardSize];
	
	/**
	 * Initializes the {@code board} to zero.
	 */
	public Board() {
		for(int row = 0; row < boardSize; row++)
			for(int col = 0; col < boardSize; col++)
				board[row][col] = 0;
	}
	
	public Board(int[][] board) {
		for(int row = 0; row < boardSize; row++)
			for(int col = 0; col < boardSize; col++)
				this.board[row][col] = board[row][col];
	}
	
	public Board(Board b) {
		for(int row = 0; row < boardSize; row++)
			for(int col = 0; col < boardSize; col++)
				this.board[row][col] = b.getValue(row, col);
	}
	
	public int[][] getBoard(){
		return this.board;
	} 
	
	public void printBoard(){
		for(int row = 0; row < boardSize; row++) {
			for(int col = 0; col < boardSize; col++)
				System.out.print(board[row][col] + " ");
		System.out.println();
		}
	} 
	
	public void setValue(int row, int col, int value) {
		if(value >= 0 && value <= 9)
			board[row][col] = value;
		else
			System.out.println("invalid number");
	}
	
	public int getValue(int row, int col) {
		if(row >= 0 && row < 9 && col >= 0 && col < 9)
			return board[row][col];
		else
			return -1;
	}
	
	public static boolean isEqual(Board board1, Board board2) {
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				if(board1.getValue(row, col) != board2.getValue(row, col))
					return false;
		return true;
		
	}
	
	public boolean[] isZero(Board b) {
		boolean[] arr = new boolean[81];
		for(int i = 0; i < 81; i++)
			arr[i] = true;
		
		for(int i = 0; i < 81; i++)
			if(b.getValue(i / 9, i % 9) == 0)
				arr[i] = false;
		
		return arr;
	}
}
