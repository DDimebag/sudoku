package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/**
 * Makes transformations on a Board object.
 *
 * Swaps random rows and colums within boundaries,
 * triplets of rows and columns and rotates the board.
 */
public class ShuffleBoard {
	
	private List<Integer> first, second, third, fourth, fifth;
				
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
	
	public void shuffleBoard(Board b) {
		generateRandoms();
		
		swapRow(b, first.get(0), first.get(1));
		swapRow(b, second.get(0), second.get(1));
		swapRow(b,third.get(0), third.get(1));
		
		swapColumn(b, first.get(0), first.get(1));
		swapColumn(b, second.get(0), second.get(1));
		swapColumn(b, third.get(0), third.get(1));

		swapTripletsOfRow(b, fourth.get(0), fourth.get(1));
		swapTripletsOfColumn(b, fourth.get(0), fourth.get(1));
		
		rotateBoard(b, fifth.get(0));
	}
	
	public void swapRow(Board b, int row1, int row2) {
		int[] tmp = new int[9];
		
		for(int col = 0; col < 9; col++)
			tmp[col] = b.getValue(row1, col);
		
		for(int col = 0; col < 9; col++)
			b.setValue(row1, col, b.getValue(row2, col));
		
		for(int col = 0; col < 9; col++)
			b.setValue(row2, col, tmp[col]);
	}
	
	public void swapColumn(Board b, int col1, int col2) {
		int[] tmp = new int[9];
		
		for(int row = 0; row < 9; row++)
			tmp[row] = b.getValue(row, col1);
		
		for(int row = 0; row < 9; row++)
			b.setValue(row, col1, b.getValue(row, col2));
		
		for(int row = 0; row < 9; row++)
			b.setValue(row, col2, tmp[row]);
	}
	
	public void swapTripletsOfRow(Board b, int startIndex1, int startIndex2) {
		int[][] tmp = new int[3][9];
		
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 9; col++)
				tmp[row][col] = b.getValue(row + startIndex1, col);
		
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 9; col++)
				b.setValue(row + startIndex1, col, b.getValue(row + startIndex2, col));
		
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 9; col++)
				b.setValue(row + startIndex2, col, tmp[row][col]);
	}
	
	public void swapTripletsOfColumn(Board b, int startIndex1, int startIndex2) {
		int[][] tmp = new int[9][3];
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 3; col++)
				tmp[row][col] = b.getValue(row, col + startIndex1);
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 3; col++)
				b.setValue(row, col + startIndex1, b.getValue(row, col + startIndex2));
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 3; col++)
				b.setValue(row, col + startIndex2, tmp[row][col]);
	}
	
	public void rotateBoard(Board b, int n) {
		int[][] tmp = new int[9][9];
		
		while(n > 0) {
			for(int row = 0; row < 9; row++)
				for(int col = 0; col < 9; col++)
					tmp[row][col] = b.getValue(9 - col - 1, row);
			
			for(int row = 0; row < 9; row++)
				for(int col = 0; col < 9; col++)
					b.setValue(row, col, tmp[row][col]);
			n--;
		}
	}
}
