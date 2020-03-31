package app.d_builder.item;

import app.d_builder.packing.Bottle;
import app.d_builder.packing.Packing;

/**
 * 饮料
 * Drink
 */
public abstract class Drink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }
}