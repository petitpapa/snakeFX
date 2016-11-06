package gameFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private SnakePane root;
	
	public static void main(String... args) {
		launch(Main.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new SnakePane(new GameManager());
		Scene scene = new Scene(root);
		primaryStage.setTitle("Snake GameFX");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
