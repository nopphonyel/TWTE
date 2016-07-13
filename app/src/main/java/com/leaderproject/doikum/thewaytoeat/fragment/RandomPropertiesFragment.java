package com.leaderproject.doikum.thewaytoeat.fragment;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.widget.TimePicker;

import com.leaderproject.doikum.thewaytoeat.ProgramStaticContent;
import com.leaderproject.doikum.thewaytoeat.R;

import java.util.Calendar;

/**
 * Created by nopphonyel on 7/5/16.
 */
public class RandomPropertiesFragment extends Fragment implements View.OnClickListener {

    private Handler updateRadioTextViewCurrentTime = new Handler();
    AppCompatSpinner typeSpinner, zoneSpinner;
    RadioButton radioTimeNow, radioTimePick;
    int currentHour, currentMin, currentSecond;
    Calendar currentTime;
    TimePickerDialog timePickerDialoge;

    private String radioButtonTimePick;
    private String radioButtonTimeNow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savBundle) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.random_properties_layout, container, false);
        typeSpinner = (AppCompatSpinner) rootView.findViewById(R.id.type_spin);
        zoneSpinner = (AppCompatSpinner) rootView.findViewById(R.id.zone_spin);
        radioTimeNow = (RadioButton) rootView.findViewById(R.id.radio_button_time_now);
        radioTimePick = (RadioButton) rootView.findViewById(R.id.radio_button_time_pick);

        radioButtonTimePick = getActivity().getString(R.string.radio_button_time_pick);
        radioButtonTimeNow = getActivity().getString(R.string.radio_button_time_now);

        radioTimePick.setOnClickListener(this);
        radioTimeNow.setOnClickListener(this);

        radioTimeNow.setChecked(true);
        setupSpinner(getActivity(), typeSpinner, ProgramStaticContent.getFoodType());
        ProgramStaticContent.setSelectedTypeCode(typeSpinner.getSelectedItemPosition());

        setupSpinner(getActivity(), zoneSpinner, ProgramStaticContent.getZone());
        ProgramStaticContent.setSelectedZoneCode(zoneSpinner.getSelectedItemPosition());

        createNeceesaryThread();

        return rootView;
    }

    private void updateTime() {
        currentTime = Calendar.getInstance();
        currentHour = currentTime.get(Calendar.HOUR);
        currentMin = currentTime.get(Calendar.MINUTE);
        currentSecond = currentTime.get(Calendar.SECOND);

    }

    private void setupSpinner(FragmentActivity fragmentActivity, AppCompatSpinner spinner, String[] stringToAdd) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(fragmentActivity, android.R.layout.simple_list_item_1, stringToAdd);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    private void createNeceesaryThread() {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (radioTimeNow.isChecked()) {
                    radioTimeNow.setText(getRadioTimeFormat());
                }
                updateRadioTextViewCurrentTime.postDelayed(this, 1000);
            }

            public String getRadioTimeFormat() {
                updateTime();
                return radioButtonTimeNow + " (" + String.format("%02d", currentHour) + ":"
                        + String.format("%02d", currentMin) + "." + String.format("%02d", currentSecond)
                        + ")";
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == radioTimePick) {
            updateTime();
            TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    radioTimePick.setText(radioButtonTimePick + " " + String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                    radioTimeNow.setText(radioButtonTimeNow);
                    ProgramStaticContent.setTimeChoose(hourOfDay, minute);
                }
            };
            TimePickerDialog.OnCancelListener cancelListener = new TimePickerDialog.OnCancelListener() {

                @Override
                public void onCancel(DialogInterface dialog) {
                    radioTimeNow.setChecked(true);
                    radioTimePick.setText(radioButtonTimePick);
                }
            };

            timePickerDialoge = new TimePickerDialog(getActivity(), timeSetListener, currentHour, currentMin, true);
            timePickerDialoge.setOnCancelListener(cancelListener);
            timePickerDialoge.setCanceledOnTouchOutside(false);
            timePickerDialoge.show();
        }
        if (v == radioTimeNow) {
            Log.d("TAG_RADIO","radio time now has been clicked");
            radioTimePick.setText(radioButtonTimePick);
        }
    }
}
