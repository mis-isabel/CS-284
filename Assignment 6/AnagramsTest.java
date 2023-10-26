package Homework6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AnagramsTest {

	@Test
	void testAddWord() {
		Anagrams ana = new Anagrams();
		ana.addWord("j");
		/* 4 is not prime so it shouldn't be in the anagrams table. 
		 * after adding "j", which is mapped to the prime number 29, 29 should be in the table.
		 */
		assertFalse(ana.anagramTable.containsKey((long) 4)); 
		assertTrue(ana.anagramTable.containsKey((long) 29));
		
		/* 
		 * try adding a whole word to the anagram table, like "night" for example. compute the hashcode of "night" (the multiplication 
		 * of the correct prime numbers) and test if it is in the table as a key
		 */
		ana.addWord("apple");
		assertTrue(ana.anagramTable.containsKey((long) 2286526));
		
		/*
		 * try adding another word, "thing". "thing" is an anagram of "night" so it should be mapped the same key as "night", and 
		 * in a list with the only other element being "night".
		 * 
		 * please use your own examples. I came up with "thing" and "night" and it'll be better if everyone tries to think of their own.
		 * just look up some anagrams online lol.
		 */
		ana.addWord("elppa");
		assertTrue(ana.anagramTable.containsKey((long) 2286526));
	}
	
	@Test
	void testMaxEntries() {
		Anagrams ana = new Anagrams();
		/* 
		 * try adding words to "ana" that are all anagrams of each other. Then add some that aren't anagrams of each other.
		 * Obviously, these don't even have to be real words. If we were using my previous example, I would use "ghint", "hngit", etc.
		 * and then add words like "apple" or something which clearly aren't anagrams of "night".
		 * 
		 * Test that getMaxEntries returns the correct mapping from the right hashcode to the right list of anagrams.  
		 * How you do this up to you.
		 */
		ana.addWord("time");
		ana.addWord("mite");
		ana.addWord("emit");
		ana.addWord("apple");
		ana.addWord("bite");
		
		assertEquals(ana.getMaxEntries().toString(), "[736483=[time, mite, emit]]");
	}

}
