package com.leaderproject.doikum.thewaytoeat.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.leaderproject.doikum.thewaytoeat.ProgramStaticContent;
import com.leaderproject.doikum.thewaytoeat.R;
import com.leaderproject.doikum.thewaytoeat.RestaurantObject;
import com.leaderproject.doikum.thewaytoeat.backgroundTask.GetRestuarant;

import org.w3c.dom.Text;

/**
 * Created by nopphonyel on 7/5/16.
 */
public class RandomResultFragment extends Fragment {

    private static TextView restaurantNameTextView;
    private static TextView restaurantDetailTextView;

    private static WebView webView;
    private static boolean fragmentCreated = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savBundle) {
        /*ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.random_result_center_layout, container, false);
        restaurantNameTextView = (TextView) rootView.findViewById(R.id.res_name);
        restaurantDetailTextView = (TextView) rootView.findViewById(R.id.res_addres);*/

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.random_result_web_view , container , false);

        webView = (WebView)rootView.findViewById(R.id.view_random_result);
        setUpWebView();

        fragmentCreated = true;
        return rootView;
    }

    public static void updateContent(String state) {
        RestaurantObject currentRestaurant = ProgramStaticContent.getRestaurantObject();
        if(state == GetRestuarant.FIRST_TIME) {
            Log.e("TAG_LINK",ProgramStaticContent.CONNECTION_LINK+currentRestaurant.getId()+"&first=true");
            webView.loadUrl(ProgramStaticContent.CONNECTION_LINK+currentRestaurant.getId()+"&first=true");
        }
        else
        webView.loadUrl(ProgramStaticContent.CONNECTION_LINK+currentRestaurant.getId());
        /*
        restaurantNameTextView.setText(currentRestaurant.getName());
        if(state.equalsIgnoreCase(GetRestuarant.NOT_FOUND)){
            restaurantDetailTextView.setText("");
        }
        else {
            restaurantDetailTextView.setText(currentRestaurant.getLocation() + "\n" +
                    currentRestaurant.getType() + "\n" +
                    currentRestaurant.getOpenTime() + "\n" +
                    currentRestaurant.getCloseTime());
        }*/
    }

    public static boolean isFragmentCreated(){
        return fragmentCreated;
    }

    private void setUpWebView(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
    }
}
