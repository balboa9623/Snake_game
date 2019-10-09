package Controller;

import Controller.observer.SnakeCreateEvent;
import Model.Snake.Snake;

import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class PlayerInputEventQueue {
    //public static float x = 0;
    //public static float y = 0;
    //public static int snake_unit_move = 5;

    public static boolean movingRight = false;
    public static boolean movingLeft = false;
    public static boolean movingUp = false;
    public static boolean movingDown = false;
    public LinkedList<InputEvent> queue = new LinkedList<>();
    //public Snake snake = (Snake) Main.gameData.friendObject.get(Main.index_snake);
    public void processInputEvents() {

        while (!queue.isEmpty()) {
            InputEvent inputEvent = queue.removeFirst();
            switch (inputEvent.type) {
                case InputEvent.KEY_PRESSED:
                    var snake = Main.gameData.friendObject.get(Main.index_snake);
                    KeyEvent ke = (KeyEvent) inputEvent.event;
                    switch (ke.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            movingUp = true;

                            if(!movingDown){
                               movingUp = true;
                                //snake.location.y -= Snake.unit_move;
                            }else{
                                movingDown = true;
                                movingUp = false;
                            }
                            movingLeft = false;
                            movingRight = false;
                            break;
                        case KeyEvent.VK_DOWN:
                            movingDown = true;
                            if(!movingUp){
                                movingDown = true;
                                //snake.location.y += Snake.unit_move;
                            }else{
                                movingDown = false;
                                movingUp = true;
                            }
                            movingLeft = false;
                            movingRight = false;
                            break;
                        case KeyEvent.VK_LEFT:
                            movingLeft = true;
                            if(!movingRight){
                                movingLeft = true;
                                //snake.location.x -= Snake.unit_move;
                            }else{
                                movingLeft = false;
                                movingRight = true;
                            }
                            movingUp = false;
                            movingDown = false;

                            break;
                        case KeyEvent.VK_RIGHT:
                            movingRight = true;
                            if(!movingLeft){
                                movingRight = true;
                                //snake.location.x += Snake.unit_move;
                            }else{
                                movingRight = false;
                                movingLeft = true;
                            }
                            movingUp = false;
                            movingDown = false;
                            break;
                    }
                    break;
                    case InputEvent.SNAKE_CREATE:
                        SnakeCreateEvent se = (SnakeCreateEvent) InputEvent.event;
                        Main.addSnakeWithListener();
            }
        }
    }
}
