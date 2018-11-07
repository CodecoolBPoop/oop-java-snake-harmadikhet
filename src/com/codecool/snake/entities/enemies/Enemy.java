package com.codecool.snake.entities.enemies;

import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import javafx.geometry.Point2D;

import java.util.Random;

public abstract class Enemy extends GameEntity{
    private final int damage;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
}
