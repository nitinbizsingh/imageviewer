package com.nitin.imageviewer.photos;

import com.nitin.imageviewer.albums.Album;

/**
 * Created by nitinsingh on 06/06/18.
 */

public interface PhotosPresenter {
    public void getPhotos();

    public void refresh();

    public void onItemClick(Photo photo);
}
