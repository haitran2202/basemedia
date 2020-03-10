package com.example.basemedia;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;


public class PopupPlayVideo extends DialogFragment {
	PlayerView videpWrapper;
    ImageView sound;
	RelativeLayout viewTop;
	ImageView close;
    ImageView notSound;
    String url = "";
	private SimpleExoPlayer player;
	private boolean playWhenReady = true;
	private int currentWindow = 0;
	private long playbackPosition = 0;
    public void setUrl(String url) {
        this.url = url;
    }
    public static PopupPlayVideo instance(String urlVideo) {
        PopupPlayVideo mPopupPlayVideo = new PopupPlayVideo();
		mPopupPlayVideo.url = urlVideo;
        return mPopupPlayVideo;
    }
	protected View mRootView;
	private WindowManager manager;
	private DisplayMetrics metrics;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.popup_play_videos, container, false);
		videpWrapper = mRootView.findViewById(R.id.videoview);
		sound= mRootView.findViewById(R.id.popup_play_video_img_sound);
		notSound = mRootView.findViewById(R.id.popup_play_video_img_notsound);
		viewTop = mRootView.findViewById(R.id.viewTop);
		close = mRootView.findViewById(R.id.btn_close_video);
		close.setOnClickListener(v -> {
			dismiss();
		});

		this.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		metrics = new DisplayMetrics();

		manager = getActivity().getWindowManager();

		setupWindowDialog();
		// Inject views
		startPresent();
		mRootView.setClickable(true);
		return mRootView;
	}

	private void setupWindowDialog() {
		if (manager != null) {
			manager.getDefaultDisplay().getMetrics(metrics);
			float with;
			float height;

			if (setWidth() > 0 && setWidth() <= 1) {
				with = metrics.widthPixels * setWidth();
				mRootView.setMinimumWidth((int) with);
			}

			if (setHeight() > 0 && setHeight() <= 1) {
				height = metrics.heightPixels * setHeight();
				mRootView.setMinimumHeight((int) height);
			}

		}
	}
	@Override
	public void onResume() {
		super.onResume();
		Window window = getDialog().getWindow();
		if (manager != null) {
			manager.getDefaultDisplay().getMetrics(metrics);
			float with;
			float height;

			if (setWidth() > 0 && setWidth() < 1 && setHeight() > 0 && setHeight() < 1) {
				with = metrics.widthPixels * setWidth();
				height = metrics.heightPixels * setHeight();
				window.setLayout((int) with, (int) height);
			} else if (setHeight() == 1 && setWidth() == 1) {
				window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			} else if (setWidth() > 0 && setWidth() < 1) {
				with = metrics.widthPixels * setWidth();
				window.setLayout((int) with, ViewGroup.LayoutParams.WRAP_CONTENT);
			} else if (setHeight() > 0 && setHeight() < 1) {
				height = metrics.heightPixels * setHeight();
				window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, (int) height);
			} else {
				window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			}
			window.setGravity(Gravity.CENTER);
		}
		if ((Build.VERSION.SDK_INT < 24 || player == null)) {
			initializePlayer();
		}
	}

	public void showDialogFragment(DialogFragment dialogFragment) {
		dialogFragment.show(getFragmentManager(), dialogFragment.getClass().toString());
	}



	protected void startPresent() {
		this.getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		sound.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sound.setVisibility(View.GONE);
				notSound.setVisibility(View.VISIBLE);
				player.setVolume(0f);
			}
		});
		notSound.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sound.setVisibility(View.VISIBLE);
				notSound.setVisibility(View.GONE);
				player.setVolume(1f);
			}
		});
	}
	

	

	protected float setWidth() {
		return 1f;
	}


	protected float setHeight() {
		return 1f;
	}

	

	private void initializePlayer() {
		Uri uri = Uri.parse(url);
		BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
		TrackSelection.Factory videoTrackSelectionFactory =
				new AdaptiveTrackSelection.Factory(bandwidthMeter);
		TrackSelector trackSelector =
				new DefaultTrackSelector(videoTrackSelectionFactory);
		MediaSource mediaSource = buildMediaSource(uri);
		player = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector);
		videpWrapper.setPlayer(player);
		player.setPlayWhenReady(playWhenReady);
		player.seekTo(currentWindow, playbackPosition);
		player.prepare(mediaSource, false, false);

	}
	private MediaSource buildMediaSource(Uri uri) {
		DataSource.Factory dataSourceFactory =
				new DefaultDataSourceFactory(getContext(), "exoplayer-codelab");
		return new ExtractorMediaSource.Factory(dataSourceFactory)
				.createMediaSource(uri);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (Build.VERSION.SDK_INT >= 24) {
			initializePlayer();
		}
	}



	private void releasePlayer() {
		if (player != null) {
			playWhenReady = player.getPlayWhenReady();
			playbackPosition = player.getCurrentPosition();
			currentWindow = player.getCurrentWindowIndex();
			player.release();
			player = null;
		}
	}
	@Override
	public void onPause() {
		super.onPause();
		if (Build.VERSION.SDK_INT < 24) {
			releasePlayer();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		if (Build.VERSION.SDK_INT >= 24) {
			releasePlayer();
		}
	}

}
