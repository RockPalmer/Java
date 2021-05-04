package calcFn;

import java.util.ArrayList;

public class GenCalcFn
{
	private static String expression;
	private static ArrayList<Character> operators = new ArrayList<Character>();
	private static ArrayList<Double> numbers = new ArrayList<Double>();
	private static boolean[] chars;
	private static final int[] sectionToSolve = new int[2];
	private static double ANSWER = 0;
	private static final String[] errorString = new String[2];
	private static char[] valid_Chars;
	private static String[] valid_Strings;
	private static char[] valid_Operations;
	private static char[] valid_Nums;
	private static char[] exception_Ops;
	private static final IllegalArgumentException ERROR = new IllegalArgumentException();
	/* Allows the class to interact with outside classes */
	public static String[] interact(String input, char[] validChars, String[] validStrings, char[] validOperations, char[] validNums, char[] exceptionOps)
	{
		/* Set valid chars and strings the calculator will use to check if the expression is valid */
		
		valid_Chars = validChars;
		valid_Strings = validStrings;
		valid_Operations = validOperations;
		valid_Nums = validNums;
		exception_Ops = exceptionOps;
		
		String result = "";
		expression = input;
		
		try {
			errorString[0] = calcErr.InvalidExpressionErr.hasValidChars(valid_Chars, valid_Strings, expression)[0];
			errorString[1] = calcErr.InvalidExpressionErr.hasValidChars(valid_Chars, valid_Strings, expression)[1];

			if (clearedErrors()) 
			{
				errorString[0] = calcErr.InvalidExpressionErr.isValidOrder(valid_Operations, exception_Ops, valid_Nums, expression)[0];
				errorString[1] = calcErr.InvalidExpressionErr.isValidOrder(valid_Operations, exception_Ops, valid_Nums, expression)[1];

				if (clearedErrors())
				{
					addImpliedTerms();
					solveExpression();
					result = expression;
				}
				else 
					throw ERROR;
			}
			else
				throw ERROR;
		}
		catch (IllegalArgumentException ERROR) 
		{
			result = "";
		}
		
		/* Build the String array that will be returned to the GUI class */
		String[] results = {result, errorString[0],errorString[1]};	
		
		return results;
	}
	/* Run through various functions to actually solve the expression */
	private static void solveExpression()
	{
		do
		{
			setSectionToSolve();
			parseSectionToSolve();
			storeValuesOfSection();
			solveSection();
			simplifyExpression();
			numbers.removeAll(numbers);
			operators.removeAll(operators);
		} while (!isSolved());	
	}
	/* Checks if the given expression contains any parentheses */
	private static boolean containsParentheses(String line)
	{
		boolean hasParentheses = false;
		for (int count = 0; count < line.length() && !hasParentheses; count++) 
			if (line.charAt(count) == '(' || line.charAt(count) == ')') 
				hasParentheses = true;
		return hasParentheses;
	}
	/* Checks if a given character is a one of the characters characterized as number characters */
	private static boolean isNumChar(char c) 
	{
		boolean isThisChar = false;
		for (int count = 0; count < valid_Nums.length && !isThisChar; count++)
			if (c == valid_Nums[count]) 
				isThisChar = true;
		return isThisChar;
	}
	/* Checks if a given character is a one of the characters characterized as operational characters */
	private static boolean isOpChar(char c) 
	{
		boolean isThisChar = false;
		for (int count = 0; count < valid_Operations.length && !isThisChar; count++)
			if (c == valid_Operations[count])
				isThisChar = true;
		return isThisChar;
	}
	/* Checks if the expression is solved or not */
	private static boolean isSolved() 
	{
		boolean hasOnlyNumChars = true;
		for (int count = 0; count < expression.length() && hasOnlyNumChars; count++) 
			if(!isNumChar(expression.charAt(count))) 
				hasOnlyNumChars = false;
		return hasOnlyNumChars;
	}
	/* Finds the first set of parentheses to solve in the expression */
	private static void findFirstParToSolve() 
	{
		boolean parIsFound = false;
		for (int count = 0; count < expression.length() && !parIsFound; count++) 
			if (expression.charAt(count) == '(') 
				sectionToSolve[0] = count;
			else if (expression.charAt(count) == ')') 
			{
				sectionToSolve[1] = count;
				parIsFound = true;
			}
	}
	/* Sets the first section of the expression to solve */
	private static void setSectionToSolve()
	{
		if (containsParentheses(expression))
		{
			errorString[0] = calcErr.ParenthesesErr.illegalParUseErr(expression)[0];
			errorString[1] = calcErr.ParenthesesErr.illegalParUseErr(expression)[1];
			
			if (clearedErrors()) 
			{
				errorString[0] = calcErr.ParenthesesErr.emptyParErr(expression)[0];
				errorString[1] = calcErr.ParenthesesErr.emptyParErr(expression)[1];
				
				if (clearedErrors())
					findFirstParToSolve();
				else 
					throw ERROR;
			}
			else 
				throw ERROR;
		}
		else 
		{
			sectionToSolve[0] = 0;
			sectionToSolve[1] = expression.length() - 1;
		}
		boolean POrRIsFound = false;
		int locationOfPOrR = 0;
		/* Look for power or root symbols */
		for (int count = sectionToSolve[0]; count <= sectionToSolve[1] && !POrRIsFound; count++) 
			if (expression.charAt(count) == '^' || expression.charAt(count) == '\u221A')
			{
				POrRIsFound = true;
				locationOfPOrR = count;
			}
		if (POrRIsFound)
		{
			for (int count = locationOfPOrR - 1; count >= 0 && isNumChar(expression.charAt(count)); count--) 
				sectionToSolve[0] = count;
			for (int count = locationOfPOrR + 1; count <= expression.length() - 1 && isNumChar(expression.charAt(count)); count++) 
				sectionToSolve[1] = count;
		}
		else
		{
			boolean MOrDIsFound = false;
			int locationOfMOrD = 0;
			/* Look for multiplication or division symbols */
			for (int count = sectionToSolve[0]; count <= sectionToSolve[1] && !MOrDIsFound; count++) 
				if (expression.charAt(count) == '\u02E3' || expression.charAt(count) == '\u00F7')
				{
					MOrDIsFound = true;
					locationOfMOrD = count;
				}
			if (MOrDIsFound) 
				for (int count = locationOfMOrD - 1; count >= 0 && isNumChar(expression.charAt(count)); count--) 
					sectionToSolve[0] = count;
				for (int count = locationOfMOrD + 1; count <= expression.length() - 1 && isNumChar(expression.charAt(count)); count++) 
					sectionToSolve[1] = count;
		}
	}
	/* Replaces any solved sections of the expression with their solved values */
	private static void simplifyExpression()
	{
		String newExpression = "";
		int beginCut = sectionToSolve[0];
		int endCut = sectionToSolve[1];
		if (beginCut == 0 && endCut == expression.length() - 1)
			newExpression = "" + ANSWER;
		else if (beginCut != 0 && endCut != expression.length() - 1)
			newExpression = expression.substring(0, beginCut) + ANSWER + expression.substring(endCut + 1, expression.length());
		else if (beginCut == 0) 
			newExpression = ANSWER + expression.substring(endCut + 1, expression.length());
		else
			newExpression = expression.substring(0, beginCut) + ANSWER;
		expression = newExpression;
	}
	/* Reads the given expression/section of the expression and creates an array of boolean terms with each
	 * index corresponding to each character in the String. If the character is a number character, it stores
	 * 'true' if the character is a number character and 'false' if the character is an operational character */
	private static void parseSectionToSolve() 
	{
		int beginParse = sectionToSolve[0];
		int endParse = sectionToSolve[1];
		if (containsParentheses(expression.substring(sectionToSolve[0], sectionToSolve[1] + 1))) 
		{
			beginParse++;
			endParse--;
		}
		String line = expression.substring(beginParse, endParse + 1);
		chars = new boolean[line.length()];
		for (int count = 0; count < line.length(); count++)
			if (isNumChar(line.charAt(count)))
				chars[count] = true;
			else if (isOpChar(line.charAt(count)))
				chars[count] = false;
	}
	/* Store the values of the expression in their corresponding array lists based upon the boolean array just created */
	private static void storeValuesOfSection() 
	{
		int beginStore = sectionToSolve[0];
		int endStore = sectionToSolve[1];
		if (containsParentheses(expression.substring(sectionToSolve[0], sectionToSolve[1] + 1))) 
		{
			beginStore++;
			endStore--;
		}
		String line = expression.substring(beginStore, endStore + 1);
		String newNum = "";
		boolean numIsStored = false;
		for (int count = 0; count < chars.length; count++) 
		{
			if (chars[count] == true)
			{
				newNum += line.charAt(count);
				numIsStored = true;
			}
			else 
				if (numIsStored)
				{	
					errorString[0] = calcErr.InvalidNumErr.isValidNumber(newNum)[0];
					errorString[1] = calcErr.InvalidNumErr.isValidNumber(newNum)[1];
					
					if (clearedErrors())
					{
						numbers.add(Double.parseDouble(newNum));
						numIsStored = false;
					}
					else 
						throw ERROR;
				}
				operators.add(line.charAt(count));
				newNum = "";
		}
		if (numIsStored)
		{
			errorString[0] = calcErr.InvalidNumErr.isValidNumber(newNum)[0];
			errorString[1] = calcErr.InvalidNumErr.isValidNumber(newNum)[1];
			
			if (clearedErrors())
			{
				numbers.add(Double.parseDouble(newNum));
				newNum = "";
				numIsStored = false;
			}
			else 
				throw ERROR;
		}
	}
	/* If there are any numbers next to a set of parentheses, the program adds a multiplication symbol
	 * in between the parenthesis and number */
	private static void addImpliedTerms() 
	{
		/* Add any implied multiplication symbols */
		for (int count = 0; count < expression.length(); count++) 
			if (isNumChar(expression.charAt(count)))
				if (count < expression.length() - 1) 
					if (expression.charAt(count + 1) == '(') 
					{
						System.out.println(1);
						expression = expression.substring(0, count + 1) + "\u02E3" + expression.substring(count + 1, expression.length());
					}
		/* Add 2 before any time the square root of something is entered */
		for (int count = 0; count < expression.length(); count++) 
			if (expression.charAt(count) == '\u221A')
				if (count > 0) 
					if (!isNumChar(expression.charAt(count - 1))) 
						expression = expression.substring(0, count) + "2" + expression.substring(count, expression.length());
				else 
					expression = expression.substring(0, count) + "2" + expression.substring(count, expression.length());
		
		/* Wherever it finds a number directly followed by the pi symbol, the program will 
		 * place a multiplication symbol between the number and the pi symbol */
		for (int count = 0; count < expression.length(); count++) 
			if (expression.charAt(count) == '\u03C0') 
				if (count > 0) 
					if (isNumChar(expression.charAt(count - 1))) 
						expression = expression.substring(0, count) + '\u02E3' + expression.substring(count, expression.length());
		
		/* Wherever there is a pi symbol the program replaces it with the actual value of pi */
		for (int count = 0; count < expression.length(); count++) 
			if (expression.charAt(count) == '\u03C0') 
				expression = expression.substring(0, count) + Math.PI + expression.substring(count + 1, expression.length());
	}
	/* Solves the expression/section of the expression based upon the array lists that 
	 * store this data */
	private static void solveSection() 
	{
		ANSWER = numbers.get(0);
		for (int count = 1; count < numbers.size(); count++) 
			switch (operators.get(count - 1)) 
			{
			case '+':
				ANSWER += numbers.get(count);
				break;
			case '\u00AD':
				ANSWER -= numbers.get(count);
				break;
			case '\u02E3':
				ANSWER *= numbers.get(count);
				break;
			case '\u2022':
				ANSWER *= numbers.get(count);
				break;
			case '\u00F7':
			case '/':
				/* Checks if they are dividing by zero */
				errorString[0] = calcErr.InvalidExpressionErr.divideByZero('\u00F7', '\u00F7', numbers.get(count))[0];
				errorString[1] = calcErr.InvalidExpressionErr.divideByZero('\u00F7', '\u00F7', numbers.get(count))[1];
				
				if (clearedErrors()) 
					ANSWER /= numbers.get(count);
				else 
					throw ERROR;
				break;
			case '^':
				ANSWER = Math.pow(ANSWER, numbers.get(count));
				break;
			case '\u221A':
				errorString[0] = calcErr.InvalidExpressionErr.isValidRoot(1.0/ANSWER, numbers.get(count))[0];
				errorString[1] = calcErr.InvalidExpressionErr.isValidRoot(1.0/ANSWER, numbers.get(count))[1];
				
				if (clearedErrors())
					if (numbers.get(count) < 0) 
						ANSWER = -1 * Math.pow(-1 * numbers.get(count), 1.0/ANSWER);
					else
						ANSWER = Math.pow(numbers.get(count), 1.0/ANSWER);	
				else 
					throw ERROR;
				break;
			default:
				ANSWER = 0;
			}
	}
	private static boolean clearedErrors() 
	{
		boolean didClearError = false;
		if (errorString[0].equals("") && errorString[1].equals("")) 
			didClearError = true;
		return didClearError;
	}
}