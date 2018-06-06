package com.nitin.imageviewer.photos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class Photo {
    @SerializedName("albumId")
    int albumId;

    @SerializedName("id")
    int photoId;

    @SerializedName("title")
    String title;

    @SerializedName("url")
    String url;

    @SerializedName("thumbnailUrl")
    String thumbnailUrl;

    public Photo(int albumId, int photoId, String title, String url, String thumbnailUrl){
        this.albumId = albumId;
        this.photoId = photoId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
}
