package model;

/**
 * Represents a Sudoku board.
 *
 */
public class Board {

	/**
	 * Size of the {@code Board}.
	 */
	public static final int boardSize = 9;
	/**
	 * Size of the boxes within a {@code Board}.
	 */
	public static final int boxSize = 3;

	private int[][] board = new int[boardSize][boardSize];
	
	/**
	 * Initializes the {@code board} to zero.
	 */
	public Board() {
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				board[row][col] = 0;
	}

	/**
	 * Initializes the {@code board} with the given parameter.
	 * 
	 * @param board
	 *            the array to copy
	 */
	public Board(int[][] board) {
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				this.board[row][col] = board[row][col];
	}

	/**
	 * Initializes the {@code board} with the given {@code Board}'s {@code board}.
	 * 
	 * @param board
	 *            the given {@code Board} object
	 */
	public Board(Board board) {
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				this.board[row][col] = board.getValue(row, col);
	}

	/**
	 * Gives you the array representation of the {@code Board}.
	 * 
	 * @return the 2d array
	 */
	public int[][] getBoard() {
		return this.board;
	}

	/**
	 * Sets the given tile to {@code value}.
	 * 
	 * @param row
	 *            row index of the {@code Board}
	 * @param col
	 *            column index of the {@code Board}
	 * @param value
	 *            the value to be set
	 * @throws IllegalArgumentException
	 *             if row, column indexes or the {@code value} is out of bounds
	 */
	public void setValue(int row, int col, int value) throws IllegalArgumentException {
		if ((row >= 0 && row < 9) && (col >= 0 && col < 9) && (value >= 0 && value <= 9))
			board[row][col] = value;
		else if (row < 0 || row > 8)
			throw new IllegalArgumentException("Illegal row index, row must be between 0 and 8");
		else if (col < 0 || col > 8)
			throw new IllegalArgumentException("Illegal column index, col must be between 0 and 8");
		else
			throw new IllegalArgumentException("Illegal value, value must be between 0 and 9");
	}

	/**
	 * Gives a value back from the {@code board}.
	 * 
	 * @param row
	 *            the row index
	 * @param col
	 *            the column index
	 * @return the vaue, of the {@code board} which situated in the {@code row} and
	 *         {@code col} indexes
	 * @throws IllegalArgumentException if row or column is out of bounds
	 */
	public int getValue(int row, int col) throws IllegalArgumentException {
		if ((row >= 0 && row < 9) && (col >= 0 && col < 9))
			return board[row][col];
		else
			throw new IllegalArgumentException("Illegal row or column index, row and col must be between 0 and 8");
	}

	/**
	 * Compare two {@code Board} objects.
	 * 
	 * @param board1
	 *            the first {@code Board} to compare
	 * @param board2
	 *            the second {@code Board} to compare
	 * @return true if the two has the same values
	 */
	public static boolean isEqual(Board board1, Board board2) {
		for (int row = 0; row < boardSize; row++)
			for (int col = 0; col < boardSize; col++)
				if (board1.getValue(row, col) != board2.getValue(row, col))
					return false;
		return true;

	}

	/**
	 * Transforms a {@code board} to a boolean array,
	 * where the 0 represents false.
	 * 
	 * @return a boolean array that represents a {@code Board}'s {@code board}.
	 */
	public boolean[] boardToBool() {
		boolean[] arr = new boolean[81];
		for (int i = 0; i < 81; i++)
			arr[i] = true;

		for (int i = 0; i < 81; i++)
			if (this.getValue(i / 9, i % 9) == 0)
				arr[i] = false;

		return arr;
	}
}
