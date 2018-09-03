package com.butola.restconsumer.data;

/**
 * Created by yogibutola on 8/25/18.
 */
public class Item {

    Long itemID;

    String name;

    String description;

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
