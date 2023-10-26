package Homework1;
//Marguerite Sutedjo I pledge my honor that I have abided by the Stevens Honor System.

import java.util.Random;

public class CoinPurse {
	private int numGalleons;
	private int numSickles;
	private int numKnuts;

	private static final int CAPACITY = 256;

	public CoinPurse() {
		numGalleons = 0;
		numSickles = 0;
		numKnuts = 0;
	}

	public CoinPurse(int g, int s, int k) {
		numGalleons = g;
		numSickles = s;
		numKnuts = k;
	}

	// deposit
	public void depositGalleons(int n) {
		if (n < 0 || n + numCoins() > CAPACITY) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		numGalleons += n;
	}

	public void depositSickles(int n) {
		if (n < 0 || n + numCoins() > CAPACITY) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		numSickles += n;
	}

	public void depositKnuts(int n) {
		if (n < 0 || n + numCoins() > CAPACITY) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		numKnuts += n;
	}

	// withdraw
	public void withdrawGalleons(int n) {
		if (n < 0 || n - numCoins() > 0) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		numGalleons -= n;
	}

	public void withdrawSickles(int n) {
		if (n < 0 || n - numCoins() > 0) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		numSickles -= n;
	}

	public void withdrawKnuts(int n) {
		if (n < 0 || n - numCoins() > 0) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		numKnuts -= n;
	}

	public int numCoins() {
		return numGalleons + numSickles + numKnuts;
	}

	public int totalValue() {
		return numKnuts + (numSickles * 29) + (numGalleons * 493);
	}

	public String toString() {
		return "Galleons: " + numGalleons + " Sickles: " + numSickles + " Knuts: " + numKnuts;
	}

	public boolean exactChange(int n) {
		// variables
		int count = n;
		int ng = numGalleons;
		int ns = numSickles;
		int nk = numKnuts;

		// goes through from biggest to smallest and takes away as each coin is used
		while ((count >= 493) && (ng > 0)) {
			count -= 493;
			ng -= 1;
		}
		while ((count >= 29) && (ns > 0)) {
			count -= 29;
			ns -= 1;
		}
		while ((count > 0) && (nk > 0)) {
			count -= 1;
			nk -= 1;
		}

		// true if exact change
		if (count == 0) {
			return true;
		}

		// false is not exact change
		else {
			return false;
		}
	}

	public int[] withdraw(int n) {
		// variables
		int c = n;
		int ngs = numGalleons;
		int nss = numSickles;
		int nks = numKnuts;
		int totalG = 0;
		int totalS = 0;
		int totalK = 0;
		int[] change = new int[3];

		// accounts for exact change
		if (exactChange(n) == true) {
			while ((c >= 493) && (ngs > 0)) {
				c -= 493;
				ngs -= 1;
				totalG += 1;
			}
			while ((c >= 29) && (nss > 0)) {
				c -= 29;
				nss -= 1;
				totalS += 1;
			}
			while ((c > 0) && (nks > 0)) {
				c -= 1;
				nks -= 1;
				totalK += 1;
			}

			// assigns to array
			change[0] = totalG;
			change[1] = totalS;
			change[2] = totalK;
			if (totalValue() < n) {
				throw new IllegalArgumentException("This wrong, you done goofed");
			}
			return change;
		}

		// when not exact change
		else {
			while ((c >= 493) && (ngs > 0)) {
				c -= 493;
				ngs -= 1;
				totalG += 1;
			}
			if (c - (nss * 29) + (nks * 1) > 0) {
				c -= 493;
				ngs -= 1;
				totalG += 1;
			}
			while ((c >= 29) && (nss > 0)) {
				c -= 29;
				nss -= 1;
				totalS += 1;
			}
			if (c - (totalK * 1) > 0) {
				c -= 29;
				nss -= 1;
				totalS += 1;
			}
			while ((c > 0) && (nks > 0)) {
				c -= 1;
				nks -= 1;
				totalK += 1;
			}

			// assignss to array
			change[0] = totalG;
			change[1] = totalS;
			change[2] = totalK;
			if (totalValue() < n) {
				throw new IllegalArgumentException("This wrong, you done goofed");
			}
			return change;
		}

	}

	public int drawRandCoin() {
		// variables
		int ranG = numGalleons;
		int ranS = ranG + numSickles;
		Random rand = new Random();
		int r = rand.nextInt(numCoins());
		int out = 0;

		if (totalValue() == 0) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}

		if (numSickles == 0) {
			ranS = 0;
		}

		if (r < numGalleons) {
			ranG -= 1;
			out = 2;
		}

		else if ((r < numSickles + numGalleons) && (r >= numGalleons)) {
			ranS -= 1;
			out = 1;
		}

		else if ((r <= numSickles + numGalleons + numKnuts) && (r >= numGalleons + numSickles)) {
			out = 0;
		}
		return out;
	}

	public int[] drawRandSequence(int n) {
		// variables
		int[] coins = new int[n];

		for (int i = 0; i < coins.length; i++) {
			coins[i] = drawRandCoin();
		}

		if (totalValue() == 0) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}
		return coins;
	}

	public static int compareSequences(int[] coinSeq1, int[] coinSeq2) {
		// variables
		int seq1Win = 0;
		int seq2Win = 0;

		if (coinSeq1.length != coinSeq2.length) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}

		for (int i = 0; i < coinSeq2.length; i++) {
			if (coinSeq2[i] > coinSeq1[i]) {
				seq2Win += 1;
			} else if (coinSeq1[i] > coinSeq2[i]) {
				seq1Win += 1;
			}
		}

		// return statements
		if (seq1Win > seq2Win) {
			return 1;
		} else if (seq2Win > seq1Win) {
			return -1;
		} else {
			return 0;
		}
	}

	public int[] drawRandSequenceNoRep1(int n) {
		// variables
		int[] coinSeq = new int[n];

		if (totalValue() == 0) {
			throw new IllegalArgumentException("This wrong, you done goofed");
		}

		for (int i = 0; i < coinSeq.length; i++) {
			int ranG = numGalleons;
			int ranS = ranG + numSickles;
			Random rand = new Random();

			int r = rand.nextInt(numCoins());

			if (r < numGalleons) {
				ranG -= 1;
				coinSeq[i] = 2;
				withdrawGalleons(1);
			}

			else if ((r < numSickles + numGalleons) && (r >= numGalleons)) {
				ranS -= 1;
				coinSeq[i] = 1;
				withdrawSickles(1);
			}

			else if ((r <= numSickles + numGalleons + numKnuts) && (r >= numGalleons + numSickles)) {
				coinSeq[i] = 0;
				withdrawKnuts(1);
			}

		}
		return coinSeq;

	}

	public static void main(String[] args) {

	}
}
