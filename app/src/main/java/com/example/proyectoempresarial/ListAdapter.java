package com.example.proyectoempresarial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements Filterable {
    private List<listaClientes> mData = new ArrayList<>();

    private Context context;
    private IAXiliarPersona iAXiliarPersona;
    private ArrayList<listaClientes> clientesArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(listaClientes item);
    }

    public ListAdapter(IAXiliarPersona iAxiliarPersona, ArrayList<listaClientes> mData, OnItemClickListener listener) {
        this.iAXiliarPersona = iAxiliarPersona;
        this.mData = mData;
        this.clientesArrayList = mData;
        this.listener = listener;

    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_clientes, parent, false);
        return new ListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int i) {
        listaClientes lista = mData.get(i);
        //holder.bindData(mData.get(i));
        holder.itemView.setOnClickListener(view -> {
            listener.onItemClick(mData.get(i));
        });
        holder.nombre.setText(lista.getNombre());
        holder.direccionCliente.setText(lista.getDireccionCliente());
        holder.numero.setText(String.valueOf(lista.getNumero()));
        holder.btnEditar.setOnClickListener(new eventoEditar(lista));
        holder.btnEliminar.setOnClickListener(new eventoEliminar(lista));
        //holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.transicion_clientes));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void agregarPersona(listaClientes lista) {
        mData.add(lista);
        this.notifyDataSetChanged();
    }

    public void eliminarPersona(listaClientes lista) {
        mData.remove(lista);
        this.notifyDataSetChanged();
    }
    public void setItems(List<listaClientes> items) {
        mData = items;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String palabra = constraint.toString();

                if (palabra.isEmpty()) {
                    mData = clientesArrayList;
                } else {
                    ArrayList<listaClientes> filtrarLista = new ArrayList<>();
                    for (listaClientes lista : clientesArrayList) {
                        if (lista.getNombre().toLowerCase().contains(constraint)) {
                            filtrarLista.add(lista);
                        }
                    }
                    mData = filtrarLista;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mData;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mData = (ArrayList<listaClientes>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class eventoEditar implements View.OnClickListener {

        private listaClientes lista;

        public eventoEditar(listaClientes lista) {
            this.lista = lista;
        }

        @Override
        public void onClick(View v) {
            iAXiliarPersona.editar(lista);
        }
    }

    class eventoEliminar implements View.OnClickListener {
        private listaClientes lista;

        public eventoEliminar(listaClientes lista) {
            this.lista = lista;
        }

        @Override
        public void onClick(View v) {
            iAXiliarPersona.eliminar(lista);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconImageView;
        TextView nombre;
        TextView numero;
        TextView direccionCliente;
        CardView cv;

        //CardView cv;
        private Button btnEditar, btnEliminar;


        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.iconImage);
            nombre = itemView.findViewById(R.id.nombre);
            numero = itemView.findViewById(R.id.numero);
            direccionCliente = itemView.findViewById(R.id.direccionCliente);
            //cv = itemView.findViewById(R.id.cv);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
