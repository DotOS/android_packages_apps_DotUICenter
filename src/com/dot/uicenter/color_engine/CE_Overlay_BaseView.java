package com.dot.uicenter.color_engine;

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

import com.dot.uicenter.R;
import com.dot.uicenter.pageindicator.PageIndicatorView;

public class CE_Overlay_BaseView extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.ce_overlay_layout, container, false);
        final ViewPager mViewPager = view.findViewById(R.id.overlay_review_container);
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
        PageIndicatorView pageIndicatorView = view.findViewById(R.id.page_indicator_overlay);
        pageIndicatorView.setViewPager(mViewPager);
        return view;
    }

    public void updateFABTheme(String theme) {
        FloatingActionButton apply_overlay = getActivity().findViewById(R.id.apply_overlay);
        apply_overlay.setContentDescription(theme);
    }

}
