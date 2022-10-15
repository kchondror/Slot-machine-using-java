public class User {
    private int balance;
    private int currentBet;

    public User(){
        balance=100;
        currentBet=1;
    }

    public void setBalance(int balance){
        this.balance=balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        if (currentBet>0)
            this.currentBet = currentBet;
    }

    public void UpdateBalance(Boolean win,int mult ){
        if(!win) {
            if (balance < currentBet)
                System.out.println("No money");
        }
        else {
            setBalance(balance + currentBet *mult + 1);
        }


    }

}
