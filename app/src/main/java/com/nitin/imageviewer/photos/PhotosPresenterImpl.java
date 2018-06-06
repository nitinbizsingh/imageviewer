package com.nitin.imageviewer.photos;

import android.content.Intent;
import android.util.Log;

import com.nitin.imageviewer.albums.Album;
import com.nitin.imageviewer.albums.AlbumsActivity;
import com.nitin.imageviewer.dataprovider.DataProvider;
import com.nitin.imageviewer.details.PhotoDetailActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class PhotosPresenterImpl implements PhotosPresenter {

    private PhotoView mPhotoView;
    private int mAlbumId;

    public PhotosPresenterImpl(PhotoView photoView, int albumId){
        mPhotoView = photoView;
        mAlbumId = albumId;
    }

    @Override
    public void getPhotos() {
        mPhotoView.showProgress();

        DataProvider.getInstance().getPhotos(mAlbumId, new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                mPhotoView.hideProgress();
                mPhotoView.setPhotos(response.body());
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                mPhotoView.hideProgress();
                mPhotoView.showErrorMessage(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void refresh() {
        getPhotos();
    }

    @Override
    public void onItemClick(Photo photo) {
        Intent intent = new Intent(mPhotoView.getcontext(), PhotoDetailActivity.class);
        intent.putExtra(PhotosActivity.EXTRAS_PHOTO_URL, photo.url);
        intent.putExtra(PhotosActivity.EXTRAS_PHOTO_TITLE, photo.title);
        mPhotoView.getcontext().startActivity(intent);
    }
}
