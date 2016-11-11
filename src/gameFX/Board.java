package gameFX;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.net.ssl.SNIHostName;

import gameFX.Board.Overlay;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Group {
	


	private VBox vGame = new VBox();
	private HBox vPanel = new HBox();
	private VBox vPanelPresentation;
	private HBox overlay = new HBox();
	private HBox buttonOverlay = new HBox();
	private VBox txtOverlay = new VBox();
	private Button btnTryAgain = new Button("Try Again");
	
	private BooleanProperty layerOnProperty = new SimpleBooleanProperty(false);
	private BooleanProperty gameOverProperty = new SimpleBooleanProperty(false);
	
	private List<Cell> cells = new ArrayList<>();
	
	private Group gridGroup = new Group();

	private Label lblScorePoint;
	
	public Board() {
		createScorePresentation();
		createGrid();
		initGameProperties();
	}


	private void initGameProperties() {
		overlay.setMinSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
		overlay.setTranslateY(Config.TOP_HEIGHT + Config.GAP_HEIGHT);
		overlay.setTranslateX(Config.GRID_WIDTH / 2);
		overlay.setAlignment(Pos.CENTER);
		overlay.getChildren().add(txtOverlay);
		
		buttonOverlay.setMinSize(Config.GRID_WIDTH, Config.GRID_WIDTH / 2d);
		buttonOverlay.setTranslateY(Config.TOP_HEIGHT + Config.GAP_HEIGHT + Config.GRID_WIDTH / 2d);
		buttonOverlay.setTranslateX(Config.GRID_WIDTH / 2);
		buttonOverlay.setAlignment(Pos.CENTER);
		
		gameOverProperty.addListener(new Overlay("Game Over!", btnTryAgain));
		
		layerOnProperty.addListener((ov, old, newValue) -> {
			if(newValue){
				 buttonOverlay.getChildren().get(0).requestFocus();
			}else{
				getChildren().removeAll(overlay, buttonOverlay);
				getParent().requestFocus();
			}
		});
		
	}


	private void createScorePresentation() {
		
		vPanelPresentation = new VBox();
		vPanelPresentation.setMinSize(Config.GRID_WIDTH / 2, Config.TOP_HEIGHT + Config.GAP_HEIGHT + Config.GRID_WIDTH + Config.TOP_HEIGHT);
		vPanelPresentation.setMaxSize(Config.GRID_WIDTH / 2, Config.TOP_HEIGHT + Config.GAP_HEIGHT + Config.GRID_WIDTH + Config.TOP_HEIGHT);
		vPanelPresentation.setPrefSize(Config.GRID_WIDTH / 2, Config.TOP_HEIGHT + Config.GAP_HEIGHT + Config.GRID_WIDTH + Config.TOP_HEIGHT);
		
		VBox vFill = new VBox();
		vFill.setMinHeight(Config.TOP_HEIGHT + Config.GAP_HEIGHT);
		vFill.setMaxHeight(Config.TOP_HEIGHT + Config.GAP_HEIGHT);
		vFill.setPrefHeight(Config.TOP_HEIGHT + Config.GAP_HEIGHT);
		vPanelPresentation.getChildren().add(vFill);
		
		HBox hTop = new HBox();
		hTop.setAlignment(Pos.CENTER_RIGHT);
		
		hTop.setMinSize(Config.GRID_WIDTH, Config.TOP_HEIGHT);
		hTop.setMaxSize(Config.GRID_WIDTH, Config.TOP_HEIGHT);
		hTop.setPrefSize(Config.GRID_WIDTH, Config.TOP_HEIGHT);
		
		Label lblScore = new Label("SCORE");
		lblScorePoint = new Label("0");
		
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
		
		HBox hTime = new HBox();
		hTime.setMinSize(Config.GRID_WIDTH, Config.GAP_HEIGHT);
		hTime.setMaxSize(Config.GRID_WIDTH, Config.GAP_HEIGHT);
		hTime.setPrefSize(Config.GRID_WIDTH, Config.GAP_HEIGHT);
		
		vGame.getChildren().add(hTime);
		
		vPanel.getChildren().addAll(vPanelPresentation, vGame);
		getChildren().add(vPanel);
		
	}
	
	private void createGrid() {
		IntStream.range(0, Config.GRID_SIZE).boxed().forEach(x -> {
			IntStream.range(0, Config.GRID_SIZE).boxed().forEach(y -> {
				Cell cell = createCell(x, y);
				cell.setLocation(new Location(x, y));
				cells.add(cell);
			});
		});
		
		cells.forEach(cell -> {
			gridGroup.getChildren().add(cell);
		});
		
		HBox hBottom = new HBox();
		
		gridGroup.setManaged(false);
		gridGroup.setLayoutX(Config.BORDER_WIDTH / 2d);
		gridGroup.setLayoutY(Config.BORDER_WIDTH / 2d);
		hBottom.setMinSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
	    hBottom.setPrefSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
	    hBottom.setMaxSize(Config.GRID_WIDTH, Config.GRID_WIDTH);
		hBottom.getChildren().add(gridGroup);
		
		vGame.getChildren().add(hBottom);
		
	}



	private Cell createCell(int i, int j) {
		Cell cell = new Cell(i, j);
		cell.setStroke(Color.GREY);
		return cell;
	}

	public List<Cell> getCells() {
		return cells;
	}

	public Label getScorePoint() {
		return lblScorePoint;
	}


	public void setToolBar(VBox toolbar) {
		vPanelPresentation.getChildren().add(toolbar);
	}


	public void setGameOver(boolean value) {
		if(!gameOverProperty.get())
			gameOverProperty.set(value);
	}
	
	public class Overlay implements ChangeListener<Boolean> {
		
		private String message;
		private Button btn;

		public Overlay(String msg, Button btn) {
			this.message = msg;
			this.btn = btn;
		}

		@Override
		public void changed(ObservableValue<? extends Boolean> arg0,
				Boolean old, Boolean newValue) {
			if(newValue){
				txtOverlay.getChildren().add(new Label(message));
				buttonOverlay.getChildren().add(btn);
				getChildren().addAll(overlay, buttonOverlay);
				if(!layerOnProperty.get())
					layerOnProperty.set(true);
			}
		}
		
	}
}
