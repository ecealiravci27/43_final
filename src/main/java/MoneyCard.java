public class MoneyCard extends SuperCard {
    private int changeMoney;

    public MoneyCard(String desc, int amt) {
        super(desc);
        this.changeMoney = amt;
    }

    public int getChangeMoney() {
        return changeMoney;
    }
}
