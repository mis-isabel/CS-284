package Homework5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Marguerite Sutedjo
//I pledge my honor that I have abided by the Stevens Honor System.

public class Sort {
	static Set<Interval> intervalSet = new HashSet<>();
	
	public static class Interval {
		//data fields
		private int lower;
		private int upper;
		
		public Interval(int lower , int upper) {
			this.lower = lower;
			this.upper = upper;
		}
		
		public int getLower () {// returns the lower bound
			return lower;
		}
		
		public int getUpper () {// returns the upper bound
			return upper;
		}
		
		public boolean equals(Object o) {// returns true if this interval and the given interval have the same lower and upper bounds
			return (((Interval)o).lower == lower && ((Interval)o).upper == upper);
		}
		
		public int hashCode () {// returns lower * lower + upper
			return (lower * (lower + upper));
		}
	}
	/** Swaps the position of two elements as a helper function
 	@param data contains all the items
 	@param int the first item to be swapped
 	@param int the second item to be swapped
	 */
	public static <T extends Comparable<T>> void swap(T[] table, int first, int last) {
		T temp = table[first];
		table[first] = table[last];
		table[last] = temp;
	}
	
	
	/** Given an interval, sorts middle, last and first, ignoring what is inbetween rest
 	@param data contains all the items
 	@param int the first item in the array
 	@param int the last item in the array
	 */
	private static <T extends Comparable<T>> int partition(T[] table, int first, int last) {
		int median;
		int middle = (first + last) / 2;
		int up = first;
		int down = last;
		
		for (int x = 0; x < 2; x++) {
			if (table[first].compareTo(table[middle]) > 0) {
				swap(table, first, middle);
			}
			if (table[middle].compareTo(table[last]) > 0) {
				swap(table, middle, last);
			}
		}
		median = middle;
		if (last-first <= 2) {
			return down;
		}
		swap(table, first, median);
		T pivot = table[first];
			
		do {
			while ((up < last) && (pivot.compareTo(table[up]) >= 0)) {
				up++;
			}
			while ((pivot.compareTo(table[down]) < 0 ) && (first < down)) {
				down--;
			}
			if (up < down) { // if up is to the left of down.
				swap(table, up, down);
			}
		} 
		while (up < down); // Repeat while up is left of down.
		swap(table, first, down);
		if (down > 0) {
			intervalSet.add(new Interval(first, (down - 1)));
		}
		if (down < table.length - 1) {
			intervalSet.add(new Interval((down + 1), last ));
		}
		return down;
	}
	
	/** sorts the data
	@param data contains all the items
 	@param int the first item in the array
 	@param int the last item in the array
	 */
	public static <T extends Comparable<T>> void quickSort(T[] table, int first, int last) {
		if (first < last) {
			intervalSet.add(new Interval(first, last));
			while (intervalSet.isEmpty() == false) {
				Interval tracker = intervalSet.iterator().next();
				intervalSet.remove(tracker);
				if (tracker.getUpper() - tracker.getLower() >= 0) {
					int nPivot = partition(table, tracker.getLower(), tracker.getUpper());
				}
			}
		}
	}
	
	/** sorts the data
	@param data contains all the items
 	@param int the first item in the array
 	@param int the last item in the array
	 */
	public static <T extends Comparable<T>> void sort(T[] table) {
		quickSort(table, 0, (table.length - 1));
			
	}
    public static void main(String[] args) {
        //Integer[] a = {3,2,1};
        //Integer[] a = {9,2,5,6,7,4,3,8,1};
        //Integer[] a = {10,9,8,7,6,5,4,3,2,1};
        //Integer[] a = {2,5,3,0,2,3,0,3};
        //Integer[] a = {3,4,7,1,8,5,2,9,0,6};
        //Integer[] a  = {3,4,7,1,5,8,2,9,0,6};
        Integer[] a = {5,4,7,1,8,3,2,9,0,6};
        
        System.out.println("Original: ");
        for (int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
        }        
        sort(a);
        System.out.println("\nSorted: ");
        for (int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}

