package calcFn;

public class LnOfBestFitFn
{
	public static String findBestFitLine(double[] xVals, double[]yVals) 
	{
		if (findYInt(xVals, yVals) < 0)
			return String.format("y = %.2fx - %.2f\n", findSlope(xVals, yVals), findYInt(xVals, yVals) * (-1));
		else
			return String.format("y = %.2fx + %.2f\n", findSlope(xVals, yVals), findYInt(xVals, yVals));
	}
	private static double findAverage(double[] array) 
	{
		double sum = 0;
		for (int count = 0; count < array.length; count++) 
			sum += array[count];
		return sum / array.length;
	}
	private static double findSlope(double[] xVals, double[] yVals) 
	{
		double average;
		double sumTop = 0;
		double sumBottom = 0;
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

