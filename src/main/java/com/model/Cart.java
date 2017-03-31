package com.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Student on 3/29/2017.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private Map<Item, Long> contents = new HashMap<>();

    public Map<Item, Long> getContents(){

        return contents;
    }

    public Set<Item> getItems(){

        return contents.keySet();
    }

    public void addItem(Item item, Long count){

        if (contents.containsKey(item)){
            contents.put(item, contents.get(item) + count);
        }
        else{
            contents.put(item, count);
        }
    }

    public void removeItem(Item item) {
        contents.remove(item);

    }
    public void clearCart(){
        contents.clear();
    }
    @Override
    public String toString(){

        return contents.toString();
    }

    public double getTotalCost(){
        double totalCost = 0;
        for (Item item : contents.keySet()){

            totalCost += item.getPrice();
        }
        return totalCost;
    }
}
