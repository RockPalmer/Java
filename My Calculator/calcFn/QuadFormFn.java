package calcFn;

public class QuadFormFn
{
	private static double[] answers = new double[2];
	private static double quadFormPos(double a, double b, double c) 
	{
		return (-1 * b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
	}
	private static double quadFormNeg(double a, double b, double c) 
	{
		return (-1 * b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
	}
	public static double[] calculate(Double val1, Double val2, Double val3) 
	{
		answers[0] = quadFormPos(val1,val2,val3);
		answers[1] = quadFormNeg(val1,val2,val3);
		return answers;
	}
}