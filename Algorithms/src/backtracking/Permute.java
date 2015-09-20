package backtracking;

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
	static void permute(char[] a, int i, int n) 
	{
	   int j; 
	   if (i == n)
	     System.out.println(a);
	   else
	   {
	        for (j = i; j <= n; j++)
	       {
	          swap(a,i,j);
	          permute(a, i+1, n);
	          swap(a,i,j); //backtrack
	       }
	   }
	} 
	 
	/* Driver program to test above functions */
	public static void main(String[] args)
	{
	   char a[] = "ABCD".toCharArray();  
	   permute(a, 0, a.length-1);
	}
}
