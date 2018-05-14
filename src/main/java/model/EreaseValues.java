package model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
 * Ereases a given number of values from a {@code Board} object.
 * 
 */
public class EreaseValues {
	
	/**
	 * Random indexes of the board.
	 */
	public List<Integer> indexes = new ArrayList<>();
	
	/**
	 * Fills the {@code indexes} list from 0 to 80,
	 * then shuffels it.
	 */
	public void shuffleIndexes(){
		for(int i = 0; i < 9 * 9; i++)
			indexes.add(i);
		
		Collections.shuffle(indexes);
	}
	
	/**
	 * Sets a given number of random numbers to zero,
	 * until the board is still solvable and has only one solution.
	 * 
	 * @param board the {@code Board}
	 * @param n		the number of cells to be emptied
	 */
	public void erease(Board board, int n) {
		int row, col;
		int ereasedValues = n;
		Solver solve = new Solver();
		int tempValue;
		
		for(Integer i : indexes) {
			if(ereasedValues == 0)
				break;
			row = i / 9;
			col = i % 9;
			tempValue = board.getValue(row, col);
			board.setValue(row, col, 0);
			
			if(!solve.isMultipleSolution(board)) {
				ereasedValues--;
				continue;
			}
			else {
				board.setValue(row, col, tempValue);
				ereasedValues++;
			}
		}
	}
}
