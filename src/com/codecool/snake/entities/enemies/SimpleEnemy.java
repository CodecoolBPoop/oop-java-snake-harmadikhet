package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public SimpleEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        double posX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        double posY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        setX(posX);
        setY(posY);
        while (Globals.getInstance().display.getObjectList().get(0).getPosition() == this.getPosition()) {
            posX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
            posY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
            setX(posX);
            setY(posY);
        }
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
