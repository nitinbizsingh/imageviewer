package com.nitin.imageviewer.photos;

import android.content.Context;

import com.nitin.imageviewer.albums.Album;

import java.util.List;

/**
 * Created by nitinsingh on 06/06/18.
 */

public interface PhotoView {
    Context getcontext();

    void showProgress();

    void hideProgress();

    void setPhotos(List<Photo> photos);

    void showErrorMessage(String message);
}
