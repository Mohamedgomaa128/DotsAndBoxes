package DotsAndBoxes;

import java.io.File;
import java.io.IOException;

public class ExpertFiles {

	public static void main(String[] args) throws IOException {
		File direc1 = new File("Expert");
		direc1.mkdir();
		File file1 = new File("Expert\\top10.txt");
		file1.createNewFile();
	}
	
	
	
	
	
	

}
