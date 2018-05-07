import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import model.Board;
import model.EreaseValues;

public class EreaseValuesTest {

	@Test
	public void ereaseTest() {
		int zeroCounter = 0;
		int arr[][] = { {1, 2, 3, 4, 5, 6, 7, 8, 9},
						{4, 5, 6, 7, 8, 9, 1, 2, 3},
						{7, 8, 9, 1, 2, 3, 4, 5, 6},
						{2, 3, 1, 5, 6, 4, 8, 9, 7},
						{5, 6, 4, 8, 9, 7, 2, 3, 1},
						{8, 9, 7, 2, 3, 1, 5, 6, 4},
						{3, 1, 2, 6, 4, 5, 9, 7, 8},
						{6, 4, 5, 9, 7, 8, 3, 1, 2},
						{9, 7, 8, 3, 1, 2, 6, 4, 5} };
		
		Board board = new Board(arr);
		EreaseValues ev = new EreaseValues();
		
		ev.erease(board, 10);
		int arr2[][] = board.getBoard();
		
		for(int row = 0; row < Board.boardSize; row++)
			for(int col = 0; col < Board.boardSize; col++)
				if(arr2[row][col] == 0)
					zeroCounter++;
		
		assertEquals(10, zeroCounter);			
	}
}
