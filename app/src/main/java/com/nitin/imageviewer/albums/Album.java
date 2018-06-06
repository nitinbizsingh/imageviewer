package com.nitin.imageviewer.albums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class Album{
    @SerializedName("userId")
    public int userId;

    @SerializedName("id")
    public int albumId;

    @SerializedName("title")
    public String title;

    Album(int userId, int albumId, String title){
        this.userId = userId;
        this.albumId = albumId;
        this.title = title;
    }
}
