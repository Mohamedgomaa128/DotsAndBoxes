package DotsAndBoxes;

import java.util.ArrayList;
import java.util.Scanner;

import DotsAndBoxes.BigennerGrid;

public class ExpertGrid {
	
	static int i = 0;
	static ArrayList<GameTurn> mainList = new ArrayList<GameTurn>();
	static ArrayList<Player> play = new ArrayList<Player>();
	static boolean redoed2Players = false;
	static boolean redoed1Players= false;
	
	public static char[][] createGrid(){
		// to be changed into private
		// creates the grid
		char grid[][] = new char[11][11];
		
		for (int i = 0; i < grid.length; i += 2)
			for (int j = 0; j < grid[0].length; j += 2)
				grid[i][j] = '.';
		return grid;
	}
	
	public static void printGrid(char[][] grid, Player p1, Player p2) {
		// print game
		System.out.println("\t\t\t\t      DOTS AND BOXES");
		System.out.print("\t\t\t\t   ");
		for (int j = 0; j < grid[0].length; j++)
			System.out.print(j + " ");
		System.out.println();
		for (int i = 0; i < grid.length; i++) {
			System.out.printf("\t\t\t\t%2d ",i);
			for (int j = 0; j < grid[0].length; j++)
				System.out.print(grid[i][j] + " ");
		    System.out.println();	
		}
		
		System.out.println("\t\t\t\t\t\t\t\tthe remaining moves is " + (60 - p1.moves - p2.moves));
		System.out.println("\t\t\t\t\t\t\t\t" + p1.name + " moves is " + p1.moves);
		System.out.println("\t\t\t\t\t\t\t\t" + p2.name + " moves is " + p2.moves);
		System.out.println("\t\t\t\t\t\t\t\t" + p1.name + " score is " + p1.score);
		System.out.println("\t\t\t\t\t\t\t\t" + p2.name + " score is " + p2.score);
	
	}

	public static void printGrid(char grid[][]) {
		BigennerGrid.printGrid(grid);
	}
	
	private static boolean isValid(char grid[][], int x, int y) {
		// check if the place is empty in the grid 
		// the place here is only the valid places 
		// the inner box place will return false , | - places only 
		return (x < 11 && y < 11  && (x % 2 == 0 && y % 2 != 0 || x % 2 != 0 && y % 2 == 0) && grid[x][y] == '\u0000');
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
		return BigennerGrid.addLetter(grid, p);
	}
	
	private static void printWinner(Player player1, Player player2) {
		BigennerGrid.printWinner(player1, player2);
	}	
	
	private static void detectWinner(Player player1, Player player2) {
		BigennerGrid.detectWinner(player1, player2);
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
				//mainList.size() == i) {
			
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
		return BigennerGrid.readData(numberOfPlayers);
	}
	
	public static Object twoPlayerMode(int mode) {
		// two players game
		
			
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
	
	public static Object onePlayerMode(int mode) {
		// one player mode 
		//cleaning the two lists if he was playing a game and finished it then starts another game without ending the program
			
		
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
			list = (ArrayList<GameTurn>) SaveAndLoad.load(1, 1)[0];
			players = (ArrayList<Player>) SaveAndLoad.load(1, 1)[1];
			
			
			
			
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
				if (remainingMoves < 60 || (mode == 7 && remainingMoves == 60)) {
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
				if (p == player2 && continuePlaying == 4) {
					System.out.println("enter a valid choice!");
					continue;
				}
				
				if (continuePlaying == 1) {
					if (p == player1) {
						// Reading input
						//here a method to read input can also be separated according to number of players
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
						else if (remainingMoves < 60) {
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
					}
					else {
						int arr[] = aI(grid);
						row = arr[0];
						column = arr[1];
					}
				}
				else if (continuePlaying == 4) {
					int arr[] = aI(grid);
					row = arr[0];
					column = arr[1];
				}
				else if (continuePlaying == 0) {
					printGrid(grid, player1, player2);
					players.add(player1);
					
					//saving data
					SaveAndLoad.save(list, players, 1, 1);
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
			SaveAndLoad.save(null, players, 1, 1);
		
		printGrid(grid, player1, player2);
		
		
		///////////
		detectWinner(player1, player2);
		player1.grid = grid;
		//player2.grid = grid; // because it is the computer
		
		//saving player to leader board
		TopPlayers.write(player1, 1);
		
		printWinner(player1, player2);
		return 0;
	}
	
	public static int[] aI(char[][] grid) {
		int cor[] = new int[2];
		
		
		//3 sided box
		//the same as begg
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j += 2) {
				if (numOfSides(grid, i, j) == 3) {
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
						//can be done without else or if here as it is the last case
						cor[0] = i;
						cor[1] = j + 1;
						return cor;
					}
				}
			}
		
		//0 sided box
		for (int k = 0; k < 3; k++) {
			// the 3 possibilities 0 >> 1 >> 2 sided boxes
			
			for (int i = 1; i < grid.length; i += 2)
				for (int j = 1; j < grid[0].length; j += 2) {
					if (numOfSides(grid, i, j) == 0) {
						int[] arr = minDirection(grid, i, j);	
						
						if (arr[2] == k) {
							cor[0] = arr[0];
							cor[1] = arr[1];
							return cor;
						}
					
					}
					
				}	
		}
		
		
		//1 sided box
		
		for (int k = 0; k < 3; k++) {
			// the 2 possibilities 1 >> 2 sided boxes
			
			for (int i = 1; i < grid.length; i += 2)
				for (int j = 1; j < grid[0].length; j += 2) {
					
					if (numOfSides(grid, i, j) == 1) {
						int[] arr = oneSidedAI(grid, i, j);
						
						if(arr[2] == k) {
							cor[0] = arr[0];
							cor[1] = arr[1];
							return cor;
						}
					}
					
				}	
		}
		
		
		
		
		
		//2 sided box
		
		//could be removed since the case of determining how to decrease no of boxes for the opponent player is not detectable may be 0 or may be 2
		// if has zero sided box behind it 
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j += 2) {
				
				if (numOfSides(grid, i, j) == 2) {
					int[] arr = oneSidedAI(grid, i, j);
					
					if(arr[2] == 0) {
						cor[0] = arr[0];
						cor[1] = arr[1];
						return cor;
					}
				}
				
				
			}
		
		
		//the last possibility if has 2 sided box behind it 
		for (int i = 1; i < grid.length; i += 2)
			for (int j = 1; j < grid[0].length; j += 2) {
				if (numOfSides(grid, i, j) == 2) {
					if (grid[i + 1][j] != '\u0000' && grid[i - 1][j] != '\u0000' || grid[i + 1][j] != '\u0000' && grid[i][j - 1] != '\u0000' || grid[i - 1][j] != '\u0000' && grid[i][j - 1] != '\u0000') {
						cor[0] = i;
						cor[1] = j + 1;
						return cor;
					}
					if (grid[i][j + 1] != '\u0000' && grid[i + 1][j] != '\u0000' || grid[i][j + 1] != '\u0000' && grid[i - 1][j] != '\u0000') {
						cor[0] = i;
						cor[1] = j - 1;
						return cor;
					}
					if (grid[i][j - 1] != '\u0000' && grid[i][j + 1] != '\u0000') {
						cor[0] = i + 1;
						cor[1] = j;
						return cor;
					}
				}
				
			}	
		
		
		
		return null;	
	}

	public static int numOfSides(char[][] grid, int i, int j) {
		if (i < 0)
			return (grid[i + 1][j] != '\u0000' ? 1 : 0);
		if (i > 10)
			return (grid[i - 1][j] != '\u0000' ? 1 : 0);
			
		if (j < 0)
			return (grid[i][j + 1] != '\u0000' ? 1 : 0);
		if (j > 10)
			return (grid[i][j - 1] != '\u0000' ? 1 : 0);
		
		if (grid[i][j] != '\u0000')
			return 4;
		int n = 0;
		if (grid[i - 1][j] != '\u0000')
			n++;
		if (grid[i + 1][j] != '\u0000')
			n++;
		if (grid[i][j - 1] != '\u0000')
			n++;
		if (grid[i][j + 1] != '\u0000')
			n++;
		return n;	
	}
	
	public static int[] minDirection(char[][] grid,int i, int j) {
		int arr[] = new int[4];
		
		int returned[] = new int[3];
		arr[0] = numOfSides(grid, i - 2, j);
		arr[1] = numOfSides(grid, i, j + 2);
		arr[2] = numOfSides(grid, i + 2, j);
		arr[3] = numOfSides(grid, i, j - 2);
		
		int minIndex = minIndex(arr);
		
		if (minIndex == 0) {
			returned[0] = i - 1;
			returned[1] = j;
		}
		else if (minIndex == 1) {
			returned[0] = i;
			returned[1] = j + 1;
		}
		else if (minIndex == 2) {
			returned[0] = i + 1;
			returned[1] = j;
		}
		else if (minIndex == 3) {
			returned[0] = i;
			returned[1] = j - 1;
		}
		returned[2] = arr[minIndex];
		return returned;
	}
		
	public static int minIndex(int[] arr) {
		int min = arr[0];
		int minIndex = 0;
		
		for (int i = 1; i < arr.length; i++)
			if (arr[i] < min) {
				min = arr[i];
				minIndex = i;
			}
		return minIndex;
	}
	
    public static int[] oneSidedAI(char[][] grid, int i, int j) {
		//detects the least direction and also the number of lines within it  
		int[] arr = new int[3];
		int[] returned = new int[3];
		
		if (grid[i - 1][j] != '\u0000') {
			// b, c, d
			arr[0] = numOfSides(grid, i, j + 2);
			arr[1] = numOfSides(grid, i + 2, j);
			arr[2] = numOfSides(grid, i, j - 2);
			
			int minIndex = minIndex(arr);
			
			switch(minIndex) {
				case 0 :
					returned[0] = i;
					returned[1] = j + 1;
					returned[2] = arr[0];
					return returned;
				case 1 :
					returned[0] = i + 1;
					returned[1] = j;
					returned[2] = arr[1];
					return returned;
				case 2 :
					returned[0] = i;
					returned[1] = j - 1;
					returned[2] = arr[2];
					return returned;
			}
			
			
		}
		else if (grid[i + 1][j] != '\u0000') {
			//a, b, d
			
			arr[0] = numOfSides(grid, i - 2, j);
			arr[1] = numOfSides(grid, i, j + 2);
			arr[2] = numOfSides(grid, i, j - 2);
			
			int minIndex = minIndex(arr);
			
			switch(minIndex) {
				case 0 :
					returned[0] = i - 1;
					returned[1] = j;
					returned[2] = arr[0];
					return returned;
				case 1 :
					returned[0] = i;
					returned[1] = j + 1;
					returned[2] = arr[1];
					return returned;
				case 2 :
					returned[0] = i;
					returned[1] = j - 1;
					returned[2] = arr[2];
					return returned;
			}
			
			
			
		}
		else if (grid[i][j + 1] != '\u0000') {
			//a, c, d
			
			arr[0] = numOfSides(grid, i - 2, j);
			arr[1] = numOfSides(grid, i + 2, j);
			arr[2] = numOfSides(grid, i, j - 2);
			
			int minIndex = minIndex(arr);
			
			switch(minIndex) {
				case 0 :
					returned[0] = i - 1;
					returned[1] = j;
					returned[2] = arr[0];
					return returned;
				case 1 :
					returned[0] = i + 1;
					returned[1] = j;
					returned[2] = arr[1];
					return returned;
				case 2 :
					returned[0] = i;
					returned[1] = j - 1;
					returned[2] = arr[2];
					return returned;
			}
			
		}
		//a, b, c
		
		arr[0] = numOfSides(grid, i - 2, j);
		arr[1] = numOfSides(grid, i, j + 2);
		arr[2] = numOfSides(grid, i + 2, j);
		
		int minIndex = minIndex(arr);
		
		switch(minIndex) {
			case 0 :
				returned[0] = i - 1;
				returned[1] = j;
				returned[2] = arr[0];
				break;
			case 1 :
				returned[0] = i;
				returned[1] = j + 1;
				returned[2] = arr[1];
				break;
			case 2 :
				returned[0] = i + 1;
				returned[1] = j;
				returned[2] = arr[2];
				break;
		}
		
		return returned;
	}
	
	
	
	
}
