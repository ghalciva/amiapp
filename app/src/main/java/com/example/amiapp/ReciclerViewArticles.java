package com.example.amiapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReciclerViewArticles extends  RecyclerView.Adapter<ReciclerViewArticles.MyViewHolder>{

    private Context mContext;
    private List<Articles> mData;

    public ReciclerViewArticles(Context mContext, List<Articles> mData) {

        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater;
        inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.list_articles, parent, false);
        final MyViewHolder viewHolder;
        viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                Intent i = new Intent(mContext, LawDetail.class);

                i.putExtra("name", mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("detalle", mData.get(viewHolder.getAdapterPosition()).getDetalle());
                i.putExtra("resumen", mData.get(viewHolder.getAdapterPosition()).getResumen());
                i.putExtra("exp_articulo", mData.get(viewHolder.getAdapterPosition()).getExpArticulo());
                i.putExtra("idley", mData.get(viewHolder.getAdapterPosition()).getIdley());

                mContext.startActivity(i);
            }

        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_detalle.setText(mData.get(position).getDetalle());

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name;
        TextView tv_detalle;
        TextView tv_resumen;
        TextView tv_exp_articulo;
        LinearLayout view_container;

        public MyViewHolder (View itemView){
            super(itemView);

            view_container = itemView.findViewById(R.id.containerA);

            tv_name = itemView.findViewById(R.id.art_nombre);
            tv_detalle = itemView.findViewById(R.id.art_detalle);
            tv_resumen = itemView.findViewById(R.id.artDescripcion);

        }

    }



}
