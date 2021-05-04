package calcNum;

public class MultiDigNum
{
	private static final char NEGATIVE_SIGN = '-';
	private static final char DECIMAL = '.';
	private int digitsToRound = 12;
	private boolean isNegative;
	private boolean isInteger;
	private boolean isEven;
	private int decimalIndex;
	private String numString;
	private int[] span = new int[2];
	private Digit[] digits;
	private char[] characters;
	public MultiDigNum()
	{
		setNumString("0.0");
	}
	public MultiDigNum(String num)
	{
		setNumString(num);
	}
	public MultiDigNum(MultiDigNum num) 
	{
		setNumString(num.getNumString());
	}
	public void setAsEqualTo(MultiDigNum num) 
	{
		setNumString(num.getNumString());
	}
	public boolean getIsNegative()
	{
		return isNegative;
	}
	public void setIsNegative(boolean isNeg)
	{
		/* Set isNegative value */
		boolean posToNeg = false;
		if (isNeg) 
			if(!getIsNegative())
			{
				this.isNegative = isNeg;
				posToNeg = true;
			}
		else 
			if (getIsNegative())
			{
				this.isNegative = isNeg;
				posToNeg = false;
			}
		
		/* Set character values */
		char[] chars;
		if (posToNeg)
		{
			chars  = new char[characters.length + 1];
			chars[0] = NEGATIVE_SIGN;
			for (int count = 1; count < chars.length; count++) 
				chars[count] = characters[count - 1];
		}
		else 
		{
			chars = new char[characters.length - 1];
			for (int count = 1; count < characters.length; count++) 
				chars[count - 1] = characters[count];
		}
		/* Set the rest of the values */
		setCharacters(chars);
	}
	public char[] getCharacters() 
	{
		return characters;
	}
	public void setCharacters(char[] chars) 
	{
		/* Set numString */
		String num = "";
		for (int count = 0; count < chars.length; count++) 
			num += chars[count];
		
		/* Set the rest of the variables */
		setNumString(num);
	}
	public String getNumString()
	{
		return numString;
	}
	public void setNumString(String num) 
	{	
		
		/* Set the isNegative variable */
		int beginNumber = -1;
		if (num.charAt(0) == NEGATIVE_SIGN) 
		{
			isNegative = true;
			beginNumber = 1;
		}
		else 
		{
			isNegative = false;
			beginNumber = 0;
		}
		
		/* Check if it has a decimal */
		boolean hasDecimal = false;
		int count = -1;
		for (count = beginNumber; count < num.length() && !hasDecimal; count++) 
			if (num.charAt(count) == DECIMAL) 
				hasDecimal = true;
		if (hasDecimal) 
			if (count == 1) 
				num = '0' + num;
			else if (count == num.length()) 
				num += '0';
		else
			num = num + DECIMAL + '0';
		
		/* Set the value of numString */
		numString = num;
		
		/* Set character values */
		characters = new char[numString.length()];
		for (count = 0; count < characters.length; count++) 
			characters[count] = numString.charAt(count);
		
		/* Set the Digit values */
		int digitStartCount = -1;
		if (numString.charAt(0) == NEGATIVE_SIGN) 
		{
			digitStartCount = 1;
			digits = new Digit[numString.length() - 2];
		}
		else 
		{
			digitStartCount = 0;
			digits = new Digit[numString.length() - 1];
		}
		int digCount = 0;
		for (count = digitStartCount; count < numString.length(); count++) 
			if (numString.charAt(count) != DECIMAL)
			{
				digits[digCount] = new Digit(numString.charAt(count));
				digCount++;
			}
		
		/* Set the decimalIndex value */
		boolean indexIsFound = false;
		for (count = 0; count < numString.length(); count++) 
			if (numString.charAt(count) == DECIMAL) 
				if (!indexIsFound) 
					decimalIndex = count;
				else 
					throw new IllegalArgumentException("A MultiDigNum was called for with more thatn one decimal point in it");
		
		/* Set the span values */
		int spanStartCount = -1;
		if (numString.charAt(0) == NEGATIVE_SIGN) 
			spanStartCount = 1;
		else 
			spanStartCount = 0;
		int span1Size = 1;
		int span2Size = 1;
		
		for (count = spanStartCount; count < numString.length() && numString.charAt(count) != DECIMAL; count++)
			span1Size++;
		for (count = numString.length() - 1; count >= 0 && numString.charAt(count) != DECIMAL; count--) 
			span2Size++;
		if (span1Size > 0 && span2Size > 0) 
		{
			span[0] = span1Size - 1;
			span[1] = span2Size - 1;
		}
		else 
			throw new IllegalArgumentException("A number was created with a span of zero.");
		
		/* Set isInteger value */
		isInteger = true;
		for (count = numString.length() - 1; numString.charAt(count) != DECIMAL && isInteger; count--) 
			if (numString.charAt(count) != '0') 
				isInteger = false;

		/* Set isEven value */
		isEven = false;
		if(isInteger) 
		{
			Digit dig = new Digit(characters[decimalIndex - 1]);
			isEven = dig.getIsEven();
		}
	}
	public int getDecimalIndex() 
	{
		return decimalIndex;
	}
	public void setDecimalIndex(int decIndex) 
	{
		/* Set the new decimal index */
		int currIndex = -1;
		String num = "";
		for (int count = 0; count < numString.length(); count++) 
			if (numString.charAt(count) == DECIMAL) 
			{
				currIndex = count;
				count = numString.length() + 1;
			}
		num = numString.substring(0, currIndex) + numString.substring(currIndex + 1, numString.length());
		num = numString.substring(0, decIndex) + DECIMAL + numString.substring(decIndex, numString.length());
		
		/* Set the rest of the values */
		setNumString(num);
	}
	public int[] getSpan() 
	{
		return span;
	}
	public void setSpan(int[] newSpan) 
	{
		String num = "";
		if (newSpan[0] < this.getSpan()[0] || newSpan[1] < this.getSpan()[1]) 
			throw new IllegalArgumentException("The spans of " + newSpan[0] + " and " + newSpan[1] + " are too small for a MultiDigNum with spans of " + this.getSpan()[0] + " and " + this.getSpan()[1] + ".");
		
		/* Set the span variable */
		else 
		{
			int difference1 = newSpan[0] - span[0];
			int difference2 = newSpan[1] - span[1];
			String digitsToAddStart = "";
			String digitsToAddEnd = "";
			for (int count = 0; count < difference1; count++) 
				digitsToAddStart += '0';
			for (int count = 0; count < difference2; count++) 
				digitsToAddEnd += '0';
			
			if (isNegative)
				num = numString.substring(0, 1) + digitsToAddStart + numString.substring(1, numString.length()) + digitsToAddEnd;
			else 
				num = digitsToAddStart + numString.substring(0, numString.length()) + digitsToAddEnd;
		}
		
		/* Set the rest of the variables */
		setNumString(num);
	}
	public Digit[] getDigits() 
	{
		return digits;
	}
	public void setDigits(Digit[] digs) 
	{
		String num = "";
		if (span[0] + span[1] < digs.length) 
		{
			IllegalArgumentException error = new IllegalArgumentException("The specified number of digits, " + digs.length +", does not fit into the total span of " + (getSpan()[0] + getSpan()[1]) + " for the designated MultiDigNum.");
			throw error;
		}
		else 
		{	
			digits = digs;
			if (isNegative)
				num += "-";
			int count = 0;
			for (count = 0; count < span[0]; count++) 
				num += digs[count];
			num += ".";
			while (count < span[0] + span[1])
			{
				num += digs[count];
				count++;
			}
		}
		
		setNumString(num);
	}
	public boolean getIsInteger() 
	{
		return isInteger;
	}
	public boolean getIsEven() 
	{
		return isEven;
	}
	private static String removeExtraZeros(String num) 
	{
		int decIndex = -1;
		for (int count = 0; count < num.length(); count++) 
			if (num.charAt(count) == DECIMAL)
			{
				decIndex = count;
				count = num.length() + 1;
			}
		/* remove extra zeros at the end */
		boolean hasExtraZeros = true;
		for (int count1 = decIndex + 1; count1 < num.length() && hasExtraZeros; count1++) 
			if (num.charAt(count1) != '0')
				hasExtraZeros = false;
		if (hasExtraZeros)
			num = num.substring(0, decIndex + 2);
		
		/* remove unnecessary zeros on the front */
		while (num.charAt(0) == '0' && num.charAt(1) != DECIMAL)
			num = num.substring(1, num.length());
		
		return num;
	}
	public boolean greaterThan(MultiDigNum num)
	{
		boolean isGreater = false;
		boolean isPosGreater = true;
		
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		
		int[] span1 = num1.getSpan();
		int[] span2 = num2.getSpan();
		int[] jointSpan = new int[2];
		
		if (span1[0] >= span2[0]) 
			jointSpan[0] = span1[0];
		else if (span1[0] < span2[0]) 
			jointSpan[0] = span2[0];
		
		if (span1[1] >= span2[1]) 
			jointSpan[1] = span1[1];
		else if (span1[1] < span2[1]) 
			jointSpan[1] = span2[1];
		
		num1.setSpan(jointSpan);
		num2.setSpan(jointSpan);
		
		/* First check the signs of each number */
		if (num1.getIsNegative() && !num2.getIsNegative()) 
			isGreater = false;
		else if (!num1.getIsNegative() && num2.getIsNegative()) 
			isGreater = true;
		else if (num1.getIsNegative() && num2.getIsNegative()) 
		{
			num1.setIsNegative(false);
			num2.setIsNegative(false);
			isGreater = num1.lessThan(num2);
		}
		else if (!num1.getIsNegative() && !num2.getIsNegative()) 
			for (int count = 0; count < num1.getDigits().length && isPosGreater; count++)
				if (num1.getDigits()[count].equals(num2.getDigits()[count])) 
					isPosGreater = true;
				else if (num1.getDigits()[count].lessThan(num2.getDigits()[count])) 
				{
					isPosGreater = false;
					isGreater = false;
				}
				else if (num1.getDigits()[count].greaterThan(num2.getDigits()[count]))
				{
					isPosGreater = false;
					isGreater = true;
				}
				else 
					throw new IllegalArgumentException("Could not determine if the digit " + num1.getDigits()[count] + " in the number " + num1 + " was greater than the digit " + num2.getDigits()[count] + " in the number " + num2 + ".");
		else 
			throw new IllegalArgumentException("Could not determine the signs of each number in greaterThan method");
		
		num1 = null;
		num2 = null;
		return isGreater;
	}
	public boolean lessThan(MultiDigNum num) 
	{
		boolean isLess = false;
		boolean isPosLess = true;
		
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		
		int[] span1 = num1.getSpan();
		int[] span2 = num2.getSpan();
		int[] jointSpan = new int[2];
		
		if (span1[0] >= span2[0]) 
			jointSpan[0] = span1[0];
		else if (span1[0] < span2[0]) 
			jointSpan[0] = span2[0];
		
		if (span1[1] >= span2[1]) 
			jointSpan[1] = span1[1];
		else if (span1[1] < span2[1]) 
			jointSpan[1] = span2[1];
		
		num2.setSpan(jointSpan);
		num1.setSpan(jointSpan);
		
		/* First check the signs of each number */
		if (num1.getIsNegative() && !num2.getIsNegative())
			isLess = true;
		else if (!num1.getIsNegative() && num2.getIsNegative()) 
			isLess = false;
		else if (num1.getIsNegative() && num2.getIsNegative()) 
		{
			num1.setIsNegative(false);
			num2.setIsNegative(false);
			isLess = num1.greaterThan(num2);
		}
		else if (!num1.getIsNegative() && !num2.getIsNegative()) 
			/* Check which one is greater in magnitude */
			for (int count = 0; count < num1.getDigits().length && isPosLess; count++) 
				if (num1.getDigits()[count].equals(num2.getDigits()[count])) 
					isPosLess = true;
				else if (num1.getDigits()[count].lessThan(num2.getDigits()[count])) 
				{
					isPosLess = false;
					isLess = true;
				}
				else if (num1.getDigits()[count].greaterThan(num2.getDigits()[count])) 
				{
					isPosLess = false;
					isLess = false;
				}
				else 
					throw new IllegalArgumentException("Could not determine if the digit " + num1.getDigits()[count] + " in the number " + num1 + " was less than the digit " + num2.getDigits()[count] + " in the number " + num2 + ".");
		else 
			throw new IllegalArgumentException("Could not determine the signs of " + num1 + " and " + num2 + " in lessThan method");
		
		num1 = null;
		num2 = null;
		
		return isLess;
	}
	public boolean equals(MultiDigNum num) 
	{
		boolean isEqual = true;
		
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = num;
		
		int[] span1 = num1.getSpan();
		int[] span2 = num2.getSpan();
		int[] jointSpan = new int[2];
		
		if (span1[0] >= span2[0]) 
			jointSpan[0] = span1[0];
		else if (span1[0] < span2[0]) 
			jointSpan[0] = span2[0];
		
		if (span1[1] >= span2[1]) 
			jointSpan[1] = span1[1];
		else if (span1[1] < span2[1]) 
			jointSpan[1] = span2[1];
		
		num2.setSpan(jointSpan);
		num1.setSpan(jointSpan);
		
		for (int count = 0; count < num1.getDigits().length && isEqual; count++) 
			if (num1.getDigits()[count].equals(num2.getDigits()[count])) 
				isEqual = true;
			else if (!num1.getDigits()[count].equals(num2.getDigits()[count])) 
				isEqual = false;
			else 
				throw new IllegalArgumentException("Could not determine if the digit " + num1.getDigits()[count] + " in the number " + num1 + " was equal to the digit " + num2.getDigits()[count] + " in the number " + num2 + ".");
		
		return isEqual;
	}
	public String toString() 
	{
		return removeExtraZeros(getNumString());
	}
	public void round() 
	{
		final Digit FIVE = new Digit(5);
		final Digit ONE = new Digit(1);
		if (digits[digits.length - 1].lessThan(FIVE)) 
			setNumString(numString.substring(0, numString.length() - 2) + digits[digits.length - 1].subtract(ONE));
		else 
			setNumString(numString.substring(0, numString.length() - 2) + digits[digits.length - 1].add(ONE));
	}
	public MultiDigNum add(MultiDigNum num) 
	{	
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		MultiDigNum result = new MultiDigNum();
		boolean addOneToNext = false;
		Digit[] newDigs;
		Digit newDig;
		Digit oldDig;
		Digit oldOtherDig;
		int[] jointSpan;
		
		/* If both numbers are positive, add them like normal */
		if (!num1.getIsNegative() && !num2.getIsNegative()) 
		{
			jointSpan = new int[2];
			if (num1.getSpan()[0] >= num2.getSpan()[0]) 
				jointSpan[0] = num1.getSpan()[0] + 1;
			else if (num1.getSpan()[0] < num2.getSpan()[0]) 
				jointSpan[0] = num2.getSpan()[0] + 1;
			
			if (num1.getSpan()[1] >= num2.getSpan()[1])
				jointSpan[1] = num1.getSpan()[1];
			else if (num1.getSpan()[1] < num2.getSpan()[1]) 
				jointSpan[1] = num2.getSpan()[1];
			
			num2.setSpan(jointSpan);
			num1.setSpan(jointSpan);
			newDigs = new Digit[jointSpan[0] + jointSpan[1]];
			
			for (int count = num1.getDigits().length - 1; count >= 0; count--) 
			{
				try
				{
					newDig = num1.getDigits()[count];
					oldDig = num1.getDigits()[count];
					oldOtherDig = num2.getDigits()[count];
				}
				catch(ArrayIndexOutOfBoundsException e) 
				{
					System.out.println(num1.getNumString() + "|" + num2.getNumString());
					throw new IllegalArgumentException();
				}
				if (addOneToNext)
				{
					newDig = newDig.add(new Digit(1));
					addOneToNext = false;
				}
				newDig = newDig.add(num2.getDigits()[count]);
				
				if (oldDig.greaterThan(newDig) || oldOtherDig.greaterThan(newDig)) 
				{
					addOneToNext = true;
				}
				newDigs[count] = newDig;
			}
			
			result.setSpan(num1.getSpan());
			result.setDigits(newDigs);
			result.setNumString(removeExtraZeros(result.getNumString()));
		}
		else if (!num1.getIsNegative() && num2.getIsNegative()) 
		{
			num2.setIsNegative(false);
			if (num2.greaterThan(num1)) 
			{
				num2.subtract(num1);
				num2.setIsNegative(true);
				result.setNumString(num2.getNumString());
			}
			else if (num1.greaterThan(num2) || num1.equals(num2))
			{
				num1.subtract(num2);
				result.setNumString(num1.getNumString());
			}
		}
		else if (num1.getIsNegative() && !num2.getIsNegative()) 
		{
			num1.setIsNegative(false);
			if (num1.greaterThan(num2)) 
			{
				num1.subtract(num2);
				num1.setIsNegative(true);
				result.setNumString(num1.getNumString());
			}
			else if (num2.greaterThan(num1) || num2.equals(num1))
			{
				num2.subtract(num1);
				result.setNumString(num2.getNumString());
			}
		}
		else if (num1.getIsNegative() && num2.getIsNegative()) 
		{
			// DO -1 * [(-1 * NUM1) + (-1 * NUM2)]
			num1.setIsNegative(false);
			num2.setIsNegative(false);
			num1.add(num2);
			num1.setIsNegative(true);
			result.setNumString(num1.getNumString());
		}
		else 
		{
			throw new IllegalArgumentException("Could not determine the relative sizes of " + num1 + " and " + num2 + " when adding them.");
		}
		
		num1 = null;
		num2 = null;
		
		return result;
	}
	public MultiDigNum subtract(MultiDigNum num) 
	{
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		MultiDigNum result = new MultiDigNum();
		int[] jointSpan;
		Digit[] newDigs;
		boolean subtractOneFromNext;
		Digit newDig;
		Digit oldDig;
		Digit oldOtherDig;
		
		if (!num1.getIsNegative() && !num2.getIsNegative()) 
		{
			if (num2.greaterThan(num1)) 
			{
				num2.subtract(num1);
				num2.setIsNegative(true);
				result.setNumString(num2.getNumString());
			}
			else 
			{
				jointSpan = new int[2];
				if (num1.getSpan()[0] >= num2.getSpan()[0]) 
					jointSpan[0] = num1.getSpan()[0] + 1;
				else if (num1.getSpan()[0] < num2.getSpan()[0]) 
					jointSpan[0] = num2.getSpan()[0] + 1;
				
				if (num1.getSpan()[1] >= num2.getSpan()[1]) 
					jointSpan[1] = num1.getSpan()[1];
				else if (num1.getSpan()[1] < num2.getSpan()[1]) 
					jointSpan[1] = num2.getSpan()[1];
				
				num2.setSpan(jointSpan);
				num1.setSpan(jointSpan);
				newDigs = new Digit[jointSpan[0] + jointSpan[1]];
				
				subtractOneFromNext = false;
				
				for (int count = num1.getDigits().length - 1; count >= 0; count--) 
				{
					newDig = num1.getDigits()[count];
					oldDig = num1.getDigits()[count];
					oldOtherDig = num2.getDigits()[count];
					if (subtractOneFromNext)
					{
						newDig = newDig.subtract(new Digit(1));
						subtractOneFromNext = false;
					}
					newDig = newDig.subtract(num2.getDigits()[count]);
					
					if (oldOtherDig.greaterThan(oldDig))
						subtractOneFromNext = true;
					newDigs[count] = newDig;
				}
				result.setSpan(num1.getSpan());
				result.setDigits(newDigs);
				result.setNumString(removeExtraZeros(result.getNumString()));
			}
		}
		else if (!num1.getIsNegative() && num2.getIsNegative()) 
		{
			num2.setIsNegative(false);
			num1.add(num2);
			result.setNumString(num1.getNumString());
		}
		else if (num1.getIsNegative() && !num2.getIsNegative()) 
		{
			num1.setIsNegative(false);
			num1.add(num2);
			num1.setIsNegative(true);
			result.setNumString(num1.getNumString());
		}
		else if (num1.getIsNegative() && num2.getIsNegative()) 
		{
			num2.setIsNegative(false);
			num1.setIsNegative(false);
			if (num1.greaterThan(num2)) 
			{
				num1.subtract(num2);
				num1.setIsNegative(true);
				result.setNumString(num1.getNumString());
			}
			else 
			{
				num2.subtract(num1);
				result.setNumString(num2.getNumString());
			}
		}
		else 
			throw new IllegalArgumentException("Could not determine the relative sizes of " + num1 + " and " + num2 + " when subtracting them.");
		
		num1 = null;
		num2 = null;
		return result;
	}
	public MultiDigNum multiply(MultiDigNum num) 
	{
		/* Initialize copies of the 2 numbers in the operation to be played with */
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		MultiDigNum result = new MultiDigNum(numString);
		final MultiDigNum ONE = new MultiDigNum("1");
		
		if (num2.getIsInteger()) 
			if (!num1.getIsNegative() && !num2.getIsNegative()) 
				if (num2.getIsInteger()) 
				{
					MultiDigNum count = new MultiDigNum("1");
					while (count.lessThan(num2))
					{
						result.setAsEqualTo(result.add(num1));
						count.setAsEqualTo(count.add(new MultiDigNum("1")));
					}
				}
			else if (!num1.getIsNegative() && num2.getIsNegative())
			{
				num2.setIsNegative(false);
				result.setAsEqualTo(num1.multiply(num2));
				result.setIsNegative(true);
			}
			else if (num1.getIsNegative() && !num2.getIsNegative()) 
			{
				num1.setIsNegative(false);
				result.setAsEqualTo(num1.multiply(num2));
				result.setIsNegative(true);
			}
			else if (num1.getIsNegative() && num2.getIsNegative())
			{
				num2.setIsNegative(false);
				num1.setIsNegative(false);
				result.setAsEqualTo(num1.multiply(num2));
			}
			else
				throw new IllegalArgumentException("Could not determine if " + num1 + " or " + num2 + " were negative when multiplying.");
		/* If the number that we are multiplying our original number by is not an integer */
		else if (!num2.getIsInteger()) 
			result.setAsEqualTo(num1.divideBy(ONE.divideBy(num2)));
		else
			throw new IllegalArgumentException("Could not determine whether " + num2 + " was an integer or not ine the divideBy() method.");
		
		num1 = null;
		num2 = null;
		
		return result;
	}
	public MultiDigNum divideBy(MultiDigNum num)
	{
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		MultiDigNum result = new MultiDigNum();
		MultiDigNum numToCompare = new MultiDigNum();
		final MultiDigNum ZERO = new MultiDigNum();
		final MultiDigNum TEN = new MultiDigNum("10");
		MultiDigNum num2Multiple = new MultiDigNum();
		
		/* If the number doing the dividing (as opposed to the number being divided) is not an integer, 
		 * the program multiplies both numbers by ten and continues to do this until the number doing the 
		 * dividing is an integer */
		while (!num2.getIsInteger()) 
		{
			num1.setNumString(num1.multiply(TEN).getNumString());
			num2.setNumString(num2.multiply(TEN).getNumString());
		}
		
		if (!num1.getIsNegative() && !num2.getIsNegative()) 
		{
			int count = 0;
			
			numToCompare.setNumString(numToCompare.getNumString().substring(0, numToCompare.getNumString().length() - 2) + num1.getDigits()[count]);
			
			do 
			{
				num2Multiple.setNumString(ZERO.getNumString());
				/* Find the amount of times num2 goes into numToCompare, multiply that by num2 and 
				 * store the result in the num2Multiple variable */
				int count2 = 0;
				while (numToCompare.subtract(num2Multiple).greaterThan(num2) || numToCompare.subtract(num2Multiple).equals(num2)) 
				{
					num2Multiple.setNumString(num2Multiple.add(num2).getNumString());
					count2++;
				}
				
				/* add the number of times num2 can go into numToCompare to the result MultiDigNum */
				result.setNumString(result.getNumString().substring(0,result.getNumString().length() - 2) + count2);
				count++;
				numToCompare.setNumString(numToCompare.subtract(num2Multiple).getNumString().substring(0, numToCompare.subtract(numToCompare).getNumString().length() - 2) + num1.getDigits()[count]);
			} while (!numToCompare.equals(ZERO) && result.getDigits().length <= num1.getDigits().length);
			
			/* If the result is going to require more decimal places than are already provided by num1, the span
			 * of result is adjusted */
			int[] num1Span = new int[2];
			int firstCount = count;
			while (!numToCompare.equals(ZERO) && result.getSpan()[1] < digitsToRound) 
			{
				num2Multiple.setNumString(ZERO.getNumString());
				num1Span[0] = num1.getSpan()[0];
				num1Span[1] = num1.getSpan()[1] + 1;
				num1.setSpan(num1Span);
				
				/* Find the amount of times num2 goes into numToCompare, multiply that by num2 and 
				 * store the result in the num2Multiple variable */
				int count2 = 0;
				while (numToCompare.subtract(num2Multiple).greaterThan(num2) || numToCompare.subtract(num2Multiple).equals(num2)) 
				{
					num2Multiple.setNumString(num2Multiple.add(num2).getNumString());
					count2++;
				}
				
				/* add the number of times num2 can go into numToCompare to the result MultiDigNum */
				if (count == firstCount) 
					result.setNumString(result.getNumString().substring(0,result.getNumString().length() - 1) + count2);
				else 
					result.setNumString(result.getNumString().substring(0,result.getNumString().length()) + count2);
				count++;
				numToCompare.setNumString(numToCompare.subtract(num2Multiple).getNumString().substring(0, numToCompare.subtract(numToCompare).getNumString().length() - 2) + num1.getDigits()[count]);
			}
			if (!numToCompare.equals(ZERO)) 
				result.round();
		}
		else if (!num1.getIsNegative() && num2.getIsNegative()) 
		{
			num2.setIsNegative(false);
			result.setNumString(num1.divideBy(num2).getNumString());
			result.setIsNegative(true);
		}
		else if (num1.getIsNegative() && !num2.getIsNegative()) 
		{
			num1.setIsNegative(false);
			result.setNumString(num1.divideBy(num2).getNumString());
			result.setIsNegative(true);
		}
		else if (num1.getIsNegative() && num2.getIsNegative()) 
		{
			num1.setIsNegative(false);
			num2.setIsNegative(false);
			result.setNumString(num1.divideBy(num2).getNumString());
		}
		else 
			throw new IllegalArgumentException("Could not determine if " + num1 + " or " + num2 + " were negative when dividing.");
		
		result.setNumString(removeExtraZeros(result.getNumString()));
		
		return result;
	}
	public MultiDigNum toPowerOf(MultiDigNum num) 
	{
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		MultiDigNum result = new MultiDigNum("1");
		MultiDigNum count = new MultiDigNum();
		final MultiDigNum ONE = new MultiDigNum("1");
		
		if (num2.getIsInteger()) 
			if (!num2.getIsNegative()) 
				while (count.lessThan(num2)) 
				{
					result.setAsEqualTo(result.multiply(num1));
					count.setAsEqualTo(count.add(ONE));
				}
			else if (num2.getIsNegative()) 
			{
				while (count.lessThan(num2))
				{
					result.setAsEqualTo(result.multiply(num1));
					count.setAsEqualTo(count.add(ONE));
				}
				result.setAsEqualTo(ONE.divideBy(result));
			}
			else
				throw new IllegalArgumentException("Could not determine if" + num1 + "was being put to the power of a positive or negative number.");
		
		return result;
	}
	public MultiDigNum root(MultiDigNum num) 
	{
		MultiDigNum num1 = new MultiDigNum(numString);
		MultiDigNum num2 = new MultiDigNum(num.getNumString());
		MultiDigNum result = new MultiDigNum("1");
		
		// Unfinished
		
		return result;
	}
}
	
