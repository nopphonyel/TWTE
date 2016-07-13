package com.leaderproject.doikum.thewaytoeat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.support.v7.widget.AppCompatSpinner;

import com.leaderproject.doikum.thewaytoeat.ProgramStaticContent;
import com.leaderproject.doikum.thewaytoeat.R;

/**
 * Created by nopphonyel on 7/5/16.
 */
public class RandomPropertiesFragment extends Fragment implements View.OnClickListener {
    AppCompatSpinner typeSpinner , zoneSpinner;
    RadioButton radioTimeNow , radioTimePick;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savBundle){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.random_properties_layout , container , false);
        typeSpinner = (AppCompatSpinner) rootView.findViewById(R.id.type_spin);
        zoneSpinner = (AppCompatSpinner) rootView.findViewById(R.id.zone_spin);
        radioTimeNow = (RadioButton) rootView.findViewById(R.id.radio_button_time_now);
        radioTimePick = (RadioButton) rootView.findViewById(R.id.radio_button_time_pick);

        radioTimeNow.setChecked(true);
        setupSpinner(getActivity() , typeSpinner , ProgramStaticContent.getFoodType());
        ProgramStaticContent.setSelectedTypeCode(typeSpinner.getSelectedItemPosition());

        setupSpinner(getActivity() , zoneSpinner , ProgramStaticContent.getZone());
        ProgramStaticContent.setSelectedZoneCode(zoneSpinner.getSelectedItemPosition());

        return rootView;
    }

    private void setupSpinner(FragmentActivity fragmentActivity , AppCompatSpinner spinner , String[] stringToAdd){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(fragmentActivity ,android.R.layout.simple_list_item_1, stringToAdd);
        spinner.setAdapter(adapter);
        spinner.setSelection(stringToAdd.length-1);
    }

    @Override
    public void onClick(View v) {

    }
}
