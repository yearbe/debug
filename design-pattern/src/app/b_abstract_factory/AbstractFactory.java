package app.b_abstract_factory;

import app.b_abstract_factory.shape.Shape;
import app.b_abstract_factory.color.Color;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}