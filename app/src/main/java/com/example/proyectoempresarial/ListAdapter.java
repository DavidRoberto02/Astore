package com.example.proyectoempresarial;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = mInflater.inflate(R.layout.list_clientes, null);
        return new ListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<listaClientes> items) {mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView nombre;

        ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImage);
            nombre = itemView.findViewById(R.id.nombre);
        }
        void bindData(final listaClientes item) {
            iconImageView.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            nombre.setText(item.getNombre());
        }
    }
}
