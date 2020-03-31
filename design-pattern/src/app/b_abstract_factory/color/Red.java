package app.b_abstract_factory.color;

/**
 * Red
 */
public class Red implements Color {

    @Override
   public void fill() {
      System.out.println("Inside Red::fill() method.");
   }
}