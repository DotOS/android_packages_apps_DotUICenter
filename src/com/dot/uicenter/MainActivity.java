package com.dot.uicenter;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dot.uicenter.bottom_fragments.AccentEngine_Fragment;
import com.dot.uicenter.bottom_fragments.OverlayEngine_Fragment;
import com.dot.uicenter.bottom_fragments.InterfaceMisc_Fragment;
import com.dot.uicenter.head_fragments.AccentEngine_Head;
import com.dot.uicenter.head_fragments.OverlayEngine_Head;
import com.dot.uicenter.head_fragments.InterfaceMisc_Head;
import com.dot.uicenter.pageindicator.PageIndicatorView;
import com.dot.uicenter.utils.ControlledViewPager;
import com.dot.uicenter.utils.ObjectToolsAnimator;
import com.dot.uicenter.utils.OverlayUtils;
import com.dot.uicenter.utils.SystemProperties;
import com.dot.uicenter.utils.WrapContentViewPager;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WrapContentViewPager viewPager = findViewById(R.id.viewPager_main);
        final ControlledViewPager fragment_pager = findViewById(R.id.fragment_viewpager);
        PageIndicatorView pageIndicatorView = findViewById(R.id.pageIndicatorView);
        pageIndicatorView.setViewPager(viewPager);
        PagerAdapter mSectionsPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new OverlayEngine_Head();
                    case 1:
                        return new AccentEngine_Head();
                    case 2:
                        return new InterfaceMisc_Head();

                    default:
                        return null;
                }
            }
        };
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int arg0) { }
            public void onPageScrolled(int arg0, float arg1, int arg2) { }
            public void onPageSelected(int currentPage) {
                fragment_pager.setCurrentItem(currentPage, true);
            }
        });
        viewPager.setAdapter(mSectionsPagerAdapter);
        loadFragments();
        mainFabListener();
    }

    public void mainFabListener() {
        final FloatingActionButton fab_main = findViewById(R.id.main_fab);
        fab_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (fab_main.getContentDescription().toString()) {
                    case "Light":
                        OverlayUtils.setOverlayTheme(MainActivity.this, 1);
                        break;
                    case "Dark":
                        OverlayUtils.setOverlayTheme(MainActivity.this, 2);
                        break;
                    case "Black":
                        OverlayUtils.setOverlayTheme(MainActivity.this, 3);
                        break;
                    case "Pixel":
                        OverlayUtils.setAccentTheme(MainActivity.this, 1);
                        break;
                    case "Green":
                        OverlayUtils.setAccentTheme(MainActivity.this, 2);
                        break;
                    case "Yellow":
                        OverlayUtils.setAccentTheme(MainActivity.this, 3);
                        break;
                    case "Red":
                        OverlayUtils.setAccentTheme(MainActivity.this, 4);
                        break;
                    case "Purple":
                        OverlayUtils.setAccentTheme(MainActivity.this, 5);
                        break;
                    case "Sky":
                        OverlayUtils.setAccentTheme(MainActivity.this, 7);
                        break;
                    case "Teal":
                        OverlayUtils.setAccentTheme(MainActivity.this, 0);
                        break;
                    case "Violet":
                        OverlayUtils.setAccentTheme(MainActivity.this, 8);
                        break;
                    case "Pink":
                        OverlayUtils.setAccentTheme(MainActivity.this, 9);
                        break;
                    case "Custom":
                        View view = View.inflate(getApplicationContext(), R.layout.ce_accent_layout, null);
                        EditText custom_accent = view.findViewById(R.id.custom_package);
                        if (!custom_accent.getText().toString().isEmpty()) {
                            OverlayUtils.enableAccentTheme(custom_accent.getText().toString());
                        }
                        break;
                }
              refreshUI();
            }
        });
    }
	
    public void refreshUI() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
	try {
            sleep(1700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, this.getClass()));
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
    }

    public void loadFragments() {
        final WrapContentViewPager headPager = findViewById(R.id.viewPager_main);
        final FloatingActionButton fab_main = findViewById(R.id.main_fab);
        ControlledViewPager viewPager = findViewById(R.id.fragment_viewpager);
        PagerAdapter mSectionsPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new OverlayEngine_Fragment();
                    case 1:
                        return new AccentEngine_Fragment();
                    case 2:
                        return new InterfaceMisc_Fragment();
                    default:
                        return null;
                }
            }
        };
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int arg0) { }
            public void onPageScrolled(int arg0, float arg1, int arg2) { }
            public void onPageSelected(int currentPage) {
                switch (currentPage) {
                    case 0:
                        if (fab_main.getTranslationX() != 0) {
                            ObjectToolsAnimator.moveAndAnimate(fab_main, "translationX", 200, 0);
                            fab_main.setTranslationX(0);
                        }
                        headPager.setCurrentItem(0, true);
                        break;
                    case 1:
                        if (fab_main.getTranslationX() != 0) {
                            ObjectToolsAnimator.moveAndAnimate(fab_main, "translationX", 200, 0);
                            fab_main.setTranslationX(0);
                        }
                        headPager.setCurrentItem(1, true);
                        break;
                    case 2:
                        ObjectToolsAnimator.moveAndAnimate(fab_main, "translationX", 0, 200);
                        fab_main.setTranslationX(200);
                        headPager.setCurrentItem(2, true);
                        break;
                }
            }
        });

    }
}
