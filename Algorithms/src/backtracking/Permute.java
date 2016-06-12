package backtracking;

import java.util.Scanner;

public class Permute {
	static void swap (char[] a,int i,int j)
	{
	    char temp;
	    temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}
	  
	/* Function to print permutations of string
	   This function takes three parameters:
	   1. String
	   2. Starting index of the string
	   3. Ending index of the string. */
	static void permute(char[] a, int i) 
	{
	   int j; 
	   if (i == a.length - 1)
	     System.out.println(a);
	   else
	   {
	        for (j = i; j <= a.length - 1; j++)
	       {
	          swap(a,i,j);
	          permute(a, i+1);
	          swap(a,i,j); //backtrack
	       }
	   }
	}
	
	static void permuteUnique(char[] a, int i) 
	{
	   int j; 
	   if (i == a.length - 1)
	     System.out.println(a);
	   else
	   {
	        for (j = i; j <= a.length - 1; j++)
	       {
	          if(j == i || (a[i] != a[j])){
	        	  swap(a,i,j);
		          permute(a, i+1);
		          swap(a,i,j); //backtrack
	          }
	       }
	   }
	}
	 
	/* Driver program to test above functions */
	public static void main(String[] args)
	{
		String str = new Scanner(System.in).nextLine();
		char a[] = str.toCharArray();
		permute(a, 0);
		System.out.println("-----------");
		permuteUnique(a, 0);
	}
}
