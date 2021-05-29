package calcGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LineOfBestFit extends BaseGUI
{
	private static JButton[] buttons;
	private static ArrayList<JTextField[]> textFields = new ArrayList<JTextField[]>();
	private ArrayList<JPanel> points = new ArrayList<JPanel>();
	private static JLabel result;
	private JPanel optionsPanel;
	private JPanel entryPanel;
	private JButton addPoint;
	private JButton removePoint;
	private JButton calculate;
	private JButton menuButton;
	private static final Dimension TEXT_FIELD_SIZE = new Dimension(60,20);
	private static final int TEXT_ARRAY_SIZE = 2;
	public LineOfBestFit() 
	{
		//Initialize instance variables
		JPanel mainPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel titlePanel = new JPanel();
		JPanel dataPanel = new JPanel();
		JPanel answerPanel = new JPanel();
		JPanel header = new JPanel();
		JScrollPane dataView = new JScrollPane();
		JLabel title = new JLabel();
		ButtonHandler handler = new ButtonHandler();
		entryPanel = new JPanel();
		optionsPanel = new JPanel();
		result = new JLabel();
		addPoint = new JButton();
		removePoint = new JButton();
		calculate = new JButton();
		menuButton = new JButton();
		
		//Build layout of mainPanel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(titlePanel, BorderLayout.PAGE_START);
		mainPanel.add(dataPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
		
		//Build layout of titlePanel
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
		title.setText("Line of Best Fit Calculator");
		titlePanel.add(Box.createHorizontalGlue());
		titlePanel.add(title);
		titlePanel.add(Box.createHorizontalGlue());
		
		//Build layout of buttonPanel
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		menuButton.setText("Return to Menu");
		calculate.setText("Calculate");
		menuButton.addActionListener(handler);
		calculate.addActionListener(handler);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(menuButton);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(calculate);
		buttonPanel.add(Box.createHorizontalGlue());
		
		//Build layout of dataPanel
		dataPanel.setLayout(new BorderLayout());
		dataPanel.add(answerPanel, BorderLayout.PAGE_START);
		dataPanel.add(dataView, BorderLayout.CENTER);
		dataPanel.add(optionsPanel, BorderLayout.PAGE_END);
		
		//Build layout of answerPanel
		answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
		result.setText("y = mx + b");
		result.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		header.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		header.setLayout(new FlowLayout(FlowLayout.CENTER));
		header.add(new JLabel("("));
		header.add(new JLabel("x"));
		header.add(new JLabel(","));
		header.add(new JLabel("y"));
		header.add(new JLabel(")"));
		answerPanel.add(result);
		answerPanel.add(header);
		
		//Build layout of dataView
		dataView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		dataView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		dataView.setViewportView(entryPanel);
		entryPanel.setLayout(new GridLayout(0,1));
		for (int count1 = 0; count1 < 2; count1++)
		{
			JPanel p = new JPanel();
			JTextField[] point = new JTextField[TEXT_ARRAY_SIZE];
			for (int count = 0; count < point.length; count++) 
			{
				point[count] = new JTextField();
				point[count].setPreferredSize(TEXT_FIELD_SIZE);
			}
			p.setLayout(new FlowLayout(FlowLayout.CENTER));
			p.add(new JLabel("("));
			p.add(point[0]);
			p.add(new JLabel(","));
			p.add(point[1]);
			p.add(new JLabel(")"));
			textFields.add(point);
			points.add(p);
			entryPanel.add(p);
		}
		
		//Build layout of optionsPanel
		addPoint.setText("Add Point");
		removePoint.setText("Remove Point");
		addPoint.addActionListener(handler);
		removePoint.addActionListener(handler);
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.X_AXIS));
		optionsPanel.add(Box.createHorizontalGlue());
		optionsPanel.add(addPoint);
		optionsPanel.add(Box.createHorizontalGlue());
		
		centerPanel.add(mainPanel, "Line of Best Fit");
	}
	public void show()
	{
		mainLayout.show(centerPanel, "Line of Best Fit");
	}
	public static JButton[] getButtons() 
	{
		JButton[] tempButtons = new JButton[2];
		tempButtons[0] = buttons[0];
		tempButtons[1] = buttons[2];
		return tempButtons;
	}
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event) 
		{
			JButton source = (JButton) event.getSource();
			if (source.equals(addPoint)) 
			{
				JPanel p = new JPanel();
				JTextField[] point = new JTextField[TEXT_ARRAY_SIZE];
				for (int count = 0; count < point.length; count++) 
				{
					point[count] = new JTextField();
					point[count].setPreferredSize(TEXT_FIELD_SIZE);
				}
				p.setLayout(new FlowLayout(FlowLayout.CENTER));
				p.add(new JLabel("("));
				p.add(point[0]);
				p.add(new JLabel(","));
				p.add(point[1]);
				p.add(new JLabel(")"));
				textFields.add(point);
				points.add(p);
				entryPanel.add(p);
				
				if (points.size() == 3) 
				{
					optionsPanel.removeAll();
					optionsPanel.add(Box.createHorizontalGlue());
					optionsPanel.add(addPoint);
					optionsPanel.add(Box.createHorizontalGlue());
					optionsPanel.add(removePoint);
					optionsPanel.add(Box.createHorizontalGlue());
					optionsPanel.repaint();
					optionsPanel.revalidate();
				}
				entryPanel.repaint();
				entryPanel.revalidate();
			}
			else if (source.equals(removePoint))
			{
				entryPanel.remove(points.get(points.size() - 1));
				points.remove(points.size() - 1);
				
				if (points.size() == 2) 
				{
					optionsPanel.removeAll();
					optionsPanel.add(Box.createHorizontalGlue());
					optionsPanel.add(addPoint);
					optionsPanel.add(Box.createHorizontalGlue());
					optionsPanel.repaint();
					optionsPanel.revalidate();
				}
				entryPanel.repaint();
				entryPanel.revalidate();
			}
			else if (source.equals(calculate)) 
			{
				try 
				{
					/* Check for errors in the text fields */
					for (int count = 0; count < textFields.size(); count++)
					{
						Double.parseDouble(textFields.get(count)[0].getText());
						Double.parseDouble(textFields.get(count)[1].getText());
					}
					
					/* Proceed with button function if the data fields are valid */
					double[] xVals = new double[textFields.size()];
					double[] yVals = new double[textFields.size()];
					for (int count = 0; count < xVals.length; count++) 
					{
						xVals[count] = Double.parseDouble(textFields.get(count)[0].getText());
						yVals[count] = Double.parseDouble(textFields.get(count)[1].getText());
					}
					String equation = calcFn.LnOfBestFitFn.findBestFitLine(xVals,yVals);
					result.setText(equation);
				}
				catch (NumberFormatException e) 
				{
					System.out.println("ERROR");
				}
			}
			else 
			{
				main.show();
			}
		}	
	}
}
