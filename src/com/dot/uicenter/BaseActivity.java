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

import com.dot.uicenter.color_engine.CE_Accent_BaseView;
import com.dot.uicenter.color_engine.CE_Misc;
import com.dot.uicenter.color_engine.CE_Overlay_BaseView;
import com.dot.uicenter.color_engine.OverlayUtils;
import com.dot.uicenter.night_mode.NightModeFragment;
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
                case R.id.navigation_nightmode:
                    loadFragment(R.id.fragments_container, new NightModeFragment());
					overlay_fab.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("theme", MODE_PRIVATE);
        setTheme(prefs.getInt("theme", 0) == 0 ? R.style.AppTheme : R.style.AppTheme_Dark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        BottomNavigationViewCustom navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FloatingActionButton expand_collpase = findViewById(R.id.expand_collpase);
        FloatingActionButton expand_collpase2 = findViewById(R.id.expand_collpase2);
        expand_collpase.setOnClickListener(this);
        expand_collpase2.setOnClickListener(this);
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
    }

    public void loadFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();
    }
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
    }

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
        FloatingActionButton expand_collpase = findViewById(R.id.expand_collpase);
        FloatingActionButton expand_collpase2 = findViewById(R.id.expand_collpase2);
        switch (v.getId()) {
            case R.id.expand_collpase:
                if (expandableLayout.isCollapsed()) {
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase2, "alpha", 0, 1, 400);
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase, "alpha", 1, 0, 400);
                    expandableLayout.expand(true);
                }
            case R.id.expand_collpase2:
                if (expandableLayout.isExpanded()) {
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase2, "alpha", 1, 0, 400);
                    ObjectToolsAnimator.moveAndAnimate(expand_collpase, "alpha", 0, 1, 400);
                    expandableLayout.collapse(true);
                }
                break;
        }
    }
}
