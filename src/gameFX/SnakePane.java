package gameFX;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class SnakePane extends StackPane {
	private Bounds gameBounds;
	private int MARGIN = 32;
	private GameManager gameManager;

	public SnakePane(GameManager gameManager) {
		this.gameManager = gameManager;
		this.gameBounds = gameManager.getLayoutBounds();
		getChildren().add(gameManager);
		gameManager.setToolBar(createToolBar());

		ChangeListener<Number> resized = (ov, oldValue, newValue) -> {
			double scale = Math.min(
					(getWidth() - MARGIN) / gameBounds.getWidth(),
					(getHeight() - MARGIN) / gameBounds.getHeight());
			gameManager.setScaleX(scale);
			gameManager.setScaleY(scale);
		};

		widthProperty().addListener(resized);
		heightProperty().addListener(resized);

		addKeyBoardHandler(this);
		setFocusTraversable(true);
		this.setOnMouseClicked(e -> requestFocus());
	}

	private VBox createToolBar() {
		VBox toolbar = new VBox(10);
		Button start = createButtonItem("Start Game", t -> gameManager.startGame());
		Button pause = createButtonItem("Pause Game", t -> gameManager.pauseGame());
		Button save = createButtonItem("Save Session", t -> gameManager.saveSession());
		Button restore = createButtonItem("Restore Game", t -> gameManager.restoreGame());
		Button tryAgain = createButtonItem("Try Again", t -> gameManager.tryAgain());
		Button quit = createButtonItem("Quit Game", t -> gameManager.quitGame());
		toolbar.getChildren().addAll(start, pause, save, restore, tryAgain, quit);
		return toolbar;
	}

	private Button createButtonItem(String msg, EventHandler<ActionEvent> t) {
		 Button btn = new Button();
		 btn.setText(msg);
		 btn.setOnAction(t);
		 btn.setMaxWidth(120);
		return btn;
	}

	private void addKeyBoardHandler(Node snakePane) {

		snakePane.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.isArrowKey()) {
				Direction dir = Direction.valueFor(keyCode);
				Snake.snakeDirectionProperty.set(dir);
			}
		});
	}

	public Group getGameManager() {
		return gameManager.getBoard();
	}
}
