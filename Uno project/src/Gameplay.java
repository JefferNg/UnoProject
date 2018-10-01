import java.util.Scanner;
import java.util.ArrayList;

public class Gameplay
	{

		public static void main(String[] args)
			{
				
				greetUser();
				chooseGameplay();
				playGame();
				
			}
		
		static Scanner userInput = new Scanner(System.in);
		static String name;
		static int numberOfPlayers;
		static int typeOfGameplay;
		static int modifier;
		static ArrayList<String> gameRules = new ArrayList();
		
		
		
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
						System.out.println("How many players are playing?");
						numberOfPlayers = userInput.nextInt();
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
								
								
								
								
							}
						
						
						
						
						
							}
						
					}
				
				
				
				
			}

		private static void playGame()
			{
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}

	}
