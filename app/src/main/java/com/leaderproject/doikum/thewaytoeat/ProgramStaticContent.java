package com.leaderproject.doikum.thewaytoeat;

import android.util.Log;

import java.sql.ResultSet;

/**
 * This class to contain some of data that require to use in different fragment
 * Created by NR7 on 7/12/2016.
 */
public class ProgramStaticContent {

    private static RestaurantObject restaurantObject = new RestaurantObject();

    public static final String CONNECTION_LINK = "http://dlitsource.com/leadership/show.php?id=";

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

    public static String[] getZone() {
        return zone;
    }

    public static String[] getFoodType() {
        return foodType;
    }

    private static int selectedZoneCode;
    private static int selectedTypeCode;

    public static void setSelectedZoneCode(int newIndex) {
        selectedZoneCode = newIndex;
        Log.d(TAG_CURRENT_VALUE, "ZoneCode:" + selectedZoneCode);
    }

    public static void setSelectedTypeCode(int newIndex) {
        selectedTypeCode = newIndex;
        Log.d(TAG_CURRENT_VALUE, "TypeCode:" + selectedTypeCode);
    }

    public static int getSelectedZoneCode() {
        return selectedZoneCode;
    }

    public static int getSelectedTypeCode() {
        return selectedTypeCode;
    }

    //When ready to use "promotion fragment", please edit betaVersion to "false"
    private static boolean betaVersion = false;

    public static boolean isBetaVersion() {
        return betaVersion;
    }

    private static int chooseTimeHour, chooseTimeMin;

    public static void setTimeChoose(int hour, int min) {
        chooseTimeHour = hour;
        chooseTimeMin = min;
        Log.d(TAG_CURRENT_VALUE, "Current time is " + chooseTimeHour + ":" + chooseTimeMin);
    }

    public static int getChooseTimeHour() {
        return chooseTimeHour;
    }

    public static int getChooseTimeMin() {
        return chooseTimeMin;
    }

    public static void setRestaurantObjectFromString(String fetchedData) {

        try {
            String[] tempString = fetchedData.split(",");
            restaurantObject.setId(tempString[0]);
            restaurantObject.setName(tempString[1]);
            restaurantObject.setType(tempString[2]);
            restaurantObject.setLocation(tempString[3]);
            restaurantObject.setOpenTime(tempString[4]);
            restaurantObject.setCloseTime(tempString[5]);
            restaurantObject.setLatitude(tempString[6]);
            restaurantObject.setLongtitude(tempString[7]);
        } catch (ArrayIndexOutOfBoundsException ex){
            restaurantObject.setName(fetchedData);
        }
    }

    public static RestaurantObject getRestaurantObject() {
        return restaurantObject;
    }

    public static void checkData(){

    }
}
