package app.t_observer.observer;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}