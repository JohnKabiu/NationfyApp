package com.apps.nationfy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.apps.nationfy.Item.FullImageItem;
import com.apps.nationfy.R;
import com.apps.nationfy.Utils.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class FullImageActivity extends AppCompatActivity {

    public static final String EXTRA_POS = "key.EXTRA_POS";
    public static final String EXTRA_IMGS = "key.EXTRA_IMGS";

    private ImageLoader imgloader = ImageLoader.getInstance();
    private FullImageItem adapter;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        if (!imgloader.isInited()) Tools.initImageLoader(this);
        viewPager = (ViewPager) findViewById(R.id.pager);

        ArrayList<String> items = new ArrayList<>();
        Intent i = getIntent();
        final int position = i.getIntExtra(EXTRA_POS, 0);
        items = i.getStringArrayListExtra(EXTRA_IMGS);
        adapter = new FullImageItem(FullImageActivity.this, items);
        final int total = adapter.getCount();




        viewPager.setAdapter(adapter);

        // displaying selected image first
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int pos, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int pos) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        ((ImageView) findViewById(R.id.btnClose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        if (!imgloader.isInited()) Tools.initImageLoader(this);
        super.onResume();
    }

}
