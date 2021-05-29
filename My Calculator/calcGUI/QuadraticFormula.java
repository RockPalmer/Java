package calcGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class QuadraticFormula extends BaseGUI
{
	private JTextField[] textFields;
	private JPanel mainPanel;
	public QuadraticFormula() 
	{
		//Initialize instance variables
		final Dimension TEXT_FIELD_SIZE = new Dimension(60,20);
		mainPanel = new JPanel();
		textFields = new JTextField[4];
		JPanel dataPanel = new JPanel();
		JPanel optionsPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		JButton menuButton = new JButton();
		JButton calculate = new JButton();
		JLabel title = new JLabel();
		
		//Build layout of mainPanel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(titlePanel, BorderLayout.PAGE_START);
		mainPanel.add(dataPanel, BorderLayout.CENTER);
		mainPanel.add(optionsPanel, BorderLayout.PAGE_END);
		
		//Build layout of titlePanel
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		title.setText("Quadratic Formula");
		titlePanel.add(title);
		
		//Build layout of dataPanel
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
		String[] labels = {"a =","b =","c =","x ="};
		for (int count = 0; count < 4; count++) 
		{
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.CENTER));
			p.add(new JLabel(labels[count]));
			JTextField t = new JTextField();
			t.setPreferredSize(TEXT_FIELD_SIZE);
			p.add(t);
			textFields[count] = t;
			dataPanel.add(p);
		}
		
		//Build layout of optionsPanel
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		menuButton.setText("Return to Menu");
		calculate.setText("Calculate");
		menuButton.addActionListener(main);
		calculate.addActionListener(new Calculate());
		optionsPanel.add(Box.createHorizontalGlue());
		optionsPanel.add(menuButton);
		optionsPanel.add(Box.createHorizontalGlue());
		optionsPanel.add(calculate);
		optionsPanel.add(Box.createHorizontalGlue());
		
		centerPanel.add(mainPanel, "Quadratic Formula");
	}
	public void show()
	{
		mainLayout.show(centerPanel, "Quadratic Formula");
	}
	private class Calculate implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			try
			{
				double x1 = calcFn.QuadFormFn.calculate(Double.parseDouble(textFields[0].getText()), Double.parseDouble(textFields[1].getText()), Double.parseDouble(textFields[2].getText()))[0];
				double x2 = calcFn.QuadFormFn.calculate(Double.parseDouble(textFields[0].getText()), Double.parseDouble(textFields[1].getText()), Double.parseDouble(textFields[2].getText()))[1];
				textFields[3].setText(x1 + " , " + x2);
			}
			catch (NumberFormatException e) 
			{
				errWarning[0].setText("ERROR");
			}
		}
	}
}
