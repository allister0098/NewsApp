package com.example.syo.newsapp;

/**
 * Created by syo on 2015/06/13.
 */
// Item.java
public class Item {
    // 記事のタイトル
    private CharSequence mTitle;
    // 記事の本文
    private CharSequence mDescription;

    public Item() {
        mTitle = "";
        mDescription = "";
    }

    public CharSequence getDescription() {
        return mDescription;
    }

    public void setDescription(CharSequence description) {
        mDescription = description;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
    }
}
