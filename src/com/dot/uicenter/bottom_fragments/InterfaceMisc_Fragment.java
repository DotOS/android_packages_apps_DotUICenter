package com.dot.uicenter.bottom_fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dot.uicenter.R;
import com.dot.uicenter.utils.OverlayUtils;

import static android.content.Context.MODE_PRIVATE;
import static com.dot.uicenter.utils.ShellUtils.killPackage;

public class InterfaceMisc_Fragment extends Fragment {

    public InterfaceMisc_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.ui_misc_holder, container, false);
        getActivity().getFragmentManager().beginTransaction().replace(R.id.auto_mode, new CE_AutoMode()).commitAllowingStateLoss();
        getActivity().getFragmentManager().beginTransaction().replace(R.id.tint_mode, new CE_TintMode()).commitAllowingStateLoss();
        getActivity().getFragmentManager().beginTransaction().replace(R.id.warn_mode, new CE_WarnMode()).commitAllowingStateLoss();
        return view;
    }

    public static class CE_AutoMode extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.auto_mode);
            SwitchPreference auto_mode = (SwitchPreference) findPreference("auto_overlay");
            auto_mode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    boolean checked = ((SwitchPreference) preference)
                            .isChecked();
                    if (checked) {
                        OverlayUtils.setOverlayTheme(0);
                        updateMode(1);
                    }
                    else {
                        updateMode(0);
                        OverlayUtils.setOverlayTheme(1);
                    }
                    return true;
                }
            });
            boolean checked = auto_mode.isChecked();
            if (checked) {
                updateMode(1);
            }
            else {
                updateMode(0);
            }

        }
        public void updateMode(int value) {
            SharedPreferences prefs = getContext().getSharedPreferences("auto_mode", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("auto_mode", value);
            editor.commit();
        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return false;
        }
    }

    public static class CE_TintMode extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.tint_mode);
            SwitchPreference tint_mode = (SwitchPreference) findPreference("tint_mode");
            tint_mode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    boolean checked = ((SwitchPreference) preference)
                            .isChecked();
                    if (checked) {
                        OverlayUtils.setTintMode(1);
                        killPackage("com.android.systemui");
                    }
                    else {
                        OverlayUtils.setTintMode(0);
                        killPackage("com.android.systemui");
                    }
                    return true;
                }
            });
        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return false;
        }
    }

    public static class CE_WarnMode extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.warn_mode);
            SwitchPreference tint_mode = (SwitchPreference) findPreference("warn_mode_pref");
            tint_mode.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    boolean checked = ((SwitchPreference) preference)
                            .isChecked();
                    if (checked) {
                        OverlayUtils.setWarnMode(0);
                    }
                    else {
                        OverlayUtils.setWarnMode(1);
                    }
                    return true;
                }
            });
        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return false;
        }
    }

}
