package Model.Cards;

//abstract class that allows us to provide a base for card subclasses (FreeCard, MoneyCard and MoveCard)
public abstract class SuperCard {
    protected String cardDescription;

    //each card has a description:
    public SuperCard(String desc) {
        this.cardDescription = desc;
    }

    //returns the description of a card
    public String getCardDescription() {
        return cardDescription;
    }
}
