package com.leaderproject.doikum.thewaytoeat;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.leaderproject.doikum.thewaytoeat.adptr.*;
import com.leaderproject.doikum.thewaytoeat.fragment.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected ViewPager viewPager;
    protected FragmentAdapter fragmentAdapter;
    protected TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_main_action);
        viewPager = (ViewPager) findViewById(R.id.pager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        setupTabs(tabLayout , viewPager , fragmentAdapter);

          // DO NOT REMOVE THIS COMMENT //
        if(fab!=null) fab.setOnClickListener(this);
    }

    private void setupTabs(TabLayout tl , ViewPager vp , FragmentAdapter fa){
        vp.setAdapter(fa);
        tl.setupWithViewPager(vp);
        try {
            if(!ProgramStaticContent.isBetaVersion()) {
                tl.getTabAt(0).setIcon(R.mipmap.ic_promotion);
                tl.getTabAt(1).setIcon(R.mipmap.ic_rand_setting);
                tl.getTabAt(2).setIcon(R.mipmap.ic_restuarant);
            }
            else {
                tl.getTabAt(0).setIcon(R.mipmap.ic_rand_setting);
                tl.getTabAt(1).setIcon(R.mipmap.ic_restuarant);
            }
        }
        catch (NullPointerException ex){
            Log.e("LOAD_ICON" , ex.toString());
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

    }
}
