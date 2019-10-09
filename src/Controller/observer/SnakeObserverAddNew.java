package Controller.observer;

import Controller.InputEvent;
import Controller.Main;
import Model.Snake.Snake;

public class SnakeObserverAddNew implements Observer {
    @Override
    public void eventRecieved() {
        InputEvent inputEvent = new InputEvent();
        int i = Main.gameData.friendObject.size() - 1;
        var snake = (Snake) Main.gameData.friendObject.get(i);
        inputEvent.event = new SnakeCreateEvent("SNAKE", (int) snake.location.x, (int) snake.location.y);
        inputEvent.type = inputEvent.SNAKE_CREATE;
        Main.playerInputEventQueue.queue.add(inputEvent);
    }
}
