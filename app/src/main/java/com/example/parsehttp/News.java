package com.example.parsehttp;

import android.os.Parcelable;

public class News {
    private String title;
    private String href;
    private String imageSrc;
    private String note;
    private String date;

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
