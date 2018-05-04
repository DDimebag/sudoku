package model;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
/**
 * Ereases a given number of values from a Board object.
 * 
 * Set a given number of random numbers to zero,
 * until the board is still solvable and has only one solution.
 *
 */
public class EreaseValues {
	
	public List<Integer> indexes = new ArrayList<>();
	//int level = 0;
	
	public void shuffleIndexes(){
		for(int i = 0; i < 9 * 9; i++)
			indexes.add(i);
		
		Collections.shuffle(indexes);
	}
	
	public void erease(Board b, int n) {
		int row, col;
		int ereasedValues = n;
		Solver solve = new Solver();
		int tempValue;
		
		for(Integer i : indexes) {
			if(ereasedValues == 0)
				break;
			row = i / 9;
			col = i % 9;
			tempValue = b.getValue(row, col);
			b.setValue(row, col, 0);
			Board b2 = new Board(b);
			
			if(solve.solveBoardAsc(b2) && !solve.isMultipleSolution(b)) {
				ereasedValues--;
				continue;
			}
			else {
				b.setValue(row, col, tempValue);
				ereasedValues++;
			}
				
		}
	}
}
