package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is required to get any JavaFX application to run on the current version of JDK
 * and thus, is the driver class.
 */
public class Main extends Application 
{
	public void start(Stage primaryStage) 
	{
		try 
		{
			MainMenu.buildGUI(primaryStage);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
