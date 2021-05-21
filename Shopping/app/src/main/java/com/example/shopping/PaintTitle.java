package com.example.shopping;

import java.io.Serializable;

public class PaintTitle implements Serializable {
    public int imgID;
    public String title;
    public String price;

    public PaintTitle(int id, String str, String _price){
        imgID = id;
        title = str;
        price = _price;
    }
}
