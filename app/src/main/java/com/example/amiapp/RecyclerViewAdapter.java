package com.example.amiapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Law> mData;

    public RecyclerViewAdapter(Context mContext, List<Law> mData) {

        this.mContext = mContext;
        this.mData = mData;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater;
        inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.list_item, parent, false);
        final MyViewHolder viewHolder;
        viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(mContext, LawOne.class);
                i.putExtra("_id", mData.get(viewHolder.getAdapterPosition()).getId());
                i.putExtra("nombre", mData.get(viewHolder.getAdapterPosition()).getNombre());
                i.putExtra("descripcion", mData.get(viewHolder.getAdapterPosition()).getDescripcion());
                i.putExtra("cod_decreto", mData.get(viewHolder.getAdapterPosition()).getCodDecreto());
                i.putExtra("fecha_publicacion", mData.get(viewHolder.getAdapterPosition()).getFechaPublicacion());
                i.putExtra("proponente", mData.get(viewHolder.getAdapterPosition()).getProponente());

                mContext.startActivity(i);
            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getNombre());
        holder.tv_description.setText(mData.get(position).getDescripcion());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_description;
        TextView tv_cod_decreto;
        TextView tv_fecha_publicacion;
        TextView tv_proponente;
        LinearLayout view_container;
        Button share;
        Button btnReadArticles;

        public MyViewHolder (View itemView){
            super(itemView);

            view_container = itemView.findViewById(R.id.container);

            tv_name = itemView.findViewById(R.id.nombre);
            tv_description = itemView.findViewById(R.id.descripcion);
            tv_cod_decreto = itemView.findViewById(R.id.cod_decreto);
            tv_fecha_publicacion = itemView.findViewById(R.id.fecha_publicacion);
            tv_proponente = itemView.findViewById(R.id.proponente);

            share = itemView.findViewById(R.id.btnCompartir);
            btnReadArticles = itemView.findViewById(R.id.btnArticulos);
        }

    }



}
