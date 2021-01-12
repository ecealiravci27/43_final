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
//        for (SuperCard card : cards) {
//            //System.out.println(card instanceof MoneyCard);-
//
//            if(MoneyCard.class.isInstance(card)) {
//                System.out.println(((MoneyCard) card).getChangeMoney());
//            }
//        }
    }
}
