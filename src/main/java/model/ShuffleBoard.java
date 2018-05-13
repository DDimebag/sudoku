package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Makes transformations on a Board object.
 *
 * Swaps random rows and colums within boundaries, triplets of rows and columns
 * and rotates the board.
 */
public class ShuffleBoard {

	public List<Integer> first, second, third, fourth, fifth;

	/**
	 * Initialises the lists, and shuffles them.
	 */
	public void generateRandoms() {
		first = Arrays.asList(0, 1, 2);
		second = Arrays.asList(3, 4, 5);
		third = Arrays.asList(6, 7, 8);
		fourth = Arrays.asList(0, 3, 6);
		fifth = Arrays.asList(0, 1, 2, 3);

		Collections.shuffle(first);
		Collections.shuffle(second);
		Collections.shuffle(third);
		Collections.shuffle(fourth);
		Collections.shuffle(fifth);
	}

	/**
	 * Does the permutations on board.
	 * 
	 * @param b the {@code Board}
	 */
	public void shuffleBoard(Board b) {
		generateRandoms();

		swapRow(b, first.get(0), first.get(1));
		swapRow(b, second.get(0), second.get(1));
		swapRow(b, third.get(0), third.get(1));

		swapColumn(b, first.get(0), first.get(1));
		swapColumn(b, second.get(0), second.get(1));
		swapColumn(b, third.get(0), third.get(1));

		swapTripletsOfRow(b, fourth.get(0), fourth.get(1));
		swapTripletsOfColumn(b, fourth.get(0), fourth.get(1));

		rotateBoard(b, fifth.get(0));
	}

	/**
	 * Swaps two rows in the given {@code Board} object.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @param row1
	 *            the index of the first row to be swapped
	 * @param row2
	 *            the index of the second row to be swapped
	 * @throws IllegalArgumentException
	 *             if the row inedexes are out of bounds
	 */
	public void swapRow(Board b, int row1, int row2) throws IllegalArgumentException {
		if (row1 < 0 || row1 > 8 || row2 < 0 || row2 > 8)
			throw new IllegalArgumentException("Illegal row index, must be between 0 and 8");
		else {
			int[] tmp = new int[Board.boardSize];

			for (int col = 0; col < Board.boardSize; col++)
				tmp[col] = b.getValue(row1, col);

			for (int col = 0; col < Board.boardSize; col++)
				b.setValue(row1, col, b.getValue(row2, col));

			for (int col = 0; col < Board.boardSize; col++)
				b.setValue(row2, col, tmp[col]);
		}
	}

	/**
	 * Swaps two columns in the given {@code Board} object.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @param col1
	 *            the index of the first column to be swapped
	 * @param col2
	 *            the index of the second column to be swapped
	 * @throws IllegalArgumentException
	 *             if the column inedexes are out of bounds
	 */
	public void swapColumn(Board b, int col1, int col2) throws IllegalArgumentException {
		if (col1 < 0 || col1 > 8 || col2 < 0 || col2 > 8)
			throw new IllegalArgumentException("Illegal col index, must be between 0 and 8");
		else {
			int[] tmp = new int[Board.boardSize];

			for (int row = 0; row < Board.boardSize; row++)
				tmp[row] = b.getValue(row, col1);

			for (int row = 0; row < Board.boardSize; row++)
				b.setValue(row, col1, b.getValue(row, col2));

			for (int row = 0; row < Board.boardSize; row++)
				b.setValue(row, col2, tmp[row]);
		}
	}

	/**
	 * Swaps triplets of rows in the given {@code Board} object.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @param startIndex1
	 *            the start index of the first tirplet of rows
	 * @param startIndex2
	 *            the start index of the second triplets of rows
	 * @throws IllegalArgumentException
	 *             if the start inedexes are out of bounds
	 */
	public void swapTripletsOfRow(Board b, int startIndex1, int startIndex2) throws IllegalArgumentException {
		if ((startIndex1 == 0 || startIndex1 == 3 || startIndex1 == 6)
				&& (startIndex2 == 0 || startIndex2 == 3 || startIndex2 == 6)) {
			int[][] tmp = new int[3][9];

			for (int row = 0; row < 3; row++)
				for (int col = 0; col < Board.boardSize; col++)
					tmp[row][col] = b.getValue(row + startIndex1, col);

			for (int row = 0; row < 3; row++)
				for (int col = 0; col < Board.boardSize; col++)
					b.setValue(row + startIndex1, col, b.getValue(row + startIndex2, col));

			for (int row = 0; row < 3; row++)
				for (int col = 0; col < Board.boardSize; col++)
					b.setValue(row + startIndex2, col, tmp[row][col]);
		} else
			throw new IllegalArgumentException("Illegal startInedx, allowed indexes are: 0, 3, 6");
	}

	/**
	 * Swaps triplets of columns in the given {@code Board} object.
	 * 
	 * @param b
	 *            the {@code Board}
	 * @param startIndex1 the start index of the first tirplet of columns
	 * @param startIndex2 the start index of the second triplets of columns
	 * @throws IllegalArgumentException
	 *             if the start inedexes are out of bounds
	 */
	public void swapTripletsOfColumn(Board b, int startIndex1, int startIndex2) throws IllegalArgumentException {
		if ((startIndex1 == 0 || startIndex1 == 3 || startIndex1 == 6)
				&& (startIndex2 == 0 || startIndex2 == 3 || startIndex2 == 6)) {
			int[][] tmp = new int[9][3];

			for (int row = 0; row < Board.boardSize; row++)
				for (int col = 0; col < 3; col++)
					tmp[row][col] = b.getValue(row, col + startIndex1);

			for (int row = 0; row < Board.boardSize; row++)
				for (int col = 0; col < 3; col++)
					b.setValue(row, col + startIndex1, b.getValue(row, col + startIndex2));

			for (int row = 0; row < Board.boardSize; row++)
				for (int col = 0; col < 3; col++)
					b.setValue(row, col + startIndex2, tmp[row][col]);
		} else
			throw new IllegalArgumentException("Illegal startInedx, allowed indexes are: 0, 3, 6");
	}

	/**
	 * Rotates the board n times.
	 * 
	 * @param b
	 *            the {@code Board} to be rotated
	 * @param n
	 *            the number of rotations
	 * @throws IllegalArgumentException
	 *             if the rotation number is negative
	 */
	public void rotateBoard(Board b, int n) throws IllegalArgumentException {
		if (n < 0)
			throw new IllegalArgumentException("Illegal rotation number, must be 0 or positive");
		else {
			int[][] tmp = new int[Board.boardSize][Board.boardSize];

			while (n > 0) {
				for (int row = 0; row < Board.boardSize; row++)
					for (int col = 0; col < Board.boardSize; col++)
						tmp[row][col] = b.getValue(Board.boardSize - col - 1, row);

				for (int row = 0; row < Board.boardSize; row++)
					for (int col = 0; col < Board.boardSize; col++)
						b.setValue(row, col, tmp[row][col]);
				n--;
			}
		}
	}
}
