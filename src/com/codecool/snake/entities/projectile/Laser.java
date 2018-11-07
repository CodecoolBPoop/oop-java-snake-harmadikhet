package com.codecool.snake.entities.projectile;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.EpicEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;


public class Laser extends GameEntity implements Animatable, Interactable {
    double speed = 4;
    public double laserrotation;

    public Laser(double headrotation){
        setImage(Globals.getInstance().getImage("Laser"));
        setPosition(Globals.getInstance().display.getObjectList().get(0).getPosition());
        laserrotation = headrotation;
    }


    @Override
    public void step(){
        if (isOutOfBounds()){
            destroy();
        }
        Point2D heading = Utils.directionToVector(laserrotation,speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SimpleEnemy){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return ("hit");
    }
}



