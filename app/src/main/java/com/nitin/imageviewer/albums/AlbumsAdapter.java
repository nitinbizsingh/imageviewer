package com.nitin.imageviewer.albums;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitin.imageviewer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitinsingh on 06/06/18.
 */

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {

    public interface ItemClickListener {
        void onItemClick(View view, Album album);
    }

    private List<Album> mAlbums;
    private ItemClickListener mClickListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTextView;
        public ViewHolder(View rowView) {
            super(rowView);
            rowView.setOnClickListener(this);
            mTitleTextView = rowView.findViewById(R.id.album_title);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, mAlbums.get(getAdapterPosition()));
        }
    }

    public AlbumsAdapter(List<Album> albums, ItemClickListener clickListener) {
        mAlbums = albums;
        mClickListener = clickListener;
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    @Override
    public AlbumsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mAlbums.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mAlbums.size();
    }
}
