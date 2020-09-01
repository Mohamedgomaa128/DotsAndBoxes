package DotsAndBoxes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveAndLoad {

	public static void main(String[] args) {
	/*	Player p1 = new Player("khaled", 'K');
		p1.score = 5;
		p1.moves = 7;
		
		Player p2 = new Player("HosSam", 'H');
		p2.score = 13;
		p2.moves = 50;
		
		Player p3 = new Player("noor", 'N');
		p3.score = 12;
		p3.moves = 49;
		
		Player p4 = new Player("bdr", 'B');
		p4.score = 5;
		p4.moves = 6;
		
		Player p5 = new Player("sami", 'S');
		p5.score = 5;
		p5.moves = 1;
		
		Player p6 = new Player("tamer", 'T');
		p6.score = 11;
		p6.moves = 0;
		
		Player p7 = new Player("Amer", 'A');
		p7.score = 15;
		p7.moves = 0;
		
		GameTurn g1 = new GameTurn();
		g1.x = 0;
		g1.y = 1;
		g1.player = p1;
		
		GameTurn g2 = new GameTurn();
		g2.x = 50;
		g2.y = 10;
		g2.player = p2;

		GameTurn g3 = new GameTurn();
		g3.x = 60;
		g3.y = 19;
		g3.player = p3;
		
		GameTurn g4 = new GameTurn();
		g4.x = 80;
		g4.y = 17;
		g4.player = p4;

		GameTurn g5 = new GameTurn();
		g5.x = 0;
		g5.y = 1;
		g5.player = p5;
		
		GameTurn g6 = new GameTurn();
		g6.x = 0;
		g6.y = 1;
		g6.player = p6;
		
		GameTurn g7 = new GameTurn();
		g7.x = 0;
		g7.y = 1;
		g7.player = p7;
		
		ArrayList<GameTurn> list = new ArrayList<GameTurn>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		
		
		
		list.add(g1);
		list.add(g2);
		list.add(g3);
		list.add(g4);
		list.add(g5);
		list.add(g6);
		list.add(g7);
		
		//save(list, arr, 0);
		
		ArrayList<GameTurn> list2;

		ArrayList<Integer> arr2;
		
		//list2 = (ArrayList<GameTurn>) load(0)[0];
		//arr2 =  (ArrayList<Integer>) load(0)[1];
	*/	
		
		
		
	}
	
	public static void save (ArrayList<GameTurn> list, ArrayList<Player> players, int mode, int noOfPlayers) {
		try {
		File direc1 = new File("beg");
		direc1.mkdir();
		File direc2 = new File("Expert");
		direc2.mkdir();
		File f1 = new File((mode == 0) ?  ("beg\\Arraylist" + noOfPlayers + ".txt") : ("Expert\\Arraylist" + noOfPlayers + ".txt")) ;
		f1.createNewFile();
		
		File f2 = new File((mode == 0) ?  ("beg\\players" + noOfPlayers + ".txt") : ("Expert\\players" + noOfPlayers + ".txt")) ;
		f2.createNewFile();
		FileOutputStream fos1 = new FileOutputStream(f1);
		ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
		
		FileOutputStream fos2 = new FileOutputStream(f2);
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		
		oos1.writeObject(list);
		
		oos1.flush();
		oos1.close();
		fos1.close();
		
		
		oos2.writeObject(players);
		
		oos2.flush();
		oos2.close();
		fos2.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public static Object[] load(int mode, int noOfPlayers) {
		
		File f1 = new File((mode == 0) ?  ("beg\\Arraylist" + noOfPlayers + ".txt") : ("Expert\\Arraylist" + noOfPlayers + ".txt")) ;
		
		File f2 = new File((mode == 0) ?  ("beg\\players" + noOfPlayers + ".txt") : ("Expert\\players" + noOfPlayers + ".txt")) ;

		ArrayList<GameTurn> list = new ArrayList<GameTurn>();
		ArrayList<Player> players = null;
		Object returned[] = new Object[2];
		try {
		FileInputStream fis1 = new FileInputStream(f1);
		ObjectInputStream ois1 = new ObjectInputStream(fis1);
		
		FileInputStream fis2 = new FileInputStream(f2);
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		
		players =  (ArrayList<Player>) ois2.readObject();
		
		list = (ArrayList<GameTurn>) ois1.readObject();			
		}
		catch (Exception e) {
			//System.out.println(e.getMessage());

		}
		
		returned[0] = list;
		returned[1] = players;
		
		return returned;
		
	}
	
	
	

}
