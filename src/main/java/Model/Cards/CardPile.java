package Model.Cards;

import Model.Cards.MoneyCard;
import Model.Cards.MoveCard;
import Model.Cards.SuperCard;

import java.util.Random;

public class CardPile {
    SuperCard[] cards = {
            new MoneyCard("i am money", 1000),
            new MoveCard("i am movetopiece", 1, 3),
            new MoveCard("i am movetofield", 2, 2),
            new MoneyCard("I am money 2", 2000),
            new MoveCard("i am movetopiece 5", 1, 5),
            new MoveCard("i am movetofield 5", 2, 8),
    };

    public SuperCard drawCard() {
        int rnd = new Random().nextInt(cards.length);
        return cards[rnd];
//        for (Model.Cards.SuperCard card : cards) {
//            //System.out.println(card instanceof Model.Cards.MoneyCard);-
//
//            if(Model.Cards.MoneyCard.class.isInstance(card)) {
//                System.out.println(((Model.Cards.MoneyCard) card).getChangeMoney());
//            }
//        }
    }
}
