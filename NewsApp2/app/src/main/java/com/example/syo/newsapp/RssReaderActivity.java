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

        // Item�I�u�W�F�N�g��ێ����邽�߂̃��X�g�𐶐����A�A�_�v�^�ɒǉ�����
        mItems = new ArrayList();
        mAdapter = new RssListAdapter(this, mItems);

        // �A�_�v�^�����X�g�r���[�ɃZ�b�g����
        setListAdapter(mAdapter);

        // �T���v���p�ɋ��Item�I�u�W�F�N�g���Z�b�g����
        for (int i = 0; i < 10; i++) {
            mAdapter.add(new Item());
        }
    }
}
