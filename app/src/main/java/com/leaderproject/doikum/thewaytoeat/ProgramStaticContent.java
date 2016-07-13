package com.leaderproject.doikum.thewaytoeat;

import android.util.Log;

/**
 * This class to contain some of data that require to use in different fragment
 * Created by NR7 on 7/12/2016.
 */
public class ProgramStaticContent {

    private final static String TAG_CURRENT_VALUE = "PSC:CurrentValue";

    private static String[] zone = new String[]{
            "ด้านหลังมหาวิทยาลัยขอนแก่น",
            "ภายในมหาวิทยาลัยขอนแก่น",
            "บริเวณกังสดาน",
            "สุ่มทั้งหมด"
    };

    private static String[] foodType = new String[]{
            "อาหารตามสั่ง",
            "บุฟเฟ่ต์",
            "อาหารคลีน",
            "อาหารญี่ปุ่น",
            "อาหารฝรั่ง",
            "อาหารเจ",
            "ข้าวราดแกง",
            "ก๋วยเตี๋ยว",
            "สุ่มทั้งหมด"
    };

    public static String[] getZone(){
        return zone;
    }

    public static String[] getFoodType(){
        return foodType;
    }

    private static int selectedZoneCode;
    private static int selectedTypeCode;

    public static void setSelectedZoneCode(int newIndex){
        selectedZoneCode = newIndex;
        Log.d(TAG_CURRENT_VALUE , "ZoneCode:"+selectedZoneCode);
    }

    public static void setSelectedTypeCode(int newIndex){
        selectedTypeCode = newIndex;
        Log.d(TAG_CURRENT_VALUE , "TypeCode:"+selectedTypeCode);
    }

    public static int getSelectedZoneCode() {
        return selectedZoneCode;
    }

    public static int getSelectedTypeCode() {
        return selectedTypeCode;
    }

    //When ready to use "promotion fragment", please edit betaVersion to "false"
    private static boolean betaVersion = true;

    public static boolean isBetaVersion(){
        return betaVersion;
    }

    private static int chooseTimeHour , chooseTimeMin;

    public static void setTimeChoose(int hour,int min){
        chooseTimeHour = hour;
        chooseTimeMin = min;
    }

    public static int getChooseTimeHour(){
        return chooseTimeHour;
    }

    public static int getChooseTimeMin(){
        return chooseTimeMin;
    }
}
