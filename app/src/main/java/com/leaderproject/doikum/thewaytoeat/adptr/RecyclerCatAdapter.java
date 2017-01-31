package com.leaderproject.doikum.thewaytoeat.adptr;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leaderproject.doikum.thewaytoeat.R;

import java.util.ArrayList;

/**
 * Adapter for management Promotion page
 * Created by Dell on 31/1/2560.
 */

public class RecyclerCatAdapter extends RecyclerView.Adapter<CatViewHolder> {

    View viewHolder;

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public CatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        String viewMode = AdaperSettings.getSettingsOrder(viewType);
        switch (AdaperSettings.getSettingsOrder(viewType)){
            case AdaperSettings.HOT_PROMO :
                viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_cat_non_bg, parent , false);
                break;
            case AdaperSettings.RECCOMMEND:
                viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_cat_bg, parent , false);
                break;
            case AdaperSettings.NEAR_BY:
                viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_cat_non_bg , parent , false);
                break;
            case AdaperSettings.SHOW_CAT:
                viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_cat_showcat , parent , false);
                break;
            default:
                viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.promo_cat_showcat , parent , false);
                break;
        }
        return new CatViewHolder(viewHolder , viewMode , viewType);
    }

    @Override
    public void onBindViewHolder(CatViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

class CatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private String viewMode;
    private int position;

    RecyclerView item_res;

    public CatViewHolder(View itemView, String viewMode , int viewType) {
        super(itemView);
        this.viewMode = viewMode;
        if(viewMode.equals(AdaperSettings.SHOW_CAT)) {
            viewType = 2;
        }else{
            viewType = viewType % 2;
        }
        switch (viewType){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            default:
        }
    }

    public String getViewMode(){
        return viewMode;
    }

    @Override
    public void onClick(View v) {

    }
}

class AdaperSettings{
    public static final String HOT_PROMO = "Hot_Promo" ,
            RECCOMMEND = "RECCOMEND" ,
            NEAR_BY = "NEAR_BY",
            SHOW_CAT = "SHOW_BY_CATEGORIES";
    private static final ArrayList<String> ITEM_CODE = new ArrayList<String>(){
        {
            add(HOT_PROMO);
            add(RECCOMMEND);
            add(NEAR_BY);
            add(SHOW_CAT);
        }
    };

    public static String getSettingsOrder (int position){
        return ITEM_CODE.get(position);
    }
}
