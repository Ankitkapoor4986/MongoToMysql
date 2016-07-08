package com.mongoTomysql.entity;

/**
 * Created by ankit on 6/7/16.
 */
public class TbbBuddyGroup {

    private long userId;
    private long buddyGroupId;

    @Override
    public String toString() {
        return "TbbBuddyGroup{" +
                "userId=" + userId +
                ", buddyGroupId=" + buddyGroupId +
                ", lat=" + lat +
                ", lng=" + lng +
                ", groupId=" + groupId +
                '}';
    }

    private double lat;
    private double lng;
    private long groupId;

    public long getGroupId() {
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

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }


}
