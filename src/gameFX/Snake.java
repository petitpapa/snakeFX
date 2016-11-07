package gameFX;



import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

public class Snake {
	private Cell head;
	private GameManager gameManager;
	public static ObjectProperty<Direction> snakeDirectionProperty = new SimpleObjectProperty<Direction>();
	public static BooleanProperty snakeEatFood = new SimpleBooleanProperty();
	
	private List<Cell> tail = new ArrayList<>();
	
	public Snake(GameManager gameManager) {
		this.gameManager = gameManager;
		snakeDirectionProperty.set(Direction.UP);
	}

	 void init() {
		
		move();
		Timeline timeline = new Timeline(move());
		
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	 private KeyFrame move() {
		 
		KeyFrame frame = new KeyFrame(Duration.millis(500), e -> {
			Location offset = head.getLacation().offset(snakeDirectionProperty.get());
			gameManager.getNextCell(offset).ifPresent(next -> {
				
				Cell last = head;
				
				for(int i = 0; i < tail.size(); i++){
					Cell c = tail.get(i);
					last.setState(State.TAIL);
					tail.set(i, last);
					last = c;
				}
				if(next.getState().equals(State.FOOD)){
					tail.add(last);
					snakeEatFood.set(true);
				}
				snakeEatFood.set(false);
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
