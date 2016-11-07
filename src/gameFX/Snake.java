package gameFX;



import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Snake {
	private Cell head;
	private Direction direction = Direction.UP;
	private GameManager gameManager;
	
	public Snake(GameManager gameManager) {
		this.gameManager = gameManager;
		init();
	}

	 void init() {
		//head.setState(State.HEAD);
		 move();
		Timeline timeline = new Timeline(move());
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	 private KeyFrame move() {
		 
		KeyFrame frame = new KeyFrame(Duration.millis(500), e -> {
			Location offset = head.getLacation().offset(direction);
			gameManager.getNextCell(offset).ifPresent(next -> {
				Cell last = head;
				last.setState(State.EMPTY);
				setHead(next);
			});
			
			
		});
		return frame;
	}

	public void setHead(Cell next) {
		this.head = next;
		head.setState(State.HEAD);
	}

	public void setLocation(Location loc) {
		this.head.setLocation(loc);
	}

	public Cell getHead() {
		return head;
	}
	
}
