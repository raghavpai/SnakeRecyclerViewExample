package com.example.dasari.snakerecycler;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dasari on 26-10-2017.
 */

public class SnakeRecyclerJson {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Item {

        @SerializedName("uuid")
        @Expose
        private String uuid;
        @SerializedName("imageUrlString")
        @Expose
        private String imageUrlString;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getImageUrlString() {
            return imageUrlString;
        }

        public void setImageUrlString(String imageUrlString) {
            this.imageUrlString = imageUrlString;
        }

    }
}
