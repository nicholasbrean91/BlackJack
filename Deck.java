import java.util.ArrayList;
import java.util.Random; 

public class Deck {
	
	private ArrayList<Card> cards;
	
	//constructor
	public Deck(){
		this.cards = new ArrayList<Card>();
	}
	
	public void createFullDeck(){
		//Gen Cards
		for(Suit cardSuit: Suit.values()){ //dataType varName: Enum Suit and Values
			for(Value cardValue: Value.values()){
				//Add a new card to the deck
				this.cards.add(new Card(cardSuit, cardValue));
			}
		}
	}
	
	public void shuffle(){
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		//use Random 
		Random randInt = new Random();
		int randomCardIndex = 0;
		int origSize = this.cards.size(); //To know the original size of deck
		for(int count = 0; count < origSize; count++){
			//Gen rand index
			randomCardIndex = randInt.nextInt((this.cards.size()-1  - 0) + 1) + 0;
			tmpDeck.add(this.cards.get(randomCardIndex));
			//remove from original deck
			this.cards.remove(randomCardIndex);
		}
		
		this.cards = tmpDeck;
	}
	
	public String toString(){
		String cardListOutput = "";
		for(Card aCard: this.cards){ //For every card in our array list of cards
			cardListOutput += "\n" + aCard.toString();
			
		}
		return cardListOutput;
	}
	
	public void moveAllToDeck(Deck moveTo){
		int thisDeckSize = this.cards.size();
		
		for(int count = 0; count < thisDeckSize; count++){
			moveTo.addCard(this.getCard(count));
		}
		
		for(int count = 0; count < thisDeckSize; count++){
			this.removeCard(0);
		}
	}
	
	public void removeCard(int index){
		this.cards.remove(index);
	}
	
	public Card getCard(int index){
		return this.cards.get(index);
	}
	
	public void addCard(Card addCard){
		this.cards.add(addCard);
	}

	
	public void draw(Deck comingFrom){
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}
	
	public int deckSize(){
		return this.cards.size();
	}
	
	//return total value of deck
	public int cardsValue(){
		int totalValue = 0;
		int aces = 0;
		
		for(Card aCard: this.cards){
			switch(aCard.getValue()){
			case TWO: totalValue += 2; break;
			case THREE: totalValue += 3; break;
			case FOUR: totalValue += 4; break;
			case FIVE: totalValue += 5; break;
			case SIX: totalValue += 6; break;
			case SEVEN: totalValue += 7; break;
			case EIGHT: totalValue += 8; break;
			case NINE: totalValue += 9; break;
			case TEN: totalValue += 10; break;
			case JACK: totalValue += 10; break;
			case QUEEN: totalValue += 10; break;
			case KING: totalValue += 10; break;
			case ACE: aces += 1; break;
			
			}
		}
		
		for(int count = 0; count < aces; count++){
			if(totalValue > 10){
				totalValue +=1;
			}
			else{
				totalValue += 11;
			}
		}
		
		return totalValue;
	}
}
