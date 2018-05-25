package com.dot.uicenter.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dot.uicenter.R;

import java.util.List;

public class RecommendedSettings_Adapter extends RecyclerView.Adapter<RecommendedSettings_Adapter.MyViewHolderInst> {
    private List<Holder> rmdholder;


    class MyViewHolderInst extends RecyclerView.ViewHolder {
        ImageView rmd_icon;
        TextView rmd_title, rmd_summary;
        RelativeLayout rmd_layout;

        MyViewHolderInst(View view) {
            super(view);
            rmd_icon = view.findViewById(R.id.rmd_icon);
            rmd_title = view.findViewById(R.id.rmd_title);
            rmd_summary = view.findViewById(R.id.rmd_summary);
            rmd_layout = view.findViewById(R.id.rmd_layout);
        }
    }

    public RecommendedSettings_Adapter(List<RecommendedSettings_Adapter.Holder> holders) {
        this.rmdholder = holders;
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
        Holder bsholder = this.rmdholder.get(position);
        holder.rmd_icon.setImageResource(bsholder.img);
        holder.rmd_title.setText(bsholder.title);
        holder.rmd_summary.setText(bsholder.summary);
        holder.rmd_layout.setOnClickListener(bsholder.ocl);
    }

    @Override
    public int getItemCount() {
        return rmdholder.size();
    }

    public static class Holder {
        String title, summary;
        int img;
        View.OnClickListener ocl;

        public Holder(String title, String summary, int img, View.OnClickListener ocl) {
            this.title = title;
            this.summary = summary;
            this.img = img;
            this.ocl = ocl;
        }
    }
}