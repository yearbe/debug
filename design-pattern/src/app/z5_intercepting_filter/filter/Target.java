package app.z5_intercepting_filter.filter;

public class Target {
    public void execute(String request) {
        System.out.println("Executing request: " + request);
    }
}