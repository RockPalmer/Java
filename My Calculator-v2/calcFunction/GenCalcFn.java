package calcFunction;

import java.util.ArrayList;

/**
 * This class, given an expression to solve in the form of a String, will return the answer to the expression to the user.
 * 
 * Through the use of several methods, this class takes in the expression as a String input and, through standard rules of order
 * of operations, determines the first set of numbers and operators to work out. The program then takes the result of these
 * operations and replaces them in the expression with their corresponding solution.
 * 
 * The program continues this process until there is only number characters contained in the expression. If this is the case, it
 * considers the expression as solved and returns it to the user.
 * 
 * Because this class solves each expression by placing the numbers and operators contained therein into their respective arrays
 * and then combining them, if the user misuses an operator (i.e. using two operators in a row or opening a set of parentheses 
 * without closing them), the program will throw an IndexOutOfBoundsException to indicate this.
 * 
 * Also, if the expression displays correct usage of operators but does not use real numbers, the program will throw a 
 * NumberFormatException to indicate this.
 * 
 * @see the application.GenCalcGUI class for the implementation of this class.
 *
 */
public class GenCalcFn
{
	private static String expression;
	private static ArrayList<Character> operators = new ArrayList<Character>();
	private static ArrayList<Double> numbers = new ArrayList<Double>();
	private static boolean[] chars;
	private static final int[] SECTION_TO_SOLVE = new int[2];
	private static double solution = 0;
	// These constants represent all valid operators and numbers that are recognized by this program.
	private static final char[] VALID_OPERATIONS = {'+','\u00AD','\u02E3','\u00F7','^','\u221A','\u2022','/'};
	private static final char[] VALID_NUMS = {'0','1','2','3','4','5','6','7','8','9','.','-','\u03C0','E'};
	/* Allows the class to interact with outside classes. 
	 * This is the only public method of the class. */
	public static String interact(String input)
	{	
		String result = "";
		expression = input;
		// If there are any operators and/or numbers that are implied to exist in
		// the expression they are added here.
		addImpliedTerms();
		
		// Once this is done, the program goes through its normal steps to solve
		// the expression.
		solveExpression();
		result = expression;
		
		return result;
	}
	// If there are any operators and/or numbers that are implied to exist in
	// the expression (i.e. 2(3+4) is technically 2*(3+4)), the program goes
	// through and adds them in so that the expression can be read and parsed.
	private static void addImpliedTerms()
	{
		/* Add any implied multiplication symbols */
		for (int count = 0; count < expression.length(); count++) 
		{
			if (isNumChar(expression.charAt(count)))
			{
				if (count < expression.length() - 1) 
				{
					if (expression.charAt(count + 1) == '(') 
					{
						System.out.println(1);
						expression = expression.substring(0, count + 1) + "\u02E3" + expression.substring(count + 1, expression.length());
					}
				}
			}
		}
		/* Add 2 before any time the root character is used without a number to specify the kind of
		 * root being taken */
		for (int count = 0; count < expression.length(); count++) 
		{
			if (expression.charAt(count) == '\u221A')
			{
				if (count > 0) 
				{
					if (!isNumChar(expression.charAt(count - 1))) 
					{
						expression = expression.substring(0, count) + "2" + expression.substring(count, expression.length());
					}
				}
				else 
				{
					expression = expression.substring(0, count) + "2" + expression.substring(count, expression.length());
				}
			}
		}
		/* Wherever it finds a number directly followed by the pi symbol, the program will 
		 * place a multiplication symbol between the number and the pi symbol */
		for (int count = 0; count < expression.length(); count++) 
		{
			if (expression.charAt(count) == '\u03C0') 
			{
				if (count > 0) 
				{
					if (isNumChar(expression.charAt(count - 1))) 
					{
						expression = expression.substring(0, count) + '\u02E3' + expression.substring(count, expression.length());
					}
				}
			}
		}
		/* Wherever there is a pi symbol the program replaces it with the actual value of pi */
		for (int count = 0; count < expression.length(); count++) 
		{
			if (expression.charAt(count) == '\u03C0') 
			{
				expression = expression.substring(0, count) + Math.PI + expression.substring(count + 1, expression.length());
			}
		}
	}
	/* This runs through various functions to actually solve the expression.
	 * 
	 * This is the step-by-step process the program goes through until the
	 * expression is solved.
	 */
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
	/* Sets the first section of the expression to solve by using standard rules of order of operations */
	private static void setSectionToSolve()
	{
		// First checks for parentheses. If there are, it finds the first set of parentheses it must solve.
		if (containsParentheses(expression))
		{
			findFirstParToSolve();
		}
		else 
		{
			SECTION_TO_SOLVE[0] = 0;
			SECTION_TO_SOLVE[1] = expression.length() - 1;
		}
		
		// If the section it is solving does not contain parentheses, it then looks for any exponent operators
		// (i.e. any power or root operators). This is what the POrRIsFound and locationOfPOrR variables keep track of.
		boolean POrRIsFound = false;
		int locationOfPOrR = 0;
		for (int count = SECTION_TO_SOLVE[0]; count <= SECTION_TO_SOLVE[1] && !POrRIsFound; count++) 
		{
			if (expression.charAt(count) == '^' || expression.charAt(count) == '\u221A')
			{
				POrRIsFound = true;
				locationOfPOrR = count;
			}
		}
		
		// If it finds a power or root operator in the section it is solving, it sets that location as the new section
		// to solve
		if (POrRIsFound)
		{
			for (int count = locationOfPOrR - 1; count >= 0 && isNumChar(expression.charAt(count)); count--) 
			{
				SECTION_TO_SOLVE[0] = count;
			}
			for (int count = locationOfPOrR + 1; count <= expression.length() - 1 && isNumChar(expression.charAt(count)); count++) 
			{
				SECTION_TO_SOLVE[1] = count;
			}
		}
		else
		{
			// If the section it is solving contains no parentheses, exponents, or roots, the program then searches the specified
			// section for any multiplication or division operators. This is what the MOrDIsFound and locationOfMOrD variables 
			// keep track of.
			boolean MOrDIsFound = false;
			int locationOfMOrD = 0;
			for (int count = SECTION_TO_SOLVE[0]; count <= SECTION_TO_SOLVE[1] && !MOrDIsFound; count++) 
			{
				if (expression.charAt(count) == '\u02E3' || expression.charAt(count) == '\u00F7')
				{
					MOrDIsFound = true;
					locationOfMOrD = count;
				}
			}
			// If it finds a multiplication or division operator in the section it is solving, it sets that location as the new section
			// to solve.
			if (MOrDIsFound) 
			{
				for (int count = locationOfMOrD - 1; count >= 0 && isNumChar(expression.charAt(count)); count--) 
				{
					SECTION_TO_SOLVE[0] = count;
				}
				for (int count = locationOfMOrD + 1; count <= expression.length() - 1 && isNumChar(expression.charAt(count)); count++) 
				{
					SECTION_TO_SOLVE[1] = count;
				}
			}
		}
		/* Keep in mind, this method does not worry about looking for addition or subtraction even though this is also an
		 * important part of the order of operations because if it does not find any of the previous operations, it will
		 * automatically solve any addition or subtraction found in the section it is solving. */
	}
	/* Reads the given expression/section of the expression and creates an array of boolean terms with each
	 * index corresponding to each character in the String. If the character is a number character, it stores
	 * 'true' if it is an operator, it stores 'false' */
	private static void parseSectionToSolve()
	{
		int beginParse = SECTION_TO_SOLVE[0];
		int endParse = SECTION_TO_SOLVE[1];
		if (containsParentheses(expression.substring(SECTION_TO_SOLVE[0], SECTION_TO_SOLVE[1] + 1))) 
		{
			beginParse++;
			endParse--;
		}
		String line = expression.substring(beginParse, endParse + 1);
		chars = new boolean[line.length()];
		for (int count = 0; count < line.length(); count++)
		{
			if (isNumChar(line.charAt(count)))
			{
				chars[count] = true;
			}
			else if (isOpChar(line.charAt(count)))
			{
				chars[count] = false;
			}
		}
	}
	/* This method stores each character in the expression or section of the expression into the numbers or operators ArrayList 
	 * based upon the boolean array just created by the previous method. */
	private static void storeValuesOfSection() 
	{
		int beginStore = SECTION_TO_SOLVE[0];
		int endStore = SECTION_TO_SOLVE[1];
		if (containsParentheses(expression.substring(SECTION_TO_SOLVE[0], SECTION_TO_SOLVE[1] + 1))) 
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
			{
				if (numIsStored)
				{	
					numbers.add(Double.parseDouble(newNum));
					numIsStored = false;
				}
				operators.add(line.charAt(count));
				newNum = "";
			}
		}
		if (numIsStored)
		{
			numbers.add(Double.parseDouble(newNum));
			newNum = "";
			numIsStored = false;
		}
	}
	/* Solves the expression/section of the expression based upon the array lists that 
	 * store this data */
	private static void solveSection()
	{
		solution = numbers.get(0);
		for (int count = 1; count < numbers.size(); count++) 
		{
			switch (operators.get(count - 1))
			{
			case '+':
				solution += numbers.get(count);
				break;
			case '\u00AD':
				solution -= numbers.get(count);
				break;
			case '\u02E3':
				solution *= numbers.get(count);
				break;
			case '\u2022':
				solution *= numbers.get(count);
				break;
			case '\u00F7':
			case '/':
				solution /= numbers.get(count);
				break;
			case '^':
				solution = Math.pow(solution, numbers.get(count));
				break;
			case '\u221A':
				if (numbers.get(count) < 0) 
				{
					if (Math.pow(-1 * Math.pow(-1 * numbers.get(count), 1.0/solution), solution) == numbers.get(count)) 
					{
						solution = -1 * Math.pow(-1 * numbers.get(count), 1.0/solution);
					}
					else 
					{
						throw new IndexOutOfBoundsException();
					}
				}
				else
				{
					solution = Math.pow(numbers.get(count), 1.0/solution);
				}
				break;
			default:
				solution = 0;
			}
		}
	}
	/* Replaces any solved sections of the expression with their solved values */
	private static void simplifyExpression()
	{
		String newExpression = "";
		int beginCut = SECTION_TO_SOLVE[0];
		int endCut = SECTION_TO_SOLVE[1];
		if (beginCut == 0 && endCut == expression.length() - 1)
		{
			newExpression = "" + solution;
		}
		else if (beginCut != 0 && endCut != expression.length() - 1)
		{
			newExpression = expression.substring(0, beginCut) + solution + expression.substring(endCut + 1, expression.length());
		}
		else if (beginCut == 0) 
		{
			newExpression = solution + expression.substring(endCut + 1, expression.length());
		}
		else
		{
			newExpression = expression.substring(0, beginCut) + solution;
		}
		expression = newExpression;
	}
	/* Checks if the given expression contains any parentheses */
	private static boolean containsParentheses(String line)
	{
		boolean hasParentheses = false;
		for (int count = 0; count < line.length() && !hasParentheses; count++) 
		{
			if (line.charAt(count) == '(' || line.charAt(count) == ')') 
			{
				hasParentheses = true;
			}
		}
		return hasParentheses;
	}
	/* Checks if a the given character is a number character. */
	private static boolean isNumChar(char c) 
	{
		boolean isThisChar = false;
		for (int count = 0; count < VALID_NUMS.length && !isThisChar; count++)
		{
			if (c == VALID_NUMS[count]) 
			{
				isThisChar = true;
			}
		}
		return isThisChar;
	}
	/* Checks if a given character is an operator */
	private static boolean isOpChar(char c) 
	{
		boolean isThisChar = false;
		for (int count = 0; count < VALID_OPERATIONS.length && !isThisChar; count++)
		{
			if (c == VALID_OPERATIONS[count])
			{
				isThisChar = true;
			}
		}
		return isThisChar;
	}
	/* Checks if the expression is solved or not by checking if there are still operators in the expression */
	private static boolean isSolved() 
	{
		boolean hasOnlyNumChars = true;
		for (int count = 0; count < expression.length() && hasOnlyNumChars; count++) 
		{
			if(!isNumChar(expression.charAt(count))) 
			{
				hasOnlyNumChars = false;
			}
		}
		return hasOnlyNumChars;
	}
	/* Finds the first set of parentheses to solve in the expression (i.e. the first set of parentheses it finds that
	 * do not contain a nested set of parentheses inside them)*/
	private static void findFirstParToSolve() 
	{
		boolean parIsFound = false;
		for (int count = 0; count < expression.length() && !parIsFound; count++) 
		{
			if (expression.charAt(count) == '(') 
			{
				SECTION_TO_SOLVE[0] = count;
			}
			else if (expression.charAt(count) == ')') 
			{
				SECTION_TO_SOLVE[1] = count;
				parIsFound = true;
			}
		}
	}
	
	
	
	
	
	
}