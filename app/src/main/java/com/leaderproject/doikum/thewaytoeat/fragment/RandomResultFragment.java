package com.leaderproject.doikum.thewaytoeat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaderproject.doikum.thewaytoeat.R;

/**
 * Created by nopphonyel on 7/5/16.
 */
public class RandomResultFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savBundle){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.random_result_layout , container , false);
        return rootView;
    }
}
