package Model.Cards;

public class CardPile {
    SuperCard[] cards;
    int counter;
    public CardPile(){
        this.cards = setupCardPile();
        this.counter = 0;

    }

    public SuperCard[] setupCardPile() {
        cards = new SuperCard[7];
        cards[0]  = new MoneyCard(" De har vundet i klasselotteriet! Modtag 1000 kr.", 1000);
        cards[1]  = new MoveCard("Du rykker 3 felter frem.", 1, 3);
        cards[2]  = new MoveCard("Du rykker til Rødovrevej.", 2, 2);
        cards[3]  = new MoneyCard("Kommunen har eftergivet et kvartals skat! Hæv i banken 2000 kr.", 2000);
        cards[4]  = new MoveCard("Du rykker 5 felter frem.", 1, 5);
        cards[5]  = new MoveCard("Du rykker til Rådhuspladsen.", 2, 40);
        cards[6] = new FreeCard("Løsladelseskort: Du kan komme ud af fængslet!");
        shuffleDeck();
        return cards;

    }
    public SuperCard drawCard() {
        counter = (counter%cards.length);
        counter++;
        return cards[counter-1];
    }

    public void swap(int a, int b){
        SuperCard card_a = cards[a];
        SuperCard card_b = cards[b];
        cards[a] = card_b;
        cards[b] = card_a;
    }

    public void shuffleDeck(){
        int a,b;
        for (int i = 0; i < 1000; i++) {
            a = (int)(Math.random()* cards.length);
            b = (int)(Math.random()* cards.length);
            swap(a,b);
        }
    }

    public static void main(String[] args) {
        CardPile cardPile = new CardPile();
        cardPile.drawCard();
    }
}

