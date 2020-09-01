package DotsAndBoxes;

import java.util.Scanner;

public class UI {

	public static void main(String[] args) {
		
			int stop = 1;
			Scanner input = new Scanner (System.in);
			while(stop != 0) {
				System.out.println("         \t\t\tDOTS AND BOXES");
		
				System.out.println("1. Start playing");
				System.out.println("2. Top players");
				System.out.println("3. Load last game");
				System.out.println("4. Close the game");
				int choice1 = 0;
				while(true) {
					try {
						choice1 = input.nextInt();
						break;
					}
					catch (Exception e) {
						System.out.println("enter a number");
						input.nextLine();
					}
				}
				
				switch (choice1) {
					case 1 :
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
							
							if (chhh == 1)
								gg = 1;
							
						
						}
						if (gg == 0)
							continue;
						break;
					case 2 :
						int back = -1;
						while(back < 0) {
							System.out.println("1. Begginer mode");
							System.out.println("2. Expert mode");
							System.out.println("enter 0 to go back");
							int choice3 = 5;

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
							
							switch (choice3) {
								case 0:
									back = 0;
									//here continue and break have same effect , both return to the while header , continue returns directly while break >> breaks switch first then go to the while header
									continue;
								case 1 :
									//begginer case
									int cc = -2;
									 
									while(cc < 0) {
										int choice4 = -5;
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
												
												}
												if (choice4 < -1)
													System.out.println("enter a valid choice");
											
											
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
															
															int n = 8;
															while(true) {
																try {
																	n = input.nextInt();
																	break;
																}
																catch (Exception e) {
																	System.out.println("enter a valid number");
																	input.nextLine();
																}
															
															}
															
															
															int found = TopPlayers.showGridOfRankedPlayer(n, 0);
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
								case 2 :
									// expert
									int jj = -2;
									
									while(jj < 0) {
										int kll = -2; 
										while (kll < -1) {
											System.out.println("Enter the number of Players you want");
											System.out.println("Enter -1 if you want all players");
											System.out.println("enter 0 to go back");
										
											while(true) {
												try {
													kll = input.nextInt();
													break;
												}
												catch (Exception e) {
													System.out.println("enter a number");
													input.nextLine();
												}
											
											}
											if (kll < -1)
												System.out.println("enter a valid choice");
										
										}
										
										if (kll == 0) {
											jj = 0;
											continue;
										}
										else {
											// showing the grid
											int found1 = TopPlayers.showTopPlayersMovesSorted(kll, 1);
											
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
														System.out.println("enter 4 empty top players list");
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
														jj = 1;
														back = 1;
														break;
													case 2 :
														int dd = -1;
														while (dd < 0) {
															System.out.println("enter the rank of player to show the grid");
															int n = 8;
															
															while(true) {
																try {
																	n = input.nextInt();
																	break;
																}
																catch (Exception e) {
																	System.out.println("enter a number");
																	input.nextLine();
																}
															
															}
															
															int found = TopPlayers.showGridOfRankedPlayer(n, 1);
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
																jj = 1;
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
														TopPlayers.showGridOfAllPlayer(1);
														
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
															jj = 1;
															back = 1;
															continue;
														}
														if (ee == 0)
															continue;
														break;
													case 4 :
														TopPlayers.emptyTheFile(1);
														int ff = -5;
														num = 50;
														/*while (ff != 0 && ff != 1) {
															System.out.println("enter 0 to go back one step");
															System.out.println("enter 1 to continue");														
															while(true) {
																try {
																	ff = input.nextInt();
																	break;
																}
																catch (Exception e) {
																	System.out.println("enter a number");
																	input.nextLine();
																}
															
															}
															if (ff != 0 && ff != 1)
																System.out.println("enter a valid choice");
															
														}					
														if (ff == 1 || ff == 0) {*/
															bb = 1;
															jj = 1;
															back = 1;
															continue;
													//	}
														
														
														//break;
														
												}

											//	back = (input.nextInt() == 0) ? -1 : 1;
												
											
											}
											if (bb == 0)
												continue;
										}
									}
									//reception
									if (jj == 0)
										continue;
									break;
									
								default :
									System.out.println("enter a valid choice");
									continue;
									
							}
						}
						//reception
						if (back == 0)
							continue;
		
						break;

					case 3 :
						int u = -1;
						while (u < 0) {
							System.out.println("1. Begginer mode");
							System.out.println("2. Expert mode");
							System.out.println("enter 0 to go back");
							int choiceLoad = 3;
							
							while(true) {
								try {
									choiceLoad = input.nextInt();
									break;
								}
								catch (Exception e) {
									System.out.println("enter a number");
									input.nextLine();
								}
							
							}
							
							switch (choiceLoad) {
								case 1:
									int q = 3;
									while (q != 0 && q != 2 && q != 1) {
										System.out.println("1. One player mode");
										System.out.println("2. Two player mode");
										System.out.println("enter 0 to go back");
									
										while(true) {
											try {
												q = input.nextInt();
												break;
											}
											catch (Exception e) {
												System.out.println("enter a number");
												input.nextLine();
											}
											
							
										}
							

										if (q != 1 && q != 2 && q != 0)
											System.out.println("enter a valid choice");
									
									}
									// to be whiled for "go back" case
									switch (q) {
										case 0 :
											continue;
										case 1 :
											BigennerGrid.onePlayerMode(6);
											break;
										case 2 :
											BigennerGrid.twoPlayerMode(6);	
											break;
									}
									break;
								case 2:
									int m = 3;
									while (m != 1 && m != 2 && m != 0) {
										System.out.println("1. One player mode");
										System.out.println("2. Two player mode");
										System.out.println("enter 0 to go back");
									
										while(true) {
											try {
												m = input.nextInt();
												break;
											}
											catch (Exception e) {
												System.out.println("enter a number");
												input.nextLine();
											}
										}
										
										if (m != 1 && m != 2 && m != 0)
											System.out.println("enter a valid choice");

									
									}
									
									// to be whiled for "go back" case
									switch (m) {
										case 0 :
											continue;
										case 1 :
											ExpertGrid.onePlayerMode(6);
											break;
										case 2 :
											ExpertGrid.twoPlayerMode(6);	
											break;
									}
									break;
								case 0 :
									continue;
								default :
										System.out.println("enter a valid choice");
										continue;
										
							}
							int j = 2;
							while (j != 0 && j != 1) {
								System.out.println("enter 0 to load another game");
								System.out.println("enter 1 to continue");
							
								while(true) {
									try {
										j = input.nextInt();
										break;
									}
									catch (Exception e) {
										System.out.println("enter a number");
										input.nextLine();
									}
								
								}
								if (j != 0 && j != 1)
									System.out.println("enter a valid choice");
								
							}
							if (j == 0)
								continue;
							else 
								u = 1;	
						}
						
						break;
					case 4 :
						System.out.println("Thanks for your time ^_^");
						System.exit(0);
						
					default:
						System.out.println("enter a valid choice");
						continue;

				}
				
				while (true) {
					// true because stop  it is defined before and has a changed value from before 
					System.out.println("\nEnter 0 to end");
					System.out.println("Enter 1 to go back to main menu");
					
					while(true) {
						try {
							stop = input.nextInt();
							break;
						}
						catch (Exception e) {
							System.out.println("enter a number");
							input.nextLine();
						}
						
					}
					
					if (stop != 0 && stop != 1)
						System.out.println("enter a valid choice");
					else 
						break;
				
			}
				
				
			}
			if (stop == 0)
				System.out.println("Thanks for your time ^_^");
			
		
	}

	}
