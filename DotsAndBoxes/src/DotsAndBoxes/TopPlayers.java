package DotsAndBoxes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TopPlayers {
	// 0 for begg mode
	// 1 for expert mode
	
	/*public static void main(String[] args) throws Throwable {

		/*File direc1 = new File("beg");
		direc1.mkdir();
		File file1 = new File("beg\\top10.txt");
		file1.createNewFile();
		Player p1 = new Player("khaled", 'K');
		p1.score = 5;
		p1.moves = 7;
		p1.win = 1;
		Player p2 = new Player("HosSam", 'H');
		p2.score = 13;
		p2.moves = 50;
		Player p3 = new Player("noor", 'N');
		p3.score = 12;
		p3.moves = 49;
		write(p1, 0);
		write(p2, 0);
		write(p3, 0);
		Player p4 = new Player("bdr", 'B');
		p4.score = 5;
		p4.moves = 6;
		Player p5 = new Player("sami", 'S');
		p5.score = 5;
		p5.moves = 1;
		Player p6 = new Player("tamer", 'T');
		p6.score = 11;
		p6.moves = 0;
		write(p5, 0);
		write(p6, 0);
		write(p4, 0);
		Player p7 = new Player("Amer", 'T');
		p7.score = 15;
		p7.moves = 0;
		write(p7, 0);
		
		showTopPlayersMovesSorted(-1, 0);
		//  stared comment here moved so as not to make error with the following closing stared comment 
	}*/

	private static void sort (ArrayList<Player> list) {
		//for main sort among scores
	
		for (int i = 0; i < list.size() - 1; i++) {
			
			Player temp = list.get(i);
			int maxIndex = maxIndex(list, i);
			Player min = list.get(maxIndex);
			list.set(i, min);
			list.set(maxIndex, temp);
		}
			
		
	}

	private static int maxIndex (ArrayList<Player> list, int index) {
		//for main sort among scores

		int max = list.get(index).score;
		int maxIndex = index;
		
		for (int i = index + 1; i < list.size(); i++)
			if (list.get(i).score > max) {
				max = list.get(i).score;
				maxIndex = i;
			}
				
		return maxIndex;
		
	}
	
	private static void sort2 (ArrayList<Player> list) {
		//for subsort among same score by moves

		for (int i = 0; i < list.size() - 1; i++) {
			
			Player temp = list.get(i);
			int minMovesIndex = midIndex(list, i);
			Player min = list.get(minMovesIndex);
			list.set(i, min);
			list.set(minMovesIndex, temp);
		}
			
		
	}
	
	private static int midIndex (ArrayList<Player> list, int index) {
		//for subsort among same score by moves

		int sameScore = list.get(index).score;
		int minMoves = list.get(index).moves;
		int midIndex = index;
		
		for (int i = index + 1; i < list.size(); i++)
			if (list.get(i).score == sameScore && list.get(i).moves < minMoves) {
				minMoves = list.get(i).moves;
				midIndex = i;
			}
				
		return midIndex;
		
	}

	private static void AddUpdateCase(ArrayList<Player> list, Player p) {
		//if found update the score or the moves if have same score
		boolean found = false;
		
			for (int i = 0; i < list.size(); i++) {
				Player current = list.get(i);
				//already found
				if(current.name.toLowerCase().equals(p.name.toLowerCase())) {
					
					found = true;
					//higher score
					if (current.score < p.score) {
						//new data but same name
						p.name = current.name;
						list.set(i, p);
					}
					//same score 
					else if (current.score == p.score && current.moves > p.moves) {
						//new data but same name
						p.name = current.name;
						list.set(i, p);
					}
					break;
				}	
			}
			//not found so we add a new element
			if (!found)
				list.add(p);
			
			
	}
	
	public static void write (Player p, int mode) {
		File direc1 = new File("beg");
			direc1.mkdir();
		File direc2 = new File("Expert");
			direc1.mkdir();
		try {
			
			File f = (mode == 0) ? new File("beg\\top10.txt") : new File("Expert\\top10.txt");
			f.createNewFile();
			// load and add
			ArrayList<Player> list = read(mode);
			
			if (list != null)
				AddUpdateCase(list, p);
			else {
				list = new ArrayList<Player>();
				list.add(p);
			}
			//sort
			
			sort(list);
			//subsort if have same score with different moves
			sort2(list);
			
			//rewrite again
			try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(list);
			//close
					oos.flush();
					oos.close();
					fos.close();
			}
			catch (Exception e){
				System.out.println(e.getMessage());
			}
			
		}
		catch (Exception e) {//do no thing
		}
		
	}
	
	private static ArrayList read(int mode) {
		File f = (mode == 0) ? new File("beg\\top10.txt") : new File("Expert\\top10.txt");
		ArrayList<Player> list = null;
		try {
		FileInputStream fis = new FileInputStream(f);
		ObjectInputStream ois = new ObjectInputStream(fis);

		list = (ArrayList<Player>) ois.readObject();
		}
		catch (Exception e) {
			
		}
		return list;			
		
	}
		
	// for both cases begg and expert
	public static int showTopPlayersMovesSorted(int n, int mode) {
		// printing n players 
		// pass -1 if you want all players
		ArrayList<Player> list = read(mode);
		if (list == null) {
			//list.size() == 0) {
			System.out.println("there is no players to show");
			return 0;
		}
		else {
			if (n == -1) {
				System.out.println("\t\t\t TOP " + list.size() + " player" );
				System.out.printf("    %-7s %-5s %-5s %-9s\n", "NAME", "SCORE", "MOVES", "SITUATION");
	
				for (int i = 0; i < list.size(); i++)
					System.out.printf("%-2d- %-7s %-5d %-5d %-9s\n", (i + 1), list.get(i).name, list.get(i).score, list.get(i).moves, list.get(i).win);
				return 1;
			}
			else if (n > list.size()) {
				System.out.println("the number of previous players is less than that number");
				return -1;
			}
			else {
				System.out.println("\t\t\t TOP " + n + " player" );
				System.out.printf("    %-7s %-5s %-5s %-9s\n", "NAME", "SCORE", "MOVES", "SITUATION");
	
				
				for (int i = 0; i < n; i++)
					System.out.printf("%-2d- %-7s %-5d %-5d %-9s\n", (i + 1), list.get(i).name, list.get(i).score, list.get(i).moves, list.get(i).win);
			
				return 1;
			}
	
		}
		
	}
	
	// show all the data of the player
	
	
	// show all the grids of players
	// for both cases begg and expert
	public static void showGridOfAllPlayer(int mode) {
		ArrayList<Player> list = read(mode);
		if (list.size() == 0)
			System.out.println("there is no players to show");
		else {
			System.out.printf("       \t\t\t%-10s  %-10s  %-2s \n", "NAME", "SITUATUION", "LETTER");

			for (int i = 0; i < list.size(); i++) {
				Player p = list.get(i);
				if (p.grid != null) {
					System.out.printf("       \t\t\t%2d-%-10s   %-10s  %-2c \n", (i + 1), p.name, p.win, p.letter);
					BigennerGrid.printGrid(p.grid);
				}
				else { 
					System.out.printf("       \t\t\t%2d-%-10s : this player has no grid to show \n", (i + 1), p.name);
					//System.out.println("");
					
				}
				
				
			}
		}
		
	}
	
	// show the grid of the player 
	// for both cases begg and expert
	public static int showGridOfRankedPlayer(int n, int mode) {
		// printing grid of player 
		// mode >> 0 for begg >> 1 for Expert
		ArrayList<Player> list = read(mode);
		if (list.size() == 0) {
			System.out.println("there is no players to show");
			return 0;
		}
		else if (list.size() < n) {
			System.out.println("the player is not found");
			return -1;
		}
		else {
			char[][] grid = list.get(n - 1).grid;
			if (grid == null)
				return 3;
			BigennerGrid.printGrid(grid);
			return 1;
		}
	}

	
	public static void emptyTheFile(int mode) {
		
		File f = (mode == 0) ? new File("beg\\top10.txt") : new File("Expert\\top10.txt");
		
		if (f.exists()) {
			try {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				oos.writeObject(null);
				//close
						oos.flush();
						oos.close();
						fos.close();
				}
			catch (Exception e) {
			}
		}
		
	}
	
}
	
	


