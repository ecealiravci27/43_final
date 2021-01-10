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

    public int calculateEyeSum() {
        int roll1 = rollDice();
        int roll2 = rollDice();
        return roll1 + roll2;

    }

    public void setEyeSum(int dicetotal) {
        eyeSum = dicetotal;
    }

    public int getGetEyesum() {
        return eyeSum;
    }


    /*
    public void rememberDice(int diceOutcome) {
        this.diceOutcome = diceOutcome;
    }

    public int getRememberDice(){
        return diceOutcome;
    }
    */
}

