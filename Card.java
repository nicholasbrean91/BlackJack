
public class Card {
	
	private Suit suit;
	private Value value;
	
	public Card(Suit suit, Value value){
		
		this.value = value;
		this.suit = suit;
		
	}
	
	public String toString(){
		return this.suit.toString() + "-" + this.value.toString(); //Returns the Value and the Suit
	}
	
	public Value getValue(){
		return this.value;
	}

}
