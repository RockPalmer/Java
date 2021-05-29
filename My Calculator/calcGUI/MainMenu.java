package calcGUI;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends BaseGUI implements ActionListener
{
	//private static JLabel title;
	private static JLabel title;
	private static JButton[] buttons = new JButton[5];
	private static JPanel mainPanel;
	private static JPanel buttonPanel;
	private static JPanel titlePanel;
	public MainMenu() 
	{
		mainPanel = new JPanel();
		buttonPanel = new JPanel();
		titlePanel = new JPanel();
		
		mainPanel.setLayout(new BorderLayout());
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		mainPanel.add(titlePanel, BorderLayout.PAGE_START);
		
		//Label stuff
		title = new JLabel("My Calculator");
		title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		titlePanel.add(title);
		
		//Button stuff
		/* fill button list with JButton objects and add them to the panel */
		for (int count = 0; count < buttons.length; count++) 
		{
			buttons[count] = new JButton();
			buttons[count].setAlignmentX(JButton.CENTER_ALIGNMENT);
		}
		//addComponentsToPanel(buttons);
		
		/* Set the text on each button for each of the functions the 
		 * calculator can carry out and one extra to quit the program */
		buttons[0].setText("Describing Data");
		buttons[1].setText("Find the line of best fit");
		buttons[2].setText("Quadratic Formula");
		buttons[3].setText("General Calculator");
		buttons[4].setText("Quit");
		
		for (int count = 0; count < buttons.length; count++)
			buttonPanel.add(buttons[count]);
		
		/* Add an action listener to each button to carry out the various
		 * functions that the names of the buttons suggest */
		ButtonHandler handler = new ButtonHandler();
		buttons[0].addActionListener(handler);
		buttons[1].addActionListener(handler);
		buttons[2].addActionListener(handler);
		buttons[3].addActionListener(handler);
		buttons[4].addActionListener(handler);
		
		centerPanel.add(mainPanel, "Main Menu");
	}
	public void show()
	{
		mainLayout.show(centerPanel, "Main Menu");
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
		show();	
	}
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			JButton source = (JButton)event.getSource();
			if (source.equals(buttons[0])) 
				dd.show();
			else if (source.equals(buttons[1])) 
			{
				lobf.show();
			}
			else if (source.equals(buttons[2])) 
			{
				qf.show();
			}
			else if (source.equals(buttons[3])) 
			{
				gc.show();
			}
			else 
				System.exit(0);
		}
	}
}
