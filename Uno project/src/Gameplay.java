import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Gameplay
	{

		public static void main(String[] args)
			{
				fillDeck();
				greetUser();
				addingCardsToHand();
				playGame();			
			}
		



		static Scanner userInput = new Scanner(System.in);
		static String name;
		static ArrayList<CardType> playerHand = new ArrayList<CardType>();
		static ArrayList<CardType> deck = new ArrayList<CardType>();
		static ArrayList<CardType> pile = new ArrayList<CardType>();
		static ArrayList<CardType> botHand1 = new ArrayList<CardType>();
		static ArrayList<CardType> botHand2 = new ArrayList<CardType>();
		static ArrayList<CardType> botHand3 = new ArrayList<CardType>();
		static boolean playerTurn = true;
		static boolean bot1Turn = true;
		static boolean bot2Turn = true;
		static boolean bot3Turn = true;
		static boolean playerReverse = false;
		static boolean bot1Reverse = false;
		static boolean bot2Reverse = false;
		static boolean bot3Reverse = false;
		static int amountOfReverse = 0;
		
		private static void fillDeck()
			{
				Cards.main(null);
				
				for(int i = 0; i < Cards.card.size(); i++)
					{
						deck.add(Cards.card.get(i));
					}
				
			}
		
		private static void greetUser()
			{
				
				System.out.println("Welcome to UNO, please input your name to begin.");
				name = userInput.nextLine();
				System.out.println("Alright " + name + ", let's start.");
				
				
				
			}
		
		private static void addingCardsToHand()
			{
				Collections.shuffle(deck);
				System.out.println("Adding cards to your hand ");
				
				for(int i = 0; i < 7; i++)
					{				
						playerHand.add(deck.get(i));
						deck.remove(i);
						
						botHand1.add(deck.get(i));
						deck.remove(i);
						
						botHand2.add(deck.get(i));
						deck.remove(i);
						
						botHand3.add(deck.get(i));
						deck.remove(i);
						
						timer();
						System.out.print(".");
					}
				pile.add(deck.get(0));
				deck.remove(0);
				
				System.out.println("");
				
				
				
				
			}
		
		private static void refillDeck()
			{
				System.out.println("Refilling deck");
				for(int i = pile.size(); i > 5; i--)
					{
						deck.add(pile.get(i - 1));
						pile.remove(i - 1);
					}
				
			}

		private static void playGame()
			{			
				playerTurn();
				bot1Turn();
				bot2Turn();
				bot3Turn();			
			}

		private static void bot1Turn()
			{
				while(botHand1.size() != 0 && bot1Turn)
					{
						
						for(int i = 0; i <= botHand1.size(); i++)
							{
								
								if(botHand1.get(i).getNumber() == 12 && botHand1.get(i).getNumber() == pile.get(pile.size() - 1).getNumber() || (botHand1.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())) && botHand1.get(i).getNumber() == 12)
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 1 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol() + ".");
										if(amountOfReverse % 2 == 1)
											{
												System.out.println("You drew 2 cards.");
												botHand1.remove(i);
												playerHand.add(deck.get(0));
												deck.remove(0);
												playerHand.add(deck.get(0));
												deck.remove(0);
												playerTurn = false;
											}
										else
											{
												System.out.println("Bot 2 drew 2 cards.");
												botHand1.remove(i);
												botHand2.add(deck.get(0));
												deck.remove(0);
												botHand2.add(deck.get(0));
												deck.remove(0);
												bot2Turn = false;
											}
										bot3Turn = true;
										break;
									}
								
								else if((botHand1.get(i).getNumber() == 11 && botHand1.get(i).getNumber() == pile.get(pile.size() - 1).getNumber()) || (botHand1.get(i).getColor().equals(pile.get(pile.size() - 1)) && botHand1.get(i).getNumber() == 11))
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 1 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol() + ".");
										botHand1.remove(i);
										bot1Reverse = true;
										amountOfReverse++;
										break;
									}
								
								else if(botHand1.get(i).getNumber() == 10  && botHand1.get(i).getNumber() == pile.get(pile.size() - 1).getNumber() || (botHand1.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) && botHand1.get(i).getNumber() == 10))
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 1 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol() + ".");
										if(amountOfReverse % 2 == 1)
											{
												System.out.println("Bot 1 skipped you.");
												botHand1.remove(i);
												playerTurn = false;
											}
										else
											{
												System.out.println("Bot 1 skipped Bot 2.");
												botHand1.remove(i);
												bot2Turn = false;
											}
										bot3Turn = true;
										break;
									}
								
								else if(botHand1.get(i).getNumber() < 10 && botHand1.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) || botHand1.get(i).getNumber() == pile.get(pile.size() - 1).getNumber())
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 1 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol() + ".");
										botHand1.remove(i);
										bot2Turn = true;
										playerTurn = true;
										break;
									}
								else if(i == botHand1.size() - 1)
									{
										timer();
										botHand1.add(deck.get(0));
										System.out.println("Bot 1 draws.");
										deck.remove(0);
										bot2Turn = true;
										playerTurn = true;
										break;
									}
								
							}
						timer();
						
						if(botHand1.size() == 0)
							{
								winner();
								break;
							}
						System.out.println("Bot 1 has " + botHand1.size() + " cards.");
						if(deck.size() < 5)
							{
								refillDeck();
							}
						if(amountOfReverse % 2 == 1 && playerTurn)
							{
								playerTurn();
							}
						else if(amountOfReverse % 2 == 1)
							{
								bot3Turn();
							}
						break;
												
					} //while bot has cards
				
			}

		private static void bot2Turn()
			{
				while(botHand2.size() != 0 && bot2Turn)
					{
						
						for(int i = 0; i <= botHand2.size(); i++)
							{
								
								if(botHand2.get(i).getNumber() == 12  && botHand2.get(i).getNumber() == pile.get(pile.size() - 1).getNumber() || (botHand2.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) && botHand2.get(i).getNumber() == 12))
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 2 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol() + ".");
										if(amountOfReverse % 2 == 1)
											{
												System.out.println("Bot 1 drew 2 cards.");
												botHand2.remove(i);
												botHand1.add(deck.get(0));
												deck.remove(0);
												botHand1.add(deck.get(0));
												deck.remove(0);
												bot1Turn = false;
											}
										else
											{
												System.out.println("Bot 3 drew 2 cards.");
												botHand2.remove(i);
												botHand3.add(deck.get(0));
												deck.remove(0);
												botHand3.add(deck.get(0));
												deck.remove(0);
												bot3Turn = false;
											}
										playerTurn = true;
										break;
									}
								
								else if((botHand2.get(i).getNumber() == 11 && botHand2.get(i).getNumber() == pile.get(pile.size() - 1).getNumber()) || (botHand2.get(i).getColor().equals(pile.get(pile.size() - 1)) && botHand2.get(i).getNumber() == 11))
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 2 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol() + ".");
										botHand2.remove(i);
										bot2Reverse = true;
										amountOfReverse++;
										break;
									}
								
								else if(botHand2.get(i).getNumber() == 10  && botHand2.get(i).getNumber() == pile.get(pile.size() - 1).getNumber() || (botHand2.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) && botHand2.get(i).getNumber() == 10))
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 2 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol() + ".");
										if(amountOfReverse % 2 == 1)
											{
												System.out.println("Bot 2 skipped Bot 1.");
												botHand2.remove(i);
												bot1Turn = false;
											}
										else
											{
												System.out.println("Bot 2 skipped Bot 3.");
												botHand2.remove(i);
												bot3Turn = false;
											}
										playerTurn = true;
										break;
									}
								
								else if(botHand2.get(i).getNumber() < 10 && botHand2.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) || botHand2.get(i).getNumber() == pile.get(pile.size() - 1).getNumber())
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 2 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol() + ".");
										botHand2.remove(i);
										bot3Turn = true;
										bot1Turn = true;
										break;
									}
								else if(i == botHand2.size() - 1)
									{
										timer();
										botHand2.add(deck.get(0));
										System.out.println("Bot 2 draws.");
										deck.remove(0);
										bot3Turn = true;
										bot1Turn = true;
										break;
									}
								
							}
						timer();
						
						if(botHand2.size() == 0)
							{
								winner();
							}
						System.out.println("Bot 2 has " + botHand2.size() + " cards.");
						if(deck.size() < 5)
							{
								refillDeck();
							}
						if(amountOfReverse % 2 == 1 && bot1Turn)
							{
								bot1Turn();
							}
						else if(amountOfReverse % 2 == 1)
							{
								playerTurn();
							}
						break;
												
					} //while bot1 has cards
				
			}

		private static void bot3Turn()
			{
				while(botHand3.size() != 0 && bot3Turn)
					{
						
						for(int i = 0; i <= botHand3.size(); i++)
							{
								
								if(botHand3.get(i).getNumber() == 12  && botHand3.get(i).getNumber() == pile.get(pile.size() - 1).getNumber() || (botHand3.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) && botHand3.get(i).getNumber() == 12))
									{
										timer();
										pile.add(botHand3.get(i));
										System.out.println("Bot 3 has played a " + botHand3.get(i).getColor() + " " + botHand3.get(i).getSymbol() + ".");
										if(amountOfReverse % 2 == 1)
											{
												System.out.println("Bot 2 drew 2 cards.");
												botHand3.remove(i);
												botHand2.add(deck.get(0));
												deck.remove(0);
												botHand2.add(deck.get(0));
												deck.remove(0);
												bot2Turn = false;
											}
										else
											{
												System.out.println("You drew 2 cards.");
												botHand3.remove(i);
												playerHand.add(deck.get(0));
												deck.remove(0);
												playerHand.add(deck.get(0));
												deck.remove(0);
												playerTurn = false;
											}
										bot1Turn = true;											
										break;
									}
								
								else if((botHand3.get(i).getNumber() == 11 && botHand3.get(i).getNumber() == pile.get(pile.size() - 1).getNumber()) || (botHand3.get(i).getColor().equals(pile.get(pile.size() - 1)) && botHand3.get(i).getNumber() == 11))
									{
										timer();
										pile.add(botHand3.get(i));
										System.out.println("Bot 3 has played a " + botHand3.get(i).getColor() + " " + botHand3.get(i).getSymbol() + ".");
										botHand3.remove(i);
										bot3Reverse = true;
										amountOfReverse++;
										break;
									}
								
								else if(botHand3.get(i).getNumber() == 10  && botHand3.get(i).getNumber() == pile.get(pile.size() - 1).getNumber() || (botHand3.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) && botHand3.get(i).getNumber() == 10))
									{
										timer();
										pile.add(botHand3.get(i));
										System.out.println("Bot 3 has played a " + botHand3.get(i).getColor() + " " + botHand3.get(i).getSymbol() + ".");
										if(amountOfReverse % 2 == 1)
											{
												System.out.println("Bot 3 skipped Bot 2.");
												botHand3.remove(i);
												bot2Turn = false;
											}
										else
											{
												System.out.println("Bot 3 skipped you.");
												botHand3.remove(i);
												playerTurn = false;
											}
										bot1Turn = true;
										break;
									}
								
								else if(botHand3.get(i).getNumber() < 10 && botHand3.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) || botHand3.get(i).getNumber() == pile.get(pile.size() - 1).getNumber())
									{
										timer();
										pile.add(botHand3.get(i));
										System.out.println("Bot 3 has played a " + botHand3.get(i).getColor() + " " + botHand3.get(i).getSymbol() + ".");
										botHand3.remove(i);
										playerTurn = true;
										bot2Turn = true;
										break;
									}
								else if(i == botHand3.size() - 1)
									{
										timer();
										botHand3.add(deck.get(0));
										System.out.println("Bot 3 draws.");
										deck.remove(0);
										playerTurn = true;
										bot2Turn = true;
										break;
									}
								
							}
						timer();
						
						if(botHand3.size() == 0)
							{
								winner();
							}
						System.out.println("Bot 3 has " + botHand3.size() + " cards.");
						break;
												
					} //while bot2 has cards
				timer();
				if(deck.size() < 5)
					{
						refillDeck();
					}
				if(amountOfReverse % 2 == 1 && bot2Turn)
					{
						bot2Turn();
					}
				else if(amountOfReverse % 2 == 1)
					{
						bot1Turn();
					}
				
				else if(playerHand.size() != 0 && botHand1.size() != 0 && botHand2.size() != 0 && botHand3.size() != 0)
					{
						playGame();
					}
				
				
			}

		private static void playerTurn()
			{
				while(playerHand.size() != 0 && playerTurn)
					{
						int amountOfCards = 1;
						boolean playing = true;
						for(CardType i : playerHand)
							{
								System.out.println("[" + amountOfCards + "] "+ i.getColor() + " " + i.getSymbol());
								amountOfCards++;
							}
						System.out.println("[" + amountOfCards + "]" + " Draw");
						
						System.out.println("The card on top is " + pile.get(pile.size() - 1).getColor() + " " + pile.get(pile.size() - 1).getSymbol() + ".");
						
						while(playing)
							{
								int choosingCard = userInput.nextInt();
						
						
								if(choosingCard > playerHand.size())
									{
										System.out.println("You draw a card.");
										playerHand.add(deck.get(0));
										deck.remove(0);
										bot1Turn = true;
										bot3Turn = true;
										playing = false;
									}
						
								else
									{
										String chosenCardColor = playerHand.get(choosingCard - 1).getColor(); 
										int chosenCardType = playerHand.get(choosingCard - 1).getNumber();
								
								
										if(chosenCardType == 12 && chosenCardType == pile.get(pile.size() - 1).getNumber() || (chosenCardColor.equals(pile.get(pile.size() - 1).getColor()) && chosenCardType == 12))
											{
												pile.add(playerHand.get(choosingCard - 1));
												System.out.println("You played a " + playerHand.get(choosingCard - 1 ).getColor() + " " + playerHand.get(choosingCard - 1).getSymbol() + ".");
												if(amountOfReverse % 2 == 1)
													{
														System.out.println("Bot 3 drew 2 cards.");
														playerHand.remove(choosingCard - 1);
														botHand3.add(deck.get(0));
														deck.remove(0);
														botHand3.add(deck.get(0));
														deck.remove(0);
														bot3Turn = false;
													}
												else
													{
														System.out.println("Bot 1 drew 2 cards.");
														playerHand.remove(choosingCard - 1);
														botHand1.add(deck.get(0));
														deck.remove(0);
														botHand1.add(deck.get(0));
														deck.remove(0);
														bot1Turn = false;
													}
												bot2Turn = true;
												playing = false;
											}
										
										else if((chosenCardType == 11 && chosenCardType == pile.get(pile.size() - 1).getNumber()) || (chosenCardColor.equals(pile.get(pile.size() - 1).getColor()) && chosenCardType == 11))
											{
												pile.add(playerHand.get(choosingCard - 1));
												System.out.println("You played a " + playerHand.get(choosingCard - 1 ).getColor() + " " + playerHand.get(choosingCard - 1).getSymbol() + ".");
												playerHand.remove(choosingCard - 1);
												playerReverse = true;
												amountOfReverse++;
												playing = false;
											}
										
										else if((chosenCardType == 10 && chosenCardType == pile.get(pile.size() - 1).getNumber() || (chosenCardColor.equals(pile.get(pile.size() - 1).getColor())) && chosenCardType == 10))
											{
												pile.add(playerHand.get(choosingCard - 1));
												System.out.println("You played a " + playerHand.get(choosingCard - 1 ).getColor() + " " + playerHand.get(choosingCard - 1).getSymbol() + ".");
												if(amountOfReverse % 2 == 1)
													{
														System.out.println("You skipped Bot 3.");
														playerHand.remove(choosingCard - 1);
														bot3Turn = false;
													}
												else
													{
														System.out.println("You skipped Bot 1.");
														playerHand.remove(choosingCard - 1);
														bot1Turn = false;
													}
												bot2Turn = true;
												playing = false;
											}
								
										else if(chosenCardType < 10 && chosenCardColor.equals(pile.get(pile.size() - 1).getColor()) || chosenCardType == pile.get(pile.size() - 1).getNumber())
											{
												pile.add(playerHand.get(choosingCard - 1));
												System.out.println("You played a " + playerHand.get(choosingCard - 1 ).getColor() + " " + playerHand.get(choosingCard - 1).getSymbol() + ".");
												playerHand.remove(choosingCard - 1);
												bot1Turn = true;
												bot3Turn = true;
												playing = false;
											}
										else
											{
												System.out.println("That card won't work, play a different card.");
											}
						
									
								
									}
						
								
								
							} //while playing
						
						if(playerHand.size() == 0)
							{
								winner();
							}
						if(deck.size() < 5)
							{
								refillDeck();
							}
						if(amountOfReverse % 2 == 1 && bot3Turn)
							{
								bot3Turn();
							}
						else if(amountOfReverse % 2 == 1)
							{
								bot2Turn();
							}
						break;				
							
						
					} //still have cards
				
			}

		private static void winner()
			{
								
					if(playerHand.size() == 0)
						{
							System.out.println("You have won!");
						}
					else if(botHand1.size() == 0)
						{
							System.out.println("Bot 1 has won!");
						}
					else if(botHand2.size() == 0)
						{
							System.out.println("Bot 2 has won!");
						}
					else if(botHand3.size() == 0)
						{
							System.out.println("Bot 3 has won!");
						}
				System.exit(0);
					
			}

		private static void timer()
			{
				
				try
					{
						Thread.sleep(1000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}

	}
