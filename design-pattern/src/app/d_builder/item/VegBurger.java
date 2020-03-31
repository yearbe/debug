package app.d_builder.item;

/**
 * 蔬菜汉堡（实体类）
 * VegBurger
 */
public class VegBurger extends Burger {

    @Override
    public String name() {
        return "Veg Burger";
    }

    @Override
    public double price() {
        return 25;
    }

    
}