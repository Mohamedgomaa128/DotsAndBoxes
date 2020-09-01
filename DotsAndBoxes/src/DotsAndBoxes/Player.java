package DotsAndBoxes;

import java.io.Serializable;

public class Player implements Serializable {
	
	public String name;
	public int score;
	public String color;
	public int moves;
	public char letter;
	public String win;
	public char[][] grid;
 	public Player(String name, char letter) {
		this.letter = letter;
		this.name = name;
		this.moves = 0;
		this.score = 0;
	}
	
}
