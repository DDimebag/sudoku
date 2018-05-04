package model;
/**
 * Uses a backtracking algorithm to solve a Board.
 * 
 * Can solve the board in ascending and descending order
 * and can determine if there is multiple solution.
 *
 */
public class Solver {
	
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
	
	public boolean isMultipleSolution(Board  b) {
		System.out.println("itt0");
		Board board1 = new Board(b);
		Board board2 = new Board(b);
		
		solveBoardAsc(board1);
		solveBoardDesc(board2);
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++) 
				if(board1.getValue(row, col) != (board2.getValue(row, col)))
					return true;
		return false;
	}
	
	public boolean isValid(Board b, int row, int col, int value) {
		if(!checkRow(b, row, value) && !checkCol(b, col, value) && !checkBox(b, row - row % 3, col - col % 3, value))
			return true;
		return false;
	}
	
	public boolean checkRow(Board b, int row, int value) {
		for(int i = 0; i < b.boardSize; i++)
			if(b.getValue(row, i) == value)
				return true;
		return false;
	}
	
	public boolean checkCol(Board b, int col, int value) {
		for(int i = 0; i < b.boardSize; i++)
			if(b.getValue(i, col) == value)
				return true;
		return false;
	}
	
	public boolean checkBox(Board b, int startRow, int startCol, int value) {
		for(int i = 0; i < b.boxSize; i++)
			for(int j = 0; j < b.boxSize; j++)
				if(b.getValue(i + startRow, j + startCol) == value)
					return true;
		return false;
	}
	
	public boolean isValidBoard(Board b) {
		int value = 0;
		System.out.println("itt");
		
		for(int row = 0; row < 9; row++)
			for(int col = 0; col < 9; col++) { 
				if(b.getValue(row, col) != 0) {
					value = b.getValue(row, col);
					b.setValue(row, col, 0);
					if(!isValid(b, row, col, value)) {
						b.setValue(row, col, value);
						return false;
					}		
					else
						b.setValue(row, col, value);
				}		
			}
		return true;
	}
}
