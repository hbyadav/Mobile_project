package com.hbyadav.myapplication;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.tourguide.R;
public class SimpleFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SimpleFragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {                 // show the right tab fragment
        if (position == 0) {
            return new Home_fragment();
        }
        else {
            return new Schedule_fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {        // set tab title
        if (position == 0) {
            return mContext.getString(R.string.Student_Home);
        }
        else
            return mContext.getString(R.string.Student_schedule);
    }
}
