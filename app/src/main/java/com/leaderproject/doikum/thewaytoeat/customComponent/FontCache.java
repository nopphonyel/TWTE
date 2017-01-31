package com.leaderproject.doikum.thewaytoeat.customComponent;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by Dell on 31/1/2560.
 */

public class FontCache {

    private static final String FONT_FOLDER = "fonts/";

    private static HashMap<String, Typeface> fontCache = new HashMap<>();

    public static Typeface getTypeFace(String fontname, Context context) {
        Typeface typeface = fontCache.get(fontname);
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), FONT_FOLDER+fontname);
            } catch (Exception e) {
                return null;
            }
            fontCache.put(fontname, typeface);
        }
        return typeface;
    }
}
