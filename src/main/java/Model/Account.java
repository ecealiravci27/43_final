package Model;

public class Account {

    /**
     * This is an account meant to keep track of, and change variables concerning a persons balance in matador
     */


    //local variable made
    private int balance;

    //constructor, made for instance variables of several objects
    public Account(int balance){
        this.balance = balance;
    }

    //method changes balance variable by a given value (change). balance can not go under 0.
    public void addBalance(int change){
        balance = balance + change;
        if (balance < 0){
            balance = 0;
        }
    }

    public void reduceBalance(int change) {
        balance = balance - change;
        if (balance < 0){
            balance = 0;
        }
    }

    //method sets balance to given value (change).
    public void setBalance(int change){
        balance = change;
    }

    //method returns balance
    public int getBalance(){return balance;}
}

