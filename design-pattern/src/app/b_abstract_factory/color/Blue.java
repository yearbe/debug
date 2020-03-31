package app.b_abstract_factory.color;

/**
 * Blue
 */
public class Blue implements Color {

    @Override
   public void fill() {
      System.out.println("Inside Blue::fill() method.");
   }
}