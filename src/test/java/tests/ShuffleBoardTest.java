package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Board;
import model.ShuffleBoard;

public class ShuffleBoardTest {
 
	ShuffleBoard shuffle = new ShuffleBoard();
	
	int sourceRowArr[][] = { {1, 1, 1, 1, 1, 1, 1, 1, 1},
							 {2, 2, 2, 2, 2, 2, 2, 2, 2},
						     {3, 3, 3, 3, 3, 3, 3, 3, 3},
							 {4, 4, 4, 4, 4, 4, 4, 4, 4},
							 {5, 5, 5, 5, 5, 5, 5, 5, 5},
							 {6, 6, 6, 6, 6, 6, 6, 6, 6},
							 {7, 7, 7, 7, 7, 7, 7, 7, 7},
							 {8, 8, 8, 8, 8, 8, 8, 8, 8},
							 {9, 9, 9, 9, 9, 9, 9, 9, 9} };
	
	int sourceColArr[][] = { {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9},
							 {1, 2, 3, 4, 5, 6, 7, 8, 9} };
	
	@Test
	public void testSwapRow() {
		Board boardSource = new Board();
		
		int destArr[][] = { {2, 2, 2, 2, 2, 2, 2, 2, 2},
							{1, 1, 1, 1, 1, 1, 1, 1, 1},
							{3, 3, 3, 3, 3, 3, 3, 3, 3},
							{4, 4, 4, 4, 4, 4, 4, 4, 4},
							{5, 5, 5, 5, 5, 5, 5, 5, 5},
							{6, 6, 6, 6, 6, 6, 6, 6, 6},
							{7, 7, 7, 7, 7, 7, 7, 7, 7},
							{8, 8, 8, 8, 8, 8, 8, 8, 8},
							{9, 9, 9, 9, 9, 9, 9, 9, 9} };
		
		boardSource = new Board(sourceRowArr);
		
		shuffle.swapRow(boardSource, 0, 1);
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(boardSource.getValue(row, col), destArr[row][col]);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapRow(board, 10, 0);
	    });
	    assertEquals("Illegal row index, must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapRow(board, 0, 10);
	    });
	    assertEquals("Illegal row index, must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapRow(board, -1, 0);
	    });
	    assertEquals("Illegal row index, must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapRow(board, 0, -1);
	    });
	    assertEquals("Illegal row index, must be between 0 and 8", exception.getMessage());
	}
	
	@Test
	public void testSwapColumn() {
		Board boardSource = new Board();
		
		int destArr[][] = { {2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9},
							{2, 1, 3, 4, 5, 6, 7, 8, 9} };
		
		boardSource = new Board(sourceColArr);
		
		shuffle.swapColumn(boardSource, 0, 1);
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(boardSource.getValue(row, col), destArr[row][col]);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapColumn(board, 10, 0);
	    });
	    assertEquals("Illegal col index, must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapColumn(board, 0, 10);
	    });
	    assertEquals("Illegal col index, must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapColumn(board, -1, 0);
	    });
	    assertEquals("Illegal col index, must be between 0 and 8", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapColumn(board, 0, -1);
	    });
	    assertEquals("Illegal col index, must be between 0 and 8", exception.getMessage());
	}
	
	@Test
	public void testSwapTripletsOfRow() {
		Board boardSource = new Board();

		int destArr[][] = { {4, 4, 4, 4, 4, 4, 4, 4, 4},
							{5, 5, 5, 5, 5, 5, 5, 5, 5},
							{6, 6, 6, 6, 6, 6, 6, 6, 6},
							{1, 1, 1, 1, 1, 1, 1, 1, 1},
							{2, 2, 2, 2, 2, 2, 2, 2, 2},		
							{3, 3, 3, 3, 3, 3, 3, 3, 3},
							{7, 7, 7, 7, 7, 7, 7, 7, 7},
							{8, 8, 8, 8, 8, 8, 8, 8, 8},
							{9, 9, 9, 9, 9, 9, 9, 9, 9} };
		
		boardSource = new Board(sourceRowArr);
		
		shuffle.swapTripletsOfRow(boardSource, 0, 3);
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(boardSource.getValue(row, col), destArr[row][col]);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapTripletsOfRow(board, 0, 1);
	    });
	    assertEquals("Illegal startInedx, allowed indexes are: 0, 3, 6", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapTripletsOfRow(board, 1, 0);
	    });
	    assertEquals("Illegal startInedx, allowed indexes are: 0, 3, 6", exception.getMessage());
	}
	
	@Test
	public void testSwapTripletsOfColumn() {
		Board boardSource = new Board();

		int destArr[][] = { {4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9},
							{4, 5, 6, 1, 2, 3, 7, 8, 9} };
		
		boardSource = new Board(sourceColArr);
		
		shuffle.swapTripletsOfColumn(boardSource, 0, 3);
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(boardSource.getValue(row, col), destArr[row][col]);
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapTripletsOfColumn(board, 0, 1);
	    });
	    assertEquals("Illegal startInedx, allowed indexes are: 0, 3, 6", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board = new Board();
	    	shuffle.swapTripletsOfColumn(board, 1, 0);
	    });
	    assertEquals("Illegal startInedx, allowed indexes are: 0, 3, 6", exception.getMessage());
	}
	
	@Test
	public void testRotateBoard() {
		Board board = new Board(sourceRowArr);
		
		shuffle.rotateBoard(board, 0);	
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(sourceRowArr[row][col], board.getValue(row, col));
		
		shuffle.rotateBoard(board, 3);		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				assertEquals(sourceColArr[row][col], board.getValue(row, col));
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	shuffle.rotateBoard(board2, -1);
	    });
	    assertEquals("Illegal rotation number, must be 0 or positive", exception.getMessage());
	}
	
	@Test
	public void testGenerateRandoms() {
		
		shuffle.generateRandoms();
		
		assertTrue(shuffle.first.contains(0));
		assertTrue(shuffle.first.contains(1));
		assertTrue(shuffle.first.contains(2));
		
		assertTrue(shuffle.second.contains(3));
		assertTrue(shuffle.second.contains(4));
		assertTrue(shuffle.second.contains(5));
		
		assertTrue(shuffle.third.contains(6));
		assertTrue(shuffle.third.contains(7));
		assertTrue(shuffle.third.contains(8));
		
		assertTrue(shuffle.fourth.contains(0));
		assertTrue(shuffle.fourth.contains(3));
		assertTrue(shuffle.fourth.contains(6));
		
		assertTrue(shuffle.fifth.contains(0));
		assertTrue(shuffle.fifth.contains(1));
		assertTrue(shuffle.fifth.contains(2));
		assertTrue(shuffle.fifth.contains(3));
	}
}
