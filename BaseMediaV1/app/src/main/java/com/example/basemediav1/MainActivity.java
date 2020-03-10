package com.example.basemediav1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.example.basemedia.PopupPlayVideo;
import com.example.basemedia.ShowListImageActivity;
import com.example.basemedia.Utils.UrlHelper;
import com.example.basemedia.adapter.MediaAdapter;
import com.example.basemedia.model.AttachmentsItem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button openBt;
    RecyclerView listRv;
    private MediaAdapter mediaAdapter;
    private List<AttachmentsItem> url = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openBt = findViewById(R.id.open_bt);
        openBt.setOnClickListener(v -> {
            if (checkPermission()) {
                clickStart(new ArrayList<>());
            }
        });
        listRv = findViewById(R.id.list_rv);
        setupGridRecyclerView(getApplicationContext(),listRv,2);
        mediaAdapter = new MediaAdapter(url);
        mediaAdapter.setOnImageSelectedEventListener(new MediaAdapter.OnImageSelectedEventListener() {
            @Override
            public void onItemSelected(AttachmentsItem attachmentsItem) {
                // show video
                PopupPlayVideo popupPlayVideo = PopupPlayVideo.instance(attachmentsItem.getUrl());
                popupPlayVideo.setUrl(attachmentsItem.getUrl());
                popupPlayVideo.show(getSupportFragmentManager(),"");

            }

            @Override
            public void showListItem(List<AttachmentsItem> attachmentsItems, int position) {
                 ArrayList<String> img_url = new ArrayList<>();
                 for (AttachmentsItem attach: attachmentsItems){
                     img_url.add(attach.getUrl());
                 }
                Intent intent= new Intent(getApplicationContext(), ShowListImageActivity.class);
                intent.putStringArrayListExtra("image_url",img_url);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
        listRv.setAdapter(mediaAdapter);
    }
    public int getRowGridView(int number){
        if(number <= 2){
            return 1;
        }else {
            return 2;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<String> strings = new ArrayList<>();
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            List<Image> images = ImagePicker.getImages(data);
            showProgress();
            for (Image img : images) {
                img.setId(img.getId() * -1);
                strings.add(img.getPath());
                if (UrlHelper.isImageFile(img.getPath())){
                    url.add(new AttachmentsItem(img.getPath(),"image"));
                }
                else {
                    url.add(new AttachmentsItem(img.getPath(),"video"));
                }
            }
            if (strings.size() > 0){
                mediaAdapter.notifyDataSetChanged();
                hideProgress();
            };
        }

//        if (requestCode == 123) {
//            if (null == data) return;
//            String path = getPathFromGalleryUri(this, data.getData());
//            if (TextUtils.isEmpty(path)) return;
//
////            compressVideo(path);
//        }
    }
    void showProgress() {
        findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
    }

    void hideProgress() {
        findViewById(R.id.progressbar).setVisibility(View.GONE);
    }
    public static void setupGridRecyclerView(Context context, RecyclerView mRecyclerView, int spanCount) {
        LinearLayoutManager layoutManager = new GridLayoutManager(context, spanCount);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setClipToPadding(false);
        mRecyclerView.setHasFixedSize(true);
    }

    public static String getPathFromGalleryUri(Context context, Uri contentUri) {
        Cursor cursor;
        String[] proj = {MediaStore.Images.Media.DATA};
        cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (null == cursor) return null;

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);

        cursor.close();
        return result;
    }


    private boolean checkPermission() {
        List<String> permissions = new LinkedList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (permissions.size() > 0) {
            String[] arr = new String[permissions.size()];
            permissions.toArray(arr);
            ActivityCompat.requestPermissions(this, arr, 124);
            return false;
        }
        return true;
    }
    void clickStart(ArrayList<Image> filepath) {
        //    selectVideoFromGallery();
        ImagePicker.create(this) // Activity or Fragment
                .origin(filepath)
                .folderMode(false)
                .includeVideo(true)
                .limit(10)
                .showCamera(true)
                .start();
    }
}
