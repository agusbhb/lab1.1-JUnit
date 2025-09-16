package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import domain.Article;
import domain.Purchase;
import exceptions.PurchaseException;

public class PurchaseTest {
	// Common variables for all test.
		private Purchase basket= new Purchase();
		private double price;
		private int quantity=1;
		private Article article;

		// Initialization of the variables before the execution of each test
		@Before
		public void initialize() {
			System.out.println("Initialize  ...");
			
			price= 234.99;	// 234.99 real value
			quantity = 1;
			article = new Article("404", "MASK PINK", price, false, quantity);
		} 

		@Test
		public void testAddBasket1() {
			quantity = 1;
			price = 234.99;

			//1. expected value
			double expected = quantity*price;

			//2. invoke the method and get the result
			double obtained = basket.addBasket(article, quantity);
			
			//3. check
			assertEquals(expected, obtained,0); //expected always 1st param
			    
	                       
	        //4. stablish the initial state 
			try {
				basket.removeBasket(article, quantity);
				assertTrue(true);
			} catch (PurchaseException e) {
				fail("Impossible!!");
			}
		}

		@Test
		public void testAddBasket2() {
		try {	
			quantity = 3;
			price = 234.99;
			double expected = quantity*price;

			double obtained = basket.addBasket(article, quantity);
			fail();
			//			assertEquals(expected, obtained, 0);
			
		}catch(RuntimeException e){
			assertTrue(true); //Esto es para comprobar a traves de un caso de test que los parametros hacen que se lanze una excepción
		}
//		try {
//				basket.removeBasket(article, quantity);
		//			} catch (PurchaseException e) {
		//		fail();
		//	}
		}
		
		@Test
		public void testAddBasket3() {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			try {
				Date date = format.parse("23/12/2020");
				basket.buy(date);
				assertNotNull(basket.getDate());
				
				quantity = 3;
				
				fail();
				
			} catch (ParseException e) {
				fail();
			} catch(RuntimeException e){
				assertTrue(true); //Esto es para comprobar a traves de un caso de test que los parametros hacen que se lanze una excepción
			}
		}
		
//Este test es para detectar un error en la aplicacion. En este caso realizará un null.getstock(), que esa funcion no está implementado de esa manera 
		@Test
		public void testAddBasket4()	{
				try {
						quantity =	1;
						basket.addBasket(null,	quantity);
						fail("The	code	must	not	continue	!!");
				}	catch (NullPointerException	e)	{
									fail("Null	pointer	exception	not	handled");
				}	catch (RuntimeException	e)	{
									assertTrue(true);
				}
		}

}
