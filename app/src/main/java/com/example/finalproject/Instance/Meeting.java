package com.example.finalproject.Instance;

import com.example.finalproject.Firebase.Authentication;

public class Meeting {
    String Title, Location, Date, Time;
    User SetUpMeetingUser;

    public Meeting() {
    }

    public Meeting(String title, String location, String date, String time) {
        Title = title.trim();
        Location = location.trim();
        Date = date.trim();
        Time = time.trim();
        SetUpMeetingUser = Authentication.getInstance().getLoggedInUser();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public User getSetUpMeetingUser() {
        return SetUpMeetingUser;
    }

    public void setSetUpMeetingUser(User setUpMeetingUser) {
        SetUpMeetingUser = setUpMeetingUser;
    }
}
