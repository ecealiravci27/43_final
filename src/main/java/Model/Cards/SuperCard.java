package Model.Cards;

public abstract class SuperCard {
    protected String cardDescription;

    public SuperCard(String desc) {
        this.cardDescription = desc;
    }

    public String getCardDescription() {
        return cardDescription;
    }
}
