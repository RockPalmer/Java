package calcErr;

public class InvalidNumErr 
{
	private static final String[] INVALID_NUM = {"One of the terms in the entered expression is not a valid number",""};
	public static String[] isValidNumber(String num) 
	{
		String[] errString = new String[2];
		try
		{
			Double.parseDouble(num);
			errString[0] = "";
			errString[1] = "";
		}
		catch (NumberFormatException e)
		{
			for (int count = 0; count < errString.length; count++) 
				errString[count] = INVALID_NUM[count];		
		}
		return errString;
	}
}
