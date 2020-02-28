import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class MegaSort
{
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in);
		//from 11-23, code fromhttps://stackoverflow.com/questions/40822533/input-a-text-file-through-command-line-in-java
		if (args.length == 0)
		{
			System.out.println("No file name.");
			System.exit(1);
		}

		try {
			File numFile = new File(args[0]);
			input = new Scanner(numFile);
		} catch (IOException ioException) {
			System.err.println("Cannot open file.");
			System.exit(1);
		}

		//from https://stackoverflow.com/questions/34208962/read-a-text-file-line-by-line-into-strings
		ArrayList<Integer> numArrayList = new ArrayList<Integer>();
		while (input.hasNextLine())
		{
			int num = Integer.parseInt(input.nextLine());
			numArrayList.add(num);
		}

		//from https://www.geeksforgeeks.org/arraylist-array-conversion-java-toarray-methods/
		int[] intArray = new int[numArrayList.size()];
		for (int i = 0; i < numArrayList.size(); i++)
		{
			intArray[i] = numArrayList.get(i);
		}

		sort(intArray);

		//for loop that gets the item in the array and prints it
		for (int i = 0; i < intArray.length; i++)
		{
			System.out.println(intArray[i]);
		}

	}

	//from class notes and copyOfRange from geeksforgeeks
	public static void sort(int [] a)
	{
		//base case for when one item is left
		if (a.length <= 1)
		{
			return;
		}

		//get size of left array
		int left_size = a.length / 2;
		
		//put values into left and right arrays
		int[] left = Arrays.copyOfRange(a, 0, left_size);
		int[] right = Arrays.copyOfRange(a, left_size, a.length);

		//recursive call of sorting left and right, then merge both
		sort(left);
		sort(right);
		merge(a, left, right);
	}

	public static void merge(int [] target, int [] left, int [] right)
	{
		int indexL = 0;
		int indexR = 0;
		int indexTarget = 0;

		//check left value against right and put in lower value
		while (indexL < left.length && indexR < right.length)
		{
			if (left[indexL] <= right[indexR])
			{
				target[indexTarget++] = left[indexL++];
			}
			else
			{
				target[indexTarget++] = right[indexR++];
			}
		}

		while (indexL < left.length)
		{
			target[indexTarget++] = left[indexL++];
		}
		while (indexR < right.length)
		{
			target[indexTarget++] = right[indexR++];
		}
	}
}