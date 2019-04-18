package Labs;

import java.util.*;
// Set Calculator 1.0: Made by George Mavroeidis on Sunday December 23rd 2018
// In this program, fundamental concepts of set theory are implemented on two sets of integer elements. 
// The program asks for user for the length or cardinality of the two sets and then, the user prompts each element to the set individually.
// Once sets have been declared and initialized, the calculations of each set operations are displayed.
// The calculations are: cardinality of the two sets, intersection, union, A - B, B - A, both power sets for each set.
// Custom methods are found bellow the main method

public class SetCalculator {

// MAIN METHOD -------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		// Scanner declaration
		Scanner keyboard = new Scanner(System.in);
		// Stores user's response on repeating the program
		String ans = "";
		
		// Do-while loop that repeats the program, if the user has requested repetition
		do {
			welcomeBanner();
			System.out.println();
			int a_length = 0;
			boolean done1 = false;
			// User enters the length of sets A and B and are stored
			do {
				try {
					System.out.print("Enter the length for set A: ");
					a_length = keyboard.nextInt();
					if (a_length < 0)
						throw new ArithmeticException();
					done1 = true;
				} catch(InputMismatchException e) {
					System.out.println();
					System.out.println("Must be an integer value");
					keyboard.nextLine();
				} catch(ArithmeticException e) {
					System.out.println();
					System.out.println("Length of set must be positive");
					keyboard.nextLine();
				}
			} while (done1 == false);
			
			int[] a = new int[a_length];
			System.out.println();
			
			int b_length = 0;
			boolean done2 = false;
			do {
				try {
					System.out.print("Enter the length for set B: ");
					b_length = keyboard.nextInt();
					if (b_length < 0)
						throw new ArithmeticException();
					done2 = true;
				} catch(InputMismatchException e) {
					System.out.println();
					System.out.println("Must be an integer value");
					keyboard.nextLine();
				} catch(ArithmeticException e) {
					System.out.println();
					System.out.println("Length of set must be positive");
					keyboard.nextLine();
				}
			} while (done2 == false);
			
			int[] b = new int[b_length];
			
			// User enters each element for each set
			System.out.println();
			System.out.println("-------------------");
			System.out.println("Enter " + a_length + " elements in set A: ");
			System.out.println();
			
			// stores element temporarily
			int a_elem = 0;
			for (int i = 0; i < a_length; i++) {
				boolean done3 = false;
				do {
					try {
						System.out.print("- Enter element " + (i+1) + ": ");
						a_elem = keyboard.nextInt();
						a[i] = a_elem;
						done3 = true;
					} catch (InputMismatchException e) {
						System.out.println("Must be an integer value");
						keyboard.nextLine();
					}
				} while (done3 == false);
			}
			
			// Every array is sorted
			Arrays.sort(a);
			System.out.println();
			System.out.println("Set A contains: " + printSet(a,a_length));
			
			System.out.println();
			System.out.println("Enter " + b_length + " elements in set B: ");
			System.out.println();
			
			int b_elem = 0;
			for (int i = 0; i < b_length; i++) {
				boolean done4 = false;
				do {
					try {
						System.out.print("- Enter element " + (i+1) + ": ");
						b_elem = keyboard.nextInt();
						b[i] = b_elem;
						done4 = true;
					} catch (InputMismatchException e) {
						System.out.println("Must be an integer value");
						keyboard.nextLine();
					}
				} while (done4 == false);
			}
			
			Arrays.sort(b);
			System.out.println();
			System.out.println("Set B contains: " + printSet(b,b_length));
			
			// Cardinality is the amount of elements for each set, therefore, the lengths the user initially entered
			System.out.println();
			System.out.println("Cardinality of set A: " + a.length);
			System.out.println("Cardinality of set B: " + b.length);
			
			// Counter for intersection elements
			int count_inter = 0;
			// Temporary array that saves common elements
			int[] temp = new int[a_length];
			for (int i = 0; i < a_length; i++) {
				for (int j = 0; j < b_length; j++) {
					if (a[i] == b[j]) {
						temp[count_inter] = a[i];
						count_inter++;
					}
				}
			}
			
			// Official array for intersection elements
			int[] intersection = new int[count_inter];
			for(int i = 0; i < count_inter; i++) {
				intersection[i] = temp[i];
			}
			
			Arrays.sort(intersection);
			System.out.println();
			System.out.println("A Intersection B: " + printSet(intersection,intersection.length));
			System.out.println("There are " + count_inter + " elements that are common for sets A and B");
			// If the intersection set is empty, the sets are called mutually disjoint or just disjoint
			if (count_inter == 0) {
				System.out.println("A and B are mutually disjoint, having nothing in common");
				System.out.println("Together, they form a partition");
			}
			System.out.println();
			
			// Temporary count and array for storing elements from set B
			int count_temp = 0;
			int[] temp_b = new int[b_length-count_inter];
			// If A and B share common elements, they are removed from B in order to avoid duplication when storing the union set
			for (int i = 0; i < b_length; i++) {
				int same = 0;
				for (int j = 0; j < intersection.length; j++) {
					// If a common element was found, the loop is alerted
					if (b[i] == intersection[j])
						same++;
				}
				// If an element in B is not found in A, store in temporary array for later use
				if (same == 0) {
					temp_b[count_temp] = b[i];
					count_temp++;
				}
			}
			
			// Counter for elements in union set
			int count_union = 0;
			// Array containing the union set's elements
			int[] union = new int[a.length+temp_b.length];
			// First, all elements of A are assigned to the union set array
			for (int i = 0; i < a.length; i++) {
				union[i] = a[i];
				count_union++;
			}
			
			// Then, the temporary array declared above is added without the duplicates
			for (int i = 0; i < temp_b.length; i++) {
				union[count_union] = temp_b[i];
				count_union++;
			}
			
			Arrays.sort(union);
			System.out.println("A Union B: " + printSet(union,union.length));
			System.out.println("There are " + count_union + " elements in the union of sets A and B");
			System.out.println();
			
			// Counter for A - B set
			int counta_b = 0;
			// Array for A - B set
			int[] a_b= new int[a_length-count_inter];
			for (int i = 0; i < a_length; i++) {
				int same = 0;
				for (int j = 0; j < b_length; j++) {
					if (a[i] == b[j])
						same++;
				}
				// A - B is a set that contains only elements that belong to A. Intersection elements are not included
				// Therefore, if an element is not found in B, it is added to the A - B array
				if (same == 0) {
					a_b[counta_b] = a[i];
					counta_b++;
				}
			}
			
			Arrays.sort(a_b);
			System.out.println("A - B: " + printSet(a_b,a_b.length));
			System.out.println("There are " + counta_b + " elements in the set of A - B");
			System.out.println();
			
			// B - A, same concept as A - B
			int countb_a = 0;
			int[] b_a= new int[b_length-count_inter];
			for (int i = 0; i < b_length; i++) {
				int same = 0;
				for (int j = 0; j < count_inter; j++) {
					if (b[i] == intersection[j])
						same++;
				}
				if (same == 0) {
					b_a[countb_a] = b[i];
					countb_a++;
				}
			}
			
			Arrays.sort(b_a);
			System.out.println("B - A: " + printSet(b_a,b_a.length));
			System.out.println("There are " + countb_a + " elements in the set of B - A");
			
			// Since the algorithm of the power set was large and complicated, it got its own method
			// Details found bellow main method
			System.out.println();
			System.out.println("Power Set of A: " + powerSet(a,a.length));
			System.out.println("There are " + powerSetCard(a.length) + " elements in the power set of A");
			
			System.out.println();
			System.out.println("Power Set of B: " + powerSet(b,b.length));
			System.out.println("There are " + powerSetCard(b.length) + " elements in the power set of B");
			
			// Prompts user to request repetition of program or not
			System.out.println();
			System.out.println("The calculations are over.");
			System.out.print("Do you want to enter new sets?(y/n): ");
			ans = keyboard.next();
			
			// If the user does not enter "y" or "n", ask again for another answer
			while ((!ans.equalsIgnoreCase("y")) && (!ans.equalsIgnoreCase("n"))) {
				System.out.print("Invalid answer. Try again: ");
				ans = keyboard.next();
			}
			
			// If user answers "n", program is closed
			if (ans.equalsIgnoreCase("n")) {
				System.out.println("Thank you for using the Set Calculator. Have a nice day!");
			}
			
			// If user answers "y", program restarts
		} while (ans.equalsIgnoreCase("y"));
		
		// Scanner is closing
		keyboard.close();
	}
	
	// Welcoming banner explaining rules in detail
	public static void welcomeBanner() {
		System.out.println();
		System.out.println("|------------------------------------------------------------------------------|");
		System.out.println("|----------------------- Welcome to the Set Calculator ------------------------|");
		System.out.println("|------------------------- made by George Mavroeidis --------------------------|");
		System.out.println("|------------------------------------------------------------------------------|");
		System.out.println();
		System.out.println("This calculator presents different set operations of two sets A and B containing integer elements");
		System.out.println("The program calculates: cardinality, intersection, union, A - B, B - A, power set of A and B, cardinality of A and B");
		System.out.println("The elements of the sets are displayed in sorting order starting from the smallest element");
	}
	
// CUSTOM METHODS -------------------------------------------------------------------------------------------
	
	// Method that prints the set with braces, which is the right way of displaying elements within a set
	public static String printSet (int[] arr, int arr_length) {
		String str = "";
		if (arr_length > 0) {
			str = "{";
			for (int i = 0; i < arr_length-1; i++)
				str += arr[i] + ", ";
			str += arr[arr_length-1] + "}";
			return str;
		}
		// If set is empty, display empty set
		else
			return str = "{}";
		// The method returns a concatenated string
	}
	
	// A set contains n elements, therefore its power set contains 2^n elements
	// Method returns an integer of the length of the power set
	public static int powerSetCard (int arr_length) {
		int powerSetLength = (int)Math.pow(2, arr_length);
		return powerSetLength;
	}
	
	// Method that calculates and prints a power set correctly, using binary string methods
	public static String powerSet (int[] arr, int arr_length) {
		// Power Set length
		int powerSetLength = (int)Math.pow(2, arr_length);
		
		String str = "";
		for (int i = 0; i < powerSetLength; i++) {
			for (int j = 0; j < arr_length; j++) {
				// Binary sequence of a power set. Catches and concatenates all elements with a binary number of 1
				if ((i & (1 << j)) > 0) {
					str += arr[j] + ",";
				}
			}
			// Since the empty set can't be really stored, it is overlooked from the binary string of the power set (0000...0)
			if (i > 0)
				str += " ";
		}
		
		String new_str = "{{}, ";
		// The string str is split by spaces in order to enter braces and commas correctly
		String[] split = str.split("\\s+");
		for (int i = 0; i < powerSetLength-1; i++) {
			split[i] = split[i].substring(0, split[i].length()-1);
			new_str += "{" + split[i] + "}";
			// The last element contains no comma, therefore it is concatenated separately outside of the concatenation loop
			if (i == powerSetLength-2)
				new_str += "";
			// All other elements, except the last one is concatenated with a comma
			else
				new_str +=", ";
		}
		// close set
		new_str += "}";
		
		// return rendered string for display on the main method
		return new_str;
	}
}

