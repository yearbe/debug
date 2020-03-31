package app.d_builder.meal;

import app.d_builder.item.Coke;
import app.d_builder.item.VegBurger;

/**
 * 点餐
 * MealBuilder
 */
public class MealBuilder {

    public Meal prepareVegMeal() {
        // 蔬菜汉堡套餐
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }

    public Meal prepareMeatMeal() {
        // 鸡肉汉堡套餐
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Coke());
        return meal;
    }
}