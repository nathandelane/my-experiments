package com.github.nathandelane.experiments.hashing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HashTableTests {

	@Test
	public void testPutValue() {
		final HashTable hashTable = new HashTable(100);
		hashTable.put("Name", "Nathan Lane");

		final String value = hashTable.get("Name");

		assertEquals("Nathan Lane", value);
	}

	@Test
	public void testPutValues() {
		final HashTable hashTable = new HashTable(10);
		hashTable.put("Milk", "2");
		hashTable.put("Butter", "8");
		hashTable.put("Rice", "32");
		hashTable.put("Black Beans", "4");
		hashTable.put("Tortillas", "2");

		assertEquals("2", hashTable.get("Milk"));
		assertEquals("8", hashTable.get("Butter"));
		assertEquals("32", hashTable.get("Rice"));
		assertEquals("4", hashTable.get("Black Beans"));
		assertEquals("2", hashTable.get("Tortillas"));
	}

}
