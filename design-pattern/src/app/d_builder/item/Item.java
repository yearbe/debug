package app.d_builder.item;

import app.d_builder.packing.Packing;

/**
 * 食物条目 Item
 */
public interface Item {
    String name();
    Packing packing();
    double price();
}