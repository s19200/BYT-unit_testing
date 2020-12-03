package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));


		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 5, 1, new Money(5000, SEK), SweBank, "Alice" );
		assertTrue(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("school", 5, 5, new Money(100000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("school"));
		testAccount.removeTimedPayment("school");
		assertFalse(testAccount.timedPaymentExists("school"));
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(10000000, SEK));
		testAccount.deposit(new Money(67000, SEK));
		assertEquals(Double.valueOf(670.0), testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
		assertEquals(Double.valueOf(100000), testAccount.getBalance().getAmount());
	}
}
