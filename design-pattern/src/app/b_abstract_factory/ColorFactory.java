package app.b_abstract_factory;

import app.b_abstract_factory.color.Blue;
import app.b_abstract_factory.color.Color;
import app.b_abstract_factory.color.Green;
import app.b_abstract_factory.color.Red;
import app.b_abstract_factory.shape.Shape;

/**
 * ColorFactory
 */
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String color) {
        if (color == null) {
            return null;
        }
        if (color.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (color.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (color.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }

}