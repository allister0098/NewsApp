package com.example.syo.tabpagersample.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.syo.tabpagersample.Model.Item;
import com.example.syo.tabpagersample.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
//import java.util.logging.Handler;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by syo on 2015/06/18.
 */
public class ItemDetailActivity extends Activity implements ListView.OnItemClickListener{
    private TextView mTitle;
    private TextView mDescr;
    private Item mItem;
    private Button mLink;
    private Button mRelated;
    private ImageView mImageView;
    private String mUrl;

    private CharSequence mImageUrl;
    private Handler imgHandler = null;
    private Handler relateHandler = null;
    private Drawable drawImg = null;
    private List<Item> listItem = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        Intent intent = getIntent();
        mItem = (Item) intent.getSerializableExtra("ITEM");
        mImageUrl = mItem.getImgUrl();

        if (mImageUrl != null) {
            imgHandler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        URL url = new URL(mItem.getImgUrl().toString());
                        InputStream is = (InputStream) url.openStream();
                        drawImg = Drawable.createFromStream(is, "");
                        is.close();

                        imgHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                ImageView img = (ImageView) findViewById(R.id.item_image);
                                img.setImageDrawable(drawImg);
                                img.invalidate();
                            }
                        });
                    } catch (MalformedURLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();

        }

        mTitle = (TextView) findViewById(R.id.item_detail_title);
        mTitle.setText(mItem.getTitle());

        mDescr = (TextView) findViewById(R.id.item_detail_descr);
        mDescr.setText(mItem.getDescription());

        mLink = (Button) findViewById(R.id.detail_button);
        mLink.setText("記事を読む");
        mLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetailActivity.this, WebDetailActivity.class);
                intent.putExtra("URL", mItem.getUrl());
                startActivity(intent);
            }
        });

        final ListView listView = (ListView)findViewById(R.id.related_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        relateHandler = new Handler();
        listView.setAdapter(adapter);
    }






    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(ItemDetailActivity.this, WebDetailActivity.class);
        intent.putExtra("URL", mUrl);
        startActivity(intent);
    }
}