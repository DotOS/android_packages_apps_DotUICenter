package com.dot.uicenter.ui_controller.custom_clocks;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dot.uicenter.R;
import com.dot.uicenter.color_engine.OverlayUtils;
import com.dot.uicenter.pageindicator.PageIndicatorView;
import com.dot.uicenter.ui_controller.custom_clocks.clock_views.Clock_Stock;
import com.dot.uicenter.ui_controller.custom_clocks.clock_views.Clock_dotOS;

public class Base_ClockVIew extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.clock_layout, container, false);
        final ViewPager mViewPager = view.findViewById(R.id.clock_viewpager);
        PageIndicatorView pageIndicatorView = view.findViewById(R.id.clock_page_indicator);
        pageIndicatorView.setViewPager(mViewPager);
        PagerAdapter mSectionsPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount()
            {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Clock_dotOS();
                    case 1:
                        return new Clock_Stock();
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
                        setClockVal(view,"0");
                        break;
                    case 1:
                        setClockVal(view,"1");
                        break;
                }
            }
        });
        mViewPager.setAdapter(mSectionsPagerAdapter);
        final ImageButton apply_clock = view.findViewById(R.id.apply_clock);
        apply_clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (apply_clock.getContentDescription().toString()) {
                    case "0":
                        OverlayUtils.setClockStyle(0);
                        break;
                    case "1":
                        OverlayUtils.setClockStyle(1);
                        break;
                }
            }
        });
        return view;
    }

    public void setClockVal(View view, String clockVal) {
        ImageButton apply_clock = view.findViewById(R.id.apply_clock);
        apply_clock.setContentDescription(clockVal);
    }
}
