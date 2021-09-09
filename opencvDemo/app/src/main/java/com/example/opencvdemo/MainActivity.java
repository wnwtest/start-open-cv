package com.example.opencvdemo;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.opencvdemo.databinding.ActivityMainBinding;

import cn.wnw_jackie.opencv453nativelibrary.OpenCVUtil;

public class MainActivity extends AppCompatActivity {

    RelativeLayout MainLayout;
    private ActivityMainBinding binding;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MainLayout = (RelativeLayout) this.findViewById(R.layout.activity_main);
        imageView=(ImageView) this.findViewById(R.id.iv1);
        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        Bitmap srcBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.rainbow);

        Bitmap bm= OpenCVUtil.bmpGrey(srcBitmap);
        imageView.setImageBitmap(bm);

    }

    /**
     * A native method that is implemented by the 'opencvdemo' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}