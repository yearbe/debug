package app.d_builder.item;

import app.d_builder.packing.Packing;
import app.d_builder.packing.Wrapper;

/**
 * 汉堡包
 * Burger
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        // 汉堡使用纸袋包装
        return new Wrapper();
    }
    
}