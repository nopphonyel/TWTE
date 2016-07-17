package com.leaderproject.doikum.thewaytoeat.customComponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by nopphonyel on 7/18/16.
 */
public class CustomBoldTextView extends TextView {
    public CustomBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Kanit-SemiBold.ttf"));
    }
}
