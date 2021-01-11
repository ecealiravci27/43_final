//This class' main functionality is giving a random number of dice roll
//Here, one should be able to easily change the properties of the dice by changing the local variables maxEye and minEye in the rolldice method

public class Dice {
    int diceOutcome;
    int temp;
    int eyeSum;

    public Dice(){
        this.diceOutcome = 0;
    }

    private int rollDice() {
        int maxEye = 6;
        int minEye = 1;
        temp = (int) ((Math.random() * maxEye) + minEye);
        return temp;
    }

    public int rollDie() {
        int roll1 = rollDice();
        int roll2 = rollDice();

        eyeSum =  roll1 + roll2;

        setDice(eyeSum);
        return eyeSum;
    }

    public void setDice(int eyeSum){
        diceOutcome = eyeSum ;
    }

    public int getDiceOutcome() {
        return eyeSum;
    }

}

