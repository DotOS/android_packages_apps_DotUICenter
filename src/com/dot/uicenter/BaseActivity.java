package com.dot.uicenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.dot.uicenter.color_engine.CE_Accent_BaseView;
import com.dot.uicenter.color_engine.CE_Misc;
import com.dot.uicenter.color_engine.CE_Overlay_BaseView;
import com.dot.uicenter.color_engine.OverlayUtils;
import com.dot.uicenter.ui_controller.custom_clocks.Base_ClockVIew;
import com.dot.uicenter.ui_controller.qs_panel.Base_QSPanel;
import com.dot.uicenter.utils.BottomNavigationViewCustom;
import com.dot.uicenter.utils.ExpandableLayout;
import com.dot.uicenter.utils.ObjectToolsAnimator;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationViewCustom.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationViewCustom.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FloatingActionButton overlay_fab = findViewById(R.id.apply_overlay);
            switch (item.getItemId()) {
                case R.id.navigation_color_overlay:
                    loadFragment(R.id.fragments_container, new CE_Overlay_BaseView());
                    overlay_fab.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_color_accent:
                    loadFragment(R.id.fragments_container, new CE_Accent_BaseView());
                    overlay_fab.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_ui_misc:
                    loadFragment(R.id.fragments_container, new CE_Misc());
                    overlay_fab.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };
    private BottomNavigationViewCustom.OnNavigationItemSelectedListener mUIOnNavigationItemSelectedListener = new BottomNavigationViewCustom.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_clock:
                    loadFragment(R.id.controller_container, new Base_ClockVIew());
                    return true;
                case R.id.qs_panel:
                    loadFragment(R.id.controller_container, new Base_QSPanel());
                    break;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("theme", MODE_PRIVATE);
        switch (prefs.getInt("theme", 0)) {
            case 0:
                setTheme(R.style.AppTheme);
                break;
            case 1:
                setTheme(R.style.AppTheme_Dark);
                break;
            case 2:
                setTheme(R.style.AppTheme_Black);
                break;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        RelativeLayout rootView = findViewById(R.id.rootView_base);
        BottomNavigationViewCustom navigation = findViewById(R.id.navigation);
        BottomNavigationViewCustom uic_navigation = findViewById(R.id.ui_c_navigation);
        uic_navigation.setOnNavigationItemSelectedListener(mUIOnNavigationItemSelectedListener);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton expand_collpase = findViewById(R.id.expand_collpase);
        FloatingActionButton expand_collpase2 = findViewById(R.id.expand_collpase2);
        FloatingActionButton expand_collpase3 = findViewById(R.id.expand_collpase3);
        FloatingActionButton expand_collpase_uic = findViewById(R.id.expand_collpase_uic);
        expand_collpase.setOnClickListener(this);
        expand_collpase2.setOnClickListener(this);
        expand_collpase3.setOnClickListener(this);
        expand_collpase_uic.setOnClickListener(this);
        loadFirstFragment();
        applySelectedTheme();
        updateViews();
    }

    public void updateViews() {
        SharedPreferences prefs = getSharedPreferences("auto_mode", MODE_PRIVATE);
        FloatingActionButton apply_overlay = findViewById(R.id.apply_overlay);
        if (prefs.getInt("auto_mode", 0) == 0) {
            apply_overlay.setVisibility(View.VISIBLE);
        } else if (prefs.getInt("auto_mode", 0) == 1) {
            apply_overlay.setVisibility(View.GONE);
        }
    }

    public void loadFirstFragment() {
        loadFragment(R.id.fragments_container, new CE_Overlay_BaseView());
        loadFragment(R.id.controller_container, new Base_ClockVIew());
    }

    public void loadFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();
    }
    /*
     * Not used anymore
    /*
    public void loadFragment(int container, android.app.Fragment fragment) {
        int backStackEntry = getSupportFragmentManager().getBackStackEntryCount();
        if (backStackEntry > 0) {
            for (int i = 0; i < backStackEntry; i++) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            for (int i = 0; i < getSupportFragmentManager().getFragments().size(); i++) {
                Fragment mFragment = getSupportFragmentManager().getFragments().get(i);
                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                }
            }
        }
        getFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();

    }*/

    public void applySelectedTheme() {
        final FloatingActionButton apply_overlay = findViewById(R.id.apply_overlay);
        apply_overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (apply_overlay.getContentDescription().toString().contains("Light")) {
                    OverlayUtils.setOverlayTheme(1);
                    SharedPreferences prefs = getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("theme", 0);
                    editor.commit();
                    RestartApp();
                }
                if (apply_overlay.getContentDescription().toString().contains("Dark")) {
                    OverlayUtils.setOverlayTheme(2);
                    SharedPreferences prefs = getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("theme", 1);
                    editor.commit();
                    RestartApp();
                }
                if (apply_overlay.getContentDescription().toString().contains("Black")) {
                    OverlayUtils.setOverlayTheme(3);
                    SharedPreferences prefs = getSharedPreferences("theme", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("theme", 2);
                    editor.commit();
                    RestartApp();
                }
            }
        });
    }

    public void RestartApp() {
        Intent i = getPackageManager().getLaunchIntentForPackage(getPackageName());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        ExpandableLayout expandableLayout = findViewById(R.id.expandable_view);
        ExpandableLayout expandableLayout_uic = findViewById(R.id.ui_controller_ex_view);
        FloatingActionButton expand_collpase = findViewById(R.id.expand_collpase);
        FloatingActionButton expand_collpase2 = findViewById(R.id.expand_collpase2);
        FloatingActionButton expand_collpase_uic = findViewById(R.id.expand_collpase_uic);
        switch (v.getId()) {
            case R.id.expand_collpase:
                if (expandableLayout.isCollapsed()) {
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase2, "alpha", 0, 1, 400);
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase, "alpha", 1, 0, 400);
                    //ObjectToolsAnimator.moveAndAnimate(expand_collpase_uic, "alpha", 1, 0, 400);
                    expandableLayout.expand(true);
                }
                break;
            case R.id.expand_collpase2:
                if (expandableLayout.isExpanded()) {
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase2, "alpha", 1, 0, 400);
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase, "alpha", 0, 1, 400);
                    //ObjectToolsAnimator.moveAndAnimate(expand_collpase_uic, "alpha", 0, 1, 400);
                    expandableLayout.collapse(true);
                }
                break;
            case R.id.expand_collpase3:
                if (expandableLayout_uic.isExpanded()) {
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase2, "alpha", 1, 0, 400);
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase, "alpha", 0, 1, 400);
                    //ObjectToolsAnimator.moveAndAnimate(expand_collpase_uic, "alpha", 0, 1, 400);
                    expandableLayout_uic.collapse(true);
                }
                break;
            case R.id.expand_collpase_uic:
                if (expandableLayout.isCollapsed()) {
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase2, "alpha", 0, 1, 400);
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase, "alpha", 1, 0, 400);
                    //ObjectToolsAnimator.moveAndAnimate(expand_collpase_uic, "alpha", 1, 0, 400);
                    expandableLayout_uic.expand(true);
                }
                break;
        }
    }
}
