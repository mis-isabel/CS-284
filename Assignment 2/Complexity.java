package Homework1;
//I pledge my honor that I have abided by the Stevens Honor System.
public class Complexity {

	//time complexity O(n)
	public static void method0(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
		System.out.println("Operation " + counter );
		counter ++;
		}
	}
	
	//time complexity O(n^2)
	public static void method1(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				counter ++;
			}
		}
		System.out.println("Operation " + counter );
	}
	
	//time complexity O(n^3)
	public static void method2(int n) {
		int counter = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					counter ++;
				}
			}
		}
		System.out.println("Operation " + counter );
	}
	
	//time complexity O(logn)
	public static void method3(int n) {
		int counter = 0;
		for (int i = 1; i < n; i *= 10) {
			counter++;
		}
		System.out.println("Operation " + counter );
	}
	
	/*
	Start length 32
	Iteration | Start | End
	1		  |   0   |	31
	2		  |  16   |	31
	3		  |  24   |	31
	4		  |  28   |	31
	5		  |  30   |	31
	6		  |  31	  |	31
	7		  |  32   |	31
	
	Start length 64
	Iteration | Start | End
	1		  |   0   | 63
	2		  |  32   |	63
	3		  |  48   |	63
	4		  |  56   |	63
	5		  |  60   |	63
	6		  |  62	  |	63
	7		  |  63   |	63
	8		  |  64   |	63
	
	5) What is the relation between the size n of a and the number of iterations?
	2+log2(n)
	
	6) What is the time complexity of bSearch?
	O(log(n))
	
	*/
	//time complexity O(nlogn)
	public static void method4(int n) {
		int counter = 0;
		for (int i = 1; i < n; i *= 10) {
			for (int j = 0; j < n; j++) {
				counter++;
			}
		}
		System.out.println("Operation " + counter );
	}
	
	//time complexity O(log log n)
	public static void method5(int n) {
		int counter = 0;
		for (int i = 2; i < n; i *= i) {
			counter++;
		}
		System.out.println("Operation " + counter );
	}
	
	//bonus time complexity O(2^n) 
	public static int method6(int n) {
		if (n == 0) {
			return 2;
		}
		else if (n == 1) {
			return 1;
		}
		else {
			return (method6(n - 1) + method6 (n - 2));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(method6(9));
	}

}
