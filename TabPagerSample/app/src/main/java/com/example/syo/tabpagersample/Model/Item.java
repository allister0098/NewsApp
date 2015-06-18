package com.example.syo.tabpagersample.Model;

import java.io.Serializable;

/**
 * Created by syo on 2015/06/17.
 */
// Item.java
public class Item implements Serializable{
    // 記事のタイトル
    private CharSequence mTitle;
    // 記事の本文
    private CharSequence mDescription;
    // 詳しい記事のURL
    private CharSequence mUrl;
    // 画像のURL
    private CharSequence imgUrl;

    // 段落記事
    private StringBuffer mParagraph;

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

    public CharSequence getUrl() {return mUrl;}

    public void setUrl(CharSequence Url) {mUrl = Url;}


    public void setImgUrl(CharSequence imgUrl) {
        this.imgUrl = imgUrl;
    }

    public CharSequence getImgUrl() {
        return imgUrl;
    }
}
