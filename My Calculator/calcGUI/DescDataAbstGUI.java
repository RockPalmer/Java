package calcGUI;

public abstract class DescDataAbstGUI extends BaseGUI
{
	protected static double[] resultVals;
	protected static double[] dataSet;
	/* Program that will run to move the user from the diagram GUI back to the data */
	protected static void GoToData()
	{
		DescDataGUI.buildGUI();
		/* Restore the users previously entered values so that he/she does not
		 * lose them when he/she moves to the diagram */
		for(int count = 0; count < resultVals.length; count++)
			DescDataGUI.answerBoxes[count].setText("" + resultVals[count]);
		DescDataGUI.textFields.get(0).setText("" + dataSet[0]);
		for(int count = 1; count < dataSet.length; count++)
		{
			DescDataGUI.addPoint.addNewPoint();
			DescDataGUI.textFields.get(count).setText("" + dataSet[count]);
		}
	}
	/* Program that will run to move the user from the data GUI to the diagram */
	protected static void GoToDiagram()
	{
		DescDataDiagramGUI.showNewPanel();
	}
	/* Program that will run to rebuild the diagram GUI according to the various parameters
	 * so that it can be later added to the frame */
	protected static void setPanel2() 
	{
		DescDataDiagramGUI.buildNewGUI();
	}
}
