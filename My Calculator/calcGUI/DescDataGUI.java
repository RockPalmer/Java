package calcGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import calcFn.DescDataFn;

public class DescDataGUI extends BaseGUI
{
	private static double[] resultVals;
	private static double[] dataSet;
	private static Data data;
	private static Diagram diagram;
	private static JPanel dPanel;
	private static CardLayout dLayout;
	/* Program that will run to move the user from the diagram GUI back to the data */
	public DescDataGUI() 
	{
		dPanel = new JPanel();
		dLayout = new CardLayout();
		dPanel.setLayout(dLayout);
		centerPanel.add(dPanel, "Describing Data");
		
		data = new Data();
		diagram = new Diagram();
	}
	public void show()
	{
		mainLayout.show(centerPanel, "Describing Data");
		data.show();
	}
	private static class Data
	{
		private static ArrayList<JTextField> textFields;
		private static ArrayList<JLabel> labels;
		private static JTextField[] answerBoxes;
		private static JPanel mainPanel;
		private static JPanel dataValsPanel;
		private static JPanel topOpPanel;
		private JButton addPoint;
		private JButton removePoint;
		private JButton mainMenu;
		private JButton calculate;
		private JButton showPlot;
		private static final int DATA_ENTRY_SIZE = 7;
		private static final Dimension ANSWER_ENTRY_SIZE = new Dimension(60,20);
		public Data() 
		{
			//Initialize instance variables
			String[] answerLabels = {"Mean = ","Median = ","Mode = ","Range = ","Q1 = ","Q3 = ","\u03C3 = "};
			textFields = new ArrayList<JTextField>();
			labels = new ArrayList<JLabel>();
			answerBoxes = new JTextField[7];
			addPoint = new JButton();
			removePoint = new JButton();
			showPlot = new JButton();
			mainMenu = new JButton();
			calculate = new JButton();
			mainPanel = new JPanel();
			dataValsPanel = new JPanel();
			topOpPanel = new JPanel();
			JPanel bottomOpPanel = new JPanel();
			JPanel dataPanel = new JPanel();
			JPanel keyValsPanel = new JPanel();
			JPanel optionsPanel = new JPanel();
			JPanel titlePanel = new JPanel();
			JPanel answerPanel = new JPanel();
			JPanel[] answerVals = new JPanel[7];
			JScrollPane dataView = new JScrollPane();
			JLabel dataSet = new JLabel();
			JLabel keyValues = new JLabel();
			ButtonHandler handler = new ButtonHandler();
			
			//Build layout of mainPanel
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(dataPanel, BorderLayout.CENTER);
			mainPanel.add(keyValsPanel, BorderLayout.LINE_END);
			mainPanel.add(optionsPanel, BorderLayout.PAGE_END);
			
			//Build layout of dataPanel
			dataPanel.setLayout(new BorderLayout());
			dataPanel.add(titlePanel, BorderLayout.PAGE_START);
			dataPanel.add(dataView, BorderLayout.CENTER);
			
			//Build layout of titlePanel
			titlePanel.setLayout(new FlowLayout());
			dataSet.setText("Data Set");
			titlePanel.add(dataSet);
			
			//Build layout of dataView
			dataView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			dataView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			dataView.setViewportView(dataValsPanel);
			
			//Build layout of dataValsPanel
			dataValsPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
			for (int count = 0; count < 3; count++)
			{
				JTextField t = new JTextField();
				t.setColumns(DATA_ENTRY_SIZE);
				JLabel l = new JLabel(",");
				textFields.add(t);
				labels.add(l);
				dataValsPanel.add(t);
				dataValsPanel.add(l);
			}
			
			//Build layout of keyValsPanel
			keyValsPanel.setLayout(new BoxLayout(keyValsPanel, BoxLayout.Y_AXIS));
			keyValues.setText("Key Values");
			showPlot.setText("Show Box Plot");
			showPlot.addActionListener(handler);
			keyValues.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			answerPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			showPlot.setAlignmentX(JButton.CENTER_ALIGNMENT);
			keyValsPanel.add(keyValues);
			keyValsPanel.add(answerPanel);
			keyValsPanel.add(showPlot);
			
			//Build layout of answerPanel
			answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
			for (int count = 0; count < answerVals.length; count++) 
			{
				answerVals[count] = new JPanel();
				answerVals[count].setAlignmentX(JPanel.RIGHT_ALIGNMENT);
				answerVals[count].setLayout(new BoxLayout(answerVals[count], BoxLayout.X_AXIS));
				answerVals[count].add(new JLabel(answerLabels[count]));
				JTextField t = new JTextField();
				t.setMaximumSize(ANSWER_ENTRY_SIZE);
				answerBoxes[count] = t;
				answerVals[count].add(t);
				answerPanel.add(answerVals[count]);
			}
			
			//Build layout of optionsPanel
			optionsPanel.setLayout(new GridLayout(2,1,0,5));
			optionsPanel.add(topOpPanel);
			optionsPanel.add(bottomOpPanel);
			
			//Build layout of topOpPanel
			topOpPanel.setLayout(new BoxLayout(topOpPanel, BoxLayout.X_AXIS));
			addPoint.setText("Add Point");
			removePoint.setText("Remove Point");
			addPoint.addActionListener(handler);
			removePoint.addActionListener(handler);
			topOpPanel.add(Box.createHorizontalGlue());
			topOpPanel.add(addPoint);
			topOpPanel.add(Box.createHorizontalGlue());
			topOpPanel.add(removePoint);
			topOpPanel.add(Box.createHorizontalGlue());
			
			//Build layout of bottomOpPanel
			bottomOpPanel.setLayout(new BoxLayout(bottomOpPanel, BoxLayout.X_AXIS));
			mainMenu.setText("Main Menu");
			calculate.setText("Calculate");
			mainMenu.addActionListener(main);
			calculate.addActionListener(handler);
			bottomOpPanel.add(Box.createHorizontalGlue());
			bottomOpPanel.add(mainMenu);
			bottomOpPanel.add(Box.createHorizontalGlue());
			bottomOpPanel.add(Box.createRigidArea(new Dimension(10,0)));
			bottomOpPanel.add(Box.createHorizontalGlue());
			bottomOpPanel.add(calculate);
			bottomOpPanel.add(Box.createHorizontalGlue());
			
			dPanel.add(mainPanel, "Data");
		}
		public void show() 
		{
			dLayout.show(dPanel, "Data");
		}
		private class ButtonHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JButton source = (JButton)arg0.getSource();
				if (source.equals(addPoint))
				{
					//addNewPoint();
					JTextField t = new JTextField();
					JLabel l = new JLabel(",");
					t.setColumns(DATA_ENTRY_SIZE);
					textFields.add(t);
					labels.add(l);
					dataValsPanel.add(t);
					dataValsPanel.add(l);
					
					if (textFields.size() == 2) 
					{
						topOpPanel.add(removePoint);
						topOpPanel.add(Box.createHorizontalGlue());
						topOpPanel.repaint();
						topOpPanel.revalidate();
					}
					
					dataValsPanel.repaint();
					dataValsPanel.revalidate();
				}
				else if (source.equals(removePoint))
				{
					JTextField t = textFields.get(textFields.size() - 1);
					JLabel l = labels.get(labels.size() - 1);
					dataValsPanel.remove(l);
					dataValsPanel.remove(t);
					textFields.remove(textFields.size() - 1);
					labels.remove(labels.size() - 1);
					
					if (textFields.size() == 1) 
					{
						topOpPanel.removeAll();
						topOpPanel.add(Box.createHorizontalGlue());
						topOpPanel.add(addPoint);
						topOpPanel.add(Box.createHorizontalGlue());
						topOpPanel.repaint();
						topOpPanel.revalidate();
					}
					
					dataValsPanel.repaint();
					dataValsPanel.revalidate();
				}
				else if (source.equals(calculate)) 
				{
					try
					{
						//Check if any text field entries are invalid
						for (int count = 0; count < textFields.size(); count++) 
						{
							Double.parseDouble(textFields.get(count).getText());
						}
						dataSet = new double[textFields.size()];
						for (int count = 0; count < textFields.size(); count++)
							dataSet[count] = Double.parseDouble(textFields.get(count).getText());
						resultVals = DescDataFn.describeData(dataSet);
						
						for (int count = 0; count < resultVals.length; count++)
							answerBoxes[count].setText("" + resultVals[count]);
						
						diagram.makeNewPlot(dataSet, resultVals[4], resultVals[1], resultVals[5]);
					}
					catch(NumberFormatException e) 
					{
						System.out.println("ERROR");
					}
				}
				else 
				{
					diagram.show();
				}
			}
		}
	}
	private class Diagram
	{
		private JLabel[] dataLabels;
		private final int DIAGRAM_HEIGHT = 300;
		private double[] diagramData;
		private double smallest;
		private double q1;
		private double q2;
		private double q3;
		private double largest;
		private JPanel middlePanel;
		/* Replaces the data panel with the newly-built diagram panel */
		public Diagram() 
		{	
			//Initialize instance variables
			middlePanel = new JPanel();
			JPanel diagramPanel = new JPanel();
			JPanel bottomPanel = new JPanel();
			JButton returnButton = new JButton();
			
			//Build Layout of diagramPanel
			diagramPanel.setLayout(new BorderLayout());
			diagramPanel.add(middlePanel, BorderLayout.CENTER);
			diagramPanel.add(bottomPanel, BorderLayout.PAGE_END);
			
			//Build Layout of bottomPanel
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
			returnButton.setText("Return to Data");
			returnButton.addActionListener(new toData());
			bottomPanel.add(Box.createHorizontalGlue());
			bottomPanel.add(returnButton);
			bottomPanel.add(Box.createHorizontalGlue());

			//Build Layout of middlePanel
			middlePanel.setLayout(new BorderLayout());
			middlePanel.add(Box.createHorizontalGlue());
			middlePanel.add(new JLabel("No Data Entered"));
			middlePanel.add(Box.createHorizontalGlue());
			
			dPanel.add(diagramPanel, "Diagram Panel");
		}
		public void show()
		{
			dLayout.show(dPanel, "Diagram Panel");
		}
		public void makeNewPlot(double[] newData, double qu1, double qu2, double qu3) 
		{
			/* Sort the data from smallest to largest */
			Arrays.sort(newData);
			
			/* Set parameter variables */
			this.diagramData = newData;
			this.q1 = qu1;
			this.q2 = qu2;
			this.q3 = qu3;
			this.smallest = diagramData[0];
			this.largest = diagramData[diagramData.length - 1];
			
			//Reset middlePanel
			Plot plot = new Plot();
			middlePanel.removeAll();
			middlePanel.add(plot);
		}
		/* Defines the graphics of the new panel object that will contain the box plot diagram */
		private class Plot extends JPanel
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public Plot()
			{
				super();
				this.setLayout(null);
				/* Fill the JLabel array with JLabel objects */
				dataLabels = new JLabel[dataSet.length + 5];
				for (int count = 0; count < dataLabels.length; count++) 
					dataLabels[count] = new JLabel();
				
				/* Set the text in each data label */
				for(int count = 0; count < dataSet.length; count++) 
					dataLabels[count].setText("" + dataSet[count]);
				dataLabels[dataLabels.length - 1].setText("" + smallest);
				dataLabels[dataLabels.length - 2].setText("" + largest);
				dataLabels[dataLabels.length - 3].setText("" + q3);
				dataLabels[dataLabels.length - 4].setText("" + q2);
				dataLabels[dataLabels.length - 5].setText("" + q1);
				
				/* Set the size and location of each data label that references a data point */
				double conversionFactor = 750/(largest - smallest);
				setComponentsSize(dataLabels);
				final int LABEL_HEIGHT = dataLabels[0].getHeight();
				for (int count = 0; count < dataLabels.length - 5; count++) 
					dataLabels[count].setLocation((int)Math.round((dataSet[count]-smallest) * conversionFactor) + 125 - dataLabels[count].getWidth()/2, DIAGRAM_HEIGHT + 10 + LABEL_HEIGHT);
				
				/* Set the location of each data label that references a diagram value */
				dataLabels[dataLabels.length - 1].setLocation(125 - (dataLabels[dataLabels.length - 1].getWidth()/2), DIAGRAM_HEIGHT - 120 - LABEL_HEIGHT);
				dataLabels[dataLabels.length - 2].setLocation(875 - (dataLabels[dataLabels.length - 2].getWidth()/2), DIAGRAM_HEIGHT - 120 - LABEL_HEIGHT);
				dataLabels[dataLabels.length - 3].setLocation((int)Math.round((q3-smallest) * conversionFactor) + 125 - (dataLabels[dataLabels.length - 3].getWidth()/2), DIAGRAM_HEIGHT - 120 - LABEL_HEIGHT);
				dataLabels[dataLabels.length - 4].setLocation((int)Math.round((q2-smallest) * conversionFactor) + 125 - (dataLabels[dataLabels.length - 4].getWidth()/2), DIAGRAM_HEIGHT - 120 - LABEL_HEIGHT);
				dataLabels[dataLabels.length - 5].setLocation((int)Math.round((q1-smallest) * conversionFactor) + 125 - (dataLabels[dataLabels.length - 5].getWidth()/2), DIAGRAM_HEIGHT - 120 - LABEL_HEIGHT);
				
				for (int count = 0; count < dataLabels.length; count++) 
					this.add(dataLabels[count]);
			}
			/* Override that paintComponent method of the JPanel class to add the box plot diagram */
			protected void paintComponent(Graphics g)
			{
				/**Draw number line**/
				
				/* Draw basic number line just with the end points (This should never change) */
				g.drawLine(125, DIAGRAM_HEIGHT, 875, DIAGRAM_HEIGHT);
				g.drawLine(125, DIAGRAM_HEIGHT - 20, 125, DIAGRAM_HEIGHT + 20);
				g.drawLine(875, DIAGRAM_HEIGHT - 20, 875, DIAGRAM_HEIGHT + 20);
			
				/* Add an extra tick mark for each data point in the set */
				double conversionFactor = 750/(largest - smallest);
				for (int count = 0; count < diagramData.length; count++) 
					if (count != smallest && count != largest)
						g.drawLine((int)Math.round((diagramData[count]-smallest) * conversionFactor) + 125, DIAGRAM_HEIGHT - 10, (int)Math.round((diagramData[count]-smallest) * conversionFactor) + 125, DIAGRAM_HEIGHT + 10);
				
				/**Draw Box Plot**/
				
				/* Draw the end points of the box plot (This should not change either */
				g.drawLine(125, DIAGRAM_HEIGHT - 120, 125, DIAGRAM_HEIGHT - 80);
				g.drawLine(875, DIAGRAM_HEIGHT - 120, 875, DIAGRAM_HEIGHT - 80);
			
				/* Build the rest of the box plot based on the data set provided */
				int q1Size = (int)Math.round((q1-smallest) * conversionFactor) + 125;
				int q2Size = (int)Math.round((q2-smallest) * conversionFactor) + 125;
				int q3Size = (int)Math.round((q3-smallest) * conversionFactor) + 125;
				
				g.drawLine(125, DIAGRAM_HEIGHT - 100, q1Size, DIAGRAM_HEIGHT - 100);
				g.drawLine(q1Size, DIAGRAM_HEIGHT - 120, q1Size, DIAGRAM_HEIGHT - 80);
				g.drawLine(q1Size, DIAGRAM_HEIGHT - 120, q3Size, DIAGRAM_HEIGHT - 120);
				g.drawLine(q1Size, DIAGRAM_HEIGHT - 80, q3Size, DIAGRAM_HEIGHT - 80);
				g.drawLine(q2Size, DIAGRAM_HEIGHT - 120, q2Size, DIAGRAM_HEIGHT - 80);
				g.drawLine(q3Size, DIAGRAM_HEIGHT - 120, q3Size, DIAGRAM_HEIGHT - 80);
				g.drawLine(q3Size, DIAGRAM_HEIGHT - 100, 875, DIAGRAM_HEIGHT - 100);
			}
		}
		private class toData implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				data.show();
			}
		}
	}
}
