package greatLearning;

import java.util.Scanner;

public class EvenOdd {
	
	public static void main(String args[]) {
		Scanner scann = new Scanner(System.in);
		System.out.println("Enter the Number");
		
		int num = scann.nextInt();
		
		findEvenOdd(num);
	}

	private static void findEvenOdd(int num) {
		// TODO Auto-generated method stub
		if (num%2==0)
			System.out.println(num+  " is even");
		else
			System.out.println(num+ " is odd");
	}

}
