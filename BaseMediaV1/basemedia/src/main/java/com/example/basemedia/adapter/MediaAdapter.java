package com.example.basemedia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.basemedia.R;
import com.example.basemedia.Utils.TimeUtils;
import com.example.basemedia.glide.GlideImageLoader;
import com.example.basemedia.model.AttachmentsItem;

import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaMessageVH> {

    private List<AttachmentsItem> mAttachmentsItems;
    private OnImageSelectedEventListener mOnImageSelectedEventListener;
    private boolean state = false;
    private int count = 0;

    public void setOnImageSelectedEventListener(OnImageSelectedEventListener onImageSelectedEventListener) {
        mOnImageSelectedEventListener = onImageSelectedEventListener;
    }

    public MediaAdapter(List<AttachmentsItem> attachmentsItems) {
        this.mAttachmentsItems = attachmentsItems;

    }

    public MediaAdapter(List<AttachmentsItem> attachmentsItems, boolean state) {
        this.mAttachmentsItems = attachmentsItems;
        this.state = state;
    }

    @Override
    public MediaMessageVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);
        return new MediaMessageVH(view);
    }

    @Override
    public void onBindViewHolder(MediaMessageVH holder, int position) {
        AttachmentsItem attachmentsItem = (AttachmentsItem) mAttachmentsItems.get(position);
        //ViewUtils.loadImageWithFresco(holder.mImageIv, attachmentsItem.getUrl());
        holder.viewImageVideo.setVisibility(View.GONE);
        holder.durationTv.setVisibility(View.GONE);
        count = mAttachmentsItems.size() - 4;
        if (count > 0 && position == 3) {
            holder.countRl.setVisibility(View.VISIBLE);
            holder.countTv.setText("+" + count);
        }
        // holder.timeMessTv.setText(TimeUtils.covertTimeMess(attachmentsItem.getSent_time()));
        if (mAttachmentsItems.size() > 1 || attachmentsItem.getSent_time().isEmpty()) {
            holder.viewSeen.setVisibility(View.GONE);
        } else {
            holder.viewSeen.setVisibility(View.VISIBLE);
        }
        if (state) {
            holder.timeMessTv.setText(TimeUtils.covertTimeMess(attachmentsItem.getSent_time()));
            if (attachmentsItem.isStateSeen()) {
                holder.seenRl.setVisibility(View.VISIBLE);
            } else {

                holder.seenRl.setVisibility(View.GONE);
            }
        } else {
            holder.timeMessTv.setText(TimeUtils.covertTimeToText(attachmentsItem.getSent_time()));
            holder.seenRl.setVisibility(View.GONE);
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.bg_default)
                .error(R.drawable.ic_gallery_gray)
                .priority(Priority.HIGH);
        ImageView imageView = null;
        if (attachmentsItem.getType().equals("video")) {
            holder.video_iv.setVisibility(View.VISIBLE);
            holder.mImageIv.setVisibility(View.GONE);
            holder.durationTv.setVisibility(View.VISIBLE);
            if (!attachmentsItem.displayDuration().isEmpty()) {
                holder.durationTv.setText(attachmentsItem.displayDuration());
            }

            imageView = holder.video_iv;
        } else {
            holder.video_iv.setVisibility(View.GONE);
            holder.durationTv.setVisibility(View.GONE);
            holder.mImageIv.setVisibility(View.VISIBLE);
            imageView = holder.mImageIv;
        }
        GlideImageLoader glideImageLoader = new GlideImageLoader(imageView,
                holder.progress_bar);

        glideImageLoader.setOnCallbackLoadImg(new GlideImageLoader.onCallbackLoadImg() {
            @Override
            public void onConnecting() {

            }

            @Override
            public void onFinnished() {
                if (attachmentsItem.getType().equals("video")) {
                    holder.viewImageVideo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailded() {
                if (attachmentsItem.getType().equals("video")) {
                    holder.viewImageVideo.setVisibility(View.VISIBLE);
                }
            }
        });
        glideImageLoader.load(attachmentsItem.getUrl(), options);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (attachmentsItem.getType().equals("video")) {
                                    if (mOnImageSelectedEventListener != null) {
                    mOnImageSelectedEventListener.onItemSelected(attachmentsItem);
                }
                }else {
                    if (mOnImageSelectedEventListener != null) {
                        mOnImageSelectedEventListener.showListItem(mAttachmentsItems,position);
                    }
                }



            }
        });


    }

    @Override
    public int getItemCount() {
        return (mAttachmentsItems.size() > 4) ? 4 : mAttachmentsItems.size();
    }

    public class MediaMessageVH extends RecyclerView.ViewHolder {
        private ImageView mImageIv;
        private ImageView viewImageVideo;
        private ImageView video_iv;
        private ProgressBar progress_bar;
        private TextView timeMessTv;
        private RelativeLayout seenRl;
        private RelativeLayout viewSeen;
        private TextView durationTv;
        private TextView countTv;
        private RelativeLayout countRl;

        public MediaMessageVH(View itemView) {
            super(itemView);
            mImageIv = itemView.findViewById(R.id.image_iv);
            video_iv = itemView.findViewById(R.id.video_iv);
            viewImageVideo = itemView.findViewById(R.id.viewImageVideo);
            progress_bar = itemView.findViewById(R.id.progress_bar);
            timeMessTv = itemView.findViewById(R.id.message_time_tv);
            seenRl = itemView.findViewById(R.id.seen_rl);
            viewSeen = itemView.findViewById(R.id.viewSeen);
            durationTv = itemView.findViewById(R.id.duration_tv);
            countTv = itemView.findViewById(R.id.tvCount);
            countRl = itemView.findViewById(R.id.count_rl);
        }
    }

    public interface OnImageSelectedEventListener {
        void onItemSelected(AttachmentsItem attachmentsItem);
        void showListItem(List<AttachmentsItem> attachmentsItems,int position);

    }
}
