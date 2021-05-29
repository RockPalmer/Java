package calcGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public abstract class BaseGUI
{
	protected static JFrame frame = new JFrame();
	protected static JPanel panel = new JPanel();
	protected static JButton menuButton = new JButton();
	protected static JLabel[] errWarning = new JLabel[2];
	protected static final int TEXT_FIELD_HEIGHT = 20;
	protected static final int TEXT_FIELD_WIDTH = 100;
	private static final int FRAME_WIDTH = 1000;
	protected static final int[] COLUMN_COORDS = {(2 * FRAME_WIDTH)/8, (3 * FRAME_WIDTH)/8, (4 * FRAME_WIDTH)/8, (5 * FRAME_WIDTH)/8, (6 * FRAME_WIDTH)/8};
	
	protected static MainMenu main;
	protected static DescDataGUI dd;
	protected static LineOfBestFit lobf;
	protected static GenCalcGUI gc;
	protected static QuadraticFormula qf;
	protected static CardLayout mainLayout;
	protected static JPanel centerPanel;
	public static void main(String[] args)
	{
		//Initialize instance variables
		mainLayout = new CardLayout();
		centerPanel = new JPanel();
		centerPanel.setLayout(mainLayout);
		main = new MainMenu();
		dd = new DescDataGUI();
		lobf = new LineOfBestFit();
		gc = new GenCalcGUI();
		qf = new QuadraticFormula();
		
		/* Build the frame */
		//frame.getContentPane();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(1000, 600);
		frame.setVisible(true);
		
		/* Build the panel */
		
		//panel.setLayout(null);
		panel.setLayout(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.add(centerPanel, BorderLayout.CENTER);
		
		/* Set the error message at the bottom of the screen */
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
		bottomPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		for (int count = 0; count < errWarning.length; count++) 
		{
			errWarning[count] = new JLabel();
			//errWarning[count].setVisible(true);
			errWarning[count].setText("");
			bottomPanel.add(errWarning[count]);
			//setComponentsSize(errWarning[count]);
			bottomPanel.add(errWarning[count]);
		}
		panel.add(bottomPanel, BorderLayout.PAGE_END);
		
		main.show();
	}
	protected static void setMenuButton() 
	{
		menuButton.setText("Main Menu");
		setOnPanel(menuButton, 0, 500);
		menuButton.addActionListener(new MainMenu());
	}
	protected static void setComponentsSize(JComponent[] components) 
	{
		Dimension[] dimensions = new Dimension[components.length];
		for (int count = 0; count < components.length; count++) 
		{
			dimensions[count] = components[count].getPreferredSize();
			components[count].setSize(dimensions[count].width, dimensions[count].height);
		}
	}
	protected static void setComponentsSize(JComponent component) 
	{
		Dimension dimension = component.getPreferredSize();
		component.setSize(dimension.width, dimension.height);
	}
	protected static void addComponentsToPanel(JComponent[] components) 
	{
		for (int count = 0; count < components.length; count++) 
			panel.add(components[count]);
	}
	protected static void setOnPanel(JComponent component, int placementX, int placementY) 
	{
		panel.add(component);
		Dimension size = component.getPreferredSize();
		component.setBounds(COLUMN_COORDS[placementX] - (size.width/2), placementY, size.width, size.height);
	}
}
