
public class Dice {
    int diceOutcome;
    int temp;

    public Dice(){
        this.diceOutcome = 0;
    }
    public int rollDice() {
        int maxEye = 6;
        int minEye = 1;
        temp = (int) ((Math.random() * maxEye) + minEye);
        return temp;
    }
    public void rememberDice(int diceOutcome) {
        this.diceOutcome = diceOutcome;
    }

    public int getRememberDice(){
        return diceOutcome;
    }
}

    public static int diceTotal() {
        int roll1 = rollDice();
        int roll2 = rollDice();
        return roll1 + roll2;