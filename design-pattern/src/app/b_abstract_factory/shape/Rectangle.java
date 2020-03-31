package app.b_abstract_factory.shape;

/**
 * Rectangle
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
    
}