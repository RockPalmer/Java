package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application 
{
	public void start(Stage primaryStage) 
	{
		try 
		{
			Assignment6 assignment = new Assignment6();
			assignment.start(primaryStage);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
