package com.nitin.imageviewer.albums;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nitin.imageviewer.R;
import com.nitin.imageviewer.dataprovider.ApiService;
import com.nitin.imageviewer.dataprovider.DataProvider;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsActivity extends AppCompatActivity implements AlbumsAdapter.ItemClickListener, AlbumView{

    public static final String EXTRAS_ALBUM_ID = "com.nitin.imageviewer.extras_album_id";

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private AlbumPresenterImpl mAlbumPresenter;
    private TextView mListEmptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        getSupportActionBar().setTitle(getString(R.string.albums_screen_title));

        mListEmptyView = findViewById(R.id.empty_list_textview);
        mProgressBar = findViewById(R.id.cyclic_progressbar);
        mRecyclerView = findViewById(R.id.albums_recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAlbumPresenter = new AlbumPresenterImpl(this);
        mAlbumPresenter.getAlbums();
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
            mAlbumPresenter.refresh();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Context getContext() {
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
    public void setAlbums(List<Album> albums) {
        if(albums.isEmpty()){
            mListEmptyView.setVisibility(View.VISIBLE);
            return;
        }
        mListEmptyView.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new AlbumsAdapter(albums, AlbumsActivity.this));
    }

    @Override
    public void showErrorMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AlbumsActivity.this, android.R.style.Theme_Material_Dialog_Alert);
        builder.setTitle(getString(R.string.network_error_title))
                .setMessage(message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(R.string.refresh, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mAlbumPresenter.refresh();
                    }
                })
                .show();
    }

    @Override
    public void onItemClick(View view, Album album) {
        mAlbumPresenter.onItemClick(album);
    }
}
