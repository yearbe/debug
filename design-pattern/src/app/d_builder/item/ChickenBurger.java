package app.d_builder.item;

/**
 * 鸡肉汉堡包（实体类）
 * ChickenBurger
 */
public class ChickenBurger extends Burger {

    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public double price() {
        return 50;
    }

    
}