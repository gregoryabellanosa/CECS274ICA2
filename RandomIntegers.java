import java.util.*;
/**
 * This program creates a set of numbers using an array list and uses a random number
 * generator in order to fill the elements of the array list. Using a scanner object, user 
 * input is utilized in order to establish which function on a provided menu will be 
 * used to access the elements in the array list. The program also contains a method needed 
 * for sorting the values of the array list into ascending order. The menu will continue to
 * repeat until the user decides to quit the program.
 * 
 * @author Gregory Abellanosa <gregoryabellanosa@gmail.com>
 *
 */
public class RandomIntegers {

	public static void main(String[] args) {	
		//creates a scanner object for user input
		Scanner input = new Scanner(System.in);
		 
		//creates a random object for a random number generator
		Random randNum = new Random();
		
		//creates an array list object with a capacity of 10 elements
		ArrayList <Integer> list = new ArrayList <Integer> ();
		
		//used to create and fill in elements of the array list
		for (int i = 0; i < 10; i++)
		{
			int num = randNum.nextInt(99);
			list.add(i, num);
		}
	
		//prints the values 
		System.out.println("Here is a random set of numbers from 1-100.");
		System.out.println(list);
		
		//use to exit the do loop for the menu when needed
		boolean quit = false;
		
		//use do while loop so that the menu prints at least once in the beginning
		do
		{
			//establish menu choices
			System.out.println("What would you like to do with these values?");
			System.out.println("1.) Compute the sum of these values.");
			System.out.println("2.) Find the maximum value within this set.");
			System.out.println("3.) Find a matching value.");
			System.out.println("4.) Quit");
			
			//keep track of what the user inputs
			int choice = 0;
			
			boolean inputChecker = true;
			
			//makes sure that the user input is the correct data type
			while (inputChecker)
			{	
				try {
					choice = input.nextInt();
					inputChecker = false;
				} catch ( InputMismatchException im ) {
					input.next();
					System.out.println("Invalid Input.Try Again.");
				}
			}
			
			//makes sure that the user's input is within the appropriate bounds
			while (choice < 1 || choice > 4)
			{
				System.out.println("That option is not available. Please pick an option provided on the menu.");
				choice = input.nextInt();
			}
			
			switch (choice)
			{
				case 1:
					int sum = computeSum(list);
					System.out.println("The sum of these values is " + sum + ".\n");
					break;
				case 2:
					int maxValue = computeMaxValue(list);
					System.out.println("The highest value in this set is " + maxValue + ".\n");
					break;
				case 3:
					System.out.println("What value would you like to find?");
					
					int numSearch = 0;
					boolean testInput = true;
					
					while ( testInput )
					{
						try {
							numSearch = input.nextInt();
							testInput = false;
						} catch ( InputMismatchException im ) {
							input.next();
						    System.out.println("Invalid Input. Please Enter a number.");
						}
					}
					int index = findSameValue(list , numSearch);
					
					//-1 indicator will tell the user that a match was not found and to try looking
					//for a match for a different number
					if (index == -1)
					{
						System.out.println("The number you entered does not exist in this set. \n");
					}
					else
					{
						System.out.println("The values are stored in slots counted from index 0-9.");
						System.out.println("The value you are seeking is in index " + index + ". \n");
					}
					break;
				case 4:
					System.out.println("Goodbye.");
					quit = true; //will cause loop to end
					break;
				default:
					System.out.println("Invalid input");
			}	
		}while( quit != true );	
		
		input.close();
	}
	
	/**
	 * Takes all of the numbers in the array list provided in the parameters and adds 
	 * those values together to get a sum
	 * 
	 * @param values the array list contains the set of numbers that will be summed up
	 * @return the sum of all the numbers in the set
	 */
	public static int computeSum(ArrayList<Integer> values)
	{
		int sum = 0;
		for (int i = 0; i < values.size(); i++)
		{
			int num = values.get(i);
			sum += num;
		}
		return sum;
	}
	
	/**
	 * Compares all the elements in the array list with each other to determine the highest value
	 * 
	 * @param values the array list that contains values that will be compared to determine the highest
	 * @return the highest number in the set
	 */
	public static int computeMaxValue(ArrayList<Integer> values)
	{
		int highest = 0;
		for (int i = 0; i < values.size() - 1; i++)
		{
			if ( values.get(i) > highest )
			{
				highest = values.get(i);
			}
		}
		return highest;	
	}
	
	/**
	 * Taking an array list and an integer value as parameters, this method uses both to step through
	 * the array list and compares the values with the parameterized integer value. If there is a match, the 
	 * method will return the index of the matching value. If there is no match, the method will return -1.
	 * 
	 * @param values the array list containing the elements that will be compared to findNum to find a match
	 * @param findNum the number the user is looking for
	 * @return the index of the value matching findNum
	 */
	public static int findSameValue(ArrayList<Integer> values, int findNum)
	{
		//-1 will be the return if findNum doesn't match any of the numbers in the set to 
		//let the program know that there is not a match 
		int matchingIndex = -1;
		for (int i = 0; i < values.size(); i++)
		{
			if ( values.get(i) == findNum )
			{
				matchingIndex = i;
			}
	    }
		return matchingIndex;
	}
	
	/**
	 * Takes the values of an array list and sorts them in ascending order
	 * 
	 * @param values array list containing the elements that will be sorted
	 */
	public static void selectionSort(ArrayList<Integer> values)
	{
		for(int i = 0; i < values.size(); i++)
		{
			int lowest = i;
			for(int j = i + 1; j < values.size(); j++)
			{
				if (values.get(j) < values.get(lowest))
				{
					lowest = j;
				}
			}
			int swap = values.get( i );
			values.set( i, values.get( lowest ) );
			values.set( lowest, swap );
		}
	}
}
