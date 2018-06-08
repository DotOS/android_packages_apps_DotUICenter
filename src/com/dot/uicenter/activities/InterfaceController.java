package com.dot.uicenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dot.uicenter.R;
import com.dot.uicenter.fragments.AccentEngine_Fragment;
import com.dot.uicenter.fragments.InterfaceMisc_Fragment;
import com.dot.uicenter.fragments.OverlayEngine_Fragment;
import com.dot.uicenter.utils.ExpandableLayout;
import com.dot.uicenter.utils.ObjectToolsAnimator;
import com.dot.uicenter.utils.OverlayUtils;

import static java.lang.Thread.sleep;

public class InterfaceController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_ct_options);
        ImageButton gb = findViewById(R.id.close_menu);
        gb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loadFragments();
        interfaceController();
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
        RelativeLayout oc_l = findViewById(R.id.overlay_c);
        RelativeLayout ac_l = findViewById(R.id.accent_c);
        RelativeLayout ms_l = findViewById(R.id.misc_s);
        final ExpandableLayout ms_ex = findViewById(R.id.ms_ex);
        final ExpandableLayout oc_ex = findViewById(R.id.oc_ex);
        final ExpandableLayout ac_ex = findViewById(R.id.ac_ex);
        final ImageView ms_v = findViewById(R.id.expand_ms);
        loadFragment(R.id.oc, new OverlayEngine_Fragment());
        loadFragment(R.id.ac, new AccentEngine_Fragment());
        loadFragment(R.id.ms, new InterfaceMisc_Fragment());

        oc_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oc_ex.isCollapsed()) {
                    oc_ex.expand(true);
                } else {
                    oc_ex.collapse(true);
                }
            }
        });
        ac_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ac_ex.isCollapsed()) {
                    ac_ex.expand(true);
                } else {
                    ac_ex.collapse(true);
                }
            }
        });
        ms_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ms_ex.isCollapsed()) {
                    ms_ex.expand(true);
                    ObjectToolsAnimator.rotate(ms_v, 0, 180);
                } else {
                    ms_ex.collapse(true);
                    ObjectToolsAnimator.rotate(ms_v, 180, 0);
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
        final ImageButton oc = findViewById(R.id.apply_oc);
        final ImageButton ac = findViewById(R.id.apply_ac);
        oc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oc.getContentDescription() != null) {
                    switch (oc.getContentDescription().toString()) {
                        case "Light":
                            OverlayUtils.setOverlayTheme(InterfaceController.this, 1);
                            break;
                        case "Dark":
                            OverlayUtils.setOverlayTheme(InterfaceController.this, 2);
                            break;
                        case "Black":
                            OverlayUtils.setOverlayTheme(InterfaceController.this, 3);
                            break;
                    }
                    refreshUI();
                }
            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ac.getContentDescription() != null) {
                    switch (ac.getContentDescription().toString()) {
                        case "Pixel":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 1);
                            break;
                        case "Green":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 2);
                            break;
                        case "Yellow":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 3);
                            break;
                        case "Red":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 4);
                            break;
                        case "Purple":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 5);
                            break;
                        case "Grey":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 6);
                            break;
                        case "Sky":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 7);
                            break;
                        case "Teal":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 0);
                            break;
                        case "Violet":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 8);
                            break;
                        case "Pink":
                            OverlayUtils.setAccentTheme(InterfaceController.this, 9);
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
            }
        });
    }
}
