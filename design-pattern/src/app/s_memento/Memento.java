package app.s_memento;

public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
}