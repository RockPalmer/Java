package calcErr;

public class InvalidExpressionErr 
{
	private static final String[] INVALID_CHARS = {"You must only use characters","contained in the keypad"};
	private static final String[] INVALID_EXPRESSION = {"The expression inputted is not","written in the proper format"};
	private static final String[] DIVIDED_BY_ZERO = {"You cannot divide by zero",""};
	private static final String[] INVALID_ROOT = {" root of ","is not a real number"};
	public static String[] isValidOrder(char[] validOperations,  char[] exceptionOps, char[] validNumChars, String expression) 
	{
		String[] errorString = new String[2];
		boolean isValid = true;
		// Goes through the expression given as input char by char
		for (int count1 = 0; count1 < expression.length() && isValid; count1++)
		{
			boolean isOperator = false;
			// For each char in the expression, it goes through the possible valid operators
			for (int count2 = 0; count2 < validOperations.length && !isOperator; count2++) 
				if (expression.charAt(count1) == validOperations[count2]) 
					isOperator = true;
			// If the first char in the expression is an operator
			if (isOperator)
				if (count1 == 0 || count1 == expression.length() - 1) 
				{
					boolean isException = false;
					// If the operator isn't one that can stand on its own, the expression is not valid
					for (int count3 = 0; count3 < exceptionOps.length; count3++) 
						if (expression.charAt(count1) == exceptionOps[count3]) 
							isException = true;
					if (!isException)
						isValid = false;
				}
				else 
				{
					boolean nextIsOperator = false;
					// Checks if the next char in the expression is also an operator
					for (int count2 = 0; count2 < validOperations.length && !nextIsOperator; count2++) 
						if (expression.charAt(count1 + 1) == validOperations[count2]) 
							nextIsOperator = true;
					// If the next char in the expression is an operator
					if (nextIsOperator) 
					{
						boolean isException = false;
						
						// If the operator isn't one that can stand on its own, the expression is not valid
						for (int count3 = 0; count3 < exceptionOps.length; count3++) 
							if (expression.charAt(count1 + 1) == exceptionOps[count3]) 
								isException = true;
						if (!isException)
							isValid = false;
					}
					else 
					{
						boolean isNumChar = false;
						
						// Check if the designated char is a number character
						for (int count2 = 0; count2 < validNumChars.length && !isNumChar; count2++) 
							if (expression.charAt(count1) == validNumChars[count2]) 
								isNumChar = true;
						if (isNumChar) 
							isValid = true;
					}
				}
		}
		
		/* If it comes out as invalid, adjust the error message */
		if (isValid) 
			for (int count = 0; count < errorString.length; count++) 
				errorString[count] = "";
		else
			for (int count = 0; count < errorString.length; count++) 
				errorString[count] = INVALID_EXPRESSION[count];	
		
		return errorString;
	}
	public static String[] hasValidChars(char[] validChars, String[] validStrings, String expression) 
	{
		String[] errString = new String[2];
		boolean isValid = true;
		/* Goes through the expression char by char */
		for (int count1 = 0; count1 < expression.length() && isValid; count1++) 
		{
			boolean isAMatch = false;
			/* For each char, it goes through the list of valid chars */
			for (int count2 = 0; count2 < validChars.length && !isAMatch; count2++) 
				/* If the char in the expression is equal to one of the chars in the valid list of chars,
				 * it tells the program that it has found a match */
				if (expression.charAt(count1) == validChars[count2])
					isAMatch = true;
			/* If it does not find a match, the expression is considered invalid */
			if (!isAMatch)
			{
				/* The program contains invalid characters, the program checks if any of those characters are part of the
				 * list of possible valid strings */
				String possibleString = "";
				boolean isPossibleMatch = true;
				for (int count2 = 0; count2 < validStrings.length && isPossibleMatch; count2++) 
					for (int count3 = count1; count3 < validStrings[count2].length() && count3 < expression.length() && isPossibleMatch; count3++) 
					{
						possibleString += expression.charAt(count3);
						if (!possibleString.equals(validStrings[count2].substring(0, count3 - count1 + 1))) 
							isPossibleMatch = false;
						else if (count3 == expression.length() - 1 && !possibleString.equals(validStrings[count2])) 
						{
							System.out.println(1);
							isPossibleMatch = false;
						}
					}
				if (!isPossibleMatch) 
					isValid = false;
			}
		}
		
		/* If it comes out as invalid, adjust the error message */
		if (isValid) 
			for (int count = 0; count < errString.length; count++) 
				errString[count] = "";
		else 
			for (int count = 0; count < errString.length; count++) 
				errString[count] = INVALID_CHARS[count];		
		return errString;
	}
	public static String[] divideByZero(char divSymb, char opChar, double num) 
	{
		boolean isValid = true;
		String[] errString = new String[2];
		if (opChar == divSymb && num == 0) 
			isValid = false;
		
		/* If it comes out as invalid, adjust the error message */
		if (isValid) 
			for (int count = 0; count < errString.length; count++) 
				errString[count] = "";
		else 
			for (int count = 0; count < errString.length; count++) 
				errString[count] = DIVIDED_BY_ZERO[count];		
		return errString;
	}
	public static String[] isValidRoot(double numberRoot, double numRootOf) 
	{
		boolean isValid = true;
		String[] errString = new String[2];
		if (numRootOf < 0)
		{
			double posNumRootOf = -1 * numRootOf;
			double possiblePosResult = Math.pow(posNumRootOf, numberRoot);
			double possibleNegResult = -1 * possiblePosResult;
			if (Math.pow(possibleNegResult, 1.0/numberRoot) != numRootOf) 
				isValid = false;
			if (Math.pow(possibleNegResult, 1.0/numberRoot) == Math.pow(possiblePosResult, 1.0/numberRoot)) 
				isValid = false;
		}
		
		/* If it comes out as invalid, adjust the error message */
		if (isValid) 
			for (int count = 0; count < errString.length; count++) 
				errString[count] = "";
		else 
		{
			errString[0] = 1.0/numberRoot + INVALID_ROOT[0] + numRootOf;
			errString[1] = INVALID_ROOT[1];
		}
		return errString;
	}
}
