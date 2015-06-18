package com.example.syo.tabpagersample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.syo.tabpagersample.Activities.ItemDetailActivity;
import com.example.syo.tabpagersample.Activities.MainActivity;
import com.example.syo.tabpagersample.Model.Content;
import com.example.syo.tabpagersample.Model.Item;

import java.util.ArrayList;

/**
 * Created by syo on 2015/06/17.
 */
public class SampleFragment extends Fragment {

    private static final String KEY = "hoge";

    private RssParserTask task;
    private int currentPosition;
    public ListView mListView;
    private ArrayList<Item> mItems;
    private MainActivity mActivity;

    public static SampleFragment newInstance(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);

        SampleFragment sampleFragment = new SampleFragment();
        sampleFragment.setArguments(bundle);

        return sampleFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity) {
            mActivity = (MainActivity) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments.containsKey(KEY)) {
            mItems = new ArrayList<>();
            task = new RssParserTask(mActivity, SampleFragment.this, mItems);
            switch (arguments.getInt(KEY)) {
                case Content.POS_MAIN :
                    task.execute(Content.MAIN);
                    break;
                case Content.POS_INTERNAL :
                    task.execute(Content.INTERNAL);
                    break;
                case Content.POS_OVERSEAS :
                    task.execute(Content.OVERSEAS);
                    break;
                case Content.POS_ITECONOMICS :
                    task.execute(Content.ITECONOMICS);
                    break;
                case Content.POS_ENTARTAINMENT :
                    task.execute(Content.ENTARTAINMENT);
                    break;
                case Content.POS_SPORTS :
                    task.execute(Content.SPORTS);
                    break;
                case Content.POS_MOVIE :
                    task.execute(Content.MOVIE);
                    break;
                case Content.POS_GROURMET :
                    task.execute(Content.GROURMET);
                    break;
                case Content.POS_WOMEN :
                    task.execute(Content.WOMEN);
                    break;
                case Content.POS_TREND :
                    task.execute(Content.TREND);
                    break;
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sample, container, false);
        mListView = (ListView) view.findViewById(android.R.id.list);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        addItems();

        final RssListAdapter rssListAdapter = new RssListAdapter(mActivity, mItems);
        mListView.setAdapter(rssListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mActivity, ItemDetailActivity.class);
                intent.putExtra("ITEM", mItems.get(position));
                startActivity(intent);
            }
        });

//        mActivity.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                task = new RssParserTask(mActivity, SampleFragment.this, rssListAdapter);
//                switch (tab.getPosition()) {
//                    case Content.POS_MAIN :
//                        task.execute(Content.MAIN);
//                        break;
//                    case Content.POS_INTERNAL :
//                        task.execute(Content.INTERNAL);
//                        break;
//                    case Content.POS_OVERSEAS :
//                        task.execute(Content.OVERSEAS);
//                        break;
//                    case Content.POS_ITECONOMICS :
//                        task.execute(Content.ITECONOMICS);
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    public ArrayList<Item> getmItems() {
        return mItems;
    }


//    private void addItems() {
//        Item item = new Item();
//        item.setTitle("hogehoge");
//        item.setDescription("piyopiyo");
//
//        mItems = new ArrayList<>();
//        this.mItems.add(item);
//        this.mItems.add(item);
//        this.mItems.add(item);
//        this.mItems.add(item);
//        this.mItems.add(item);
//        this.mItems.add(item);
//    }
}

