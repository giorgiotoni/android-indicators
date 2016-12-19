package com.tengio.android.indicators.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainFragment extends Fragment {

    public static final String FRAGMENT_POS = "FRAGMENT_POS";

    private FrameLayout fragmentLayout;

    public static Fragment newInstance(int position) {
        MainFragment fragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_POS, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int page = getArguments().getInt(FRAGMENT_POS, 0);
        fragmentLayout = (FrameLayout) getView().findViewById(R.id.main_fragment_layout);
        displayContent(page);
    }

    private void displayContent(int page) {
        switch (page) {
            case 0:
                fragmentLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            case 1:
                fragmentLayout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
        }
    }
}
