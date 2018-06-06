package com.nitin.imageviewer.photos;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.nitin.imageviewer.R;
import com.nitin.imageviewer.albums.Album;
import com.nitin.imageviewer.albums.AlbumsAdapter;

import java.util.List;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    public interface ItemClickListener {
        void onItemClick(View view, Photo photo);
    }

    private List<Photo> mPhotos;
    private PhotosAdapter.ItemClickListener mClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTextView;
        public SimpleDraweeView mPhotoView;
        public ViewHolder(View rowView) {
            super(rowView);
            rowView.setOnClickListener(this);
            mTitleTextView = rowView.findViewById(R.id.photo_title);
            mPhotoView = rowView.findViewById(R.id.photo);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, mPhotos.get(getAdapterPosition()));
        }
    }

    public PhotosAdapter(List<Photo> photos, PhotosAdapter.ItemClickListener clickListener) {
        mPhotos = photos;
        mClickListener = clickListener;
    }

    void setClickListener(PhotosAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public PhotosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_cell, parent, false);
        PhotosAdapter.ViewHolder viewHolder = new PhotosAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PhotosAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mPhotos.get(position).title);
        holder.mPhotoView.setImageURI(Uri.parse(mPhotos.get(position).thumbnailUrl));
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }
}
