import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Board;

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
	public void testGetBoard() {
		int[][] arr = new int[Board.boardSize][Board.boardSize];
		int[][] arr2 = new int[Board.boardSize][Board.boardSize];
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				arr[row][col] = 1;
		
		Board board = new Board(arr);
		arr2 = board.getBoard();
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(1, arr2[row][col]);	
	}
	
	@Test
	public void testSetValue() {
		Board board = new Board();
		int[][] boardArray = new int [Board.boardSize][Board.boardSize];
		int row = 0, col = 0, value = 9;
		
		board.setValue(row, col, value);
		boardArray = board.getBoard();
		
		assertEquals(value, boardArray[row][col]);
	}
	
	@Test
	public void testSetValueException() {
		Board board = new Board();
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.setValue(10, 0, 0);
	    });
	    assertEquals("Illegal row index, row must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.setValue(-1, 0, 0);
	    });
	    assertEquals("Illegal row index, row must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.setValue(0, 10, 0);
	    });
	    assertEquals("Illegal column index, col must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.setValue(0, -1, 0);
	    });
	    assertEquals("Illegal column index, col must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.setValue(0, 0, 10);
	    });
	    assertEquals("Illegal value, value must be between 0 and 9", exception.getMessage());
	}
	
	@Test
	public void testGetValueException() {
		Board board = new Board();
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.getValue(10, 0);
	    });
	    assertEquals("Illegal row or column index, row and col must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	board.getValue(0, 10);
	    });
	    assertEquals("Illegal row or column index, row and col must be between 0 and 8", exception.getMessage());
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
		Board board3 = new Board();
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++) {
				int value = (int)(Math.random() * 9) + 1;
				board.setValue(row, col, value);
				board2.setValue(row, col, value);
				board3.setValue(row, col, (int)(Math.random() * 9) + 1);
			}
		
		assertTrue(Board.isEqual(board, board2));
		assertFalse(Board.isEqual(board, board3));
		assertFalse(Board.isEqual(board2, board3));
	}
	
	@Test
	public void testBoardToBool() {
		int[][] arr = new int[Board.boardSize][Board.boardSize];
		boolean[] tmp = new boolean[81];
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++) {
				if (col % 2 == 0)
					arr[row][col] = 0;
				else
					arr[row][col] = 1;
			}
						
		Board board = new Board(arr);
		
		tmp = board.boardToBool();
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++) {
				if (col % 2 == 0)
					assertFalse(tmp[row * 9 + col]);
				else
					assertTrue(tmp[row * 9 + col]);
			}
	}
}
