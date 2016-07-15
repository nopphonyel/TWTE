package com.leaderproject.doikum.thewaytoeat;

/**
 * Created by NR7 on 7/15/2016.
 */
public class RestaurantObject {
    private String name , location , type , openTime , closeTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public void importRetaurantData(String s){
        String[] tempString = s.split(" | ");
        setName(tempString[1]);
        
    }
}