package calcGUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DescDataDiagramGUI extends DescDataAbstGUI
{
	private static JLabel[] dataLabels;
	protected final static int DIAGRAM_HEIGHT = 300;
	protected static double smallest;
	protected static double q1;
	protected static double q2;
	protected static double q3;
	protected static double largest;
	protected static Diagram panel2 = new Diagram();
	private static JButton returnButton = new JButton();
	/* Replaces the data panel with the newly-built diagram panel */
	protected static void showNewPanel() 
	{
		frame.remove(panel);
		frame.repaint();
		frame.add(panel2);
		addCompsToPanel2();
	}
	/* Adds all components to the panel2 object after it has been added to the frame */
	private static void addCompsToPanel2() 
	{
		panel2.setLayout(null);
		panel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		panel2.add(returnButton);
		for (int count = 0; count < dataLabels.length; count++) 
			panel2.add(dataLabels[count]);
	}
	/* Builds the components on the panel2 object */
	protected static void buildNewGUI()
	{
		buildNewPanel();
		buildReturnButton();
	}
	/* build the diagram on the new panel */
	private static void buildNewPanel()
	{	
		panel2.setVars(dataSet, resultVals[4], resultVals[0], resultVals[5]);
		setDataLabels();
	}
	/* Build the button to return the user back to the data panel */
	private static void buildReturnButton()
	{
		returnButton.setText("Return to Data");
		setComponentsSize(returnButton);
		returnButton.setLocation(COLUMN_COORDS[0] - (returnButton.getWidth()/2), 500);
		returnButton.removeActionListener(new ReturnToData());
		returnButton.addActionListener(new ReturnToData());
	}
	/* build the labels that will go onto the box plot diagram */
	private static void setDataLabels() 
	{
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
	}
	/* Defines the graphics of the new panel object that will contain the box plot diagram */
	protected static class Diagram extends JPanel
	{
		private double[] data;
		private static int smallestIndex;
		private static int largestIndex;
		
		/* Set the various parameters that will be used to build the box plot diagram */
		private void setVars(double[] data, double q1, double q2, double q3) 
		{
			/* Sort the data from smallest to largest */
			Arrays.sort(data);
			
			/* Set parameter variables */
			this.data = data;
			DescDataDiagramGUI.q1 = q1;
			DescDataDiagramGUI.q2 = q2;
			DescDataDiagramGUI.q3 = q3;
			DescDataDiagramGUI.smallest = data[0];
			DescDataDiagramGUI.largest = data[data.length - 1];
		}
		/* Override that paintComponent method of the JPanel class to add the box plot diagram */
		protected void paintComponent(Graphics g)
		{
			drawNumLine(g);
			drawBoxPlot(g);
		}
		/* Build the bottom number line */
		private void drawNumLine(Graphics g)
		{
			/* Draw basic number line just with the end points (This should never change) */
			g.drawLine(125, DIAGRAM_HEIGHT, 875, DIAGRAM_HEIGHT);
			g.drawLine(125, DIAGRAM_HEIGHT - 20, 125, DIAGRAM_HEIGHT + 20);
			g.drawLine(875, DIAGRAM_HEIGHT - 20, 875, DIAGRAM_HEIGHT + 20);
		
			/* Add an extra tick mark for each data point in the set */
			double conversionFactor = 750/(largest - smallest);
			for (int count = 0; count < data.length; count++) 
				if (count != smallestIndex && count != largestIndex)
					g.drawLine((int)Math.round((data[count]-smallest) * conversionFactor) + 125, DIAGRAM_HEIGHT - 10, (int)Math.round((data[count]-smallest) * conversionFactor) + 125, DIAGRAM_HEIGHT + 10);
		}
		/* Build the actual box plot */
		private void drawBoxPlot(Graphics g)
		{
			/* Draw the end points of the box plot (This should not change either */
			g.drawLine(125, DIAGRAM_HEIGHT - 120, 125, DIAGRAM_HEIGHT - 80);
			g.drawLine(875, DIAGRAM_HEIGHT - 120, 875, DIAGRAM_HEIGHT - 80);
		
			/* Build the rest of the box plot based on the data set provided */
			double conversionFactor = 750/(largest - smallest);
			
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
	/* Runs the program that will be run if the user clicks the 'Return to Data' button */
	private static class ReturnToData implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			GoToData();
		}
	}
}
