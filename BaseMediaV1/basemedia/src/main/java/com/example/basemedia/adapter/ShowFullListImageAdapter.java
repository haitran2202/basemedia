package com.example.basemedia.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.basemedia.R;
import com.example.basemedia.Utils.GlideHelper;
import com.example.basemedia.Utils.UrlHelper;
import com.github.chrisbanes.photoview.PhotoView;

import java.net.URLConnection;
import java.util.ArrayList;

public class ShowFullListImageAdapter extends RecyclerView.Adapter<ShowFullListImageAdapter.ViewHolder> {
    private ArrayList<String> url ;
    private int position1;
    private Context mContext;
    public ShowFullListImageAdapter(ArrayList<String> urlImage, int pos){
        this.url = urlImage;
        this.position1 = pos;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_full_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideHelper.loadUrl(url.get(position),holder.imageView);
                holder.cancelIv.setOnClickListener(v -> {
                      if (onLisener!=null){
                          onLisener.back();
                      }
                });
    }

    @Override
    public int getItemCount() {
        return url.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private PhotoView imageView;
        private ImageView cancelIv;
        private ImageView viewImageVideo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.details_image_iv);
            cancelIv = itemView.findViewById(R.id.cancel_iv);
        }
    }
    OnLisener onLisener;
    public ShowFullListImageAdapter setOnLisener(OnLisener onLisener1){
        this.onLisener = onLisener1;
        return this;
    }
    public interface OnLisener {
        void back();
    }
}
