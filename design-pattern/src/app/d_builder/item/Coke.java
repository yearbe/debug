package app.d_builder.item;

/**
 * 可口可乐（实体类）
 * Coke
 */
public class Coke extends Drink {

    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public double price() {
        return 30;
    }

    
}