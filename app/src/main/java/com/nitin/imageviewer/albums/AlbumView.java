package com.nitin.imageviewer.albums;

import android.content.Context;

import java.util.List;

/**
 * Created by nitinsingh on 06/06/18.
 */

public interface AlbumView {
    Context getContext();

    void showProgress();

    void hideProgress();

    void setAlbums(List<Album> albums);

    void showErrorMessage(String message);
}
