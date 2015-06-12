package com.example.syo.newsapp;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by syo on 2015/06/13.
 */
public class RssReaderActivity extends ListActivity {
    private ArrayList mItems;
    private RssListAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Itemオブジェクトを保持するためのリストを生成し、アダプタに追加する
        mItems = new ArrayList();
        mAdapter = new RssListAdapter(this, mItems);

        // アダプタをリストビューにセットする
        setListAdapter(mAdapter);

        // サンプル用に空のItemオブジェクトをセットする
        for (int i = 0; i < 10; i++) {
            mAdapter.add(new Item());
        }
    }
}
