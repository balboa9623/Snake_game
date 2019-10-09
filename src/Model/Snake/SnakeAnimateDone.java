package Model.Snake;

import Controller.Main;

public class SnakeAnimateDone implements SnakeAnimateStratergy {
    public Snake context;
    public SnakeAnimateDone(Snake context) {
        this.context = context;
    }

    @Override
    public void animate() {
        context.done = true;
        Main.gameData.update();
        Main.gameOver();
    }
}
