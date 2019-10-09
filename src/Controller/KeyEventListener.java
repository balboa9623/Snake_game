package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventListener extends KeyAdapter {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        InputEvent inputEvent = new InputEvent();
        inputEvent.event = e;
        inputEvent.type = InputEvent.KEY_PRESSED;
        Main.playerInputEventQueue.queue.add(inputEvent);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
