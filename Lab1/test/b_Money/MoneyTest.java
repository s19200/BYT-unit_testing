package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100, EUR30, SEK300;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		SEK300 = new Money(30000, SEK);
		EUR10 = new Money(1000, EUR);
		EUR30 = new Money(3000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {	//comparing the output of the function to the number we stated while creating this Money object
		Double amountInDouble = 100.00;
		assertEquals(amountInDouble, SEK100.getAmount());
	}

	@Test
	public void testGetCurrency() {  //comparing the name of the output currency to stated one
		assertEquals("EUR", EUR10.getCurrency().getName());
	}

	@Test
	public void testToString(){	//comparing the output of toString (with parameters from setUp()) to manually written data
		Integer amount  = 2000;
		Currency curr = EUR20.getCurrency();
		String compareThis = EUR20.toString(amount, curr);
		String compareToThis = 20.00 +" "+ EUR10.getCurrency().getName();
		assertEquals(compareToThis, compareThis);

	}

	@Test
	public void testUniversalValue() {
		Integer amount = 3000;
		assertEquals(amount, EUR20.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(EUR30.equals(SEK300));
	}

	@Test
	public void testAdd() {
		Money sum = EUR30.add(SEK300);
		Integer amount = 6000;
		Money check = new Money(amount, EUR30.getCurrency());
		assertEquals(check.getAmount(), sum.getAmount());
	}

	@Test
	public void testSub() {
		Money sub = EUR30.sub(SEK300);
		Integer amount = 0;
		Money check = new Money(amount, EUR30.getCurrency());
		assertEquals(check.getAmount(), sub.getAmount());
	}

	@Test
	public void testIsZero() {
		assertTrue(SEK0.isZero());
	}

	@Test
	public void testNegate() {
		Money negated = SEK200.negate();
		assertNotEquals(negated.getAmount(), SEK200.getAmount());
	}

	@Test
	public void testCompareTo() {	//checking all cases by:
		assertEquals(0, EUR30.compareTo(SEK300)); 		//this is equal to other
		assertEquals(-1, EUR10.compareTo(EUR20));		//this is less valuable than other
		assertEquals(1, SEK100.compareTo(SEKn100));	//this is more valuable then other
	}
}
