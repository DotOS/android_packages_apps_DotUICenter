package com.dot.uicenter.color_engine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.dot.uicenter.R;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Green;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Grey;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Pink;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Pixel;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Purple;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Red;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Sky;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Teal;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Violet;
import com.dot.uicenter.color_engine.accent_contaners.CE_Accent_Yellow;
import com.dot.uicenter.pageindicator.PageIndicatorView;
import com.dot.uicenter.utils.ExpandableLayout;

public class CE_Accent_BaseView extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.ce_accent_layout, container, false);
        show_accent_info(view);
        show_accent_custom(view);
        final ViewPager mViewPager = view.findViewById(R.id.accent_viewpager);
        PagerAdapter mSectionsPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount()
            {
                return 9;
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
        PageIndicatorView pageIndicatorView = view.findViewById(R.id.page_indicator_accent);
        pageIndicatorView.setViewPager(mViewPager);
        applyAccent(view);
        return view;
    }

    public void applyAccent(final View view) {
        final ImageButton apply_accent = view.findViewById(R.id.apply_preset);
        apply_accent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accent = String.valueOf(apply_accent.getContentDescription());
                switch (accent) {
                    case "Pixel":
                        OverlayUtils.enableAccentTheme("com.android.dot.pixel");
                        break;
                    case "Green":
                        OverlayUtils.enableAccentTheme("com.android.dot.green");
                        break;
                    case "Yellow":
                        OverlayUtils.enableAccentTheme("com.android.dot.yellow");
                        break;
                    case "Red":
                        OverlayUtils.enableAccentTheme("com.android.dot.red");
                        break;
                    case "Purple":
                        OverlayUtils.enableAccentTheme("com.android.dot.purple");
                        break;
                    case "Grey":
                        OverlayUtils.enableAccentTheme("com.android.dot.grey");
                        break;
                    case "Sky":
                        OverlayUtils.enableAccentTheme("com.android.dot.sky");
                        break;
                    case "Teal":
                        OverlayUtils.enableAccentTheme("com.android.dot.stock");
                        break;
                    case "Violet":
                        OverlayUtils.enableAccentTheme("com.android.dot.violet");
                        break;
                    case "Pink":
                        OverlayUtils.enableAccentTheme("com.android.dot.pink");
                        break;
                    case "Custom":
                        EditText custom_accent = view.findViewById(R.id.custom_package);
                        if (!custom_accent.getText().toString().isEmpty()) {
                            OverlayUtils.enableAccentTheme(custom_accent.getText().toString());
                        }
                        break;
                    default :
                        Log.e("dotOS OverlayManager", "Error: No Accent Package set");
                        Toast.makeText(getContext(), "dotOS Overlay Manager : 'Error: No Accent Package set'", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void updateAccentButton(String theme) {
        ImageButton apply_accent = getActivity().findViewById(R.id.apply_preset);
        apply_accent.setContentDescription(theme);
    }

    public void show_accent_info(View view) {
        final ImageButton show_info = view.findViewById(R.id.show_accent_info);
        final ExpandableLayout info_layout = view.findViewById(R.id.overlay_info_layout);
        show_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info_layout.isCollapsed()) {
                    show_info.setImageResource(R.drawable.ic_expand_less);
                    info_layout.expand(true);
                } else if (info_layout.isExpanded()) {
                    show_info.setImageResource(R.drawable.ic_expand_more);
                    info_layout.collapse(true);
                }
            }
        });
    }

    public void show_accent_custom(View view) {
        final CardView custom_apply = view.findViewById(R.id.card_ca);
        final ExpandableLayout custom_layout = view.findViewById(R.id.overlay_custom_layout);
        final ImageButton apply_accent = view.findViewById(R.id.apply_preset);
        custom_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (custom_layout.isCollapsed()) {
                    custom_layout.expand(true);
                    apply_accent.setContentDescription("Custom");
                } else if (custom_layout.isExpanded()) {
                    custom_layout.collapse(true);
                    apply_accent.setContentDescription("Pixel");
                }
            }
        });
    }

}
