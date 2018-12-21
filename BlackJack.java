//Created By: Nicholas Brean
//Created On:dec 2018
//this program is a black jack program using OOP


import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Nick's Black Jack, Enjoy your stay.");
		
		Deck playingDeckObj = new Deck();
		playingDeckObj.createFullDeck(); //Creates deck and fill with 52 cards
		playingDeckObj.shuffle();
		//Create a deck for player
		Deck playerDeck = new Deck();
		
		Deck dealerDeck = new Deck();
		
		System.out.println(playingDeckObj);//You dont have to specify what class because it is the only class
										   // That returns a value
		
		double playerMoney = 500.00;
		
		Scanner userInput = new Scanner(System.in);
		
		//Game Loop (full turn)
		while(playerMoney > 0){
			System.out.println("You have $" + playerMoney + ", How much of this would you want to bet?");
			double playerBet = userInput.nextDouble();
			if (playerBet > playerMoney){
				System.out.println("You cannot bet more than you have please leave.");
				break;
			}
			
			boolean endRound = false;
			
			//Start Dealing
			//player gets 2 cards
			playerDeck.draw(playingDeckObj);
			playerDeck.draw(playingDeckObj);
			
			//Dealer gets 2 cards
			dealerDeck.draw(playingDeckObj);
			dealerDeck.draw(playingDeckObj);
				
			while(true){
				System.out.println("Your hand: ");
				System.out.print(playerDeck.toString() + "\n"); //All cards player has in his hand
				System.out.println("\nYour deck is valued at: " + playerDeck.cardsValue());
				
				//Dealer hand
				System.out.println("Dealers hand: " + dealerDeck.getCard(0).toString() + " [Hidden]");
				
				
				//What do they want to do?
				System.out.println("Would you like to '1'Hit or '2'Stand?");
				int response = userInput.nextInt();
				
				if (response == 1){
					playerDeck.draw(playingDeckObj);
					System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1));
					//Bust
					if(playerDeck.cardsValue() > 21){
						System.out.println("You are currently valued over 21, you have busted.");
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
				}
				if (response == 2){
					break;
				}
				
			}
			
			//Reveal Dealers Cards
			System.out.println("Dealer Cards: " + dealerDeck.toString());
			//Test for win
			if ((dealerDeck.cardsValue() > playerDeck.cardsValue()) && endRound == false){
				System.out.println("Dealer Wins!");
				playerMoney -= playerBet;
				endRound = true;
			}
			
			if ((dealerDeck.cardsValue() < playerDeck.cardsValue()) && endRound == false){
				System.out.println("Player Wins");
				playerMoney += playerBet;
				endRound = true;
			}
			
			//Dealer Draws to 16, stand at 17
			while ((dealerDeck.cardsValue() < 17) && endRound == false){
				dealerDeck.draw(playingDeckObj);
				System.out.println("Dealer Draws: " +  dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
			}
			//Display total Values
			System.out.println("Dealer's Hand is Valued at: " + dealerDeck.cardsValue());
			//If dealer busted
			if(dealerDeck.cardsValue() > 21 && endRound == false){
				System.out.println("Dealer busts! You win.");
				playerMoney += playerBet;
				endRound = true;
			}
			
			//Determine if tie, tie = push
			if(playerDeck.cardsValue() == dealerDeck.cardsValue() && endRound == false){
				System.out.println("Push");
				endRound = true;
			}
			
			if(playerDeck.cardsValue() < dealerDeck.cardsValue() && endRound == false){
				System.out.println("You win the hand !");
				playerMoney += playerBet;
				endRound = true;
			}else if(endRound == false){
				System.out.println("You lose the hand.");
				playerMoney -= playerBet;
				endRound = true;
			}
				
			
			
			playerDeck.moveAllToDeck(playingDeckObj);
			dealerDeck.moveAllToDeck(playingDeckObj);
			System.out.println("End of Hand");
			
		}
		
		System.out.println("Game over, you are out of money!");

	}

}
