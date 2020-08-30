package com.knoxtech.majhishala.ui.Model;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ItemData {
    private String name,image,url;

    public ItemData() {

    }

    public ItemData(String name, String image, String url) {
        this.name = name;
        this.image = image;
        this.url = url;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
