package calcNum;

public class Digit 
{
	private static final char[] DIGITS = {'0','1','2','3','4','5','6','7','8','9'};
	private static final int[] DIGIT_VALS = {0,1,2,3,4,5,6,7,8,9};
	private static final char[] EVEN_CHARS = {'0','2','4','6','8'};
	private static final int[] EVEN_DIGITS = {0,2,4,6,8};
	private static final char[] PERFECT_SQUARES = {'1','4','9'};
	private int index;
	private boolean isEven;
	public Digit() 
	{
		setDigit('0');
	}
	public Digit(int x) 
	{
		setDigit(x);
	}
	public Digit(char c) 
	{
		setDigit(c);
	}
	private static int parseInt(int x) 
	{
		int result = 0;
		if (x < 0) 
			result = 10 + (x % 10);
		else if (x > 9) 
			result = (x % 10);
		else 
			result = x;
		return result;
	}
	public void setDigit(char c) 
	{
		int charIndex = 0;
		boolean charIsFound = false;
		for (int count = 0; count < DIGITS.length && !charIsFound; count++) 
		{
			charIndex++;
			if (c == DIGITS[count])
			{
				charIsFound = true;
				charIndex = count;
			}
		}
		if (charIndex == DIGITS.length) 
			throw new IllegalArgumentException("An illegal digit was called for. '" + c + "' is not a valid digit.");
		else 
			index = charIndex;
		
		/* Set isEven value */
		isEven = false;
		for (int count = 0; count < EVEN_CHARS.length && !isEven; count++) 
			if (c == EVEN_CHARS[count])
				isEven = true;
	}
	public void setDigit(int x) 
	{
		x = parseInt(x);
		index = x;
		
		/* Set isEven value */
		isEven = false;
		for (int count = 0; count < EVEN_DIGITS.length && !isEven; count++) 
			if (x == EVEN_DIGITS[count])
				isEven = true;
	}
	public int getIntVal() 
	{
		return DIGIT_VALS[index];
	}
	public char getCharVal() 
	{
		return DIGITS[index];
	}
	public int getIndex() 
	{
		return index;
	}
	public boolean getIsEven() 
	{
		return isEven;
	}
	public String toString() 
	{
		return "" + DIGITS[index];
	}
	public Digit add(Digit digit) 
	{
		int newIndex = getIndex() + digit.getIndex();
		return new Digit(newIndex);
	}
	public Digit subtract(Digit digit) 
	{
		int newIndex = getIndex() - digit.getIndex();
		return new Digit(newIndex);
	}
	public boolean equals(Digit dig) 
	{
		boolean result = false;
		if (this.getIntVal() == dig.getIntVal() && this.getCharVal() == dig.getCharVal() && this.getIndex() == this.getIndex()) 
			result = true;
		return result;
	}
	public boolean lessThan(Digit dig) 
	{
		boolean isLessThan = false;
		if (getIntVal() < dig.getIntVal()) 
			isLessThan = true;
		return isLessThan;
	}
	public boolean greaterThan(Digit dig) 
	{
		boolean isGreaterThan = false;
		if (getIntVal() > dig.getIntVal()) 
			isGreaterThan = true;
		return isGreaterThan;
	}
}
