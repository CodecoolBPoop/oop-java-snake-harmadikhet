package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.projectile.Laser;

import com.codecool.snake.entities.powerups.SpeedDown;
import com.codecool.snake.entities.powerups.SpeedUp;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;

    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public static float getTurnRate() {
        return turnRate;
    }

    public void controlSnake(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }
        if (turnDirection.equals(SnakeControl.SHOOT)){
            new Laser(headRotation);
        }


        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }



    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof Enemy) {
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage());
        }
        if (entity instanceof SimplePowerUp) {
            System.out.println(getMessage());
            snake.addPart(4);
            Globals.getInstance().setGameScore(Globals.getInstance().getGameScore() + (1 * Globals.getInstance().getMultiplier()));
        }
        if (entity instanceof SpeedUp) {
            System.out.println(getMessage());
            snake.addSpeed(1);
            Globals.getInstance().setMultiplier(Globals.getInstance().getMultiplier()+1);

        }
        if(entity instanceof SpeedDown){
            System.out.println(getMessage());
            snake.slowSpeed(1);
            if (Globals.getInstance().getMultiplier() > 1){
            Globals.getInstance().setMultiplier(Globals.getInstance().getMultiplier()-1);
            }

        }
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }
}
