package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("Blake");
		assertTrue(SweBank.getAccountList().containsKey("Blake"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(50000, SEK));
		assertEquals((Integer)500, SweBank.getBalance("Bob"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Bob", new Money(50000, SEK));
		assertEquals((Integer)500, SweBank.getBalance("Bob"));
		SweBank.withdraw("Bob", new Money(50000, SEK));
		assertEquals((Integer)0, SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals((Integer)0, SweBank.getBalance("Bob"));
		SweBank.deposit("Bob", new Money(50000, SEK));
		assertEquals((Integer)500, SweBank.getBalance("Bob"));

		SweBank.withdraw("Bob", new Money(30000, SEK));
		assertEquals((Integer)(200), SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(50000, SEK));
		Nordea.transfer("Bob", SweBank, "Ulrika", new Money(47000, SEK));
		assertEquals((Integer)30, Nordea.getBalance("Bob"));
		assertEquals((Integer)470, SweBank.getBalance("Ulrika"));		//checking transferring between different banks

		SweBank.transfer("Ulrika", "Bob", new Money(47000, SEK));
		assertEquals((Integer)0, SweBank.getBalance("Ulrika"));
		assertEquals((Integer)470, SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {  //adding timedPayment and checking, then removing and checking again
		SweBank.addTimedPayment("Ulrika", "salary", 5, 5, new Money(100000, SEK), DanskeBank, "Gertrud");
		assertTrue(SweBank.getAccount("Ulrika").timedPaymentExists("salary"));
		SweBank.removeTimedPayment("Ulrika", "salary");
		assertFalse(SweBank.getAccount("Ulrika").timedPaymentExists("salary"));
	}
}
