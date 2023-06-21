import java.util.Scanner;

// Base class for all transactions
abstract class Transaction {
    protected double amount;
    protected boolean isExecuted;

    public Transaction(double amount) {
        this.amount = amount;
        this.isExecuted = false;
    }

    public abstract void execute();
}

// Interface for sending money
interface MoneySender {
    void sendMoney(double amount, String recipient);
}

// Implementation of MoneySender interface
class UserMoneySender implements MoneySender {
    private String username;

    public UserMoneySender(String username) {
        this.username = username;
    }

    @Override
    public void sendMoney(double amount, String recipient) {
        // Perform send-money operation
        System.out.println("Sending money: " + amount + " to " + recipient);
        // Update account balance or perform necessary operations
    }
}

// Subclass for cash-in transaction
class CashInTransaction extends Transaction {
    public CashInTransaction(double amount) {
        super(amount);
    }

    @Override
    public void execute() {
        // Perform cash-in operation
        System.out.println("Cashing in: " + amount);
        // Update account balance or perform necessary operations
        isExecuted = true;
    }
}

// Subclass for cash-out transaction
class CashOutTransaction extends Transaction {
    public CashOutTransaction(double amount) {
        super(amount);
    }

    @Override
    public void execute() {
        // Perform cash-out operation
        System.out.println("Cashing out: " + amount);
        // Update account balance or perform necessary operations
        isExecuted = true;
    }
}

// Main application class
class Main{
    public static void main(String[] args) {
        // Example account balance
        double accountBalance = 0.0;

        // Example user for sending money
        MoneySender moneySender = new UserMoneySender("user");

        // CLI application menu
        while (true) {
            System.out.println("\n=== Digital Money System ===");
            System.out.println("1. Cash In");
            System.out.println("2. Cash Out");
            System.out.println("3. Send Money");
            System.out.println("4. View Balance");
            System.out.println("5. Exit");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter cash-in amount: ");
                    double cashInAmount = scanner.nextDouble();
                    if (cashInAmount > 0) {
                        Transaction cashInTransaction = new CashInTransaction(cashInAmount);
                        cashInTransaction.execute();
                        if (cashInTransaction.isExecuted) {
                            accountBalance += cashInAmount;
                            System.out.println("Cash-in successful. Account balance: " + accountBalance);
                        } else {
                            System.out.println("Cash-in failed.");
                        }
                    } else {
                        System.out.println("Invalid amount.");
                    }
                    break;

                case 2:
                    System.out.print("Enter cash-out amount: ");
                    double cashOutAmount = scanner.nextDouble();
                    if (cashOutAmount > 0 && cashOutAmount <= accountBalance) {
                        Transaction cashOutTransaction = new CashOutTransaction(cashOutAmount);
                        cashOutTransaction.execute();
                        if (cashOutTransaction.isExecuted) {
                            accountBalance -= cashOutAmount;
                            System.out.println("Cash-out successful. Account balance: " + accountBalance);
                        } else {
                            System.out.println("Cash-out failed.");
                        }
                    } else {
                        System.out.println("Invalid amount or insufficient balance.");
                    }
                    break;

                case 3:
                    System.out.print("Enter recipient username: ");
                    String recipient = scanner.next();
                    System.out.print("Enter amount to send: ");
                    double sendAmount = scanner.nextDouble();
                    if (sendAmount > 0 && sendAmount <= accountBalance) {
                        moneySender.sendMoney(sendAmount, recipient);
                        accountBalance -= sendAmount;
                        System.out.println("Money sent successfully. Account balance: " + accountBalance);
                    } else {
                        System.out.println("Invalid amount or insufficient balance.");
                    }
                    break;

                case 4:
                    System.out.println("Account balance: " + accountBalance);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }
}