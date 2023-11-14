package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {
	
	@Test
	@SuppressWarnings("static-access")
	public void mainTest() {
		// Testing main() function
		GildedRose inn = new GildedRose();
		inn.main(null);
	}

	@Test
	public void testSellInValueDecreases() {
		/*
		 * All items have a SellIn value which denotes the number of days we have to sell the item
		 * This test take a notice "Sulfuras, Hand of Ragnaros" which sell in value not change
		 */
		
		// Format
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
	    inn.setItem(new Item("Aged Brie", 2, 0));
	    inn.setItem(new Item("Elixir of the Mongoose", 5, 7));
	    inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
	    inn.setItem(new Item("Conjured Mana Cake", 3, 6));

	    // Act
	    inn.oneDay();

	    // Asserts
	    assertEquals("SellIn should decrease for +5 Dexterity Vest", 9, inn.getItems().get(0).getSellIn());
	    assertEquals("SellIn should decrease for Aged Brie", 1, inn.getItems().get(1).getSellIn());
	    assertEquals("SellIn should decrease for Elixir of the Mongoose", 4, inn.getItems().get(2).getSellIn());
	    assertEquals("SellIn for Sulfuras should not change", 0, inn.getItems().get(3).getSellIn());
	    assertEquals("SellIn should decrease for Backstage passes", 14, inn.getItems().get(4).getSellIn());
	    assertEquals("SellIn should decrease for Conjured Mana Cake", 2, inn.getItems().get(5).getSellIn());
	}
	
	@Test
	public void testQualityValue() {
		/*
		 * All items have a Quality value which denotes how valuable the item is
		 * At the end of each day our system lowers both values for every item
		 * Pretty simple, right? Well this is where it gets interesting:
		 */
		
		// Format
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
	    inn.setItem(new Item("Aged Brie", 2, 0));
	    inn.setItem(new Item("Elixir of the Mongoose", 5, 7));
	    inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	    inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
	    inn.setItem(new Item("Conjured Mana Cake", 3, 6));

	    // Act
	    inn.oneDay();
	    
	    // Asserts
	    assertEquals("Quality should decrease for +5 Dexterity Vest", 19, inn.getItems().get(0).getQuality());
	    // Aged Brie quality increase if sell in decrease
	    assertEquals("Quality should increase for Aged Brie", 1, inn.getItems().get(1).getQuality());
	    assertEquals("Quality should decrease for Elixir of the Mongoose", 6, inn.getItems().get(2).getQuality());
	    // Stay stable
	    assertEquals("Quality for Sulfuras should not change", 80, inn.getItems().get(3).getQuality());
	    
	    // "Backstage passes", like aged brie, increases in Quality as it's SellIn value approaches 
	    assertEquals("Quality should decrease for Backstage passes", 21, inn.getItems().get(4).getQuality());
	    assertEquals("Quality should decrease for Conjured Mana Cake", 5, inn.getItems().get(5).getQuality());
	}
	
	@Test 
	public void testBackstage() {
		/* QUALITY NEVER MORE THEN 50 */
		
		// Format
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		
		// Act
		for(int day = 0; day < 15; day++) {
			inn.oneDay();
		}
		
		// Asserts
		// Tarkistetaan että "Backstage passes to a TAFKAL80ETC concert" päivät ovat 0
		assertEquals("Days is now zero", 0, inn.getItems().get(0).getSellIn());
		assertTrue("Quality is not increase above 50", inn.getItems().get(0).getQuality() <= 50);
		assertEquals("Quality is right", 50, inn.getItems().get(0).getQuality());
	}

	@Test
	public void sellInNegative() {
		// Format
		GildedRose inn = new GildedRose();
		 inn.setItem(new Item("+5 Dexterity Vest", 0, 20));
		 inn.setItem(new Item("Aged Brie", 0, 0));
		 inn.setItem(new Item("Elixir of the Mongoose", 0, 7));
		 inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		 inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));
		 inn.setItem(new Item("Conjured Mana Cake", 0, 6));
		 
		 // Act
		 inn.oneDay();
		
		 // Asserts already tested above of this 
	}
}
