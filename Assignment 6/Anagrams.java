package Homework6;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Marguerite Sutedjo
//I pledge my honor that I have abided by the Stevens Honor System.
public class Anagrams {
	final Integer[] primes =  
		{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
				31, 37, 41, 43, 47, 53, 59, 61, 67, 
				71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;
	
	/** Builds a hashtable using the given entries
	 */
	public void buildLetterTable() {
		letterTable = new HashMap<Character,Integer>();
		for (int x = 97; x <= 122; x++) {
			letterTable.put((Character)((char) x), primes[x - 97]);
		}
	}
	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}
	
	/** Computes the hash code and adds the word to the hash table
	 * @param string s, hash code of string
	 */
	public void addWord(String s) {
		Long x = myHashCode(s);
		if (anagramTable.containsKey(x)) {
			if (anagramTable.get(x).contains(s)) {
				throw new IllegalArgumentException("addWord: duplicate value");
			}
			else {
				ArrayList<String> temp = anagramTable.get(x);
				temp.add(s);
				anagramTable.replace(x, temp);
			}
		}
		else {
			ArrayList<String> temp = anagramTable.get(x);
			anagramTable.put(x, new ArrayList<String>(Arrays.asList(s)));
		}
	}
	
	/** Computes the hash code of a given string
	 * @param string s, containing a word
	 */
	public long myHashCode(String s) {
		if (s.isEmpty()) {
			throw new IllegalArgumentException("new word is empty");
		}
		else {
			long x = 1;
			for (int i = 0; i < s.length(); i++) {
				x = x * letterTable.get(s.charAt(i));
			}
			return x;
		}
	}
	
	/** Creates a hashtable
	 * @param text file containing one word per line
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
			this.addWord(strLine);
		}
		br.close();
	}
	
	/** Returns the entries in anagramTable that has the largest number of anagrams
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		int maxTemp = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> max = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			if (entry.getValue().size() > maxTemp) {
				maxTemp = entry.getValue().size();
			}
		}
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			if (maxTemp == entry.getValue().size()) {
				max.add(entry);
			}
		}
		return max;
	}
	
	/** Reads all strings in the file, reports which words has the largest number of anagrams
	 */
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = 
				a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}