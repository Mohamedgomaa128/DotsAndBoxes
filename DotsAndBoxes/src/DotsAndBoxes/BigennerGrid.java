package DotsAndBoxes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BigennerGrid {
	
	static int i = 0;
	static ArrayList<GameTurn> mainList = new ArrayList<GameTurn>();
	static ArrayList<Player> play = new ArrayList<Player>();
	static boolean redoed2Players = false;
	static boolean redoed1Players = false;
	
	
	private static char[][] createGrid(){
		// creates the grid
		char grid[][] = new char[5][5];
		
		for (int i = 0; i < grid.length; i += 2)
			for (int j = 0; j < grid[0].length; j += 2)
				grid[i][j] = '.';
		return grid;
	}
	
	public static void printGrid(char grid[][]) {
		// print the grid only
		for (int i = 0; i < grid.length; i++) {
			System.out.print("\t\t\t\t");
			for (int j = 0; j < grid[0].length; j++)
				System.out.print(grid[i][j] + " ");
		    System.out.println();	
		}
	}
	
	public static void printGrid(char grid[][], Player p1, Player p2) {
		// print game
		System.out.println("         \t\t\tDOTS AND BOXES");
		System.out.print("\t\t\t\t  ");
		for (int j = 0; j < grid[0].length; j++)
			System.out.print(j + " ");
		System.out.println();
		for (int i = 0; i < grid.length; i++) {
			System.out.print("\t\t\t\t" + i + " ");
			for (int j = 0; j < grid[0].length; j++)
				System.out.print(grid[i][j] + " ");
		    System.out.println();	
		}
		System.out.println("\t\t\t\t\t\t\t\tthe remaining moves is " + (12 - p1.moves - p2.moves));
		System.out.println("\t\t\t\t\t\t\t\t" + p1.name + " moves is " + p1.moves);
		System.out.println("\t\t\t\t\t\t\t\t" + p2.name + " moves is " + p2.moves);
		System.out.println("\t\t\t\t\t\t\t\t" + p1.name + " score is " + p1.score);
		System.out.println("\t\t\t\t\t\t\t\t" + p2.name + " score is " + p2.score);
		
	}
	
	private static boolean isValid(char grid[][], int x, int y) {
		// check if the place is empty in the grid 
		// the place here is only the valid places 
		// the inner box place will return false , | - places only 
		return (x < 5 && y < 5  && (x % 2 == 0 && y % 2 != 0 || x % 2 != 0 && y % 2 == 0) && grid[x][y] == '\u0000');
	}
	
	public static boolean modifyGrid(char grid[][], int x, int y) {
		// adds a line to the grid
		if (isValid(grid, x, y)) {
			grid[x][y] = (x % 2 == 0) ? '_' : '|';
			return true;
		}
		else {
			System.out.println("wrong place");
			return false;
		}
	}
	
	public static boolean addLetter(char[][] grid, Player p) {
		// adds letter to the grid also treats case of dualbox line 
		boolean done = false;
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j++)
				if (grid[i][j] == '\u0000' && grid[i][j - 1] != '\u0000' && grid[i][j + 1] != '\u0000' && grid[i - 1][j] != '\u0000' && grid[i + 1][j] != '\u0000') {
					grid[i][j] =  p.letter;
					p.score++;
					done = true;
				}
		return done;
	}
	
	public static void printWinner(Player player1, Player player2) {
		if (player1.score > player2.score)
			System.out.println("the winner is " + player1.name + " and with score " + player1.score);
		else if (player1.score < player2.score)
			System.out.println("the winner is " + player2.name + " and with score " + player2.score);
		else 
			System.out.println("DRAW");
	}
	
	public static void detectWinner(Player player1, Player player2) {
		if (player1.score > player2.score) {
			player1.win = "WINNER";
			player2.win = "LOSER";
		}
		else if (player1.score == player2.score) {
			player1.win = "DRAW";
			player2.win = "DRAW";
		}
		else {
			player1.win = "LOSER";
			player2.win = "WINNER";
		}
	}

	private static int[] aI(char[][] grid) {
		// AI of computer
		int cor[] = new int[2];
		//three sided box
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j += 2) {
				if (grid[i][j] == '\u0000' && grid[i][j + 1] != '\u0000' && grid[i][j - 1] != '\u0000' && grid[i + 1][j] != '\u0000') {
					cor[0] = i - 1;
					cor[1] = j;
					return cor;
				}
				if (grid[i][j] == '\u0000' && grid[i][j + 1] != '\u0000' && grid[i][j - 1] != '\u0000' && grid[i - 1][j] != '\u0000') {
					cor[0] = i + 1;
					cor[1] = j;
					return cor;
				}
				if (grid[i][j] == '\u0000' && grid[i][j + 1] != '\u0000' && grid[i - 1][j] != '\u0000' && grid[i + 1][j] != '\u0000') {
					cor[0] = i;
					cor[1] = j - 1;
					return cor;
				}
				if (grid[i][j] == '\u0000' && grid[i][j - 1] != '\u0000' && grid[i + 1][j] != '\u0000' && grid[i - 1][j] != '\u0000') {
					cor[0] = i;
					cor[1] = j + 1;
					return cor;
				}
			}
				
		//zero sided box
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j += 2) {
				if (grid[i][j] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i + 1][j] == '\u0000' && grid[i - 1][j] == '\u0000' && i == 1) {
					cor[0] = i - 1;
					cor[1] = j;
					return cor;
				}
				if (grid[i][j] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i + 1][j] == '\u0000' && grid[i - 1][j] == '\u0000' && i == 3) {
					cor[0] = i + 1;
					cor[1] = j;
					return cor;
				}
			}
		
		//one sided box 
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j += 2) {
				if (grid[i][j] == '\u0000' && i == 1 &&(grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i - 1][j] == '\u0000' || grid[i][j + 1] == '\u0000' && grid[i + 1][j] == '\u0000' && grid[i - 1][j] == '\u0000' || grid[i][j - 1] == '\u0000' && grid[i - 1][j] == '\u0000' && grid[i + 1][j] == '\u0000')) {
					cor[0] = i - 1;
					cor[1] = j;
					return cor;
				}
				if (grid[i][j] == '\u0000' && i == 1 && grid[i][j - 1] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i + 1][j] == '\u0000') {
					cor[0] = i;
					cor[i] = (j == 1) ? 0 : 4;
					return cor;
				}
				
				if (grid[i][j] == '\u0000' && i == 3 &&(grid[i][j + 1] == '\u0000' && grid[i][j - 1] == '\u0000' && grid[i + 1][j] == '\u0000' || grid[i][j + 1] == '\u0000' && grid[i + 1][j] == '\u0000' && grid[i - 1][j] == '\u0000' || grid[i][j - 1] == '\u0000' && grid[i - 1][j] == '\u0000' && grid[i + 1][j] == '\u0000')) {
					cor[0] = i + 1;
					cor[1] = j;
					return cor;
				}
				if (grid[i][j] == '\u0000' && i == 3 && grid[i][j - 1] == '\u0000' && grid[i][j + 1] == '\u0000' && grid[i - 1][j] == '\u0000') {
					cor[0] = i;
					cor[1] = (j == 1) ? 0 : 4;
					return cor;
				}
				
				
			}
		// two sided box
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[0].length; j++)
				if (grid[i][j] == '\u0000' && !(i == 1 && j == 1 || i == 1 && j == 3 || i == 3 && j == 1 || i == 3 && j == 3)) {
					cor[0] = i;
					cor[1] = j;
					return cor;
				}
		return null;
				
	}
	
	private static ArrayList<GameTurn> undo() {
		
		ArrayList<GameTurn> list = new ArrayList<GameTurn>();
		if (i == 0) {
			System.out.println("there is no moves to undo");
			return list;
		}
		for (int j = 0; j < i - 1; j++)
			list.add(mainList.get(j));
		//(ArrayList<GameTurn>) mainList.subList(0, i - 1);
		i--;
		return list;	
	}
	
	private static ArrayList<GameTurn> redo() {
		ArrayList<GameTurn> list = new ArrayList<GameTurn>();
		if (mainList.get(i) == null) {
			System.out.println("error there is no moves to redo");
			
			for (int j = 0; j < i; j++)
				list.add(mainList.get(j));
			
			return list;
		}
		for (int j = 0; j < i + 1; j++)
			list.add(mainList.get(j));
		//(ArrayList<GameTurn>) mainList.subList(0, i + 1);
		i++;
		return list;		
	}
	
	private static void add(GameTurn g) {
		mainList.add(i, g);
		mainList.add(i + 1, null);
		i++;
	}
	
	private static void cleanLists() {
			mainList.clear();
			play.clear();
			i = 0;
	}
	
	public static Player[] readData(int numberOfPlayers) {
		
		Scanner input = new Scanner(System.in);
		
		Player[] arr = new Player[2];
		String name1;
		String name2 = null;
		char firstChar;
		char secondChar = 0;
		
		System.out.println("enter the name of the first player");
		name1 = input.next();
		
		
		
		while (true) {
			System.out.println("enter the character of the first player");
			firstChar = input.next().charAt(0);	
			if (firstChar >= 65 && firstChar <= 90 || firstChar >= 97 && firstChar <= 122) 
				break;
			else 
				System.out.println("error!\nchoose an alphabetic character");
		}
		
		
		
		if (numberOfPlayers == 2) {
			while (true) {
				System.out.println("enter the name of the second player");
				name2 = input.next();	
				if (name2.toLowerCase().equals(name1.toLowerCase())) {
					System.out.println("error!\nchoose different name from the first player");
				}
				else {
					while (true) {
						System.out.println("enter the character of the second player");
						secondChar = input.next().charAt(0);	
						if ((secondChar >= 65 && secondChar <= 90 || secondChar >= 97 && secondChar <= 122) && Character.toLowerCase(secondChar) != Character.toLowerCase(firstChar)) 
							break;
						else if (Character.toLowerCase(secondChar) == Character.toLowerCase(firstChar))
							System.out.println("error!\nchoose different character from the first player");
						else 
							System.out.println("error!\nchoose an alphabetic character");
					}
					break;
				}
			
			}
		}
		else if (numberOfPlayers == 1) {
			name2 = "computer";
			secondChar = 'C';
		}
		arr[0] = new Player(name1, firstChar);
		arr[1] = new Player(name2, secondChar);	
		
		return arr;
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
		return 0;
	}
		
	public static Object onePlayerMode(int mode) {
		// one player mode 
		//cleaning the two lists if he was playing a game and finished it then starts another game without ending the program
			
		
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
			cleanLists();
			redoed1Players = false;
			Player[] arr = readData(1);
			player1 = arr[0];
			player2 = arr[1];
		//printGrid(grid);
		}
		else if (mode == 6) {
			cleanLists();
			// reading from the file 
			list = (ArrayList<GameTurn>) SaveAndLoad.load(0, 1)[0];
			players = (ArrayList<Player>) SaveAndLoad.load(0, 1)[1];
			

			if (players == null) {
				System.out.println("you haven't saved any game before in this mode");
				return null;
			}
			else if (list == null) {
				System.out.println("the last saved game in this mode has been completed");
				return null;
			}
				
			play.add(players.get(0));
			redoed1Players = true;
			//getting the player
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
			player1.moves = 0;
			player1.score = 0;
			player2 = new Player("computer", 'C');
			
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
			player1.moves = 0;
			player1.score = 0;
			player2 = new Player("computer", 'C');
			
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
			
			Player p = (turn % 2 == 1) ? player1 : player2;
			// player or computer 
			
				int continuePlaying = 1;
				if (remainingMoves < 12 || (mode == 7 && remainingMoves == 12)) {
					//here a method to print this menu can also be separated according to number of players
					System.out.println("enter 0 to save and end game");
					System.out.println("enter 1 to " + ((p == player1) ? "play turn" : "let the computer play" ));
					System.out.println("enter 2 to undo");
					System.out.println("enter 3 to redo");
					if (p == player1)
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
				
				if (continuePlaying == 1) {
					if (p == player1) {
						// Reading input
						//here a method to read input can also be separated according to number of players
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
						else if (remainingMoves < 12) {
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
					}
					else {
						int arr[] = aI(grid);
						row = arr[0];
						column = arr[1];
					}
				}
				else if (continuePlaying == 4 && p == player1) {
					int arr[] = aI(grid);
					row = arr[0];
					column = arr[1];
				}
				else if (continuePlaying == 0) {
					printGrid(grid, player1, player2);
					players.add(player1);
					
					//saving data
					SaveAndLoad.save(list, players, 0, 1);
					//System.out.println("thanks for your time ^_^");
					return null;
				}
				else if (continuePlaying == 2) {
					play.add(player1);
					//play.add(player2);
					
					onePlayerMode(7);
					//System.out.println("thanks for your time ^_^");
					return null;
				}
				else if (continuePlaying == 3) {
					play.add(player1);
					//play.add(player2);
					onePlayerMode(8);
					//System.out.println("thanks for your time ^_^");
					return null;
				}
				else {
					System.out.println("enter a valid choice!");
					continue;
				}
			
			
			
			// Adding turn to the list
			//here a method to do this task can also be separated according to number of players

			GameTurn g = new GameTurn();
			if (isValid(grid, row, column)) {
				// you can add this block to the following block and decrease #lines of code
				g.x = row;
				g.y = column;
				g.player = p;
				list.add(g);
				add(g);
			}
			
			if (modifyGrid(grid, row, column)) {
				remainingMoves--;
				p.moves++;
				if (!addLetter(grid, p))
					turn++;		
			}
			
			
			
			
		}
		if (redoed1Players) 
			SaveAndLoad.save(null, players, 0, 1);
		
		printGrid(grid, player1, player2);
		
		
		///////////
		detectWinner(player1, player2);
		player1.grid = grid;
		//player2.grid = grid; // because it is the computer
		
		//saving player to leader board
		TopPlayers.write(player1, 0);
		
		printWinner(player1, player2);
		return 0;
	}
	
			
}