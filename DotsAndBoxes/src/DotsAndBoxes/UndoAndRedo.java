package DotsAndBoxes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UndoAndRedo {

	public static void main(String[] args) {

		File f1 = new File("undoAndRedo");
		f1.mkdir();
		
		File f2 = new File("undoAndRedo\\allMoves");
		File f3 = new File("undoAndRedo\\lastMove");

		try {
			f2.createNewFile();
			f3.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	
	
	
	public static void undo(ArrayList<GameTurn> list, GameTurn g) {
		File f = new File("undoAndRedo\\theList");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ArrayList<GameTurn> generated = new ArrayList<GameTurn>();
		
		if (g == null)
			g = list.get(list.size() - 1);
			
		generated = (ArrayList<GameTurn>) list.subList(0, list.lastIndexOf(g));
		
		
		
	}
	
	
	
	
	
	
	

}
