package com.nitin.imageviewer.albums;

/**
 * Created by nitinsingh on 06/06/18.
 */

public interface AlbumsPresenter {
    public void getAlbums();

    public void refresh();

    public void onItemClick(Album album);
}
