package com.nitin.imageviewer.dataprovider;

import com.nitin.imageviewer.albums.Album;
import com.nitin.imageviewer.photos.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class DataProvider {

    private static DataProvider dataProvider;
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private static ApiService service;

    public static DataProvider getInstance() {
        if (dataProvider == null) {
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    private DataProvider(){
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public void getAlbums(Callback<List<Album>> callback){
        Call<List<Album>> call = service.getAlbums();
        call.enqueue(callback);
    }

    public void getPhotos(int albumId, Callback<List<Photo>> callback){
        Call<List<Photo>> call = service.getPhotos(albumId);
        call.enqueue(callback);
    }
}
