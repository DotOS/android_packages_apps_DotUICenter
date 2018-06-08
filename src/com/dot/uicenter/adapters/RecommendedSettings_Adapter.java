package com.dot.uicenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dot.uicenter.R;
import com.dot.uicenter.fragments.OverlayEngine_Fragment;

import java.util.List;

public class RecommendedSettings_Adapter extends RecyclerView.Adapter<RecommendedSettings_Adapter.MyViewHolderInst> {
    Context context;
    private List<Holder> rmdholder;


    class MyViewHolderInst extends RecyclerView.ViewHolder {
        ImageView rmd_icon;
        TextView rmd_title;
        LinearLayout rmd_layout;

        MyViewHolderInst(View view) {
            super(view);
            rmd_icon = view.findViewById(R.id.rmd_icon);
            rmd_title = view.findViewById(R.id.rmd_title);
            rmd_layout = view.findViewById(R.id.rmd_layout);
        }
    }

    public RecommendedSettings_Adapter(List<RecommendedSettings_Adapter.Holder> holders, Context context) {
        this.rmdholder = holders;
        this.context = context;
    }

    @NonNull
    @Override
    public RecommendedSettings_Adapter.MyViewHolderInst onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recommended_settings_layout_adapter, parent, false);
        return new RecommendedSettings_Adapter.MyViewHolderInst(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecommendedSettings_Adapter.MyViewHolderInst holder, int position) {
        final Holder bsholder = this.rmdholder.get(position);
        holder.rmd_icon.setImageResource(bsholder.img);
        holder.rmd_title.setText(bsholder.title);
        holder.rmd_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(bsholder.intent);
                switch (bsholder.ocl) {
                    case 0:
                        Toast.makeText(context, R.string.rs_dark, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, R.string.rs_ac, Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(context, R.string.rs_black, Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return rmdholder.size();
    }

    public static class Holder {
        String title;
        int img, ocl;
        Intent intent;

        public Holder(String title, int img, int ocl, Intent intent) {
            this.title = title;
            this.img = img;
            this.ocl = ocl;
            this.intent = intent;
        }
    }
}