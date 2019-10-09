package Controller.observer;

public interface Subject {
    void attachListener(Observer o);
    void detachListener(Observer o);
    void notifyEvent();
}
