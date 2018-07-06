package com.dot.uicenter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;

import com.dot.uicenter.R;
import com.dot.uicenter.adapters.RecommendedSettings_Adapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_layout);
        loadRecommendedSettings();
        fabController();
    }

    private void loadRecommendedSettings() {
        String[] title = {
                getString(R.string.rs_title0),
                getString(R.string.rs_title1),
                getString(R.string.rs_title2)
        };
        int[] img = {
                R.drawable.ic_theme_main,
                R.drawable.ic_color_accent,
                R.drawable.ic_nightmode
        };
        intent = new Intent(MainActivity.this, InterfaceController.class);

        List<RecommendedSettings_Adapter.Holder> rs_adpt = new ArrayList<>();
        RecommendedSettings_Adapter adapter = new RecommendedSettings_Adapter(rs_adpt, this);
        for (int i = 0; i < title.length; i++) {
            RecommendedSettings_Adapter.Holder adpt = new RecommendedSettings_Adapter.Holder(title[i], img[i], i, intent);
            rs_adpt.add(adpt);
        }
        RecyclerView recyclerView = findViewById(R.id.rmd_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void fabController() {
        CardView fc = findViewById(R.id.fc_layout);
        fc.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InterfaceController.class)));
        ImageButton close = findViewById(R.id.go_back);
        close.setOnClickListener(v -> finish());
    }


}
