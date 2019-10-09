package Model.Snake;


import Controller.Main;
import Controller.PlayerInputEventQueue;
import Controller.observer.Observer;
import Controller.observer.Subject;
import Model.GameFigure;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Snake extends GameFigure implements Subject {

    public static final int SIZE = 5;
    // dont want to be final because the speed increases as collotion occurs with the prey..
    public static double unit_move = .5;

    public static Color color;// = Color.green;

    public  Rectangle2D.Float snake_head;

    public static Point2D.Float snakeLocation;

    public final int STATE_DONE = 0;
    public final int STATE_ACTIVE = 1;
    public int state;


    SnakeAnimateStratergy animateStratergy;
    ArrayList<Observer> listeners = new ArrayList<>();

    public Snake(int x, int y){
        super(x, y);
        this.snakeLocation = new Point2D.Float((float) x, (float) y);
        snake_head = new Rectangle2D.Float(location.x, location.y, SIZE, SIZE);
        //snake_head = new Rectangle2D.Float(x - SIZE, y - SIZE, SIZE, SIZE);
        color = color.GREEN;
        state = STATE_ACTIVE;
        animateStratergy = new SnakeAnimateMoving(this);
    }



    public void setPosition(int x, int y){
        this.location.x = x;
        this.snake_head.x = x;
        this.location.y = y;
        this.snake_head.y = y;
    }
    @Override
    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.setStroke(new BasicStroke(6));
        g2.draw(snake_head);
    }



    @Override
    public void update() {
        updateState();
        animateStratergy.animate();
    }

    private void updateState() {
        if(state == STATE_ACTIVE){
            animateStratergy = new SnakeAnimateMoving(this);
        }
        else if(state == STATE_DONE){
            //super.done = true;
            animateStratergy = new SnakeAnimateDone(this);
            notifyEvent();
        }
    }

    @Override
    public int getCollitionRadius() {
        return  (SIZE/2);
    }

    @Override
    public void attachListener(Observer o) {
        listeners.add(o);
    }

    @Override
    public void detachListener(Observer o) {
        listeners.add(o);
    }

    @Override
    public void notifyEvent() {
        for (var all : listeners){
            all.eventRecieved();
        }
    }
}
