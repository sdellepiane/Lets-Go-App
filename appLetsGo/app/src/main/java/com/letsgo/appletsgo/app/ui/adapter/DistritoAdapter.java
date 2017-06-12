package com.letsgo.appletsgo.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.letsgo.appletsgo.R;
import com.letsgo.appletsgo.domain.model.entity.Distrito;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by louislopez on 11/06/17.
 */

public class DistritoAdapter extends RecyclerView.Adapter<DistritoAdapter.ViewHolder> {
    private String TAG = "DistritoAdapter" ;
    private Context context;
    private List<Distrito> distritoList;

    public DistritoAdapter(Context context, List<Distrito> distritoList) {
        this.context = context;
        this.distritoList = distritoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_distrito,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Distrito distrito = distritoList.get(position);
        holder.tviDistrito.setText(distrito.getDescription());
    }

    @Override
    public int getItemCount() {
        return distritoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tviDistrito)
        TextView tviDistrito;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
