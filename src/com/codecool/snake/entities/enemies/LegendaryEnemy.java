package com.codecool.snake.entities.enemies;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

import java.util.Random;


public class LegendaryEnemy extends Enemy implements Animatable, Interactable {

    private static Random rnd = new Random();

    private double snakeHeadX = Globals.getInstance().display.getObjectList().get(0).getX();
    private double snakeHeadY = Globals.getInstance().display.getObjectList().get(0).getY();

    private Point2D heading;
    private int direction = 90;
    private double speed = 3.5;

    public LegendaryEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("LegendaryEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (getX() > Globals.WINDOW_WIDTH) {
            heading = Utils.directionToVector(-direction, speed);
        } else if (getX() < 0) {
            heading = Utils.directionToVector(direction, speed);
        }
        setX(getX() + heading.getX());
    }

    @Override
    public void apply(GameEntity entity) {
        if (entity instanceof SnakeHead) {
            System.out.println(getMessage());
            destroy();
        }
        if (entity instanceof SnakeBody) {
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
