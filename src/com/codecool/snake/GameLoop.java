package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.SpeedUp;
import com.codecool.snake.entities.snakes.Snake;

import java.util.List;
import java.util.Random;

public class GameLoop {
    private Snake snake;
    private boolean running = false;

    public GameLoop(Snake snake) { this.snake = snake; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.step();
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
            spawnSpeed();
            spawnSimple();
            spawnEnemy();
            Game.destroyHeart();
        }

        Globals.getInstance().display.frameFinished();
    }

    private void spawnSpeed() {
        Random rnd = new Random();
        int low = 1;
        int high = 1000;
        int result = rnd.nextInt(high-low);
        if(result < 2) {
            new SpeedUp();
        }
    }

    private void spawnSimple() {
        Random rnd = new Random();
        int low = 1;
        int high = 1000;
        int result = rnd.nextInt(high-low);
        if(result < 4) {
            new SimplePowerUp();
        }
    }

    private void spawnEnemy() {
        Random rnd = new Random();
        int low = 1;
        int high = 1000;
        int result = rnd.nextInt(high-low);
        if(result < 3) {
            new SimpleEnemy();
        }
    }


    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
