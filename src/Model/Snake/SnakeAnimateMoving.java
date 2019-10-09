package Model.Snake;

import Controller.Main;
import Controller.PlayerInputEventQueue;
import View.MyCanvas;

import java.awt.*;

public class SnakeAnimateMoving implements SnakeAnimateStratergy{

    public Snake context;

    public SnakeAnimateMoving(Snake context){
        this.context = context;
    }

    @Override
    public void animate() {

        if(PlayerInputEventQueue.movingDown){

            var snake = (Snake) Main.gameData.friendObject.get(0);
            snake.snake_head.x = snake.snake_head.x;
            snake.snake_head.y += Snake.unit_move/2;

            snake.location.x = snake.snake_head.x;
            snake.location.y = snake.snake_head.y;

            /*context.snake_head.x = context.snake_head.x;
            context.snake_head.y += context.unit_move;

            context.location.x = context.snake_head.x;
            context.location.y = context.snake_head.y;*/

        }
        else if(PlayerInputEventQueue.movingUp){

            var snake = (Snake) Main.gameData.friendObject.get(0);
            snake.snake_head.x = snake.snake_head.x;
            snake.snake_head.y -= Snake.unit_move/2;

            snake.location.x = snake.snake_head.x;
            snake.location.y = snake.snake_head.y;

            /*context.snake_head.x = context.snake_head.x;
            context.snake_head.y  -= context.unit_move;

            context.location.x = context.snake_head.x;
            context.location.y = context.snake_head.y;*/

        }
        else if(PlayerInputEventQueue.movingLeft) {

            var snake = (Snake) Main.gameData.friendObject.get(0);
            snake.snake_head.y = snake.snake_head.y;
            snake.snake_head.x -= Snake.unit_move/2;

            snake.location.x = snake.snake_head.x;
            snake.location.y = snake.snake_head.y;

            /*context.snake_head.y = context.snake_head.y;
            context.snake_head.x -= context.unit_move;

            context.location.x = context.snake_head.x;
            context.location.y = context.snake_head.y;*/

        }
        else if(PlayerInputEventQueue.movingRight) {


            var snake = (Snake) Main.gameData.friendObject.get(0);
            snake.snake_head.y = snake.snake_head.y;
            snake.snake_head.x += Snake.unit_move/2;

            snake.location.x = snake.snake_head.x;
            snake.location.y = snake.snake_head.y;

            /*context.snake_head.y = context.snake_head.y;
            context.snake_head.x += context.unit_move;

            context.location.x = context.snake_head.x;
            context.location.y = context.snake_head.y;*/

        }
    }

}
