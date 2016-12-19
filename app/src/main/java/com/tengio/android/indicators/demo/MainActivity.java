package com.tengio.android.indicators.demo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.tengio.android.indicators.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleIndicator = (CircleIndicator) findViewById(R.id.indicator_view);

        viewPager = (ViewPager) findViewById(R.id.main_view_pager);
        viewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        circleIndicator.bindTo(viewPager);

        circleIndicator.setIndicatorSize(4F);
        circleIndicator.setSelectedIndicatorSize(6F);
        circleIndicator.setMaxDistanceBetweenIndicators(30F);
    }
}

