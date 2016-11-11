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
		Scene scene = new Scene(root);
		primaryStage.setTitle("Snake GameFX");
		scene.getStylesheets().add(Main.class.getResource("game.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	@Override
	public void init() throws Exception {
		root = new SnakePane(new GameManager());
		root.getGameManager().requestFocus();
	}

}
