package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Board;
import model.Solver;

public class SolverTest {
	Solver solver = new Solver();
	
	int noConflictArr[][] = { {1, 0, 0, 0, 0, 0, 0, 0, 0},
							  {0, 2, 0, 0, 0, 0, 0, 0, 0},
							  {0, 0, 3, 0, 0, 0, 0, 0, 0},
							  {0, 0, 0, 4, 0, 0, 0, 0, 0},
							  {0, 0, 0, 0, 5, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0, 6, 0, 0, 0},
							  {0, 0, 0, 0, 0, 0, 7, 0, 0},
						      {0, 0, 0, 0, 0, 0, 0, 8, 0},
							  {0, 0, 0, 0, 0, 0, 0, 0, 9} };
	
	int conflictArr[][] = { {1, 0, 0, 0, 0, 0, 0, 0, 1},
							{0, 2, 0, 0, 0, 0, 0, 0, 0},
							{0, 1, 3, 0, 0, 0, 0, 0, 0},
							{0, 0, 0, 4, 0, 0, 0, 0, 0},
							{0, 0, 0, 0, 5, 0, 0, 0, 0},
							{0, 0, 0, 0, 0, 6, 0, 0, 6},
							{0, 0, 0, 0, 0, 0, 7, 0, 0},
						    {0, 0, 0, 0, 0, 0, 0, 8, 0},
							{1, 0, 0, 0, 0, 0, 8, 0, 9} };
	
	@Test
	public void testisInRow() {
		Board board = new Board(conflictArr);
		
		assertTrue(solver.isInRow(board, 0, 1));
		assertFalse(solver.isInRow(board, 0, 2));
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInRow(board2, -1, 0);
	    });
	    assertEquals("Illegal row index or value", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInRow(board2, 9, 0);
	    });
	    assertEquals("Illegal row index or value", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInRow(board2, 0, -1);
	    });
	    assertEquals("Illegal row index or value", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInRow(board2, 0, 10);
	    });
	    assertEquals("Illegal row index or value", exception.getMessage());
	}
	
	@Test
	public void testCheckCol() {
		Board board = new Board(conflictArr);
		
		assertTrue(solver.isInColumn(board, 0, 1));
		assertFalse(solver.isInColumn(board, 0, 2));
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInColumn(board2, -1, 0);
	    });
	    assertEquals("Illegal column index or value", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInColumn(board2, 9, 0);
	    });
	    assertEquals("Illegal column index or value", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInColumn(board2, 0, -1);
	    });
	    assertEquals("Illegal column index or value", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInColumn(board2, 0, 10);
	    });
	    assertEquals("Illegal column index or value", exception.getMessage());
	}
	
	@Test
	public void testIsInBox() {
		Board board = new Board(conflictArr);
		
		assertTrue(solver.isInBox(board, 0, 0, 1));
		assertFalse(solver.isInBox(board, 0, 0, 9));
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInBox(board2, -1, 0, 9);
	    });
	    assertEquals("Illegal startRow or startCol", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInBox(board2, 10, 0, 9);
	    });
	    assertEquals("Illegal startRow or startCol", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInBox(board2, 0, -1, 9);
	    });
	    assertEquals("Illegal startRow or startCol", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	Board board2 = new Board();
	    	solver.isInBox(board2, 0, 10, 9);
	    });
	    assertEquals("Illegal startRow or startCol", exception.getMessage());
	}
	
	@Test
	public void testIsValid() {
		Board board = new Board(noConflictArr);
		
		assertTrue(solver.isValid(board, 0, 1, 4));
		assertFalse(solver.isValid(board, 0, 1, 1));
		
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	solver.isValid(board, -1, 0, 9);
	    });
	    assertEquals("Illegal row or column index", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	solver.isValid(board, 10, 0, 9);
	    });
	    assertEquals("Illegal row or column index", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	solver.isValid(board, 0, -1, 9);
	    });
	    assertEquals("Illegal row or column index", exception.getMessage());
	    
	    exception = assertThrows(IllegalArgumentException.class, () -> 
	    {
	    	solver.isValid(board, 0, 10, 9);
	    });
	    assertEquals("Illegal row or column index", exception.getMessage());
	}
	
	@Test
	public void testIsMultipleSolution() {
		Board boardEmpty = new Board();

		int trivialBoard[][] = { {0, 2, 3, 4, 5, 6, 7, 8, 9},
								 {4, 5, 6, 7, 8, 9, 1, 2, 3},
								 {7, 8, 9, 1, 2, 3, 4, 5, 6},
								 {2, 3, 1, 5, 6, 4, 8, 9, 7},
								 {5, 6, 4, 8, 9, 7, 2, 3, 1},
								 {8, 9, 7, 2, 3, 1, 5, 6, 4},
								 {3, 1, 2, 6, 4, 5, 9, 7, 8},
							     {6, 4, 5, 9, 7, 8, 3, 1, 2},
								 {9, 7, 8, 3, 1, 2, 6, 4, 5} };
		
		Board board2 = new Board(trivialBoard);
		
		assertTrue(solver.isMultipleSolution(boardEmpty));
		assertFalse(solver.isMultipleSolution(board2));
	}
	
	@Test
	public void testIsNoConflictsOnBoard() {
		Board conflictBoard = new Board(conflictArr);
		Board noConflictBoard = new Board(noConflictArr);
		
		assertTrue(solver.isNoConflictsOnBoard(noConflictBoard));
		assertFalse(solver.isNoConflictsOnBoard(conflictBoard));
	}
}
