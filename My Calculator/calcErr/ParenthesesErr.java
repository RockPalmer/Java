package calcErr;

public class ParenthesesErr 
{
	private static final String[] INVLAID_PARENTHESES_USE = {"Invalid use of parentheses",""};
	private static final String[] EMPTY_PARENTHESES = {"All expressions surrounded by parentheses must contain values",""};
	public static String[] illegalParUseErr(String expression) 
	{
		/* Stores whether the expression being checked uses parentheses in a valid way */
		boolean isValid = true;
		String[] errString = new String[2];
		
		/* Counts the total number of '(' characters used in the expression */
		int openParenthesesCount = 0;
		
		/* Goes through the expression char by char and counts the number of both '(' and ')' characters in the expression */
		for (int count = 0; count < expression.length() && isValid; count++) 
			if (expression.charAt(count) == '(') 
				openParenthesesCount++;
			else if (expression.charAt(count) == ')') 
				openParenthesesCount--;
			if (openParenthesesCount < 0) 
				isValid = false;
		
		/* If there is an equal number of both '(' and ')' characters in the expression, the expression is set to valid,
		 * and the error message is hidden */
		if (openParenthesesCount == 0) 
		{
			isValid = true;
			
			for (int count = 0; count < errString.length; count++) 
				errString[count] = "";
		}
		/* If there is not the same amount of '(' characters as ')' characters, the expression is set to not valid, the 
		 * label that stores the error message is filled with the proper message to tell the user what went wrong,
		 * and the error message is set to visible */
		else 
		{
			isValid = false;
			
			for (int count = 0; count < errString.length; count++) 
				errString[count] = INVLAID_PARENTHESES_USE[count];
		}
		
		return errString;
	}
	public static String[] emptyParErr(String expression) 
	{
		boolean isValid = true;
		String[] errString = new String[2];
		
		for (int count = 0; count < expression.length() - 1 && isValid; count++) 
			if (expression.charAt(count) == '(') 
				if (expression.charAt(count + 1) == ')') 
					isValid = false;
		
		if (isValid) 
			for (int count = 0; count < errString.length; count++) 
				errString[count] = "";
		else
			for (int count = 0; count < errString.length; count++) 
				errString[count] = EMPTY_PARENTHESES[count];
		
		return errString;
	}
}
