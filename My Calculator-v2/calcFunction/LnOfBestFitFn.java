package calcFunction;

/**
 * This class, given an array of (x,y) coordinate points, will calculate the line of best fit for the set and return
 * it in the form y = mx + b.
 * 
 * This class is completely encapsulated and is used in the LnOfBestFitGUI class to provide function to the program.
 * 
 * It contains 4 methods that calculate each 
 *
 */
public class LnOfBestFitFn
{
	/**
	 * This method, given a set of x and y values, will return the equation for the best fit line in the form of a
	 * String in the form "y = mx + b" or "y = mx - b" based on whether the y-intercept of the function is positive
	 * or negative respectively
	 * 
	 * @param xVals that is a double array containing all the x-values for the data
	 * @param yVals that is a double array containing all the y-values for the data
	 * @return a String that represents the equation for the best fit line
	 */
	public static String findBestFitLine(double[] xVals, double[]yVals) 
	{
		if (String.valueOf(findSlope(xVals, yVals)).equals("NaN") || String.valueOf(findYInt(xVals, yVals)).equals("NaN")) 
		{
			return "No Solution";
		}
		else 
		{
			if (findYInt(xVals, yVals) < 0)
			{
				return String.format("y = %.2fx - %.2f\n", findSlope(xVals, yVals), findYInt(xVals, yVals) * (-1));
			}
			else
			{
				return String.format("y = %.2fx + %.2f\n", findSlope(xVals, yVals), findYInt(xVals, yVals));
			}
		}
	}
	
	// Utilized in both the findSlope() and findYInt() methods.
	// Finds the average of a given set of double values.
	private static double findAverage(double[] array) 
	{
		double sum = 0;
		for (int count = 0; count < array.length; count++) 
		{
			sum += array[count];
		}
		return sum / array.length;
	}
	private static double findSlope(double[] xVals, double[] yVals) 
	{
		double average;
		double sumTop = 0;
		double sumBottom = 0;
		
		// Uses the standard linear regression line formula to find the slope of the best fit
		// line for the data set.
		for (int count = 0; count < xVals.length; count++) 
		{
			sumTop += (xVals[count] - findAverage(xVals))*(yVals[count] - findAverage(yVals));
			sumBottom += (xVals[count] - findAverage(xVals))*(xVals[count] - findAverage(xVals));
		}
		average = sumTop / sumBottom;
		return average;
	}
	private static double findYInt(double[] xVals, double[] yVals) 
	{
		return findAverage(yVals) - findSlope(xVals, yVals) * findAverage(xVals);
	}
}
