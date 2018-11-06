package com.codecool.snake.entities;

import com.codecool.snake.Globals;


public class Health extends GameEntity {
    public Health(int x, int y) {
        setImage(Globals.getInstance().getImage("Health"));
        setX(x);
        setY(y);
    }
}
