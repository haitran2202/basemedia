package com.example.basemedia;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basemedia.Utils.UrlHelper;
import com.example.basemedia.adapter.ShowFullListImageAdapter;

import java.util.ArrayList;

public class ShowListImageActivity extends AppCompatActivity {

    RecyclerView myImage;
    TextView positionTv;
    ArrayList<String> url = new ArrayList<>();
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_full_image);

        url = getIntent().getStringArrayListExtra("image_url");
        position = getIntent().getIntExtra("position",0);
        positionTv = findViewById(R.id.position_tv1);
        positionTv.setText(String.format("%s/%s",position+1,url.size()));
        myImage = findViewById(R.id.full_image_rv);
        setupHorizontalRecyclerView(getApplicationContext(),myImage);
        ShowFullListImageAdapter imageAdapter = new ShowFullListImageAdapter(url,position);
        myImage.setAdapter(imageAdapter);
        imageAdapter.setOnLisener(new ShowFullListImageAdapter.OnLisener() {
            @Override
            public void back() {
           finish();
            }

        });
        myImage.setOnFlingListener(null);
        snapHelper.attachToRecyclerView(myImage);
        myImage.scrollToPosition(position);
    }

    LinearSnapHelper snapHelper = new LinearSnapHelper() {
        @Override
        public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager,
                                          int velocityX, int velocityY) {
            View centerView = findSnapView(layoutManager);
            if (centerView == null) {
                return RecyclerView.NO_POSITION;
            }

            int position = layoutManager.getPosition(centerView);
            int targetPosition = -1;
            if (layoutManager.canScrollHorizontally()) {
                if (velocityX < 0) {
                    targetPosition = position - 1;
                } else {
                    targetPosition = position + 1;
                }

            }

            if (layoutManager.canScrollVertically()) {
                if (velocityY < 0) {
                    targetPosition = position - 1;
                } else {
                    targetPosition = position + 1;
                }
            }

            final int firstItem = 0;
            final int lastItem = layoutManager.getItemCount() - 1;
            targetPosition = Math.min(lastItem, Math.max(targetPosition, firstItem));
            positionTv.setText(String.format("%s/%s",targetPosition+1,url.size()));
            return targetPosition;
        }
    };

    public static void setupHorizontalRecyclerView(Context context, RecyclerView mRecyclerView) {
        LinearLayoutManager layoutManager = new GridLayoutManager(context, 1,
                GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setClipToPadding(false);
//        layoutManager.setStackFromEnd(true);
        mRecyclerView.setHasFixedSize(true);
    }
}
