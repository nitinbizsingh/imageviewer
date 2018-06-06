package com.nitin.imageviewer.albums;

import android.content.Context;
import android.content.Intent;

import com.nitin.imageviewer.dataprovider.DataProvider;
import com.nitin.imageviewer.photos.PhotosActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class AlbumPresenterImpl implements AlbumsPresenter {

    private AlbumView mAlbumView;

    public AlbumPresenterImpl(AlbumView view){
        mAlbumView = view;
    }

    @Override
    public void getAlbums() {
        mAlbumView.showProgress();

        DataProvider.getInstance().getAlbums(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                mAlbumView.hideProgress();
                mAlbumView.setAlbums(response.body());
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                mAlbumView.hideProgress();
                mAlbumView.showErrorMessage(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void refresh() {
        getAlbums();
    }

    @Override
    public void onItemClick(Album album) {
        Intent intent = new Intent(mAlbumView.getContext(), PhotosActivity.class);
        intent.putExtra(AlbumsActivity.EXTRAS_ALBUM_ID, album.albumId);
        mAlbumView.getContext().startActivity(intent);
    }
}
