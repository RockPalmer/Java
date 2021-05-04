package calcGUI;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends BaseGUI implements ActionListener
{
	private static JLabel title = new JLabel();
	private static JButton[] buttons = new JButton[5];
	public static void buildGUI()
	{
		/* Clear the panel */
		panel.removeAll();
		panel.repaint();
		
		buildLabels();
		buildButtons();
		
	}
	public static void buildButtons() 
	{
		//Button stuff
		/* fill button list with JButton objects and add them to the panel */
		for (int count = 0; count < buttons.length; count++) 
			buttons[count] = new JButton();
		addComponentsToPanel(buttons);
		
		/* Set the text on each button for each of the functions the 
		 * calculator can carry out and one extra to quit the program */
		buttons[0].setText("Describing Data");
		buttons[1].setText("Find the line of best fit");
		buttons[2].setText("Quadratic Formula");
		buttons[3].setText("General Calculator");
		buttons[4].setText("Quit");
		
		/* Add an action listener to each button to carry out the various
		 * functions that the names of the buttons suggest */
		buttons[0].addActionListener(new DescDataGUI());
		buttons[1].addActionListener(new LineOfBestFit());
		buttons[2].addActionListener(new QuadraticFormula());
		buttons[3].addActionListener(new GenCalcGUI());
		buttons[4].addActionListener(new QuitProgram());
				
		/* Set the dimensions for each button based on the text on each button */
		setComponentsSize(buttons);
		
		/* Set the buttons on the frame in increments of 30 pixels apart */
		for (int count = 0; count < buttons.length; count++) 
			buttons[count].setLocation(COLUMN_COORDS[2] - (buttons[count].getWidth() / 2), 40 + count * 30);
	}
	public static void buildLabels() 
	{
		setErrWarning();
		
		//Label stuff
		title.setText("My Calculator");
		Dimension size = title.getPreferredSize();
		title.setBounds(COLUMN_COORDS[2] - (size.width / 2), 20, size.width, size.height);
		panel.add(title);
	}
	public static JButton[] getButtons() 
	{
		return buttons;
	}
	public static JLabel getTitle() 
	{
		return title;
	}
	public void actionPerformed(ActionEvent arg0) 
	{
		buildGUI();	
	}
	private static class QuitProgram implements ActionListener 
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			System.exit(0);
		}
	}
}
