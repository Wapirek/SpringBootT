package dev.norka.shop.controller;

import dev.norka.shop.model.Item;
import dev.norka.shop.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController

public class ShopController {


    private final ShoppingCart shoppingCart;
    @Value("${spring.profiles.active}")
    private String shopType;
    @Value("${shop.vat}")
    private int vat;
    @Value("${shop.discount}")
    private int discount;

    public ShopController(){
        shoppingCart = new ShoppingCart();

        Random rand = new Random();

        shoppingCart.addNewItem(new Item("Pomidory", (int)(Math.random() * 250 + 50)));
        shoppingCart.addNewItem(new Item("Kalarepa",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Kubek",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Krzesło",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Laptop",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Ogórek",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Monitor",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Ser",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Herbata",(int)(Math.random()*250+50)));
        shoppingCart.addNewItem(new Item("Kawa",(int)(Math.random()*250+50)));


    }

    @GetMapping()
    public ResponseEntity getAllItem(){

        StringBuilder body= new StringBuilder();

        if(shopType.equals("PLUS") || shopType.equals("PRO")){
            upgradedShopResponse(body);
        }else {
          normalShopResponse(body);
        }
        return new ResponseEntity<>(body.toString(),HttpStatus.OK);

    }

    @PostMapping("/add")
    public ResponseEntity addNewItemToCart(@RequestBody Item newItem){
        shoppingCart.addNewItem(newItem);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    private void upgradedShopResponse(StringBuilder body){
        for(Item item: shoppingCart.getItems()){
            body.append(item.getName()).append(" Price:").append(item.getPrice()).append(" Vatinclude:").append(vat*item.getPrice()/100).append("</br>");
            if( shopType.equals("PRO")){
                body.append("Discount :").append((100-discount)*item.getPrice()/100).append("</br>");
            }
            body.append("</br>");
        }
    }

    private void normalShopResponse(StringBuilder body) {
        for(Item item: shoppingCart.getItems()){
            body.append(item.getName()).append(" Price:").append(item.getPrice()).append("</br>");
            body.append("</br>");
        }
    }



}
