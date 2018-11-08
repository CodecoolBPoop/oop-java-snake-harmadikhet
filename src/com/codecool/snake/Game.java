package com.codecool.snake;

import com.codecool.snake.entities.enemies.EpicEnemy;
import com.codecool.snake.entities.Health;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.LegendaryEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;


import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sun.font.TrueTypeFont;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import  java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Game extends Pane {
    private Snake snake = null;
    private GameTimer gameTimer = new GameTimer();
    private static Health h1;
    private static Health h2;
    private static Health h3;

    public static void destroyHeart(){
        if (Snake.getHealth() == 20) {
            h3.destroy();
        } else if (Snake.getHealth() == 10) {
            h2.destroy();
        } else if (Snake.getHealth() == 0){
            h1.destroy();
        }
    }

    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }

    public void init() {
        spawnSnake();
        Snake.setSpeed(2);
        Globals.getInstance().display.frameFinished();
        spawnEnemies(1);
        spawnPowerUps(2);
        spawnSpeedUps(1);

        spawnHearts();

        GameLoop gameLoop = new GameLoop(snake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }


    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) {
            new EpicEnemy();
            new LegendaryEnemy();
            SimpleEnemy enemy = new SimpleEnemy();
            while (GameLoop.checkCollisionOnSpawn(enemy)) {
                enemy.destroy();
                enemy = new SimpleEnemy();
            }
        }
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void spawnSpeedUps(int numberOfSpeedUps) {
        for(int i =0; i < numberOfSpeedUps; ++i) new SpeedUp();
    }





    private void spawnHearts(){
        h1 = new Health(20, 40);
        h2 = new Health(80, 40);
        h3 = new Health(140, 40);
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
