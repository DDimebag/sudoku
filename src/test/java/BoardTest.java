import model.Board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardTest {
	
	
	@Test
	public void testConstructorOne() {
		Board board = new Board();
		int[][] boardArray = new int[9][9];
		boardArray = board.getBoard();
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				assertEquals(0, boardArray[row][col]);
	}
	
	@Test
	public void testConstructorTwo() {
		int[][] array = new int[9][9];
		int[][] array2 = new int[9][9];

		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				array[row][col] = (int)(Math.random() * 9) + 1;
		
		Board board = new Board(array);
		array2 = board.getBoard();
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				assertEquals(array[row][col], array2[row][col]);
	}
	
	@Test
	public void testConstructorThree(){
		Board board = new Board();

		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				board.setValue(row, col, (int)(Math.random() * 9) + 1);
				
		Board board2 = new Board(board);
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++)
				assertEquals(board.getValue(row, col), board2.getValue(row, col));	
	}
	
	@Test
	public void testSetValue() {
		Board board = new Board();
		int[][] boardArray = new int [9][9];
		int row = 0, col = 0, value = 9;
		
		board.setValue(row, col, value);
		boardArray = board.getBoard();
		
		assertEquals(value, boardArray[row][col]);
	}
	
	@Test
	public void testGetValue() {
		Board board = new Board();
		int[][] boardArray = new int [9][9];
		boardArray = board.getBoard();
		int row = 0, col = 0, value;
		
		value = board.getValue(row, col);
		
		assertEquals(boardArray[row][col], value);
	}
	
	@Test
	public void testIsEqual() {
		Board board = new Board();
		Board board2 = new Board();
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++) {
				int value = (int)(Math.random() * 9) + 1;
				board.setValue(row, col, value);
				board2.setValue(row, col, value);
			}
		
		assertTrue(Board.isEqual(board, board2));
	}
}
