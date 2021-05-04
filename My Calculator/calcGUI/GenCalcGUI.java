package calcGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import calcFn.GenCalcFn;

public class GenCalcGUI extends BaseGUI implements ActionListener
{
	protected static final char[] VALID_CHARS = {'0','1','2','3','4','5','6','7','8','9','\u00AD','+','.','-','(',')',
			'\u02E3','\u00F7','^','\u221A', '\u03C0','\u2022','/','E'};
	protected static final String[] VALID_STRINGS = {"cos"};
	protected static final char[] FREE_FLOATING_OPS = {'\u221A'};
	protected static final char[] VALID_OPERATIONS = {'+','\u00AD','\u02E3','\u00F7','^','\u221A','\u2022','/'};
	protected static final char[] VALID_NUMS = {'0','1','2','3','4','5','6','7','8','9','.','-','\u03C0','E'};
	private static final String[][] BUTTON_LABELS = 
		{
			{"1","2","3","+","\u00AD"},
			{"4","5","6","^","\u221A"},
			{"7","8","9","\u02E3","\u00F7"},
			{"CLEAR","0","\u2190","(",")"},
			{"E","( - )",".","\u03C0","="}
		};
	protected static JTextField answer;
	private static JButton[][] buttons = 
		{
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()}
		};
	private final static int KEYBOARD_PLACEMENT = 150;
	private final static int BUTTON_HEIGHT = 52;
	private final static int BUTTON_WIDTH = 82;
	private static void buildGUI() 
	{
		/* Clear the panel */
		panel.removeAll();
		panel.repaint();
		
		setErrWarning();
		buildButtons();
		buildAnswerField();
	}
	private static void buildButtons()
	{	
		/* Add the 'Main Menu' button to the panel */
		setMenuButton();
		
		/* Set the attributes of the JButtons in the buttons array */
		for (int count1 = 0; count1 < buttons.length; count1++)
			for (int count2 = 0; count2 < buttons[count1].length; count2++)
			{
				buttons[count1][count2].setText(BUTTON_LABELS[count1][count2]);
				buttons[count1][count2].setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
				buttons[count1][count2].setLocation((int)(COLUMN_COORDS[2] - (BUTTON_WIDTH * (findMaxRowLength(buttons)/2)) + (BUTTON_WIDTH * count2)), KEYBOARD_PLACEMENT + (BUTTON_HEIGHT * count1));
				panel.add(buttons[count1][count2]);
			}
		
		/* Reset any action listeners on each button */
		for (int count1 = 0; count1 < buttons.length; count1++) 
			for (int count2 = 0; count2 < buttons[count1].length; count2++) 
			{
				buttons[count1][count2].removeActionListener(new ButtonHandler());
				buttons[count1][count2].addActionListener(new ButtonHandler());
			}
	}
	private static void buildAnswerField() 
	{
		answer = new JTextField();
		answer.setSize((int)(BUTTON_WIDTH * findMaxRowLength(buttons)), TEXT_FIELD_HEIGHT);
		answer.setLocation(COLUMN_COORDS[2] - (answer.getWidth())/2, 150 - answer.getHeight());
		panel.add(answer);
	}
	private static double findMaxRowLength(JButton[][] buttonArr) 
	{
		double maxLength = buttonArr[0].length;
		for (int count = 1; count < buttonArr.length; count++) 
			if (buttonArr[count].length > maxLength) 
				maxLength = buttonArr[count].length;
		return maxLength;
	}
	public void actionPerformed(ActionEvent arg0)
	{
		buildGUI();
	}
	private static class ButtonHandler implements ActionListener 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			String buttonName = ((JButton)(arg0.getSource())).getText();
			switch (buttonName) 
			{
			case "CLEAR":
				answer.setText("");
				break;
			case "=":
				if (calcErr.TextFieldErr.isFull(answer, errWarning))
				{
					String[] errorMessage = new String[3];
					errorMessage = GenCalcFn.interact(answer.getText(), VALID_CHARS, VALID_STRINGS, VALID_OPERATIONS, VALID_NUMS, FREE_FLOATING_OPS);
					answer.setText(errorMessage[0]);
					
					for (int count = 0; count < errWarning.length; count++) 
					{
						errWarning[count].setText(errorMessage[count + 1]);
						setComponentsSize(errWarning[count]);
						errWarning[count].setLocation(COLUMN_COORDS[2] - (errWarning[count].getWidth()/2), errWarning[count].getY());
						errWarning[count].setVisible(true);
					}
				}
				break;
			case "( - )":
				answer.setText(answer.getText() + '-');
				break;
			case "\u2190":
				if (!answer.getText().equals(""))
					answer.setText(answer.getText().substring(0,answer.getText().length() - 1));
				break;
			default:
				answer.setText(answer.getText() + buttonName);
				break;
			}
		}
	}
}
