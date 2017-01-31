package com.leaderproject.doikum.thewaytoeat.customComponent;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by nopphonyel on 7/18/16.
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CustomTextView(Context context) {
        super(context);
        setCustomFont(context);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }

    private void setCustomFont(Context context) {
        Typeface aNewFont = FontCache.getTypeFace("CSPraJad.otf", context);
        setTypeface(aNewFont);
    }

    public void setFont(String fontName){
        Typeface aNewFont = FontCache.getTypeFace(fontName , getContext());
        setTypeface(aNewFont);
    }
}
