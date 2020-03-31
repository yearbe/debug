package app.a_factory;

import app.a_factory.shape.Circle;
import app.a_factory.shape.Rectangle;
import app.a_factory.shape.Shape;
import app.a_factory.shape.Square;

/**
 * ShapeFactory
 */
public class ShapeFactory {

    /**
     * 使用getShape方法获取形状类型的对象
     * @return
     */
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("square")) {
            return new Square();
        }
        return null;
    }
}