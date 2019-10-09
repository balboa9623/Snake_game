package Controller;

import java.util.EventObject;

public class InputEvent {
    public static final int KEY_PRESSED = 0;
    public static final int PREY_CREATE = 1;
    public static final int SNAKE_CREATE = 2;


    public static EventObject event;
    public int type;
}
