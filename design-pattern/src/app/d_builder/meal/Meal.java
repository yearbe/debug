package app.d_builder.meal;

import java.util.ArrayList;
import java.util.List;

import app.d_builder.item.Item;

/**
 * 餐点
 * Meal
 */
public class Meal {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void clear() {
        items.clear();
    }

    public Double getCost() {
        double cost = 0;
        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println("Item: " + item.name() + ", Packing: " + item.packing().pack() + ", Price: " + item.price());
        }
    }
    
}