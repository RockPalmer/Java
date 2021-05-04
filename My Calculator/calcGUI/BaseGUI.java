package calcGUI;

import java.awt.Dimension;

import javax.swing.BorderFactory;
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
	public static void main(String[] args) 
	{
		buildStandardWindow();
		MainMenu.buildGUI();
	}
	/* Build the panel and window that will be used for all calculator functions */
	protected static void buildStandardWindow()
	{
		/* Build the frame */
		frame.getContentPane();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setSize(1000, 600);
		frame.setVisible(true);
		
		/* Build the panel */
		buildStandardPanel();
	}
	protected static void setMenuButton() 
	{
		menuButton.setText("Main Menu");
		setOnPanel(menuButton, 0, 500);
		menuButton.addActionListener(new MainMenu());
	}
	protected static void buildStandardPanel() 
	{
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	protected static void setErrWarning() 
	{
		/* Build error warning label */
		for (int count = 0; count < errWarning.length; count++) 
		{
			errWarning[count] = new JLabel();
			errWarning[count].setVisible(true);
			panel.add(errWarning[count]);
			errWarning[count].setText("");
			setComponentsSize(errWarning[count]);
		}
		errWarning[0].setLocation(COLUMN_COORDS[2] - (errWarning[0].getWidth()/2), 484);
		errWarning[1].setLocation(COLUMN_COORDS[2] - (errWarning[1].getWidth()/2), 500);
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