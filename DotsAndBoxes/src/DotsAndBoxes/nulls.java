package DotsAndBoxes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class nulls {
	
	
	
	
	/*
	 	public static void twoPlayerMode(int mode) {
		// two players game
		
		Scanner input = new Scanner(System.in);
		ArrayList<GameTurn> list = new ArrayList<GameTurn>();
		ArrayList<Player> players = new ArrayList<Player>();
		
		char grid[][] = createGrid();
		int remainingMoves = 12;
		Player player1 = null;
		Player player2 = null;
		
		int turn = 1;
		
		int row = 0;
		int column = 0;
		
		// reading data
		if (mode == 5) {
			System.out.println("enter the name of the first player");
			String name1 = input.next();
			System.out.println("enter the name of the second player");
			String name2 = input.next();
			
			System.out.println("enter the character of the first player");
			char firstChar = input.next().charAt(0);
			System.out.println("enter the character of the second player");
			char secondChar = input.next().charAt(0);
			
			player1 = new Player(name1, firstChar);
			player2 = new Player(name2, secondChar);
			
			players.add(player1);
			players.add(player2);
		}
		else if (mode == 6) {
			// reading from the file 
			list = (ArrayList<GameTurn>) SaveAndLoad.load(0, 2)[0];
			players = (ArrayList<Player>) SaveAndLoad.load(0, 2)[1];
			
			if (players == null) {
				System.out.println("you haven't saved any game before");
				System.exit(0);
			}
			else if (list == null) {
				System.out.println("the last saved game have been completed");
				System.exit(0);
			}
			
			//getting the two players
			Player player1FromList = (Player) players.get(0); // index 0
			Player player2FromList = (Player) players.get(1); // index 1
			
			//creating new two players having same name and letter
			player1 = new Player(player1FromList.name, player1FromList.letter);
			player2 = new Player(player2FromList.name, player2FromList.letter);
			
			// resemulate the game 
			GameTurn gOut = new GameTurn();
			for (int i = 0; i < list.size(); i++) {
				Player p = (turn % 2 == 1) ? player1 : player2;
				//printGrid(grid, player1, player2);  //for debugging
				gOut = list.get(i);
				row = gOut.x;
				column = gOut.y;
				
				if (modifyGrid(grid, row, column)) {
					remainingMoves--;
					p.moves++; // take care of error in moves 
					if (!addLetter(grid, p))
						turn++;		
				}	
			}			
		}
		
		while (remainingMoves != 0) {
			
				printGrid(grid, player1, player2);
	
				// Determine whom player is playing 
				Player p = (turn % 2 == 1) ? player1 : player2;
				int continuePlaying = 1;
				if (remainingMoves < 12) {
					System.out.println("enter 0 to save and end game");
					System.out.println("enter 1 to play turn");
					continuePlaying = input.nextInt();
				}
				
				if (continuePlaying == 1) {
					
					// Reading input
					System.out.println(p.name + " enter a number from 0 to 4 for row");
					row = input.nextInt();
					System.out.println(p.name + " enter a number from 0 to 4 for column");
					column = input.nextInt();
					
					// Adding turn to the list
					GameTurn g = new GameTurn();
					if (isValid(grid, row, column)) {
						// you can add this block to the following block and decrease #lines of code
						g.x = row;
						g.y = column;
						g.player = p;
						list.add(g);
					}
					
					//modifies the grid and increases the score 
					if (modifyGrid(grid, row, column)) {
						remainingMoves--;
						p.moves++; // take care of error in moves 
						if (!addLetter(grid, p))
							turn++;		
					}
				
				}
				else if (continuePlaying == 0) {
					printGrid(grid, player1, player2);
					
					//saving data
					SaveAndLoad.save(list, players, 0, 2);
					System.out.println("thanks for your time ^_^");
					System.exit(0);
				}
				
				
		}
		
		if (mode == 6) 
			SaveAndLoad.save(null, players, 0, 2);
			
		printGrid(grid, player1, player2);
		
		////////////////////////
		detectWinner(player1, player2);
		
		player1.grid = grid;
		player2.grid = grid;
		
		//saving players to leader board
		TopPlayers.write(player1, 0);
		TopPlayers.write(player2, 0);
		
		printWinner(player1, player2);
	}
	 */
	
	
	/*
	 public static void onePlayerMode() {
		// one player mode 
		File direc1 = new File("beg");
		direc1.mkdir();
		File file1 = new File("beg\\top10.txt");
		try {
			file1.createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Scanner input = new Scanner(System.in);
		
		char grid[][] = createGrid();
		int remainingMoves = 12;
		
		System.out.println("enter the name of the first player");
		String name1 = input.next();
		String name2 = "computer";
		
		System.out.println("enter the character of the first player");
		char firstChar = input.next().charAt(0);
		char secondChar = 'C';
		
		Player player1 = new Player(name1, firstChar);
		Player player2 = new Player(name2, secondChar);
		
		int turn = 1;
		//printGrid(grid);
		int row;
		int column;
		while (remainingMoves != 0) {
			printGrid(grid, player1, player2);
			
			Player p = (turn % 2 == 1) ? player1 : player2;
			// player or computer 
			if (p == player1) {
			System.out.println(p.name + " enter a number from 0 to 4 for row");
			row = input.nextInt();
			System.out.println(p.name + " enter a number from 0 to 4 for column");
			column = input.nextInt();
			}
			else {
				// case of computer
		    	row = aI(grid)[0];
				column = aI(grid)[1];
			
			}
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++;
				if (!addLetter(grid, p))
					turn++;	
				
				
			}	
		}
		printGrid(grid, player1, player2);
		
		
		///////////
		detectWinner(player1, player2);
		player1.grid = grid;
		//player2.grid = grid; // because it is the computer
		
		//saving player to leader board
		TopPlayers.write(player1, 0);
		
		printWinner(player1, player2);	
	}
	 */


	/*public static int aICol(char[][] grid) {
	
	//three sided box
	for (int i = 1; i < grid.length; i += 2)
		for (int j = 1; j < grid[0].length; j += 2) {
			if (grid[i][j] == '\u0000' && grid[i - 1][j] != '\u0000' && grid[i + 1][j] != '\u0000' && grid[i][j + 1] != '\u0000')
				return j - 1;
			if (grid[i][j] == '\u0000' && grid[i - 1][j] != '\u0000' && grid[i + 1][j] != '\u0000' && grid[i][j - 1] != '\u0000')
				return j + 1;	
		}
			
	//zero sided box
	for (int i = 1; i < grid.length; i += 2)
		for (int j = 1; j < grid[0].length; j += 2) {
			if (grid[i][j] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i + 1][j] == '\u0000' && grid[i - 1][j] != '\u0000')
				return j - 1;	
		}
	
	//one sided box 
	for (int i = 1; i < grid.length; i += 2)
		for (int j = 1; j < grid[0].length; j += 2) {
			if (grid[i][j] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i + 1][j] == '\u0000')
				return i + 1;
			if (grid[i][j] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i - 1][j] == '\u0000')
				return i - 1;	
		}
	//two sided box
	for (int i = 1; i < grid.length; i += 2)
		for (int j = 1; j < grid[0].length; j += 2) {
			if (grid[i][j] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000')
				return i + 1;	
		}
	return 0;
			
}*/
	
	/*if (remainingMoves == 0) {
	if (modifyGrid(grid, row, column)) {
		remainingMoves--;
		p.moves++;
		if (!addLetter(grid, p.getLetter()))
			turn++;	
		else 
			p.score++;
		
	}
}*/
	
	

	/*	BigennerGrid.printGrid(grid);
	
		for (int i = 0; i < 900; i++)
		System.out.print();
	
		BigennerGrid.modifyGrid(grid, 0, 1);
		BigennerGrid.modifyGrid(grid, 1, 0);
		BigennerGrid.modifyGrid(grid, 1, 2);
		BigennerGrid.modifyGrid(grid, 2, 1);

		BigennerGrid.addLetter(grid, 'A');
		
		BigennerGrid.modifyGrid(grid, 0, 3);
		BigennerGrid.modifyGrid(grid, 1, 4);
		BigennerGrid.modifyGrid(grid, 1, 2);
		BigennerGrid.modifyGrid(grid, 2, 3);
		
		BigennerGrid.addLetter(grid, 'B');

		
		BigennerGrid.printGrid(grid);

		*/
	
	
	/*	public static void write (File f, Object o) throws Exception {
	FileOutputStream fos = new FileOutputStream(f);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(o);
	oos.flush();
	oos.close();
	fos.close();
}*/

	
	/*public static void showTopPlayers(int n) {
	 // more code as here you arranged players ascendingly 
	// printing n players 
	// pass -1 if you want all players
	ArrayList<Player> list = read();
	int j = 1;
	if (n == -1) {
	System.out.println("\t\t\t TOP " + list.size() + " player" );
	
	
	for (int i = list.size() - 1; i >= 0; i--)
		System.out.printf("%-2d- %-7s %-5d\n", (j++), list.get(i).name, list.get(i).score);
	}
	else {
		System.out.println("\t\t\t\t\t\t\t\t TOP " + n + " player" );
		
		
		for (int i = list.size() - 1; i >= list.size() - n; i--)
			System.out.printf("%-2d- %-7s %-5d\n", (j++), list.get(i).name, list.get(i).score);
	}

	

}*/
	
	
	/*
	Player[] arr =  new Player[3];
	arr[0] = player1;
	arr[1] = player2;
	arr[2] = player3;*/

//	write(file1, player1);
	
	//write(player2);
	//write(player3);
	
	//write(file1, player2);
	//write(file1, player3);
	//Player[] ans =  new Player[3];
	//ArrayList<Player> ans;
//	ans = read();
	
//	for (int i = 0; i < ans.size(); i++)
	//	System.out.println(ans.get(i).name + " " + ans.get(i).score);
	
	
	
	/*	private static void sort (ArrayList<Player> list) {
	
	for (int i = 0; i < list.size() - 1; i++) {
		
		Player temp = list.get(i);
		int minIndex = minIndex(list, i);
		Player min = list.get(minIndex);
		list.set(i, min);
		list.set(minIndex, temp);
	}
		
	
}

private static int minIndex (ArrayList<Player> list, int index) {
	int min = list.get(index).score;
	int minIndex = index;
	
	for (int i = index + 1; i < list.size(); i++)
		if (list.get(i).score < min) {
			min = list.get(i).score;
			minIndex = i;
		}
			
	return minIndex;
	
}
*/
	
	/*public static void showTopPlayers(int n) {
	// printing n players 
	// pass -1 if you want all players
	// without scores subsorting
	ArrayList<Player> list = read();
	
	if (n == -1) {
		System.out.println("\t\t\t TOP " + list.size() + " player" );
		System.out.printf("   %-7s %-5s\n", "NAME", "SCORE");

		for (int i = 0; i < list.size(); i++)
			System.out.printf("%-2d- %-7s %-5d\n", (i + 1), list.get(i).name, list.get(i).score);
	}
	
	else {
		System.out.println("\t\t\t TOP " + n + " player" );
		System.out.printf("   %-7s %-5s\n", "NAME", "SCORE");

		
		for (int i = 0; i < n; i++)
			System.out.printf("%-2d- %-7s %-5d\n", (i + 1), list.get(i).name, list.get(i).score);
	}

	

}*/

	/*
	 public static void begginerUI() {
		int stop = 1;
		Scanner input = new Scanner (System.in);
		while(stop != 0) {
			System.out.println("         \t\t\tDOTS AND BOXES");
	
			System.out.println("1. Start playing");
			System.out.println("2. Top players");
			System.out.println("3. Load last game");
			System.out.println("4. Close the game");
			int choice1 = input.nextInt();
			
			switch (choice1) {
				case 1 :
					System.out.println("1. One player mode");
					System.out.println("2. Two player mode");
					System.out.println("enter 0 to go back");
					int choice2 = input.nextInt();
					switch (choice2) {
						case 0:
							continue;
						case 1 :
							onePlayerMode();
							break;
						case 2 :
							game();
							break;
					}
					break;
				case 2 :
					int back = -1;
					while(back < 0) {
						System.out.println("1. Begginer mode");
						System.out.println("2. Expert mode");
						System.out.println("enter 0 to go back");
						int choice3 = input.nextInt();
						
						switch (choice3) {
							case 0:
								back = 0;
								//here continue and break have same effect , both return to the while header , continue returns directly while break >> breaks switch first then go to the while header
								continue;
							case 1 :
								//begginer case
								int cc = -2;
								int choice4; 
								while(cc < 0) {
									System.out.println("Enter the number of Players you want");
									System.out.println("Enter -1 if you want all players");
									System.out.println("enter 0 to go back");
									
									choice4 = input.nextInt();
									if (choice4 == 0) {
										cc = 0;
										continue;
									}
									else {
										// showing the grid
										int found1 = TopPlayers.showTopPlayersMovesSorted(choice4, 0);
										
										if (found1 == -1) {
											System.out.println("enter a valid number");
											continue;
										}
										int num = 0;
										if (found1 == 0)
											num = 50;
										
											
										int bb = -1;
										while(bb < 0) {											
											System.out.println("enter 0 to go back one step");
											System.out.println("enter 1 to continue");
											if (num != 50) {
											System.out.println("enter 2 to show the grid of a player");
											System.out.println("enter 3 to show the grid of all players");
											}
											
											int choice5 = input.nextInt();
											
											switch (choice5) {
												case 0 :
													bb = 0;
													continue;
												case 1 :
													bb = 1;
													cc = 1;
													back = 1;
													break;
												case 2 :
													int dd = -1;
													while (dd < 0) {
														System.out.println("enter the rank of player to show the grid");
														int found = TopPlayers.showGridOfRankedPlayer(input.nextInt(), 0);
														if (found == -1) {
															System.out.println("choose a valid number");
															continue;
														}
														if (found == 3) {
															System.out.println("this player has no grid , choose a valid player number");
															continue;
														}
															
														
														System.out.println("enter 0 to go back one step");
														System.out.println("enter 1 to continue");
														System.out.println("enter 10 to show the rank of another player");

														int choice6 = input.nextInt();
														
														switch (choice6) {
														case 0 :
															dd = 0;
															continue;
														case 1 :
															dd = 1;
															bb = 1;
															cc = 1;
															back = 1;
															continue;
														case 10 :
															continue;
														
														}
													
													}
													if (dd == 0)
														continue;
													break;
												case 3 : 
														int ee;
														TopPlayers.showGridOfAllPlayer(0);
														
														System.out.println("enter 0 to go back one step");
														System.out.println("enter 1 to continue");														
														ee = input.nextInt();
					
													if (ee == 1) {
														bb = 1;
														cc = 1;
														back = 1;
														continue;
													}
													if (ee == 0)
														continue;
													break;
													
											}

										//	back = (input.nextInt() == 0) ? -1 : 1;
											
										
										}
										if (bb == 0)
											continue;
									}
								}
								//reception
								if (cc == 0)
									continue;
								break;
							case 2 :
								break;
								
						}
					}
					//reception
					if (back == 0)
						continue;
	
					break;
				case 3 :
					break;
				case 4 : 
					System.exit(0);
			}
			
			System.out.println("\nEnter 0 to end");
			System.out.println("Enter 1 to go back to main menu");
			stop = input.nextInt();
			
		}
		if (stop == 0)
			System.out.println("Thanks for your time ^_^");
		
	}
	 */
	
	
	
	// if you want to return the next two blocks return them before the /////.. line
		
	/*
	 //pc mode
	  // printing the winner 
		/*Player winner ;
		if (player1.getScore() >= player2.getScore())
			winner = (player1.getScore() > player2.getScore() ? player1 : null);
		else 
			winner = player2;
		
		if (winner != null)
			System.out.println("the winner is " + winner.getName() + " and with score " + winner.getScore());
		else 
			System.out.print("draw");
		*/
	 
	 //////////////////////////////
	 
	 /*
	  // 2 players mode
	   // printing the winner 
	/*	Player winner ;
		if (player1.getScore() >= player2.getScore())
			winner = (player1.getScore() > player2.getScore() ? player1 : null);
		else 
			winner = player2;
		
		if (winner != null)
			System.out.println("the winner is " + winner.getName() + " and with score " + winner.getScore());
		else 
			System.out.print("draw");*/
		////////////////////////////////////
	  
	
	
	////////////////////////////////////////////////********************************\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	//safety
	/*public static void game() {
	// two players game
	File direc1 = new File("beg");
	direc1.mkdir();
	//from here 
	//File file1 = new File("beg\\top10.txt"); // already done when you start to save player data .. you may use it when you want the redo and undo and save and load
	//try {
		//file1.createNewFile();
	//} catch (IOException e) {
		//System.out.println(e.getMessage());
	//}
	//to here
	
	
	Scanner input = new Scanner(System.in);
	
	char grid[][] = createGrid();
	int remainingMoves = 12;
	// reading data
	System.out.println("enter the name of the first player");
	String name1 = input.next();
	System.out.println("enter the name of the second player");
	String name2 = input.next();
	
	System.out.println("enter the character of the first player");
	char firstChar = input.next().charAt(0);
	System.out.println("enter the character of the second player");
	char secondChar = input.next().charAt(0);
	
	Player player1 = new Player(name1, firstChar);
	Player player2 = new Player(name2, secondChar);
	
	int turn = 1;
	//printGrid(grid);

	while (remainingMoves != 0) {
		printGrid(grid, player1, player2);
		// Determine whom player is playing 
		Player p = (turn % 2 == 1) ? player1 : player2;
		System.out.println(p.getName() + " enter a number from 0 to 4 for row");
		int row = input.nextInt();
		System.out.println(p.getName() + " enter a number from 0 to 4 for column");
		int column = input.nextInt();
		//modifies the grid and increases the score 
		if (modifyGrid(grid, row, column)) {
			remainingMoves--;
			p.setMoves(p.getMoves() + 1);
			if (!addLetter(grid, p))
				turn++;		
		}
	}
	printGrid(grid, player1, player2);
	
	////////////////////////
	if (player1.getScore() > player2.getScore()) {
		player1.win = "WINNER";
		player2.win = "LOSER";
	}
	else if (player1.getScore() == player2.getScore()) {
		player1.win = "DRAW";
		player2.win = "DRAW";
	}
	else {
		player1.win = "LOSER";
		player2.win = "WINNER";
	}
	
	player1.grid = grid;
	player2.grid = grid;
	
	//saving players to leader board
	TopPlayers.write(player1, 0);
	TopPlayers.write(player2, 0);
	
	if (player1.win.equals("WINNER"))
		System.out.println("the winner is " + player1.getName() + " and with score " + player1.getScore());
	else if (player2.equals("WINNER"))
		System.out.println("the winner is " + player2.getName() + " and with score " + player2.getScore());
	else 
		System.out.print("DRAW");
	
	
}*/
	
	
	
	
	
	
	
	//
	
  /*
   	public static void onePlayerMode(int mode) {
		// one player mode 
		if (mode == 5) 
			cleanLists();
		
		Scanner input = new Scanner(System.in);
		
		ArrayList<GameTurn> list = new ArrayList<GameTurn>();
		ArrayList<Player> players = new ArrayList<Player>();
		
		char grid[][] = createGrid();
		int remainingMoves = 12;
		Player player1 = null;
		Player player2 = null;
		
		int turn = 1;
		
		int row = 0;
		int column = 0;
		
		if (mode == 5) {
			System.out.println("enter the name of the first player");
			String name1 = input.next();
			String name2 = "computer";
			
			System.out.println("enter the character of the first player");
			char firstChar = input.next().charAt(0);
			char secondChar = 'C';
			
			player1 = new Player(name1, firstChar);
			player2 = new Player(name2, secondChar);
			
			players.add(player1);
		//printGrid(grid);
		}
		else if (mode == 6) {
			// reading from the file 
			list = (ArrayList<GameTurn>) SaveAndLoad.load(0, 1)[0];
			players = (ArrayList<Player>) SaveAndLoad.load(0, 1)[1];
			
			if (players == null) {
				System.out.println("you haven't saved any game before");
				System.exit(0);
			}
			else if (list == null) {
				System.out.println("the last saved game have been completed");
				System.exit(0);
			}
				
			//getting the two players
			Player player1FromList = (Player) players.get(0); // index 0
			
			//creating new two players having same name and letter
			player1 = new Player(player1FromList.name, player1FromList.letter);
			player2 = new Player("computer", 'C');
			
			// resemulate the game 
			GameTurn gOut = new GameTurn();
			for (int i = 0; i < list.size(); i++) {
				Player p = (turn % 2 == 1) ? player1 : player2;
				//printGrid(grid, player1, player2);  //for debugging
				gOut = list.get(i);
				row = gOut.x;
				column = gOut.y;
				
				if (modifyGrid(grid, row, column)) {
					remainingMoves--;
					p.moves++; // take care of error in moves 
					if (!addLetter(grid, p))
						turn++;		
				}	
			}			
		}
		
		
		
		while (remainingMoves != 0) {
			printGrid(grid, player1, player2);
			
			Player p = (turn % 2 == 1) ? player1 : player2;
			// player or computer 
			if (p == player1) {
				int continuePlaying = 1;
				if (remainingMoves < 12) {
					System.out.println("enter 0 to save and end game");
					System.out.println("enter 1 to play turn");
					continuePlaying = input.nextInt();
				}
				
				if (continuePlaying == 1) {
					
					// Reading input
					System.out.println(p.name + " enter a number from 0 to 4 for row");
					row = input.nextInt();
					System.out.println(p.name + " enter a number from 0 to 4 for column");
					column = input.nextInt();
				
				}
				else if (continuePlaying == 0) {
					printGrid(grid, player1, player2);
					
					//saving data
					SaveAndLoad.save(list, players, 0, 1);
					System.out.println("thanks for your time ^_^");
					System.exit(0);
				}
			}
			else {
				// case of computer
		    	row = aI(grid)[0];
				column = aI(grid)[1];
			}
			
			
			// Adding turn to the list
			GameTurn g = new GameTurn();
			if (isValid(grid, row, column)) {
				// you can add this block to the following block and decrease #lines of code
				g.x = row;
				g.y = column;
				g.player = p;
				list.add(g);
			}
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++;
				if (!addLetter(grid, p))
					turn++;	
				
				
			}	
		}
		if (mode == 6) 
			SaveAndLoad.save(null, players, 0, 1);
		
		printGrid(grid, player1, player2);
		
		
		///////////
		detectWinner(player1, player2);
		player1.grid = grid;
		//player2.grid = grid; // because it is the computer
		
		//saving player to leader board
		TopPlayers.write(player1, 0);
		
		printWinner(player1, player2);	
	}
	
	
	
	
	*/
	
	
	
	
	
	
	
	
	/*	public static void twoPlayerMode(int mode) {
	// two players game
	
	Scanner input = new Scanner(System.in);
	ArrayList<GameTurn> list = new ArrayList<GameTurn>();
	ArrayList<Player> players = new ArrayList<Player>();
	
	char grid[][] = createGrid();
	int remainingMoves = 12;
	Player player1 = null;
	Player player2 = null;
	
	int turn = 1;
	
	int row = 0;
	int column = 0;
	
	// reading data
	if (mode == 5) {
		System.out.println("enter the name of the first player");
		String name1 = input.next();
		System.out.println("enter the name of the second player");
		String name2 = input.next();
		
		System.out.println("enter the character of the first player");
		char firstChar = input.next().charAt(0);
		System.out.println("enter the character of the second player");
		char secondChar = input.next().charAt(0);
		
		player1 = new Player(name1, firstChar);
		player2 = new Player(name2, secondChar);
		
		players.add(player1);
		players.add(player2);
	}
	else if (mode == 6) {
		// reading from the file 
		list = (ArrayList<GameTurn>) SaveAndLoad.load(0, 2)[0];
		players = (ArrayList<Player>) SaveAndLoad.load(0, 2)[1];
		
		if (players == null) {
			System.out.println("you haven't saved any game before");
			System.exit(0);
		}
		else if (list == null) {
			System.out.println("the last saved game have been completed");
			System.exit(0);
		}
		
		//getting the two players
		Player player1FromList = (Player) players.get(0); // index 0
		Player player2FromList = (Player) players.get(1); // index 1
		
		//creating new two players having same name and letter
		player1 = new Player(player1FromList.name, player1FromList.letter);
		player2 = new Player(player2FromList.name, player2FromList.letter);
		
		// resemulate the game 
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}			
	}
	
	while (remainingMoves != 0) {
		
			printGrid(grid, player1, player2);

			// Determine whom player is playing 
			Player p = (turn % 2 == 1) ? player1 : player2;
			int continuePlaying = 1;
			if (remainingMoves < 12) {
				System.out.println("enter 0 to save and end game");
				System.out.println("enter 1 to play turn");
				continuePlaying = input.nextInt();
			}
			
			if (continuePlaying == 1) {
				
				// Reading input
				System.out.println(p.name + " enter a number from 0 to 4 for row");
				row = input.nextInt();
				System.out.println(p.name + " enter a number from 0 to 4 for column");
				column = input.nextInt();
				
				// Adding turn to the list
				GameTurn g = new GameTurn();
				if (isValid(grid, row, column)) {
					// you can add this block to the following block and decrease #lines of code
					g.x = row;
					g.y = column;
					g.player = p;
					list.add(g);
				}
				
				//modifies the grid and increases the score 
				if (modifyGrid(grid, row, column)) {
					remainingMoves--;
					p.moves++; // take care of error in moves 
					if (!addLetter(grid, p))
						turn++;		
				}
			
			}
			else if (continuePlaying == 0) {
				printGrid(grid, player1, player2);
				
				//saving data
				SaveAndLoad.save(list, players, 0, 2);
				System.out.println("thanks for your time ^_^");
				System.exit(0);
			}
			
			
	}
	
	if (mode == 6) 
		SaveAndLoad.save(null, players, 0, 2);
		
	printGrid(grid, player1, player2);
	
	////////////////////////
	detectWinner(player1, player2);
	
	player1.grid = grid;
	player2.grid = grid;
	
	//saving players to leader board
	TopPlayers.write(player1, 0);
	TopPlayers.write(player2, 0);
	
	printWinner(player1, player2);
}
*/
	
	/*public static void game() {
	// two players game
	File direc1 = new File("beg");
	direc1.mkdir();
	//from here 
	//File file1 = new File("beg\\top10.txt"); // already done when you start to save player data .. you may use it when you want the redo and undo and save and load
	//try {
		//file1.createNewFile();
	//} catch (IOException e) {
		//System.out.println(e.getMessage());
	//}
	//to here
	
	
	Scanner input = new Scanner(System.in);
	
	char grid[][] = createGrid();
	int remainingMoves = 12;
	// reading data
	System.out.println("enter the name of the first player");
	String name1 = input.next();
	System.out.println("enter the name of the second player");
	String name2 = input.next();
	
	System.out.println("enter the character of the first player");
	char firstChar = input.next().charAt(0);
	System.out.println("enter the character of the second player");
	char secondChar = input.next().charAt(0);
	
	Player player1 = new Player(name1, firstChar);
	Player player2 = new Player(name2, secondChar);
	
	int turn = 1;
	//printGrid(grid);

	while (remainingMoves != 0) {
		printGrid(grid, player1, player2);
		// Determine whom player is playing 
		Player p = (turn % 2 == 1) ? player1 : player2;
		System.out.println(p.getName() + " enter a number from 0 to 4 for row");
		int row = input.nextInt();
		System.out.println(p.getName() + " enter a number from 0 to 4 for column");
		int column = input.nextInt();
		//modifies the grid and increases the score 
		if (modifyGrid(grid, row, column)) {
			remainingMoves--;
			p.setMoves(p.getMoves() + 1);
			if (!addLetter(grid, p))
				turn++;		
		}
	}
	printGrid(grid, player1, player2);
	
	////////////////////////
	if (player1.getScore() > player2.getScore()) {
		player1.win = "WINNER";
		player2.win = "LOSER";
	}
	else if (player1.getScore() == player2.getScore()) {
		player1.win = "DRAW";
		player2.win = "DRAW";
	}
	else {
		player1.win = "LOSER";
		player2.win = "WINNER";
	}
	
	player1.grid = grid;
	player2.grid = grid;
	
	//saving players to leader board
	TopPlayers.write(player1, 0);
	TopPlayers.write(player2, 0);
	
	if (player1.win.equals("WINNER"))
		System.out.println("the winner is " + player1.getName() + " and with score " + player1.getScore());
	else if (player2.equals("WINNER"))
		System.out.println("the winner is " + player2.getName() + " and with score " + player2.getScore());
	else 
		System.out.print("DRAW");
	
	
}*/




/*public static void onePlayerMode() {
	// one player mode 
	File direc1 = new File("beg");
	direc1.mkdir();
	File file1 = new File("beg\\top10.txt");
	try {
		file1.createNewFile();
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
	
	Scanner input = new Scanner(System.in);
	
	char grid[][] = createGrid();
	int remainingMoves = 12;
	
	System.out.println("enter the name of the first player");
	String name1 = input.next();
	String name2 = "computer";
	
	System.out.println("enter the character of the first player");
	char firstChar = input.next().charAt(0);
	char secondChar = 'C';
	
	Player player1 = new Player(name1, firstChar);
	Player player2 = new Player(name2, secondChar);
	
	int turn = 1;
	//printGrid(grid);
	int row;
	int column;
	while (remainingMoves != 0) {
		printGrid(grid, player1, player2);
		
		Player p = (turn % 2 == 1) ? player1 : player2;
		// player or computer 
		if (p == player1) {
		System.out.println(p.name + " enter a number from 0 to 4 for row");
		row = input.nextInt();
		System.out.println(p.name + " enter a number from 0 to 4 for column");
		column = input.nextInt();
		}
		else {
			// case of computer
	    	row = aI(grid)[0];
			column = aI(grid)[1];
		
		}
		if (modifyGrid(grid, row, column)) {
			remainingMoves--;
			p.moves++;
			if (!addLetter(grid, p))
				turn++;	
			
			
		}	
	}
	printGrid(grid, player1, player2);
	
	
	///////////
	detectWinner(player1, player2);
	player1.grid = grid;
	//player2.grid = grid; // because it is the computer
	
	//saving player to leader board
	TopPlayers.write(player1, 0);
	
	printWinner(player1, player2);	
}
*/
	
	/*public static void game(int mode) {
			// two players game
			File direc1 = new File("beg");
			direc1.mkdir();
			
			Player player1 = null;
			Player player2 = null;
			int turn = 1;

			ArrayList<GameTurn> list;
			
			if (mode == 6) 
				list = (ArrayList<GameTurn>) SaveAndLoad.load(0)[0];
			else {
				list = new ArrayList<GameTurn>();
			}
			
			
			Scanner input = new Scanner(System.in);
			
			char grid[][] = createGrid();
			int remainingMoves = 12;
			// reading data
			if (mode != 6) {
				System.out.println("enter the name of the first player");
				String name1 = input.next();
				System.out.println("enter the name of the second player");
				String name2 = input.next();
				
				System.out.println("enter the character of the first player");
				char firstChar = input.next().charAt(0);
				System.out.println("enter the character of the second player");
				char secondChar = input.next().charAt(0);
				
				player1 = new Player(name1, firstChar);
				player2 = new Player(name2, secondChar);
			}
			
			//printGrid(grid);
		
			while (remainingMoves != 0) {
				
				if (mode != 6) {
					printGrid(grid, player1, player2);
					// Determine whom player is playing 
					Player p = (turn % 2 == 1) ? player1 : player2;
					if (remainingMoves < 12) {
						System.out.println("enter 0 to undo");
						System.out.println("enter 10 to continue");

						//System.out.println("enter 1 to redo");
						int num = input.nextInt();
						if (num == 0) {
							SaveAndLoad.save(list, null, 0);
							n++;
							game(6);
							System.exit(0);
						}
					}
					
					System.out.println(p.getName() + " enter a number from 0 to 4 for row");
					int row = input.nextInt();
					System.out.println(p.getName() + " enter a number from 0 to 4 for column");
					int column = input.nextInt();
					GameTurn g = new GameTurn();
					g.x = row;
					g.y = column;
					g.player = p;
					list.add(g);
					//modifies the grid and increases the score 
					if (modifyGrid(grid, row, column)) {
						remainingMoves--;
						p.setMoves(p.getMoves() + 1);
						if (!addLetter(grid, p))
							turn++;		
					}
				}
				else {
					for (int i = 0; i < list.size() - n; i++) {
						Player p = list.get(i).player;
						if (modifyGrid(grid, list.get(i).x, list.get(i).y)) {
							remainingMoves--;
							p.setMoves(p.getMoves() + 1);
							if (!addLetter(grid, p))
								turn++;		
						}
					}
					mode = 5;
					player1 = list.get(0).player;
					player2 = list.get(1).player;
				}
				
			}
			printGrid(grid, player1, player2);
			
			////////////////////////
			if (player1.getScore() > player2.getScore()) {
				player1.win = "WINNER";
				player2.win = "LOSER";
			}
			else if (player1.getScore() == player2.getScore()) {
				player1.win = "DRAW";
				player2.win = "DRAW";
			}
			else {
				player1.win = "LOSER";
				player2.win = "WINNER";
			}
			
			player1.grid = grid;
			player2.grid = grid;
			
			//saving players to leader board
			TopPlayers.write(player1, 0);
			TopPlayers.write(player2, 0);
			
			if (player1.win.equals("WINNER"))
				System.out.println("the winner is " + player1.getName() + " and with score " + player1.getScore());
			else if (player2.equals("WINNER"))
				System.out.println("the winner is " + player2.getName() + " and with score " + player2.getScore());
			else 
				System.out.print("DRAW");
			
			
		}*/
	
	
	
	
	/*public static ArrayList<GameTurn> load(int mode, int NoOFPlayers) {
	
	File f1 = new File((mode == 0) ?  ("beg\\Arraylist" + NoOFPlayers + ".txt") : ("Expert\\Arraylist" + NoOFPlayers + ".txt")) ;
	
	ArrayList<GameTurn> list = new ArrayList<GameTurn>();
	try {
	FileInputStream fis1 = new FileInputStream(f1);
	ObjectInputStream ois1 = new ObjectInputStream(fis1);

	list = (ArrayList<GameTurn>) ois1.readObject();			
	}
	catch (Exception e) {
		System.out.println(e.getMessage());

	}
	
	return list;
}*/

/*public static void save (ArrayList list, int mode, int NoOFPlayers) {
	try {
	File f1 = new File((mode == 0) ?  ("beg\\Arraylist" + NoOFPlayers + ".txt") : ("Expert\\Arraylist" + NoOFPlayers + ".txt")) ;
	f1.createNewFile();
	
	
	FileOutputStream fos1 = new FileOutputStream(f1);
	ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
	
	oos1.writeObject(list);
	
	}
	catch (Exception e){
		System.out.println(e.getMessage());
	}
	
}*/

	/*
	while(true) {
		try {
			choice3 = input.nextInt();
			break;
		}
		catch (Exception e) {
			System.out.println("enter a number");
			input.nextLine();
		}
	}
	
	
	int chhh = 2;
	while (chhh != 0 && chhh != 1) {
		//if you want to go with the while two lines ok if you don't like to rePrint the two lines
		System.out.println("enter 0 to play another game");
		System.out.println("enter 1 to continue");
	
		while(true) {
			try {
				chhh = input.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("enter a number");
				input.nextLine();
			}
		
		}
		
		
		
		if (chhh != 0 && chhh != 1)
			System.out.println("enter a valid choice");
		
		
		
	}
	
default :
	System.out.println("enter a valid choice");
	continue;
	
	
	
	
	//begginer case
	int cc = -2;
	int choice4; 
	while(cc < 0) {
		choice4 = -5;
		while (choice4 < -1) {
			System.out.println("Enter the number of Players you want");
			System.out.println("Enter -1 if you want all players");
			System.out.println("enter 0 to go back");
	
			while(true) {
				try {
					choice4 = input.nextInt();
					break;
				}
				catch (Exception e) {
					System.out.println("enter a number");
					input.nextLine();
				}
				
				if (choice4 < -1)
					System.out.println("enter a valid choice");
			
			}
		}
		
		
		if (choice4 == 0) {
			cc = 0;
			continue;
		}
		else {
			// showing the grid
			int found1 = TopPlayers.showTopPlayersMovesSorted(choice4, 0);
			
			if (found1 == -1) {
				System.out.println("enter a valid number");
				continue;
			}
			int num = 0;
			if (found1 == 0)
				num = 50;
			
				
			int bb = -1;
			while(bb < 0) {	
				
				int choice5 = -1;
				while (choice5 < 0 || (choice5 > 1 && num == 50) || (choice5 > 4 && num != 50)) {
					System.out.println("enter 0 to go back one step");
					System.out.println("enter 1 to continue");
					if (num != 50) {
						System.out.println("enter 2 to show the grid of a player");
						System.out.println("enter 3 to show the grid of all players");
						System.out.println("enter 4 to empty top player list");

					}
					while(true) {
						try {
							choice5 = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					
					}
					if (choice5 < 0 || (choice5 > 1 && num == 50) || (choice5 > 4 && num != 50))
						System.out.println("enter a valid choice");
					
				}
				
				switch (choice5) {
					case 0 :
						bb = 0;
						continue;
					case 1 :
						bb = 1;
						cc = 1;
						back = 1;
						break;
					case 2 :
						int dd = -1;
						while (dd < 0) {
							System.out.println("enter the rank of player to show the grid");
							int found = TopPlayers.showGridOfRankedPlayer(input.nextInt(), 0);
							if (found == -1) {
								System.out.println("choose a valid number");
								continue;
							}
							if (found == 3) {
								System.out.println("this player has no grid , choose a valid player number");
								continue;
							}
								
							int choice6 = -1;
							while (choice6 != 0 && choice6 != 10 && choice6 != 1) {
								System.out.println("enter 0 to go back one step");
								System.out.println("enter 1 to continue");
								System.out.println("enter 10 to show the rank of another player");
								
								while(true) {
									try {
										choice6 = input.nextInt();
										break;
									}
									catch (Exception e) {
										System.out.println("enter a number");
										input.nextLine();
									}
								
								}
								if (choice6 != 0 && choice6 != 10 && choice6 != 1)
									System.out.println("enter a valid choice");
								
							}
							switch (choice6) {
							case 0 :
								dd = 0;
								continue;
							case 1 :
								dd = 1;
								bb = 1;
								cc = 1;
								back = 1;
								continue;
							case 10 :
								continue;
							
							}
						
						}
						if (dd == 0)
							continue;
						break;
					case 3 : 
							int ee = -1;
							TopPlayers.showGridOfAllPlayer(0);
							while (ee != 0 && ee != 1) {
								System.out.println("enter 0 to go back one step");
								System.out.println("enter 1 to continue");	
							
								while(true) {
									try {
										ee = input.nextInt();
										break;
									}
									catch (Exception e) {
										System.out.println("enter a number");
										input.nextLine();
									}
								
								}
								if (ee != 0 && ee != 1)
									System.out.println("enter a valid choice");
							
							}

						if (ee == 1) {
							bb = 1;
							cc = 1;
							back = 1;
							continue;
						}
						if (ee == 0)
							continue;
						break;
					case 4 :
						TopPlayers.emptyTheFile(0);
						int ff;
						num = 50;
						
						bb = 1;
						cc = 1;
						back = 1;
						continue;
																			
				}

			//	back = (input.nextInt() == 0) ? -1 : 1;
				
			
			}
			if (bb == 0)
				continue;
		}
	}
	//reception
	if (cc == 0)
		continue;
	break;
	
	
	
	int gg = -1;
	while (gg < 0) {
		System.out.println("1. Beginner mode");
		System.out.println("2. Expert mode");
		System.out.println("enter 0 to go back");
		int choice2 = 3;
		while(true) {
			try {
				choice2 = input.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("enter a number");
				input.nextLine();
			}
		}
		switch (choice2) {
			case 0:
				gg = 0;
				continue;
			case 1 :
				int cho = 3;
				while (cho != 1 && cho != 2 && cho != 0) {
					//
					System.out.println("1. One player mode");
					System.out.println("2. Two player mode");
					System.out.println("enter 0 to go back");
					
					while(true) {
						try {
							cho = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					}
					if (cho != 1 && cho != 2 && cho != 0)
						System.out.println("enter a valid choice");
					
				}
				switch(cho) {
					case 0 :
						continue;
					case 1 :
						BigennerGrid.onePlayerMode(5);
						break;
					case 2 :
						BigennerGrid.twoPlayerMode(5);
						break;
					}
				
				break;
			case 2 :
				int c = 3;
				while (c != 1 && c != 2 && c != 0) {
					//
					System.out.println("1. One player mode");
					System.out.println("2. Two player mode");
					System.out.println("enter 0 to go back");
					
					
					while(true) {
						try {
							c = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					}
					if (c != 1 && c != 2 && c != 0)
						System.out.println("enter a valid choice");

				
				}
				switch(c) {
					case 0 :
						continue;
					case 1 :
						ExpertGrid.onePlayerMode(5);
						break;
					case 2 :
						ExpertGrid.twoPlayerMode(5);
						break;
					
				}
				break;
			default :
				System.out.println("enter a valid choice");
				continue;
				
		}
		// to be revised
		int chhh ;
		while (chhh != 0 && chhh != 1) {
			//if you want to go with the while two lines ok if you don't like to rePrint the two lines
			System.out.println("enter 0 to play another game");
			System.out.println("enter 1 to continue");
		
			while(true) {
				try {
					chhh = input.nextInt();
					break;
				}
				catch (Exception e) {
					System.out.println("enter a number");
					input.nextLine();
				}
			
			}
			if (chhh != 0 && chhh != 1)
				System.out.println("enter a valid choice");
		}
		
		if (chhh == 1)
			gg = 1;
		
	
	}
	if (gg == 0)
		continue;
	break;
	*/
	
	/*
	
	Scanner input = new Scanner(System.in);
	ArrayList<GameTurn> list = new ArrayList<GameTurn>();
	ArrayList<Player> players = new ArrayList<Player>();
	
	char grid[][] = createGrid();
	int remainingMoves = 60;
	Player player1 = null;
	Player player2 = null;
	
	int turn = 1;
	
	int row = 0;
	int column = 0;
	
	// reading data
	if (mode == 5) {
		cleanLists();
		redoed2Players = false;
		Player[] arr = readData(2);
		player1 = arr[0];
		player2 = arr[1];
	}
	else if (mode == 6) {
		cleanLists();
		
		// reading from the file
		
		list = (ArrayList<GameTurn>) SaveAndLoad.load(1, 2)[0];
		players = (ArrayList<Player>) SaveAndLoad.load(1, 2)[1];
		
		

		
		if (players == null) {
			System.out.println("you haven't saved any game before in this mode");
			return null;
		}
		else if (list == null) {
			System.out.println("the last saved game in this mode has been completed");
			return null;
		}
		
		
		play.add(players.get(0));
		play.add(players.get(1));
		
		redoed2Players = true;
		//getting the two players
		Player player1FromList = (Player) players.get(0); // index 0
		Player player2FromList = (Player) players.get(1); // index 1
		
		//creating new two players having same name and letter
		player1 = new Player(player1FromList.name, player1FromList.letter);
		player2 = new Player(player2FromList.name, player2FromList.letter);
		
		// resemulate the game 
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (isValid(grid, row, column))
				add(gOut);
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}			
	}
	else if (mode == 7) {
		player1 = play.get(0);
		player2 = play.get(1);
		player1.moves = 0;
		player1.score = 0;
		player2.moves = 0;
		player2.score = 0;
		list = undo();
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}
		
		
	}
	else if (mode == 8) {
		player1 = play.get(0);
		player2 = play.get(1);
		player1.moves = 0;
		player1.score = 0;
		player2.moves = 0;
		player2.score = 0;
		list = redo();
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}			
	}
	
	while (remainingMoves != 0) {
		
			printGrid(grid, player1, player2);

			// Determine whom player is playing 
			Player p = (turn % 2 == 1) ? player1 : player2;
			int continuePlaying = 1;
			if (remainingMoves < 60 || (mode == 7 && remainingMoves == 60)) {
				System.out.println("enter 0 to save and end game");
				System.out.println("enter 1 to play turn");
				System.out.println("enter 2 to undo");
				System.out.println("enter 3 to redo");
				System.out.println("enter 4 to autofill");
				//continuePlaying = input.nextInt();
				while(true) {
					try {
						continuePlaying = input.nextInt();
						break;
					}
					catch (Exception e) {
						System.out.println("enter a number");
						input.nextLine();
					}
				
				}
			}
			
			if (continuePlaying == 1 || continuePlaying == 4) {
				
				// Reading input
				if (remainingMoves == 60) {
					System.out.println("enter 0 to autofill");
					System.out.println("enter 1 to play turn");
					
					int in = 7;//input.nextInt();
					while(true) {
						try {
							in = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					
					}
					
					
					
					if (in == 0) {
						int arr[] = aI(grid);
						row = arr[0];
						column = arr[1];
					}
					else if (in == 1) {
						System.out.println(p.name + " enter a number from 0 to 10 for row");
						//row = input.nextInt();
						

						while(true) {
							try {
								row = input.nextInt();
								break;
							}
							catch (Exception e) {
								System.out.println("enter a number");
								input.nextLine();
							}
						
						}
						
						
						System.out.println(p.name + " enter a number from 0 to 10 for column");
						//column = input.nextInt();
					
						while(true) {
							try {
								column = input.nextInt();
								break;
							}
							catch (Exception e) {
								System.out.println("enter a number");
								input.nextLine();
							}
						
						}
					
					
					
					}
					else {
						System.out.println("enter a valid choice!");
						continue;
					}
				}
				else if (continuePlaying == 1) {
					
				System.out.println(p.name + " enter a number from 0 to 10 for row");
				//row = input.nextInt();
				
				while(true) {
					try {
						row = input.nextInt();
						break;
					}
					catch (Exception e) {
						System.out.println("enter a number");
						input.nextLine();
					}
				
				}
				
				
				
				
				System.out.println(p.name + " enter a number from 0 to 10 for column");
				//column = input.nextInt();
				
				while(true) {
					try {
						column = input.nextInt();
						break;
					}
					catch (Exception e) {
						System.out.println("enter a number");
						input.nextLine();
					}
				
				}
				
				
				}
				else if (continuePlaying == 4) {
					// may be else only
					int arr[] = aI(grid);
					row = arr[0];
					column = arr[1];
				}
				// Adding turn to the list
				GameTurn g = new GameTurn();
				if (isValid(grid, row, column)) {
					// you can add this block to the following block and decrease #lines of code
					g.x = row;
					g.y = column;
					g.player = p;
					list.add(g);
					add(g);
				}
				
				//modifies the grid and increases the score 
				if (modifyGrid(grid, row, column)) {
					remainingMoves--;
					p.moves++; // take care of error in moves 
					if (!addLetter(grid, p))
						turn++;		
				}
			
			}
			
			else if (continuePlaying == 0) {
				printGrid(grid, player1, player2);
				players.add(player1);
				players.add(player2);
				//saving data
				SaveAndLoad.save(list, players, 1, 2);
				//System.out.println("thanks for your time ^_^");
				return null;
			}
			else if (continuePlaying == 2) {
				play.add(player1);
				play.add(player2);
				
				twoPlayerMode(7);
				//System.out.println("thanks for your time ^_^");
				return null;
			}
			else if (continuePlaying == 3) {
				play.add(player1);
				play.add(player2);
				twoPlayerMode(8);
				//System.out.println("thanks for your time ^_^");
				return null;
			}
			else {
				System.out.println("enter a valid choice!");
				continue;
			}
			
	}
	
	if (redoed2Players) 
		SaveAndLoad.save(null, players, 1, 2);
		
	printGrid(grid, player1, player2);
	
	////////////////////////
	detectWinner(player1, player2);
	
	player1.grid = grid;
	player2.grid = grid;
	
	//saving players to leader board
	TopPlayers.write(player1, 1);
	TopPlayers.write(player2, 1);
	
	printWinner(player1, player2);
	return 0;
}
	
}
public static Object twoPlayerMode(int mode) {
	// two players game
	
	Scanner input = new Scanner(System.in);
	ArrayList<GameTurn> list = new ArrayList<GameTurn>();
	ArrayList<Player> players = new ArrayList<Player>();
	
	char grid[][] = createGrid();
	int remainingMoves = 12;
	Player player1 = null;
	Player player2 = null;
	
	int turn = 1;
	
	int row = 0;
	int column = 0;
	
	// reading data
	if (mode == 5) {
		cleanLists();
		redoed2Players = false;
		Player[] arr = readData(2);
		player1 = arr[0];
		player2 = arr[1];
	}
	else if (mode == 6) {
		cleanLists();
		
		// reading from the file 
		list = (ArrayList<GameTurn>) SaveAndLoad.load(0, 2)[0];
		players = (ArrayList<Player>) SaveAndLoad.load(0, 2)[1];
		
		
		if (players == null) {
			System.out.println("you haven't saved any game before in this mode");
			return null;
		}
		else if (list == null) {
			System.out.println("the last saved game in this mode has been completed");
			return null;
		}
		
		play.add(players.get(0));
		play.add(players.get(1));
		
		redoed2Players = true;
		//getting the two players
		Player player1FromList = (Player) players.get(0); // index 0
		Player player2FromList = (Player) players.get(1); // index 1
		
		//creating new two players having same name and letter
		player1 = new Player(player1FromList.name, player1FromList.letter);
		player2 = new Player(player2FromList.name, player2FromList.letter);
		
		// resemulate the game 
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (isValid(grid, row, column))
				add(gOut);
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}			
	}
	else if (mode == 7) {
		player1 = play.get(0);
		player2 = play.get(1);
		player1.moves = 0;
		player1.score = 0;
		player2.moves = 0;
		player2.score = 0;
		list = undo();
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}
		
		
	}
	else if (mode == 8) {
		player1 = play.get(0);
		player2 = play.get(1);
		player1.moves = 0;
		player1.score = 0;
		player2.moves = 0;
		player2.score = 0;
		list = redo();
		GameTurn gOut = new GameTurn();
		for (int i = 0; i < list.size(); i++) {
			Player p = (turn % 2 == 1) ? player1 : player2;
			//printGrid(grid, player1, player2);  //for debugging
			gOut = list.get(i);
			row = gOut.x;
			column = gOut.y;
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++; // take care of error in moves 
				if (!addLetter(grid, p))
					turn++;		
			}	
		}			
	}
	
	while (remainingMoves != 0) {
		
			printGrid(grid, player1, player2);

			// Determine whom player is playing 
			Player p = (turn % 2 == 1) ? player1 : player2;
			int continuePlaying = 1;
			if (remainingMoves < 12 || (mode == 7 && remainingMoves == 12)) {
				System.out.println("enter 0 to save and end game");
				System.out.println("enter 1 to play turn");
				System.out.println("enter 2 to undo");
				System.out.println("enter 3 to redo");
				System.out.println("enter 4 to autofill");

			//	continuePlaying = input.nextInt();
				
				while(true) {
					try {
						continuePlaying = input.nextInt();
						break;
					}
					catch (Exception e) {
						System.out.println("enter a number");
						input.nextLine();
					}
				
				}
			}
			
			if (continuePlaying == 1 || continuePlaying == 4) {
				
				// Reading input
				if (remainingMoves == 12) {
					System.out.println("enter 0 to autofill");
					System.out.println("enter 1 to play turn");
					
					int in = 7;//input.nextInt();
					while(true) {
						try {
							in = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					
					}
					
					
					
					if (in == 0) {
						int arr[] = aI(grid);
						row = arr[0];
						column = arr[1];
					}
					else if (in == 1) {
						System.out.println(p.name + " enter a number from 0 to 4 for row");
						//row = input.nextInt();
					
						while(true) {
							try {
								row = input.nextInt();
								break;
							}
							catch (Exception e) {
								System.out.println("enter a number");
								input.nextLine();
							}
						
						}
						
						System.out.println(p.name + " enter a number from 0 to 4 for column");
						//column = input.nextInt();
						
						while(true) {
							try {
								column = input.nextInt();
								break;
							}
							catch (Exception e) {
								System.out.println("enter a number");
								input.nextLine();
							}
						
						}
						
					
					
					}
					else {
						System.out.println("enter a valid choice!");
						continue;
					}
				}
				else if (continuePlaying == 1) {
					
					System.out.println(p.name + " enter a number from 0 to 4 for row");
					
					//row = input.nextInt();
					
					while(true) {
						try {
							row = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					
					}
					System.out.println(p.name + " enter a number from 0 to 4 for column");
					//column = input.nextInt();
					
					while(true) {
						try {
							column = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
					
					}
					
				}
				else if (continuePlaying == 4) {
					// may be else only
					int arr[] = aI(grid);
					row = arr[0];
					column = arr[1];
				}
				
				// Adding turn to the list
				GameTurn g = new GameTurn();
				if (isValid(grid, row, column)) {
					// you can add this block to the following block and decrease #lines of code
					g.x = row;
					g.y = column;
					g.player = p;
					list.add(g);
					add(g);
				}
				
				//modifies the grid and increases the score 
				if (modifyGrid(grid, row, column)) {
					remainingMoves--;
					p.moves++; // take care of error in moves 
					if (!addLetter(grid, p))
						turn++;		
				}
			
			}
			else if (continuePlaying == 0) {
				printGrid(grid, player1, player2);
				players.add(player1);
				players.add(player2);
				//saving data
				SaveAndLoad.save(list, players, 0, 2);
				//System.out.println("thanks for your time ^_^");
				return null;
			}
			else if (continuePlaying == 2) {
				play.add(player1);
				play.add(player2);
				
				twoPlayerMode(7);
				//System.out.println("thanks for your time ^_^");
				return null;
			}
			else if (continuePlaying == 3) {
				play.add(player1);
				play.add(player2);
				twoPlayerMode(8);
				//System.out.println("thanks for your time ^_^");
				return null;
			}
			else {
				System.out.println("enter a valid choice!");
				continue;
			}
			
			
	}
	
	if (redoed2Players) 
		SaveAndLoad.save(null, players, 0, 2);
		
	printGrid(grid, player1, player2);
	
	////////////////////////
	detectWinner(player1, player2);
	
	player1.grid = grid;
	player2.grid = grid;
	
	//saving players to leader board
	TopPlayers.write(player1, 0);
	TopPlayers.write(player2, 0);
	
	printWinner(player1, player2);
	return 0;}
	*/
}
