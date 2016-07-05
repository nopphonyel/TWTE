package com.leaderproject.doikum.thewaytoeat.adptr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.leaderproject.doikum.thewaytoeat.fragment.PromotionFragment;
import com.leaderproject.doikum.thewaytoeat.fragment.RandomPropertiesFragment;
import com.leaderproject.doikum.thewaytoeat.fragment.RandomResultFragment;

/**
 * Created by nopphonyel on 7/5/16.
 */
public class FragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private final static int PAGE_NUM = 3;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0 : return new PromotionFragment();
           case 1 : return new RandomPropertiesFragment();
           case 2 : return new RandomResultFragment();
           default: return null;
       }
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }
}
