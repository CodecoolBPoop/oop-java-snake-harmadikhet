package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;




public class SimpleEnemy extends Enemy implements Animatable, Interactable {

    private static Random rnd = new Random();

    public SimpleEnemy() {
        super(10);

        setImage(Globals.getInstance().getImage("SimpleEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        setDirection();
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            setDirection();
            //destroy();
        }
        setX(getX() + getHeading().getX());
        setY(getY() + getHeading().getY());
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
