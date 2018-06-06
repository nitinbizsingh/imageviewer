package com.nitin.imageviewer.photos;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nitin.imageviewer.R;
import com.nitin.imageviewer.albums.AlbumsActivity;

import java.util.List;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class PhotosActivity extends AppCompatActivity implements PhotosAdapter.ItemClickListener, PhotoView{
    public final static String EXTRAS_PHOTO_TITLE = "com.nitin.imageviewere.photo_title";
    public final static String EXTRAS_PHOTO_URL = "com.nitin.imageviewere.photo_url";

    private final static int GRID_COLUMNS = 2;

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private PhotosPresenterImpl mPhotoPresenter;
    private TextView mListEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        Fresco.initialize(this);

        getSupportActionBar().setTitle(getString(R.string.photos_screen_title));

        mListEmptyView = findViewById(R.id.empty_list_textview);
        mProgressBar = findViewById(R.id.cyclic_progressbar);
        mRecyclerView = findViewById(R.id.photos_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, GRID_COLUMNS));

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        mRecyclerView.addItemDecoration(itemDecoration);

        mPhotoPresenter = new PhotosPresenterImpl(this, getIntent().getIntExtra(AlbumsActivity.EXTRAS_ALBUM_ID, -1));
        mPhotoPresenter.getPhotos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh_button) {
            mPhotoPresenter.refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getcontext() {
        return this;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setPhotos(List<Photo> photos) {
        if(photos.isEmpty()){
            mListEmptyView.setVisibility(View.VISIBLE);
            return;
        }
        mListEmptyView.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new PhotosAdapter(photos, PhotosActivity.this));
    }

    @Override
    public void showErrorMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(PhotosActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle(getString(R.string.network_error_title))
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.refresh, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mPhotoPresenter.refresh();
                    }
                })
                .show();
    }

    @Override
    public void onItemClick(View view, Photo photo) {
        mPhotoPresenter.onItemClick(photo);
    }
}
