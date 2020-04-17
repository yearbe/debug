package app.k_facade;

import app.k_facade.shape.Circle;
import app.k_facade.shape.Rectangle;
import app.k_facade.shape.Shape;
import app.k_facade.shape.Square;

public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}