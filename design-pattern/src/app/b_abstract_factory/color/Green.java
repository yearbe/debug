package app.b_abstract_factory.color;

/**
 * Green
 */
public class Green implements Color {

    @Override
   public void fill() {
      System.out.println("Inside Green::fill() method.");
   }
}