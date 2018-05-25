package com.dot.uicenter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.dot.uicenter.R;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Green;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Grey;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Pink;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Pixel;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Purple;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Red;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Sky;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Teal;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Violet;
import com.dot.uicenter.fragments.accent_engine.CE_Accent_Yellow;
import com.dot.uicenter.utils.ExpandableLayout;

public class AccentEngine_Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.ce_accent_layout, container, false);
        show_accent_custom(view);
        final ViewPager mViewPager = view.findViewById(R.id.accent_viewpager);
        PagerAdapter mSectionsPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount()
            {
                return 10;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new CE_Accent_Pixel();
                    case 1:
                        return new CE_Accent_Green();
                    case 2:
                        return new CE_Accent_Yellow();
                    case 3:
                        return new CE_Accent_Red();
                    case 4:
                        return new CE_Accent_Purple();
                    case 5:
                        return new CE_Accent_Grey();
                    case 6:
                        return new CE_Accent_Sky();
                    case 7:
                        return new CE_Accent_Teal();
                    case 8:
                        return new CE_Accent_Violet();
                    case 9:
                        return new CE_Accent_Pink();
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
                        updateAccentButton("Pixel");
                        break;
                    case 1:
                        updateAccentButton("Green");
                        break;
                    case 2:
                        updateAccentButton("Yellow");
                        break;
                    case 3:
                        updateAccentButton("Red");
                        break;
                    case 4:
                        updateAccentButton("Purple");
                        break;
                    case 5:
                        updateAccentButton("Grey");
                        break;
                    case 6:
                        updateAccentButton("Sky");
                        break;
                    case 7:
                        updateAccentButton("Teal");
                        break;
                    case 8:
                        updateAccentButton("Violet");
                        break;
                    case 9:
                        updateAccentButton("Pink");
                        break;
                }
            }
        });
        mViewPager.setAdapter(mSectionsPagerAdapter);
        return view;
    }

    public void updateAccentButton(String theme) {
        //ImageButton apply_accent = getActivity().findViewById(R.id.main_fab);
        //apply_accent.setContentDescription(theme);
    }

    public void show_accent_custom(View view) {
        final CardView custom_apply = view.findViewById(R.id.card_ca);
        final ExpandableLayout custom_layout = view.findViewById(R.id.overlay_custom_layout);
        //final ImageButton apply_accent = getActivity().findViewById(R.id.main_fab);
        custom_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (custom_layout.isCollapsed()) {
                    custom_layout.expand(true);
                 //   apply_accent.setContentDescription("Custom");
                } else if (custom_layout.isExpanded()) {
                    custom_layout.collapse(true);
                //    apply_accent.setContentDescription("Pixel");
                }
            }
        });
    }

}

