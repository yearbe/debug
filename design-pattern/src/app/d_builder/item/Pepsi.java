package app.d_builder.item;

/**
 * 百事可乐（实体类）
 * Pepsi
 */
public class Pepsi extends Drink {

    @Override
    public String name() {
        return "Pepsi";
    }

    @Override
    public double price() {
        return 35;
    }

}