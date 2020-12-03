package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());   //comparing written by us name to the one getName() returns
	}
	
	@Test
	public void testGetRate() {  //comparing stated by us rate to the value that getRate() returns
		Double r = 0.20;
		assertEquals(r, DKK.getRate());
	}
	
	@Test
	public void testSetRate() { //changing rate and comparing it to the initial one
		Double initialRate = EUR.getRate();
		EUR.setRate(2.0);
		assertNotEquals(initialRate, EUR.getRate());
	}
	
	@Test
	public void testUniversalValue() { // checking if amount in universal currency is equal if we take stated amount of sek and eur (1 eur = 10 sek)
		Integer sekUniversal = SEK.universalValue(50000);
		Integer eurUniversal = EUR.universalValue(5000);
		assertEquals(sekUniversal, eurUniversal);
	}
	
	@Test
	public void testValueInThisCurrency() {  //
		Integer valueInThisCurrency = SEK.valueInThisCurrency(100, EUR);	//100 as amount means 1 EUR since we state amount as Integers
		Double d = 10.0;															// checking if the output of the method equals to manually computed number
		Integer convert = d.intValue()*100;
		assertEquals(convert, valueInThisCurrency);

	}

}
