package com.nitin.imageviewer.dataprovider;

import com.nitin.imageviewer.albums.Album;
import com.nitin.imageviewer.photos.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by nitinsingh on 06/06/18.
 */

public interface ApiService {

    @GET("albums")
    Call<List<Album>> getAlbums();

    @GET("albums/{albumId}/photos")
    Call<List<Photo>> getPhotos(@Path("albumId") int albumId);
}
