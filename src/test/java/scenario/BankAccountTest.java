package scenario;

import com.example.testcase.scenario.BankAccount;

import junit.framework.TestCase;

public class BankAccountTest extends TestCase {
    private BankAccount account;
    private static final double INITIAL_BALANCE = 1000.0;
    private static final String ACCOUNT_HOLDER = "Menuk Fernando";
    
    @Override
    protected void setUp() {
        account = new BankAccount(ACCOUNT_HOLDER, INITIAL_BALANCE);
    }
    
    public void testConstructor() {
        assertEquals(INITIAL_BALANCE, account.getBalance());
        assertEquals(ACCOUNT_HOLDER, account.getAccountHolder());
    }
    
    public void testNegativeInitialBalance() {
        try {
            new BankAccount(ACCOUNT_HOLDER, -100.0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    public void testDeposit() {
        double depositAmount = 500.0;
        account.deposit(depositAmount);
        assertEquals(INITIAL_BALANCE + depositAmount, account.getBalance());
    }
    
    public void testNegativeDeposit() {
        try {
            account.deposit(-100.0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    public void testWithdraw() {
        double withdrawalAmount = 500.0;
        account.withdraw(withdrawalAmount);
        assertEquals(INITIAL_BALANCE - withdrawalAmount, account.getBalance());
    }
    
    public void testInsufficientFunds() {
        try {
            account.withdraw(INITIAL_BALANCE + 100.0);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
        }
    }
    
    public void testNegativeWithdrawal() {
        try {
            account.withdraw(-100.0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    public void testTransfer() {
        BankAccount recipient = new BankAccount("Lakindu Nimesh", 500.0);
        double transferAmount = 300.0;
        
        account.transfer(recipient, transferAmount);
        
        assertEquals(INITIAL_BALANCE - transferAmount, account.getBalance());
        assertEquals(800.0, recipient.getBalance());
    }
    
    public void testTransferToNullAccount() {
        try {
            account.transfer(null, 100.0);
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
    }
    
    public void testTransferInsufficientFunds() {
        BankAccount recipient = new BankAccount("Lakindu Nimesh", 500.0);
        try {
            account.transfer(recipient, INITIAL_BALANCE + 100.0);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException e) {
        }
    }
}
