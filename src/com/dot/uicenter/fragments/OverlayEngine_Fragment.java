package com.dot.uicenter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dot.uicenter.R;
import com.dot.uicenter.fragments.overlay_engine.CE_Overlay_Showcase_Black;
import com.dot.uicenter.fragments.overlay_engine.CE_Overlay_Showcase_Dark;
import com.dot.uicenter.fragments.overlay_engine.CE_Overlay_Showcase_Light;

public class OverlayEngine_Fragment extends Fragment {

    static ViewPager mViewPager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.ce_overlay_layout, container, false);
        mViewPager = view.findViewById(R.id.overlay_review_container);
        PagerAdapter mSectionsPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new CE_Overlay_Showcase_Light();
                    case 1:
                        return new CE_Overlay_Showcase_Dark();
                    case 2:
                        return new CE_Overlay_Showcase_Black();
                    default:
                        return null;
                }
            }
        };
        updateFABTheme("Light");
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int arg0) { }
            public void onPageScrolled(int arg0, float arg1, int arg2) { }
            public void onPageSelected(int currentPage) {
                switch (currentPage) {
                    case 0:
                        updateFABTheme("Light");
                        break;
                    case 1:
                        updateFABTheme("Dark");
                        break;
                    case 2:
                        updateFABTheme("Black");
                        break;
                }
            }
        });

        mViewPager.setAdapter(mSectionsPagerAdapter);
        return view;
    }

    public static void setState(int i) {
        if (mViewPager != null)
        mViewPager.setCurrentItem(i, true);
    }

    public void updateFABTheme(String theme) {
        ImageButton apply_overlay = getActivity().findViewById(R.id.apply_oc);
        apply_overlay.setContentDescription(theme);
    }

}

