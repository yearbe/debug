package app.d_builder;

import app.d_builder.meal.Meal;
import app.d_builder.meal.MealBuilder;

/**
 * 建造者模式（Builder Pattern）使用多个简单的对象一步一步构建成一个复杂的对象。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。
 * 一个 Builder 类会一步一步构造最终的对象。该 Builder 类是独立于其他对象的。
 */
public class Test {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("Veg Meal");
        vegMeal.showItems();
        System.out.println("Total Cost: " + vegMeal.getCost());

        System.out.println("-----------------------------------");

        Meal meatMeal = mealBuilder.prepareMeatMeal();
        System.out.println("Meat Meal");
        meatMeal.showItems();
        System.out.println("Total Cost: " + meatMeal.getCost());
    }
}