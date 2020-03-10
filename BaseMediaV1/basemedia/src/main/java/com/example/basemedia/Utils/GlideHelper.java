package com.example.basemedia.Utils;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.basemedia.R;

public class GlideHelper {
    public static void loadUrl(@NonNull String url, @NonNull ImageView image) {
        Glide.with(image.getContext())
                .load(url)
                .apply(new RequestOptions().error(R.drawable.placeholder_image).centerCrop().placeholder(R.drawable.placeholder_image)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                ).into(image);
    }
}
