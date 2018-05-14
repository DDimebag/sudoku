package model;

/**
 * Uses a backtracking algorithm to solve a {@code Board}.
 * 
 * Can solve the board in ascending and descending order and can determine if
 * there is multiple solution.
 *
 */
public class Solver {

	/**
	 * Solves the given {@code Board} object in ascending order.
	 * 
	 * @param b
	 *            the {@code Board} to be solved
	 * @return true  if there is no empty cells
	 * 		   false if you can't paste any value in
	 *         		 a cell without violation it triggers backtracking
	 * 
	 */
	public boolean solveBoardAsc(Board b) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (b.getValue(row, col) != 0) {
					continue;
				}
				for (int value = 1; value <= 9; value++) {
					if (isValid(b, row, col, value)) {
						b.setValue(row, col, value);
						if (solveBoardAsc(b)) {
							return true;
						} else {
							b.setValue(row, col, 0);
						}
					}
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Solves the given {@code Board} object in descending order.
	 * 
	 * @param b
	 *            the {@code Board} to be solved
	 * @return true  if there is no empty cells
	 * 		   false if you can't paste any value in
	 *         a cell without violation it triggers backtracking
	 * 
	 */
	public boolean solveBoardDesc(Board b) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (b.getValue(row, col) != 0) {
					continue;
				}
				for (int value = 9; value >= 1; value--) {
					if (isValid(b, row, col, value)) {
						b.setValue(row, col, value);
						if (solveBoardDesc(b)) {
							return true;
						} else {
							b.setValue(row, col, 0);
						}
					}
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if the {@code Board} has multiple solution.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @return true if it has multiple solution
	 */
	public boolean isMultipleSolution(Board b) {
		Board board1 = new Board(b);
		Board board2 = new Board(b);

		solveBoardAsc(board1);
		solveBoardDesc(board2);

		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++)
				if (board1.getValue(row, col) != (board2.getValue(row, col)))
					return true;
		return false;
	}

	/**
	 * Checks if the value you wanna paste in, has no conflicts in row, column and
	 * box.
	 * 
	 * @param b
	 *            the {@code Board} in which you want to past in the value
	 * @param row
	 *            the row index of the value
	 * @param col
	 *            the column index of the value
	 * @param value
	 *            the value to be pasted in
	 * @throws IllegalArgumentException
	 *             if the row or column indexes are out of bounds.
	 * @return true if the value is pastable
	 */
	public boolean isValid(Board b, int row, int col, int value) throws IllegalArgumentException {
		if (row < 0 || row > 8 || col < 0 || col > 8)
			throw new IllegalArgumentException("Illegal row or column index");
		else {
			if (!isInRow(b, row, value) && !isInColumn(b, col, value)
					&& !isInBox(b, row - row % 3, col - col % 3, value))
				return true;
			return false;
		}
	}

	/**
	 * Checks if the value you want to paste in, can be fond in the given row.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @param row
	 *            the given row
	 * @param value
	 *            the value to be pasted
	 * @return true if the value can be found in the given row
	 * @throws IllegalArgumentException
	 *             if the row index is invalid
	 */
	public boolean isInRow(Board b, int row, int value) throws IllegalArgumentException {
		if (row < 0 || row > 8 || value < 0 || value > 9)
			throw new IllegalArgumentException("Illegal row index or value");
		else {
			for (int i = 0; i < Board.boardSize; i++)
				if (b.getValue(row, i) == value)
					return true;
			return false;
		}
	}

	/**
	 * Checks if the value you want to paste in, can be found in the given column.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @param col
	 *            the given column
	 * @param value
	 *            the value to be pasted
	 * @return true if the value can be found in the given column
	 * @throws IllegalArgumentException
	 *             if the column index is invalid
	 */
	public boolean isInColumn(Board b, int col, int value) throws IllegalArgumentException {
		if (col < 0 || col > 8 || value < 0 || value > 9)
			throw new IllegalArgumentException("Illegal column index or value");
		else {
			for (int i = 0; i < Board.boardSize; i++)
				if (b.getValue(i, col) == value)
					return true;
			return false;
		}
	}

	/**
	 * Checks if the given value can be found in the given 3x3 box.
	 * 
	 * @param b
	 *            the {@code Board} to be checked
	 * @param startRow
	 *            the row index of the 3x3 box
	 * @param startCol
	 *            the column index of the 3x3 box
	 * @param value
	 *            the pasted value
	 * @return true if the value is pastable
	 * @throws IllegalArgumentException
	 *             if the indexes are out of bounds
	 */
	public boolean isInBox(Board b, int startRow, int startCol, int value) throws IllegalArgumentException {
		if (startRow < 0 || startRow > 8 || startCol < 0 || startCol > 8)
			throw new IllegalArgumentException("Illegal startRow or startCol");
		else {
			for (int i = 0; i < Board.boxSize; i++)
				for (int j = 0; j < Board.boxSize; j++)
					if (b.getValue(i + startRow, j + startCol) == value)
						return true;
			return false;
		}
	}

	/**
	 * Checks if the {@code Board} has any conflicts on it.
	 * 
	 * @param b
	 *            the {@code Board} to be checked
	 * @return true the {@code Board} has no conflicts.
	 */
	public boolean isNoConflictsOnBoard(Board b) {
		int value = 0;

		for (int row = 0; row < 9; row++)
			for (int col = 0; col < 9; col++) {
				if (b.getValue(row, col) != 0) {
					value = b.getValue(row, col);
					b.setValue(row, col, 0);
					if (!isValid(b, row, col, value)) {
						b.setValue(row, col, value);
						return false;
					} else
						b.setValue(row, col, value);
				}
			}
		return true;
	}
}
