package com.example.proyectoempresarial;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<listaClientes> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<listaClientes> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    @Override
    public int getItemCount() {return mData.size();}


    @Override
    public ListAdapter.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = mInflater.from(parent.getContext()).inflate(R.layout.list_clientes, parent, false);
        return new ListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.transicion_clientes));
    }

    public void setItems(List<listaClientes> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView nombre;
        CardView cv;

        ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImage);
            nombre = itemView.findViewById(R.id.nombre);
            cv = itemView.findViewById(R.id.cv);
        }
        void bindData(final listaClientes item) {
            iconImageView.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombre());
        }
    }
}
