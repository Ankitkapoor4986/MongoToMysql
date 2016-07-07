package com.mongoTomysql.entity;

/**
 * Created by ankit on 6/7/16.
 */
public class TbbBuddyGroup {

    private long userId;
    private long buddyGroupId;
    private double lat;
    private double lng;
    private int groupId;

    public int getGroupId() {
        return groupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBuddyGroupId() {
        return buddyGroupId;
    }

    public void setBuddyGroupId(long buddyGroupId) {
        this.buddyGroupId = buddyGroupId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


}
