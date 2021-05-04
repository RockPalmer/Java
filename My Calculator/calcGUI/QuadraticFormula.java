package calcGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class QuadraticFormula extends BaseGUI implements ActionListener
{
	private static JButton button;
	protected static JTextField[] textFields;
	protected static JTextField answerField;
	protected static JLabel[] labels;
	public static void buildGUI()
	{
		/* Clear panel */
		panel.removeAll();
		panel.repaint();
		
		setErrWarning();
		buildButton();
		buildTextFields();
		buildLabels();
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		buildGUI();
	}
	private static void buildButton() 
	{
		setMenuButton();
		
		button = new JButton();
		panel.add(button);
		
		button.setText("Calculate");
		setOnPanel(button, 4, 500);
		
		/* Add actionListener to button to calculate results */
		button.addActionListener(new calculate());
	}
	private static void buildTextFields() 
	{
		buildInputFields();
		buildAnswerField();
	}
	private static void buildInputFields() 
	{
		textFields = new JTextField[3];
		
		/* Fill the textField array and place the textFields onto the panel */
		for (int count = 0; count < textFields.length; count++) 
			textFields[count] = new JTextField();
		addComponentsToPanel(textFields);			
		/* Set the size of each textField to be 100 wide and 26 tall */
		for (int count = 0; count < textFields.length; count++)
		{
			textFields[count].setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
			textFields[count].setLocation(COLUMN_COORDS[2] + 50 - (TEXT_FIELD_WIDTH/2), 40 + count * 30);
		}
	}
	private static void buildAnswerField() 
	{
		answerField = new JTextField();
		
		answerField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
		answerField.setLocation(COLUMN_COORDS[2] + 50 - (TEXT_FIELD_WIDTH)/2, textFields[2].getY() + 30);
		
		panel.add(answerField);
	}
	private static void buildLabels() 
	{
		labels = new JLabel[4];
		
		/* Fill labels array and place the labels onto the panel */
		for (int count = 0; count < labels.length; count++)
			labels[count] = new JLabel();
		addComponentsToPanel(labels);
		
		/* Set the text of the labels to be the 3 variables that the user will
		 * input and the last one as the 'x' the user is looking for */
		labels[0].setText("a = ");
		labels[1].setText("b = ");
		labels[2].setText("c = ");
		labels[3].setText("x = ");
		
		/* Set the size of each label based on the text in each one */
		setComponentsSize(labels);
		
		/* Set the locations of the various labels and textField to appear in 2 
		 * columns next to each other*/
		for (int count = 0; count < labels.length; count++) 
			labels[count].setLocation(COLUMN_COORDS[2] - 50 - (labels[count].getWidth()/2), 40 + count * 30);
	}
	private static class calculate implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			if (calcErr.TextFieldErr.isValidInput(textFields, errWarning)) 
			{
				double x1 = calcFn.QuadFormFn.calculate(Double.parseDouble(textFields[0].getText()), Double.parseDouble(textFields[1].getText()), Double.parseDouble(textFields[2].getText()))[0];
				double x2 = calcFn.QuadFormFn.calculate(Double.parseDouble(textFields[0].getText()), Double.parseDouble(textFields[1].getText()), Double.parseDouble(textFields[2].getText()))[1];
				answerField.setText(x1 + " , " + x2);
			}
		}
	}
}
