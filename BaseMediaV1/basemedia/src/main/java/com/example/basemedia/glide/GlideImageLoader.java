package com.example.basemedia.glide;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GlideImageLoader {
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	Handler mHandler = new Handler();
	onCallbackLoadImg onCallbackLoadImg ;

	public void setOnCallbackLoadImg(
			GlideImageLoader.onCallbackLoadImg onCallbackLoadImg) {
		this.onCallbackLoadImg = onCallbackLoadImg;
	}

	public GlideImageLoader(ImageView imageView, ProgressBar progressBar) {
		mImageView = imageView;
		mProgressBar = progressBar;
	}

	public void load(final String url, RequestOptions options) {
		if (url == null || options == null) return;

		onConnecting();

		//set Listener & start

		ProgressAppGlideModule.expect(url, new ProgressAppGlideModule.UIonProgressListener() {
			@Override
			public void onProgress(long bytesRead, long expectedLength) {
				if (mProgressBar != null) {

					mProgressBar.setProgress((int) (100 * bytesRead / expectedLength));
				}
			}

			@Override
			public float getGranualityPercentage() {
				return 1.0f;
			}
		});
		//Get Image
		Glide.with(mImageView.getContext())
				.load(url)
				.transition(withCrossFade())
				.apply(options.skipMemoryCache(true))
				.listener(new RequestListener<Drawable>() {
					@Override
					public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
						ProgressAppGlideModule.forget(url);
						if(onCallbackLoadImg!=null){
							onCallbackLoadImg.onFailded();
						}
						onFinished();
						return false;
					}

					@Override
					public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
						ProgressAppGlideModule.forget(url);
						onFinished();
						return false;
					}
				})
				.into(mImageView);
	}


	private void onConnecting() {
		if(onCallbackLoadImg!=null){
			onCallbackLoadImg.onConnecting();
		}
		if (mProgressBar != null)
			mHandler.postDelayed(new Runnable() {
				@Override public void run() {
					if(mProgressBar.getVisibility() == View.GONE){
						mProgressBar.setVisibility(View.VISIBLE);
					}
				}
			},1000);

	}

	private void onFinished() {
		if(onCallbackLoadImg!=null){
			onCallbackLoadImg.onFinnished();
		}
		mHandler.removeCallbacksAndMessages(null);
		if (mProgressBar != null && mImageView != null) {
			mProgressBar.setVisibility(View.GONE);
			mImageView.setVisibility(View.VISIBLE);
		}
	}

	public interface onCallbackLoadImg{
		void onConnecting();
		void onFinnished();
		void onFailded();
	}
}
