package com.dot.uicenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dot.uicenter.R;
import com.dot.uicenter.fragments.AccentEngine_Fragment;
import com.dot.uicenter.fragments.InterfaceMisc_Fragment;
import com.dot.uicenter.fragments.OverlayEngine_Fragment;
import com.dot.uicenter.utils.ExpandableLayout;
import com.dot.uicenter.utils.ObjectToolsAnimator;
import com.dot.uicenter.utils.SettingsUtils;

import static java.lang.Thread.sleep;

public class InterfaceController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interface_ct_options);
        ImageButton gb = findViewById(R.id.close_menu);
        gb.setOnClickListener(v -> finish());
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
        RelativeLayout qs_panel = findViewById(R.id.qs_panel);
        final ExpandableLayout ms_ex = findViewById(R.id.ms_ex);
        final ExpandableLayout oc_ex = findViewById(R.id.oc_ex);
        final ExpandableLayout ac_ex = findViewById(R.id.ac_ex);
        final ImageView ms_v = findViewById(R.id.expand_ms);
        loadFragment(R.id.oc, new OverlayEngine_Fragment());
        loadFragment(R.id.ac, new AccentEngine_Fragment());
        loadFragment(R.id.ms, new InterfaceMisc_Fragment());

        oc_l.setOnClickListener(v -> {
            if (oc_ex.isCollapsed()) {
                oc_ex.expand(true);
            } else {
                oc_ex.collapse(true);
            }
        });
        ac_l.setOnClickListener(v -> {
            if (ac_ex.isCollapsed()) {
                ac_ex.expand(true);
            } else {
                ac_ex.collapse(true);
            }
        });
        ms_l.setOnClickListener(v -> {
            if (ms_ex.isCollapsed()) {
                ms_ex.expand(true);
                ObjectToolsAnimator.rotate(ms_v, 0, 180);
            } else {
                ms_ex.collapse(true);
                ObjectToolsAnimator.rotate(ms_v, 180, 0);
            }
        });
        qs_panel.setOnClickListener((v) -> {
            startActivity(new Intent(InterfaceController.this, QSPanelCustomization.class));
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
        oc.setOnClickListener(v -> {
            if (oc.getContentDescription() != null) {
                switch (oc.getContentDescription().toString()) {
                    case "Light":
                        SettingsUtils.setOverlayTheme(InterfaceController.this, 1);
                        break;
                    case "Dark":
                        SettingsUtils.setOverlayTheme(InterfaceController.this, 2);
                        break;
                    case "Black":
                        SettingsUtils.setOverlayTheme(InterfaceController.this, 3);
                        break;
                }
                refreshUI();
            }
        });
        ac.setOnClickListener(v -> {
            if (ac.getContentDescription() != null) {
                switch (ac.getContentDescription().toString()) {
                    case "Pixel":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 1);
                        break;
                    case "Green":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 2);
                        break;
                    case "Yellow":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 3);
                        break;
                    case "Red":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 4);
                        break;
                    case "Purple":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 5);
                        break;
                    case "Grey":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 6);
                        break;
                    case "Sky":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 7);
                        break;
                    case "Teal":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 0);
                        break;
                    case "Violet":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 8);
                        break;
                    case "Pink":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 9);
                        break;
                    case "Oxygen":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 10);
                        break;
                    case "Lemon":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 11);
                        break;
                    case "Orange":
                        SettingsUtils.setAccentTheme(InterfaceController.this, 12);
                        break;
                    case "Custom":
                        View view = View.inflate(getApplicationContext(), R.layout.ce_accent_layout, null);
                        EditText custom_accent = view.findViewById(R.id.custom_package);
                        if (!custom_accent.getText().toString().isEmpty()) {
                            SettingsUtils.enableAccentTheme(custom_accent.getText().toString());
                        }
                        break;
                }
                refreshUI();
            }
        });
    }
}
