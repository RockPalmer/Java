package calcGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calcFn.GenCalcFn;

public class GenCalcGUI extends BaseGUI
{
	private static final char[] VALID_CHARS = {'0','1','2','3','4','5','6','7','8','9','\u00AD','+','.','-','(',')',
			'\u02E3','\u00F7','^','\u221A', '\u03C0','\u2022','/','E'};
	private static final String[] VALID_STRINGS = {"cos"};
	private static final char[] FREE_FLOATING_OPS = {'\u221A'};
	private static final char[] VALID_OPERATIONS = {'+','\u00AD','\u02E3','\u00F7','^','\u221A','\u2022','/'};
	private static final char[] VALID_NUMS = {'0','1','2','3','4','5','6','7','8','9','.','-','\u03C0','E'};
	private static final String[][] BUTTON_LABELS = 
		{
			{"1","2","3","+","\u00AD"},
			{"4","5","6","^","\u221A"},
			{"7","8","9","\u02E3","\u00F7"},
			{"CLEAR","0","\u2190","(",")"},
			{"E","( - )",".","\u03C0","="}
		};
	private static JTextField answer;
	private static JButton[][] buttons = 
		{
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton()}
		};
	private JPanel mainPanel;
	public GenCalcGUI() 
	{
		//Initialize instance variables
		mainPanel = new JPanel();
		answer = new JTextField();
		JPanel entryPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel title = new JLabel();
		JButton menuButton = new JButton();
		
		//Build layout of mainPanel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(titlePanel, BorderLayout.PAGE_START);
		mainPanel.add(entryPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
		
		//Build layout of titlePanel
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		title.setText("General Calculator");
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(title);
		titlePanel.add(Box.createHorizontalGlue());
		
		//Build layout of bottomPanel
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
		menuButton.setText("Return to Menu");
		menuButton.addActionListener(main);
		bottomPanel.add(Box.createHorizontalGlue());
		bottomPanel.add(menuButton);
		bottomPanel.add(Box.createHorizontalGlue());
		
		//Build layout of entryPanel
		entryPanel.setLayout(new BorderLayout());
		entryPanel.add(buttonPanel, BorderLayout.CENTER);
		entryPanel.add(answer, BorderLayout.PAGE_START);
		
		//Build layout of buttonPanel
		buttonPanel.setLayout(new GridLayout(buttons[0].length, buttons.length));
		ButtonHandler handler = new ButtonHandler();
		for (int count1 = 0; count1 < buttons.length; count1++) 
			for (int count2 = 0; count2 < buttons[count1].length; count2++) 
			{
				buttons[count1][count2].setText(BUTTON_LABELS[count1][count2]);
				buttonPanel.add(buttons[count1][count2]);
				buttons[count1][count2].addActionListener(handler);
			}
		
		centerPanel.add(mainPanel, "General Calculator");
	}
	public void show() 
	{
		mainLayout.show(centerPanel, "General Calculator");
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
