package com.example.parsehttp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class News implements Parcelable {
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    @Override
    public int describeContents() {
        return 0;
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
