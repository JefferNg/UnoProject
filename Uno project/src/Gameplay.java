import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Gameplay
	{

		public static void main(String[] args)
			{
				fillDeck();
				greetUser();
				chooseGameplay();
				addingCardsToHand();
				playGame();			
			}
		



		static Scanner userInput = new Scanner(System.in);
		static String name;
		static int numberOfPlayers;
		static int numberOfBots;
		static int typeOfGameplay;
		static int modifier;
		static ArrayList<String> gameRules = new ArrayList<String>();
		static ArrayList<CardType> hand = new ArrayList<CardType>();
		static ArrayList<CardType> deck = new ArrayList<CardType>();
		static ArrayList<CardType> pile = new ArrayList<CardType>();
		static ArrayList<CardType> botHand = new ArrayList<CardType>();
		static ArrayList<CardType> botHand1 = new ArrayList<CardType>();
		static ArrayList<CardType> botHand2 = new ArrayList<CardType>();
		static boolean playerTurn = true;
		static boolean bot1Turn = true;
		static boolean bot2Turn = true;
		static boolean bot3Turn = true;
		
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
				
				System.out.println("Welcome to UNO, please input your name to begin");
				name = userInput.nextLine();
				
				
				
			}

		private static void chooseGameplay()
			{
				
				System.out.println("Ok, " + name + ", would you like to play single player (which is with bots) or multiplayer (with people around you)?");
				System.out.println("[1] Single Player");
				System.out.println("[2] Multiplayer");
				int amountOfPlayers = userInput.nextInt();
				
				if(amountOfPlayers == 2)
					{
						System.out.println("How many other players are playing?(1-3)");
						numberOfPlayers = userInput.nextInt();
						if(numberOfPlayers >= 1 && numberOfPlayers <= 3)
							{
								
							}
						else
							{
								System.out.println("That is an invalid amount");
							}
						
					}
				else
					{
						numberOfBots = 3;
					}
				
				System.out.println("Cool, would you like to play the original game or would you like to add some house rules?");
				System.out.println("[1] Orignal");
				System.out.println("[2] Modify");
				typeOfGameplay = userInput.nextInt();
				
				
				if(typeOfGameplay == 2)
					{
						gameRules.add("Stacking");
						gameRules.add("7-0 Rule");
						gameRules.add("Draw Until You Can Play");
						int i = 1;
						while(modifier != i)
							{
						
						System.out.println("Here are the house rules");
						
						for(String s : gameRules)
							{
								System.out.println("[" + i + "] " + s);
								i++;
							}
						System.out.println("[" + i + "] Done");
						
						modifier = userInput.nextInt();
								
						if(modifier == 1)
							{
								System.out.println("Stacking is a rule where you are allowed to stack your +2 or +4 wild cards and pass it to the next player");
								System.out.println("Would you like to add this rule?");
								System.out.println("[1] Yes");
								System.out.println("[2] No");
								
								int confirm = userInput.nextInt();
								
								if(confirm == 1)
									{
										gameRules.remove(0);
										i = 1;
										modifier = 0;
									}
								else
									{
										modifier = 0;
										i = 1;
									}
								
								
								
							}
						
						
						
						
						
							}
						
					}
				
				
				
				
			}
		
		private static void addingCardsToHand()
			{
				Collections.shuffle(deck);
				System.out.println("Adding cards to your hand ");
				
				for(int i = 0; i < 7; i++)
					{				
						hand.add(deck.get(i));
						deck.remove(i);
						
						botHand.add(deck.get(i));
						deck.remove(i);
						
						botHand1.add(deck.get(i));
						deck.remove(i);
						
						botHand2.add(deck.get(i));
						deck.remove(i);
						
						timer();
						System.out.print(".");
					}
				pile.add(deck.get(0));
				deck.remove(0);
				
				System.out.println("");
				
				
				
				
			}

		private static void playGame()
			{
				
				
				
				while(hand.size() != 0 && playerTurn)
					{
						int amountOfCards = 1;
						boolean playing = true;
						for(CardType i : hand)
							{
								System.out.println("[" + amountOfCards + "] "+ i.getColor() + " " + i.getSymbol());
								amountOfCards++;
							}
						System.out.println("[" + amountOfCards + "]" + " Draw");
						
						System.out.println("The card on top is " + pile.get(pile.size() - 1).getColor() + " " + pile.get(pile.size() - 1).getSymbol());
						
						while(playing)
							{
								int choosingCard = userInput.nextInt();
						
						
						
								if(choosingCard > hand.size())
									{
										System.out.println("You draw a card");
										hand.add(deck.get(0));
										deck.remove(0);
										bot1Turn = true;
										playing = false;
									}
						
								else
									{
										String chosenCardColor = hand.get(choosingCard - 1).getColor(); 
										int chosenCardType = hand.get(choosingCard - 1).getNumber();
								
								
										if((chosenCardType == 12 || chosenCardColor.equals(pile.get(pile.size() - 1).getColor())))
											{
												pile.add(hand.get(choosingCard - 1));
												System.out.println("You played a " + hand.get(choosingCard - 1 ).getColor() + " " + hand.get(choosingCard - 1).getSymbol());
												System.out.println("Bot 1 drew 2 cards");
												hand.remove(choosingCard - 1);
												botHand.add(deck.get(0));
												deck.remove(0);
												botHand.add(deck.get(0));
												deck.remove(0);
												bot1Turn = false;
												bot2Turn = true;
												playing = false;
											}
										
										else if((chosenCardType == 10 || chosenCardColor.equals(pile.get(pile.size() - 1).getColor())))
											{
												pile.add(hand.get(choosingCard - 1));
												System.out.println("You played a " + hand.get(choosingCard - 1 ).getColor() + " " + hand.get(choosingCard - 1).getSymbol());
												System.out.println("You have skipped bot 1");
												hand.remove(choosingCard - 1);
												bot1Turn = false;
												bot2Turn = true;
												playing = false;
											}
								
										else if(chosenCardType < 10 && chosenCardColor.equals(pile.get(pile.size() - 1).getColor()) || chosenCardType == pile.get(pile.size() - 1).getNumber())
											{
												pile.add(hand.get(choosingCard - 1));
												System.out.println("You played a " + hand.get(choosingCard - 1 ).getColor() + " " + hand.get(choosingCard - 1).getSymbol());
												hand.remove(choosingCard - 1);
												bot1Turn = true;
												playing = false;
											}
										else
											{
												System.out.println("That card won't work, play a different card");
											}
						
									
								
									}
						
								
								
							} //while playing
						
						if(hand.size() == 0)
							{
								winner();
							}
						System.out.println(pile.get(pile.size() - 1).getNumber());
						break;				
							
						
					} //still have cards
						
				while(botHand.size() != 0 && bot1Turn)
					{
						
						for(int i = 0; i <= botHand.size(); i++)
							{
								
								if((botHand.get(i).getNumber() == 12 || botHand.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())))
									{
										timer();
										pile.add(botHand.get(i));
										System.out.println("Bot 1 has played a " + botHand.get(i).getColor() + " " + botHand.get(i).getSymbol());
										System.out.println("Bot 2 drew 2 cards");
										botHand.remove(i);
										botHand1.add(deck.get(0));
										deck.remove(0);
										botHand1.add(deck.get(0));
										deck.remove(0);
										bot2Turn = false;
										bot3Turn = true;
										break;
									}
								
								else if((botHand.get(i).getNumber() == 10 || botHand.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())))
									{
										timer();
										pile.add(botHand.get(i));
										System.out.println("Bot 1 has played a " + botHand.get(i).getColor() + " " + botHand.get(i).getSymbol());
										System.out.println("Bot 1 skipped bot 2");
										botHand.remove(i);
										bot2Turn = false;
										bot3Turn = true;
										break;
									}
								
								else if(botHand.get(i).getNumber() < 10 && botHand.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) || botHand.get(i).getNumber() == pile.get(pile.size() - 1).getNumber())
									{
										timer();
										pile.add(botHand.get(i));
										System.out.println("Bot 1 has played a " + botHand.get(i).getColor() + " " + botHand.get(i).getSymbol());
										botHand.remove(i);
										bot2Turn = true;
										break;
									}
								else if(i == botHand.size() - 1)
									{
										timer();
										botHand.add(deck.get(0));
										System.out.println("Bot 1 draws");
										deck.remove(0);
										bot2Turn = true;
										break;
									}
								
							}
						timer();
						
						if(botHand.size() == 0)
							{
								winner();
								break;
							}
						System.out.println("Bot 1 has " + botHand.size() + " cards");
						System.out.println(pile.get(pile.size() - 1).getNumber());
						break;
												
					} //while bot has cards
				
				while(botHand1.size() != 0 && bot2Turn)
					{
						
						for(int i = 0; i <= botHand1.size(); i++)
							{
								
								if((botHand1.get(i).getNumber() == 12 || botHand1.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())))
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 2 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol());
										System.out.println("Bot 3 drew 2 cards");
										botHand1.remove(i);
										botHand2.add(deck.get(0));
										deck.remove(0);
										botHand2.add(deck.get(0));
										deck.remove(0);
										bot3Turn = false;
										playerTurn = true;
										break;
									}
								
								else if((botHand1.get(i).getNumber() == 10 || botHand1.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())))
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 2 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol());
										System.out.println("Bot 2 skipped bot 3");
										botHand1.remove(i);
										bot3Turn = false;
										playerTurn = true;
										break;
									}
								
								else if(botHand1.get(i).getNumber() < 10 && botHand1.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) || botHand1.get(i).getNumber() == pile.get(pile.size() - 1).getNumber())
									{
										timer();
										pile.add(botHand1.get(i));
										System.out.println("Bot 2 has played a " + botHand1.get(i).getColor() + " " + botHand1.get(i).getSymbol());
										botHand1.remove(i);
										bot3Turn = true;
										break;
									}
								else if(i == botHand1.size() - 1)
									{
										timer();
										botHand1.add(deck.get(0));
										System.out.println("Bot 2 draws");
										deck.remove(0);
										bot3Turn = true;
										break;
									}
								
							}
						timer();
						
						if(botHand1.size() == 0)
							{
								winner();
							}
						System.out.println("Bot 2 has " + botHand1.size() + " cards");
						System.out.println(pile.get(pile.size() - 1).getNumber());
						break;
												
					} //while bot1 has cards
				
				while(botHand2.size() != 0 && bot3Turn)
					{
						
						for(int i = 0; i <= botHand2.size(); i++)
							{
								
								if((botHand2.get(i).getNumber() == 12 || botHand2.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())))
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 3 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol());
										System.out.println("You drew 2 cards");
										botHand2.remove(i);
										hand.add(deck.get(0));
										deck.remove(0);
										hand.add(deck.get(0));
										deck.remove(0);
										playerTurn = false;
										bot1Turn = true;
										break;
									}
								
								else if((botHand2.get(i).getNumber() == 10 || botHand2.get(i).getColor().equals(pile.get(pile.size() - 1).getColor())))
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 3 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol());
										System.out.println("Bot 3 skipped you");
										botHand2.remove(i);
										playerTurn = false;
										bot1Turn = true;
										break;
									}
								
								else if(botHand2.get(i).getNumber() < 10 && botHand2.get(i).getColor().equals(pile.get(pile.size() - 1).getColor()) || botHand2.get(i).getNumber() == pile.get(pile.size() - 1).getNumber())
									{
										timer();
										pile.add(botHand2.get(i));
										System.out.println("Bot 3 has played a " + botHand2.get(i).getColor() + " " + botHand2.get(i).getSymbol());
										botHand2.remove(i);
										playerTurn = true;
										break;
									}
								else if(i == botHand2.size() - 1)
									{
										timer();
										botHand2.add(deck.get(0));
										System.out.println("Bot 3 draws");
										deck.remove(0);
										playerTurn = true;
										break;
									}
								
							}
						timer();
						
						if(botHand2.size() == 0)
							{
								winner();
							}
						System.out.println("Bot 3 has " + botHand2.size() + " cards");
						break;
												
					} //while bot2 has cards
				timer();
				System.out.println(pile.get(pile.size() - 1).getNumber());
				
				if(hand.size() != 0 && botHand.size() != 0 && botHand1.size() != 0 && botHand2.size() != 0)
					{
						playGame();
					}
				
				
			}

		private static void winner()
			{
								
					if(hand.size() == 0)
						{
							System.out.println("You have won!");
						}
					else if(botHand.size() == 0)
						{
							System.out.println("Bot 1 has won!");
						}
					else if(botHand1.size() == 0)
						{
							System.out.println("Bot 2 has won!");
						}
					else if(botHand2.size() == 0)
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
