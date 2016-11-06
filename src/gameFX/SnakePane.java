package gameFX;


import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class SnakePane extends StackPane {
	private GameManager gameManger;
	private Bounds gameBounds;
	private int MARGIN = 32;
	
	public SnakePane(GameManager gameManager) {
		this.gameManger = gameManager;
		this.gameBounds = gameManager.getLayoutBounds();
		getChildren().add(gameManager);
		
		ChangeListener<Number> resized = (ov, oldValue, newValue) -> {
			double scale = Math.min((getWidth() - MARGIN) / gameBounds.getWidth(), (getHeight() - MARGIN) / gameBounds.getHeight());
			gameManager.setScaleX(scale);
			gameManager.setScaleY(scale);
		};
		
		widthProperty().addListener(resized);
		heightProperty().addListener(resized);
		
		addKeyBoardHandler(this);
	}

	private void addKeyBoardHandler(Node snakePane) {
		
	}
}
