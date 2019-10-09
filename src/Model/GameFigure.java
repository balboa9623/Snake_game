package Model;

import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public abstract class GameFigure {
    public  Point2D.Float location;
    public static boolean done = false;
    public static int preyHitCount = 0;

    public GameFigure(float x, float y){
        location = new Point2D.Float(x, y);
    }

    public GameFigure()
    {
        this(0,0);
    }

    public void setLocation(float x, float y ){
        location.x = x;
        location.y = y;
    }

    public abstract void render(Graphics2D g2);
    public abstract void update();
    public abstract int getCollitionRadius();


    public boolean collideWith(GameFigure o) {
        var dist = this.location.distance(o.location);
        if(dist <= this.getCollitionRadius() + o.getCollitionRadius()){
            return true;
        }
        else {
            return false;
        }
    }
}
