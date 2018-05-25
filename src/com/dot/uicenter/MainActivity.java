package com.dot.uicenter;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dot.uicenter.adapters.RecommendedSettings_Adapter;
import com.dot.uicenter.fragments.AccentEngine_Fragment;
import com.dot.uicenter.fragments.InterfaceMisc_Fragment;
import com.dot.uicenter.fragments.OverlayEngine_Fragment;
import com.dot.uicenter.utils.ObjectToolsAnimator;
import com.dot.uicenter.utils.OverlayUtils;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    CardView fc;
    RelativeLayout int_ct;
    NestedScrollView main_ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_layout);
        loadRecommendedSettings();
        fabController();
        loadFragments();
        interfaceController();
    }

    private void loadRecommendedSettings() {
        String[] title = {
                getString(R.string.rs_title0),
                getString(R.string.rs_title1),
                getString(R.string.rs_title2)
        };
        String[] summary = {
                getString(R.string.rs_summary0),
                getString(R.string.rs_summary1),
                getString(R.string.rs_summary2)
        };
        int[] img = {
                R.drawable.ic_theme_main,
                R.drawable.ic_color_accent,
                R.drawable.ic_nightmode
        };
        List<RecommendedSettings_Adapter.Holder> rs_adpt = new ArrayList<>();
        RecommendedSettings_Adapter adapter = new RecommendedSettings_Adapter(rs_adpt);
        for (int i = 0; i < title.length; i++) {
            RecommendedSettings_Adapter.Holder adpt = new RecommendedSettings_Adapter.Holder(title[i], summary[i], img[i], null);
            rs_adpt.add(adpt);
        }
        RecyclerView recyclerView = findViewById(R.id.rmd_recycler);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private void fabController() {
        fc = findViewById(R.id.fc_layout);
        int_ct = findViewById(R.id.int_ct);
        main_ss = findViewById(R.id.main_view);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectToolsAnimator.move(fc, "alpha", 1, 0, 500);
                fc.setVisibility(View.GONE);
                int_ct.setVisibility(View.VISIBLE);
                ObjectToolsAnimator.move(int_ct, "translationY", 900 , 0);
                ObjectToolsAnimator.move(int_ct, "alpha", 0, 1, 800);
                main_ss.setVisibility(View.GONE);
            }
        });
        ImageButton gb = findViewById(R.id.close_menu);
        gb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.setVisibility(View.VISIBLE);
                ObjectToolsAnimator.move(fc, "alpha", 0, 1, 500);
                ObjectToolsAnimator.move(int_ct, "alpha", 1, 0, 800);
                ObjectToolsAnimator.move(int_ct, "translationY", 0 , 900);
                int_ct.setVisibility(View.GONE);
                main_ss.setVisibility(View.VISIBLE);
            }
        });
        ImageButton close = findViewById(R.id.go_back);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.int_ct).getVisibility() == View.VISIBLE) {
            fc.setVisibility(View.VISIBLE);
            ObjectToolsAnimator.move(fc, "alpha", 0, 1, 500);
            ObjectToolsAnimator.move(int_ct, "alpha", 1, 0, 800);
            ObjectToolsAnimator.move(int_ct, "translationY", 0 , 900);
            int_ct.setVisibility(View.GONE);
            main_ss.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
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
        LinearLayout oc_l = findViewById(R.id.overlay_c);
        LinearLayout ac_l = findViewById(R.id.accent_c);
        LinearLayout ms_l = findViewById(R.id.misc_s);
        final FrameLayout oc = findViewById(R.id.oc);
        final FrameLayout ac = findViewById(R.id.ac);
        final FrameLayout ms = findViewById(R.id.ms);
        loadFragment(R.id.oc, new OverlayEngine_Fragment());
        loadFragment(R.id.ac, new AccentEngine_Fragment());
        loadFragment(R.id.ms, new InterfaceMisc_Fragment());
        oc_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oc.getVisibility() != View.VISIBLE) {
                    oc.setVisibility(View.VISIBLE);
                    updateApplyVisibility();
                } else {
                    oc.setVisibility(View.GONE);
                }
            }
        });
        ac_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ac.getVisibility() != View.VISIBLE) {
                    ac.setVisibility(View.VISIBLE);
                    updateApplyVisibility();
                } else {
                    ac.setVisibility(View.GONE);
                }
            }
        });
        ms_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ms.getVisibility() != View.VISIBLE) {
                    ms.setVisibility(View.VISIBLE);
                    updateApplyVisibility();
                } else {
                    ms.setVisibility(View.GONE);
                }
            }
        });
    }

    public void loadFragment(int container, Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commitAllowingStateLoss();
    }

    private void interfaceController() {
        final Button fab_main = findViewById(R.id.apply_settings);
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
                    case "Grey":
                        OverlayUtils.setAccentTheme(MainActivity.this, 6);
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

    public void updateApplyVisibility() {
        if (findViewById(R.id.apply_settings).getContentDescription() != null) {
            findViewById(R.id.apply_layout).setVisibility(View.VISIBLE);
        }
    }

}
