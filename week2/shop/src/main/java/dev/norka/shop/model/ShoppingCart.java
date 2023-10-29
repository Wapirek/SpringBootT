package dev.norka.shop.model;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Item> items;

    public ShoppingCart() {
        this.items =new  ArrayList<>();
    }

    public void addNewItem(Item item){
        items.add(item);
    }
    public List<Item> getItems(){
        return items;
    }


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "items=" + items +
                '}';
    }
}
