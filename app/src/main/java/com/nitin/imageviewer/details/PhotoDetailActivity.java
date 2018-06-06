package com.nitin.imageviewer.details;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nitin.imageviewer.R;
import com.nitin.imageviewer.photos.PhotosActivity;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class PhotoDetailActivity extends AppCompatActivity {
    private TextView mPhotoTitleTextView;
    private SimpleDraweeView mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        Fresco.initialize(this);

        getSupportActionBar().setTitle(getString(R.string.details_screen_title));

        mPhotoTitleTextView = findViewById(R.id.photo_title);
        mPhoto = findViewById(R.id.photo);

        String photoUrl = getIntent().getStringExtra(PhotosActivity.EXTRAS_PHOTO_URL);
        String photoTitle = getIntent().getStringExtra(PhotosActivity.EXTRAS_PHOTO_TITLE);

        if(photoTitle != null) {
            mPhotoTitleTextView.setText(photoTitle);
        }

        if(photoUrl != null){
            mPhoto.setImageURI(Uri.parse(photoUrl));
        }
    }
}
