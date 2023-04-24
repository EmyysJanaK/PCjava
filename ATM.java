import java.util.ArrayList;
import java.util.Scanner;

/*Overall, this class provides a way to represent a client with various attributes and
 access those attributes via getter methods. */

//constructor that initializes the client's attributes.
class Client {
    private int id;
    private String name;
    private String nationality;
    private String occupation;
    private String address;
    private int age;
    private String gender;

    public Client(int id, String name, String nationality, String occupation, String address, int age, String gender) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.occupation = occupation;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }
    // getter methods that return the client's attributes.
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}

class Account {
    private int accountNumber;//acc no.
    private String currency;// currency used in the account
    private String branch; // branch where it is held.
    private double balance;// current balance of the account.

    public Account(int accountNumber, String currency, String branch, double balance) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.branch = branch;
        this.balance = balance;
    }
    // return the account number.
    public int getAccountNumber() {
        return accountNumber;
    }
    // return the currency.
    public String getCurrency() {
        return currency;
    }
    // return the branch where it is held.
    public String getBranch() {
        return branch;
    }
    // return the current balance.
    public double getBalance() {
        return balance;
    }
    //set the balance.
    public void setBalance(double balance) {
        this.balance = balance;
    }
}


class SavingAccount extends Account {
    private double interestRate;
    /*
 * Constructor to initialize the SavingAccount object with
 *  account number, currency, branch, balance, and interest rate
 */
    
    public SavingAccount(int accountNumber, String currency, String branch, double balance, double interestRate) {
        // call the parent calss account's constructor using super class.
        super(accountNumber, currency, branch, balance);
        this.interestRate = interestRate;// Setting the interest rate of the SavingAccount object
    }
    // get the interest rate of the SavingAccount object
    public double getInterestRate() {
        return interestRate;
    }
}

class Loan {
    private Account account;
    private double amount;
    private double interest;
    private int duration;
    private String paymentMethod;

    public Loan(Account account, double amount, double interest, int duration, String paymentMethod) {
        /* constructor for the Loan class. It takes in an account, amount, interest, duration, and paymentMethod as
         parameters, and initializes the corresponding instance variables.
         * 
         */
        this.account = account;
        this.amount = amount;
        this.interest = interest;
        this.duration = duration;
        this.paymentMethod = paymentMethod;
    }
    /* getter methods for the Loan object.Allow the user to 
    retrieve the instance variables of the Loan object.  */
    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterest() {
        return interest;
    }

    public int getDuration() {
        return duration;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

/*
 represents a financial transaction.
*/
class Transaction {
    private int id; // transaction ID.
    private int accountId; // ID of the account.
    private String date; // date of the transaction.
    private String status; // status of the transaction.
    private double amount; //amount of the transaction.

    // Constructs a new Transaction object with the given parameters.
    public Transaction(int id, int accountId, String date, String status, double amount) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.status = status;
        this.amount = amount;
    }
    // return the ID of the transaction.
    public int getId() {
        return id;
    }
    // Returns the ID of the account involved in the transaction.
    public int getAccountId() {
        return accountId;
    }
    // Returns the date of the transaction.
    public String getDate() {
        return date;
    }
    //Returns the status of the transaction.
    public String getStatus() {
        return status;
    }
    //Return the amount of the transaction.
    public double getAmount() {
        return amount;
    }
}

public class ATM {
    private ArrayList<Account> accounts;// An ArrayList of Account objects
    private ArrayList<Transaction> transactions; // An ArrayList of Transaction objects

    //Constructor that initializes the accounts and transactions ArrayLists.
    public ATM(ArrayList<Account> accounts, ArrayList<Transaction> transactions) {
        this.accounts = accounts;
        this.transactions = transactions;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome!");

        // Ask for PIN
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        // Find client's accounts
        ArrayList<Account> clientAccounts = findClientAccounts(pin);
        if (clientAccounts.size() == 0) {
            System.out.println("Sorry, we couldn't find any accounts associated with the PIN entered.");
            return;
        }

        // Display client's accounts
        System.out.println("Here are your accounts:");
        for (int i = 0; i < clientAccounts.size(); i++) {
            Account account = clientAccounts.get(i);
            System.out.println((i+1) + ". Account Number: " + account.getAccountNumber()
                    + ", Currency: " + account.getCurrency()
                    + ", Branch: " + account.getBranch()
                    + ", Balance: " + account.getBalance());
        }

        // Ask for account choice
        System.out.print("Please select one account to proceed");

        int choice = scanner.nextInt();
        if (choice < 1 || choice > clientAccounts.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Account account = clientAccounts.get(choice-1);

        // Display account menu
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("1. Check balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Take a loan");
        System.out.print("Please select an option: ");
        int option = scanner.nextInt();
        // Ask for an option.
        switch (option) {
            case 1:
                checkBalance(account);
                break;
            case 2:
                withdraw(account);
                break;
            case 3:
                deposit(account);
                break;
            case 4:
                takeLoan(account);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    /**
    Finds all accounts associated with a client using their PIN number,
    the PIN number of the client and
    return an ArrayList of all accounts associated with the client
    */
    private ArrayList<Account> findClientAccounts(int pin) {
        ArrayList<Account> clientAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (account.getAccountNumber() % 10000 == pin) {
                clientAccounts.add(account);
            }
        }
        return clientAccounts;
    }

    private void checkBalance(Account account) {
        System.out.println("Balance: " + account.getBalance());
    }

    private void withdraw(Account account) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        // If the total amount exceeds the account's balance, display an error message and return.
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        // Deduct the loan amount from the account's balance.
        account.setBalance(account.getBalance() - amount);
        transactions.add(new Transaction(transactions.size() + 1, account.getAccountNumber(), "now", "withdrawal", amount));// Add the loan transaction to the account's transaction history.
        System.out.println("Withdrawal successful.");
        System.out.println("Balance: " + account.getBalance());
    }

    /**
    This method allows the user to deposit a specified amount into their account.
    * It updates the account balance, records the transaction, and prints out a success message with the updated balance.
    */

    private void deposit(Account account) {
        // create a scanner object to read input.
        Scanner scanner = new Scanner(System.in);
        //prompt the user to enter the amount to deposit.
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        // Update account balance and record the transaction
        account.setBalance(account.getBalance() + amount);
        // Add a new Transaction object to the account's list of transactions to record the deposit.
        transactions.add(new Transaction(transactions.size() + 1, account.getAccountNumber(), "now", "deposit", amount));
        // Print success message with the updated balance
        System.out.println("Deposit successful.");
        System.out.println("Balance: " + account.getBalance());
    }

    private void takeLoan(Account account) {
        Scanner scanner = new Scanner(System.in);//creater scanner obj.

        System.out.println("Enter loan amount: ");
        double amount = scanner.nextDouble(); // Get loan amount from user.

        System.out.println("Enter loan duration (in months): ");
        int duration = scanner.nextInt();

        System.out.println("Enter interest rate: ");
        double interestRate = scanner.nextDouble();
        // add the interests to the saving amount.
        double interest = amount * interestRate * duration / 1200;
        double total = amount + interest;

        // If the total amount exceeds the account's balance, display an error message and return.
        if (total > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        // update the acc balance.
        account.setBalance(account.getBalance() - total);
        transactions.add(new Transaction(transactions.size() + 1, account.getAccountNumber(), "now", "loan", total));
        System.out.println("Loan approved.");
        System.out.println("Amount: " + amount);// display amount.
        System.out.println("Interest: " + interest);//display interest.
        System.out.println("Total: " + total);//display total.
        System.out.println("Balance: " + account.getBalance());//display the balance.
    }



    public static void main(String[] args) {
        // Create some sample objects
        Client client = new Client(1234, "Janak Senevirathne", "SriLankan", "University students", "103/j,Piliyandala", 22, "Male");
        Account account1 = new SavingAccount(1001, "LKR", "Main Branch", 5000.00, 2.5);
        Account account2 = new Account(1002, "LKR", "Piliyandala Branch", 2500.00);
        ArrayList<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        ArrayList<Transaction> transactions = new ArrayList<>();

        // Create an ATM object and run the program
        ATM atm = new ATM(accounts, transactions);
        atm.run();
    }
}
