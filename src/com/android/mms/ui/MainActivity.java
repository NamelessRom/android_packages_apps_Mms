package com.android.mms.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.android.mms.R;
import com.android.mms.widget.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 18.03.14.
 */
public class MainActivity extends Activity {

    private static ConversationList mConversationList;
    private        CustomViewPager  mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (CustomViewPager) findViewById(R.id.pager);

        final List<Fragment> mFragments = getFragments();
        final List<String> mTitles = getTitles();

        ScreenSlidePagerAdapter mTabsAdapter = new ScreenSlidePagerAdapter(
                getFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mTabsAdapter);

    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<Fragment>();

        mConversationList = new ConversationList();
        list.add(mConversationList);

        // TODO new thingy
        final Fragment f = new ConversationList();
        list.add(f);

        return list;
    }

    private List<String> getTitles() {
        List<String> list = new ArrayList<String>();

        list.add(getString(R.string.app_label));

        list.add(getString(R.string.app_label));

        return list;
    }

    @Override
    public boolean onSearchRequested() {
        if (mConversationList != null) {
            mConversationList.onSearchRequested();
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (mConversationList != null) {
            mConversationList.onNewIntent(intent);
        }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> fragments;
        private final List<String>   titles;

        public ScreenSlidePagerAdapter(FragmentManager fm, List<Fragment> fragments,
                List<String> titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

}
