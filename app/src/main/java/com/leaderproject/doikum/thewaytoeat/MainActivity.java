package com.leaderproject.doikum.thewaytoeat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.leaderproject.doikum.thewaytoeat.adptr.*;
import com.leaderproject.doikum.thewaytoeat.backgroundTask.GetRestuarant;
import com.leaderproject.doikum.thewaytoeat.fragment.RandomPropertiesFragment;
import com.leaderproject.doikum.thewaytoeat.fragment.RandomResultFragment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected ViewPager viewPager;
    protected FragmentAdapter fragmentAdapter;
    protected TabLayout tabLayout;
    protected FloatingActionButton fab, fabNavigate;
    private boolean fabNavigateExist = false;

    private Animation fabOpen, fabClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab_main_action);
        fabNavigate = (FloatingActionButton) findViewById(R.id.fab_navigate_action);
        fabNavigate.setVisibility(View.INVISIBLE);
        viewPager = (ViewPager) findViewById(R.id.pager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        fabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        setupTabs(tabLayout, viewPager, fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPagerFABHandler());

        if (fab != null) fab.setOnClickListener(this);
        if (fabNavigate != null) fabNavigate.setOnClickListener(this);
        getFirstDataFromDlitSource();
    }

    private void setupTabs(TabLayout tl, ViewPager vp, FragmentAdapter fa) {
        vp.setAdapter(fa);
        tl.setupWithViewPager(vp);
        try {
            if (!ProgramStaticContent.isBetaVersion()) {
                tl.getTabAt(0).setIcon(R.mipmap.ic_promotion);
                tl.getTabAt(1).setIcon(R.mipmap.ic_rand_setting);
                tl.getTabAt(2).setIcon(R.mipmap.ic_restuarant);
            } else {
                tl.getTabAt(0).setIcon(R.mipmap.ic_rand_setting);
                tl.getTabAt(1).setIcon(R.mipmap.ic_restuarant);
            }
        } catch (NullPointerException ex) {
            Log.e("LOAD_ICON", ex.toString());
        }
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

    @Override
    public void onClick(View v) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        if (v == fab) {
            viewPager.setCurrentItem(1);
            if (RandomPropertiesFragment.isTimeNowChecked()) {
                RandomPropertiesFragment.setTimeNow();
            }
            getDataFromDlitSource();
        }
        if(v == fabNavigate){
            RestaurantObject restaurantObject = ProgramStaticContent.getRestaurantObject();
            String latitude = restaurantObject.getLatitude() , longitude = restaurantObject.getLongtitude();
            String label = restaurantObject.getName();

            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


    // ไอซ์มาเขียนตรงนี้เบย ด้านล่างนี่เลย

    //////////////////////////////////////////
    //     //////     //////    //////      //
    //       //      //         ////        //
    //       //      //         //          //
    //     //////     //////    //////      //
    //////////////////////////////////////////
    private void getDataFromDlitSource() {
        int chooseHour = ProgramStaticContent.getChooseTimeHour(), chooseMin = ProgramStaticContent.getChooseTimeMin();
        int zoneCode = ProgramStaticContent.getSelectedZoneCode(), typeCode = ProgramStaticContent.getSelectedTypeCode();

        String filter = typeCode + "," + zoneCode + "," + String.format("%02d", chooseHour) + ":" + String.format("%02d", chooseMin) + ":00"; //type,zone,time เวลาต้องอยู่ในรูป xx:xx:xx เท่านั้นนาจา
        new GetRestuarant().execute(filter);
    }

    private void getFirstDataFromDlitSource() {
        int chooseHour = ProgramStaticContent.getChooseTimeHour(), chooseMin = ProgramStaticContent.getChooseTimeMin();
        int zoneCode = ProgramStaticContent.getSelectedZoneCode(), typeCode = ProgramStaticContent.getSelectedTypeCode();

        String filter = typeCode + "," + zoneCode + "," + String.format("%02d", chooseHour) + ":" + String.format("%02d", chooseMin) + ":00"; //type,zone,time เวลาต้องอยู่ในรูป xx:xx:xx เท่านั้นนาจา
        new GetRestuarant().execute(filter,GetRestuarant.FIRST_TIME);
        viewPager.setCurrentItem(1);
    }

    public class ViewPagerFABHandler implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (ProgramStaticContent.isBetaVersion()) {
                incomingAnimation(position);
            }
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        public void incomingAnimation(int position){

            if (position == 0 && fabNavigateExist) {
                fabNavigate.startAnimation(fabClose);
                fabNavigateExist = false;
                Log.d("TAG" , "A: CLOSING "+fabNavigateExist);
            }
            else if (position == 1 && !fabNavigateExist) {
                fabNavigate.startAnimation(fabOpen);
                fabNavigateExist = true;
                Log.d("TAG" , "A: OPENING "+fabNavigateExist);
            }
        }
    }
}
