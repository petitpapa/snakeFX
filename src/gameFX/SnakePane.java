package gameFX;


import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class SnakePane extends StackPane {
	private Bounds gameBounds;
	private int MARGIN = 32;
	private GameManager gameManager;
	
	public SnakePane(GameManager gameManager) {
		this.gameManager = gameManager;
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
		setFocusTraversable(true);
	    this.setOnMouseClicked(e -> requestFocus());
	}

	private void addKeyBoardHandler(Node snakePane) {
		
		snakePane.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if(keyCode.isArrowKey()){
				Direction dir = Direction.valueFor(keyCode);
				Snake.snakeDirectionProperty.set(dir);
				System.out.println(dir);
			}
		});
	}

	public Group getGameManager() {
		return gameManager.getBoard();
	}
}
