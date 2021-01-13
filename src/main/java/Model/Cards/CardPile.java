package Model.Cards;

import Model.Cards.MoneyCard;
import Model.Cards.MoveCard;
import Model.Cards.SuperCard;

import java.util.Random;



public class CardPile {
    SuperCard[] cards;
    int counter;
    public CardPile(){
        this.cards = setupCardPile();
        this.counter = 0;

    }

    public SuperCard[] setupCardPile() {
        SuperCard[] cards = {
                new MoneyCard("i am money", 1000),
                new MoveCard("i am movetopiece", 1, 3),
                new MoveCard("i am movetofield", 2, 2),
                new MoneyCard("I am money 2", 2000),
                new MoveCard("i am movetopiece 5", 1, 5),
                new MoveCard("i am movetofield 5", 2, 8),
        };

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
        cardPile.swap(0,1);
        cardPile.shuffleDeck();
    }
}

