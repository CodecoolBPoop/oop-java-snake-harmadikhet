package com.codecool.snake.entities;

import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.snakes.Snake;

import java.util.ArrayList;
import java.util.List;

public class Health extends GameEntity {
    public Health(int x, int y) {
        setImage(Globals.getInstance().getImage("Health"));
        setX(x);
        setY(y);
    }
    }
