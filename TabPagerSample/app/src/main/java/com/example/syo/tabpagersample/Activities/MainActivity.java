package com.example.syo.tabpagersample.Activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.syo.tabpagersample.Model.Content;
import com.example.syo.tabpagersample.Model.Item;
import com.example.syo.tabpagersample.R;
import com.example.syo.tabpagersample.RssListAdapter;
import com.example.syo.tabpagersample.SampleFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Item> mItems;
    private RssListAdapter mAdapter;
    private Activity _activity;

    private ViewPager mViewPager;

    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mItems = new ArrayList<Item>();
        mAdapter = new RssListAdapter(this, mItems);

        initTabLayout();



        // Execute Task
//        RssParserTask task = new RssParserTask(this, mAdapter);
//        task.execute(Content.MAIN);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initTabLayout() {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setBackgroundColor(getResources().getColor(R.color.bg_brand));

        PagerAdapter pagerAdapter = new PagerAdapter(MainActivity.this, mViewPager);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }


    static class PagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private static final int PAGE_COUNT = 10;

        private ViewPager mViewPager;

        private RssListAdapter mAdapter;

        private ArrayList<Item> mItems = new ArrayList<Item>();

        public PagerAdapter(AppCompatActivity activity, ViewPager viewPager) {
            super(activity.getSupportFragmentManager());
            mViewPager = viewPager;
            mViewPager.setAdapter(this);
            mViewPager.addOnPageChangeListener(this);
        }

        public void setRssListAdapter(RssListAdapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public Fragment getItem(int position) {
            SampleFragment sampleFragment = SampleFragment.newInstance(position);
//            sampleFragment.getmItems().get(position);
            return sampleFragment;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
//            return "Title " + position;
            String title = "title";
            switch (position) {
                case Content.POS_MAIN:
                    return "主要";
                case Content.POS_INTERNAL:
                    return "国内";
                case Content.POS_OVERSEAS:
                    return "海外";
                case Content.POS_ITECONOMICS:
                    return "IT 経済";
                case Content.POS_ENTARTAINMENT:
                    return "芸能";
                case Content.POS_SPORTS:
                    return "スポーツ";
                case Content.POS_MOVIE:
                    return "映画";
                case Content.POS_GROURMET:
                    return "グルメ";
                case Content.POS_WOMEN:
                    return "女性";
                case Content.POS_TREND:
                    return "トレンド";

            }
            return title;
        }
    }

    public RssListAdapter getAdapter() {
        return mAdapter;
    }

}
