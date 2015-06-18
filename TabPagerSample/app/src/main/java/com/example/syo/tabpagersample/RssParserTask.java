package com.example.syo.tabpagersample;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.Xml;

import com.example.syo.tabpagersample.Activities.MainActivity;
import com.example.syo.tabpagersample.Model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by syo on 2015/06/17.
 */
// RssParserTask.java
public class RssParserTask extends AsyncTask<String, Integer, List<Item>> {
    private MainActivity mActivity;
    private List<Item> items;
    private ProgressDialog mProgressDialog;
    private SampleFragment fragment;
    private RuntimeException error;

    // コンストラクタ
    public RssParserTask(MainActivity activity, SampleFragment fragment, List<Item> list) {
        mActivity = activity;
        this.fragment = fragment;
        items = list;
    }

    // タスクを実行した直後にコールされる
    @Override
    protected void onPreExecute() {
        // プログレスバーを表示する
        mProgressDialog = new ProgressDialog(mActivity);
        mProgressDialog.setMessage("Now Loading...");
        mProgressDialog.show();
    }

    // バックグラウンドにおける処理を担う。タスク実行時に渡された値を引数とする
    @Override
    protected List<Item> doInBackground(String... params) {
        List<Item> result = null;
        try {
            // HTTP経由でアクセスし、InputStreamを取得する
            URL url = new URL(params[0]);
            Log.d("ULR", url.toString());
            InputStream is = url.openConnection().getInputStream();
            result = parseXml(is);
        } catch (RuntimeException e) {
            error = e;
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ここで返した値は、onPostExecuteメソッドの引数として渡される
        return result;
    }

    // メインスレッド上で実行される
    @Override
    protected void onPostExecute(List<Item> result) {
        mProgressDialog.dismiss();
        mActivity.getAdapter().notifyDataSetChanged();
        if (error != null) {
            Snackbar.make(fragment.mListView, "URLに接続できませんでした", Snackbar.LENGTH_SHORT).show();
        }
    }

    // XMLをパースする
    public List<Item> parseXml(InputStream is) throws IOException, XmlPullParserException {
        XmlPullParser parser = Xml.newPullParser();
        try {
            items.clear();
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            Item currentItem = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = null;
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")) {
                            currentItem = new Item();
                        } else if (currentItem != null) {
                            if (tag.equals("title")) {
                                currentItem.setTitle(parser.nextText());
                            } else if (tag.equals("link")) {
                                currentItem.setUrl(parser.nextText());
                            } else if (tag.equals("description")) {
                                String description = refactorDescription(parser.nextText());
                                int first = description.indexOf("src=\"");
                                int last = description.indexOf(".jpg");
                                int aFirst = description.indexOf("<a");
                                int aLast = description.indexOf("/a>");
                                if (first >= 0 && last >0) {
                                    int altFirst = description.indexOf("alt=\"");
                                    int altLast = description.indexOf("<a");
                                    String imgUrl = description.substring(first + 5, last + 4);
                                    String text = description.substring(altFirst + 5, altLast - 2);
//                                    currentItem.setDescription(description);
                                    currentItem.setImgUrl(imgUrl);
                                    currentItem.setDescription(text);
//                                currentItem.setDescription(refactorDescription(parser.nextText()));
//                                currentItem.setDescription(refactorDescription(parser.getText()));
                                } else if (aFirst >= 0 && aLast > 0) {
                                    String text = description.substring(aFirst, aLast + 3);
                                    currentItem.setDescription(removePartDescription(description, text));
                                } else {
                                    currentItem.setDescription(description);
                                }
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        tag = parser.getName();
                        if (tag.equals("item")) {
                            items.add(currentItem);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public String refactorDescription(String description) {
        description = description.replace("<br />", "");
        description = description.replace("<li>", "");
        description = description.replace("</li>", "");
        description = description.replace("<ul>", "");
        description = description.replace("</ul>", "");
        return description;
    }

    public String removePartDescription(String description, String text) {
        description = description.replace(text, "");
        return description;

    }
}