package calcFunction;

import java.util.Arrays;

/**
 * This is a public class that is used in the DescDataGUI class of the application package. 
 * It is completely encapsulated and, given an array of doubles, will return 7 key values in 
 * the data set: namely mean, median, mode, range, first quartile, third quartile, and standard
 * deviation
 * 
 * The class contains an individual private method to calculate each of these values individually and a
 * central method that interacts with outside classes and calls each of these private methods.
 */
public class DescDataFn
{ 
	private static double[] results = new double[7];
	/**
	 * This method receives an array of doubles as the data set and returns an array of doubles containing
	 * the key values of the data set.
	 * 
	 * This is the only public method and, therefore, is the only one designated to interacts with outside 
	 * classes.
	 * 
	 * @param nums array of doubles that contains the data set that is to be analyzed.
	 * @return double[] containing the 7 key values mentioned above.
	 */
	public static double[] describeData(double[] nums)
	{
		Arrays.sort(nums);
		results[0] = calculateMean(nums);
		results[1] = calculateMedian(nums);
		results[2] = calculateMode(nums);
		results[3] = calculateRange(nums);
		results[4] = calculateQ1(nums);
		results[5] = calculateQ3(nums);
		results[6] = calculateStandardDeviation(nums);
		return results;
	}
	
	// Calculates the mean of the data set
	private static double calculateMean(double[] array) 
	{
		double result;
		double sum = 0;
		int count = 0;
		for (count = 0; count < array.length; count++) 
		{
			sum += array[count];
		}
		result = sum / (double)(count);
		return result;
	}
	
	// Calculates the median of the data set
	private static double calculateMedian(double[] array) 
	{
		double result;
		if (array.length % 2 == 0) 
		{
			result = (array[(array.length / 2) - 1] + array[array.length / 2]) / 2;
		}
		else 
		{
			result = array[((array.length - 1) / 2)];
		}
		return result;
	}
	
	// Calculates the mode of the data set
	private static double calculateMode(double[] array) 
	{
		double result = 0;
		int[] dataCount = new int[array.length];
		for (int count1 = 0; count1 < array.length; count1++) 
		{
			for (int count2 = count1; count2 < array.length; count2++) 
			{
				if (array[count1] == array[count2]) 
				{
					dataCount[count1]++;
				}
			}
		}
		int largestNum = 0;
		for (int count = 0; count < dataCount.length; count++) 
		{
			if (dataCount[count] > largestNum) 
			{
				largestNum = dataCount[count];
				result = array[count];
			}
		}
		return result;
	}

	// Calculates the range of the data set
	private static double calculateRange(double[] array) 
	{
		return array[array.length - 1] - array[0];
	}

	// Calculates the first quartile value of the data set by finding the median between the 
	// first data point and the median of the data set
	private static double calculateQ1(double[] array) 
	{
		double[] array2;
		if (array.length % 2 == 0) 
		{
			array2 = new double[(array.length) / 2];
		}
		else
		{
			array2 = new double[(array.length + 1) / 2];	
		}
		for (int count = 0; count < array2.length; count++) 
		{
			array2[count] = array[count];
		}
		return calculateMedian(array2);
	}
	
	// Calculates the third quartile value of the data set by finding the median between the 
	// median of the data set and the last point in the set.
	private static double calculateQ3(double[] array) 
	{
		double[] array2;
		if (array.length % 2 == 0) 
		{
			array2 = new double[(array.length) / 2];
		}
		else
		{
			array2 = new double[(array.length + 1) / 2];	
		}
		for (int count = 0; count < array2.length; count++) 
		{
			array2[count] = array[(array.length / 2) + count];
		}
		return calculateMedian(array2);
	}
	
	// Calculates the standard deviation of the data set.
	private static double calculateStandardDeviation(double[] array) 
	{
		double mean = calculateMean(array);
		double sum = 0;
		int count = 0;
		for (count = 0; count < array.length; count++) 
		{
			sum += Math.pow(mean - array[count], 2);
		}
		return Math.sqrt(sum / count);
	}
}
