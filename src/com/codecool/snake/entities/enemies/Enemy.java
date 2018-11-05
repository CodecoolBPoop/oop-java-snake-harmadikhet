package com.codecool.snake.entities.enemies;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Enemy extends GameEntity{
    private final int damage;

    public Point2D getHeading() {
        return heading;
    }

    private Point2D heading;
    private static Random rnd = new Random();

    public void setDirection(){
        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }


    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
