package com.leaderproject.doikum.thewaytoeat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leaderproject.doikum.thewaytoeat.ProgramStaticContent;
import com.leaderproject.doikum.thewaytoeat.R;
import com.leaderproject.doikum.thewaytoeat.RestaurantObject;

import org.w3c.dom.Text;

/**
 * Created by nopphonyel on 7/5/16.
 */
public class RandomResultFragment extends Fragment {

    private static TextView restaurantNameTextView;
    private static TextView restaurantDetailTextView;

    private static boolean fragmentCreated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savBundle) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.random_result_layout, container, false);
        restaurantNameTextView = (TextView) rootView.findViewById(R.id.res_name);
        restaurantDetailTextView = (TextView) rootView.findViewById(R.id.res_addres);
        fragmentCreated = true;
        return rootView;
    }

    public static void updateContent() {
        RestaurantObject currentRestaurant = ProgramStaticContent.getRestaurantObject();
        restaurantNameTextView.setText(currentRestaurant.getName());
        restaurantDetailTextView.setText(currentRestaurant.getLocation() + "\n" +
                        currentRestaurant.getType() + "\n" +
                        currentRestaurant.getOpenTime() + "\n" +
                        currentRestaurant.getCloseTime());
    }

    public static boolean isFragmentCreated(){
        return fragmentCreated;
    }
}
