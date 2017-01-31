package com.leaderproject.doikum.thewaytoeat.customComponent;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * This is custom TextView for providing custom font
 * Created by nopphonyel on 7/18/16.
 */
public class CustomBoldTextView extends TextView {
    public CustomBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context);
    }

    public CustomBoldTextView(Context context) {
        super(context);
        setCustomFont(context);
    }

    public CustomBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context);
    }

    private void setCustomFont(Context context) {
        Typeface aNewFont = FontCache.getTypeFace("CSPraJad-bold.otf", context);
        setTypeface(aNewFont);
    }

    public void setFont(String fontName){
        Typeface aNewFont = FontCache.getTypeFace(fontName , getContext());
        setTypeface(aNewFont);
    }
}
