package Model;

import Model.Cards.MoneyCard;
import Model.Cards.MoveCard;
import Model.Cards.SuperCard;

public class CardController {
    // Cardpile test
    public static void main(String[] args) {
        SuperCard card = new CardPile().drawCard();
        System.out.println(card);
        if (MoneyCard.class.isInstance(card)) {
            System.out.println(("Charging money: " + ((MoneyCard) card).getChangeMoney()));
        } else if(MoveCard.class.isInstance(card)) {
            if(((MoveCard) card).getType() == 1) {
                // movepiece here
                System.out.println("Moving piece " + ((MoveCard) card).getMovePiece());
            } else {
                // movefield here
                System.out.println("Moving field " + ((MoveCard) card).getMoveToField());
            }
        }
    }
}
