package com.leaderproject.doikum.thewaytoeat.adptr;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.leaderproject.doikum.thewaytoeat.ProgramStaticContent;
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
        if (ProgramStaticContent.isBetaVersion()) {
            switch (position) {
                case 0:
                    return new RandomPropertiesFragment();
                case 1:
                    return new RandomResultFragment();
                default:
                    return null;
            }
        } else {
            switch (position) {
                case 0:
                    return new PromotionFragment();
                case 1:
                    return new RandomPropertiesFragment();
                case 2:
                    return new RandomResultFragment();
                default:
                    return null;
            }
        }
    }

    @Override
    public int getCount() {
        if (ProgramStaticContent.isBetaVersion()) {
            return PAGE_NUM - 1;
        } else {
            return PAGE_NUM;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (ProgramStaticContent.isBetaVersion()) {
            switch (position) {
                case 0:
                    return "Filter";
                case 1:
                    return "Restaurant";
                default:
                    return null;
            }
        } else {
            switch (position) {
                case 0:
                    return "Promotion";
                case 1:
                    return "Filter";
                case 2:
                    return "Restaurant";
                default:
                    return null;
            }

        }
    }
}
