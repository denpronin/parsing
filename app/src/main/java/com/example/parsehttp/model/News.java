package com.example.parsehttp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class News {
    private final String title;
    private final String href;
    private final String imageSrc;
    private final String note;
    private final String date;

    public News(String argTitle, String argHref, String argImageSrc, String argNote, String argDate) {
        title = argTitle;
        href = argHref;
        imageSrc = argImageSrc;
        note = argNote;
        date = argDate;
    }

    public String getHref() {
        return href;
    }

    public String getDate() {
        return date;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }
}
