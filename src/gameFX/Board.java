package gameFX;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Board extends Group {
	
	private VBox vGame = new VBox();
	
	public Board() {
		createScorePresentation();
	}

	private void createScorePresentation() {
		HBox hTop = new HBox();
		hTop.setAlignment(Pos.CENTER_RIGHT);
		
		hTop.setMinSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
		hTop.setMaxSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
		hTop.setPrefSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
		
		Label lblScore = new Label("SCORE");
		Label lblScorePoint = new Label("0");
		
		VBox vScore = new VBox(-3);
		vScore.getChildren().add(lblScore);
		vScore.getChildren().add(lblScorePoint);
		
		Label lblBest = new Label("BEST");
		Label lblBestPoint = new Label("0");
		
		VBox vRecord = new VBox(-3);
		vRecord.getChildren().add(lblBest);
		vRecord.getChildren().add(lblBestPoint);
		
		HBox hScores = new HBox(10);
		hScores.getChildren().addAll(vScore, vRecord);
		
		HBox hFill = new HBox();
		HBox.setHgrow(hFill, Priority.ALWAYS);
		
		hTop.getChildren().addAll(hFill, hScores);
		
		vGame.getChildren().add(hTop);
		
		getChildren().add(vGame);
	}
}
