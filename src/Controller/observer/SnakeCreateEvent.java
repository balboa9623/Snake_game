package Controller.observer;

import java.util.EventObject;

public class SnakeCreateEvent extends EventObject {
    private int x;
    private int y;

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public SnakeCreateEvent(Object source, int x, int y) {
        super(source);
        this.x = x;
        this.y = y;
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }
}
