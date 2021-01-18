package Model.Cards;

//card that rewards player with money
public class MoneyCard extends SuperCard {
    private int changeMoney;

    //inherits from SuperCard class
    public MoneyCard(String desc, int amt) {
        super(desc);
        this.changeMoney = amt;
    }

    //this method returns the reward money
    public int getChangeMoney() {
        return changeMoney;
    }
}
