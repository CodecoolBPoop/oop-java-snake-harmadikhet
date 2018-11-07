package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;

import java.util.List;

import com.codecool.snake.entities.snakes.Snake;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Display {
    private Pane displayPane;
    private DelayedModificationList<GameEntity> gameObjects = new DelayedModificationList<>();
    private Button restartButton = new Button("Restart");

    public Display(Pane pane) {
        displayPane = pane;
        restartButton.setOnAction((event) -> {
            System.out.println("Restart...");
            clear();
            Snake.setHealth(30);
            Globals.getInstance().display = new Display(displayPane);
            Globals.getInstance().stopGame();
            Globals.getInstance().game.init();
            Globals.getInstance().game.start();
            Globals.getInstance().setGameScore(0);
        });
        displayPane.getChildren().add(restartButton);
    }

    public void add(GameEntity entity) {
        displayPane.getChildren().add(entity);
        gameObjects.add(entity);
    }

    public void remove(GameEntity entity) {
        displayPane.getChildren().remove(entity);
        gameObjects.remove(entity);
    }

    public List<GameEntity> getObjectList() {
        return gameObjects.getList();
    }

    public void frameFinished() {
        gameObjects.doPendingModifications();
    }

    public boolean spawnCheck(Class objectClass) {
        return gameObjects.isThereAPowerUp(objectClass);
    }

    public void updateSnakeHeadDrawPosition(GameEntity snakeHead) {
        displayPane.getChildren().remove(snakeHead);
        displayPane.getChildren().add(snakeHead);
    }

    public void clear() {
        displayPane.getChildren().clear();
        gameObjects.clear();
    }

    public void gameOver() {
        clear();
        Text gameOverText = new Text();
        Text gameScoreText = new Text();
        gameOverText.setText("Game Over!");
        gameOverText.setX(400);
        gameOverText.setY(300);
        gameOverText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        gameScoreText.setText("Score: " + Integer.toString(Globals.getInstance().getGameScore()));
        gameScoreText.setX(400);
        gameScoreText.setY(350);
        gameScoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        displayPane.getChildren().add(gameOverText);
        displayPane.getChildren().add(gameScoreText);
        displayPane.getChildren().add(restartButton);
    }
}
