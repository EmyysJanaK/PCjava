public class Loan {
    private BankAccount account;
    private double amount;
    private double interest;
    private int duration;
    private String paymentMethod;
    
    public Loan(BankAccount account, double amount, double interest, int duration, String paymentMethod) {
        this.account = account;
        this.amount = amount;
        this.interest = interest;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
    }
    
    //getters and setters
}
