import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	Visual group = new Visual();
	


	
	Scene scene = new Scene(group,1100,650);
    primaryStage.setTitle("Graph Example");
    primaryStage.setScene(scene);
    primaryStage.show();
	}


}
