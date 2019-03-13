package com.example.photogram.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.photogram.R;
import com.example.photogram.model.Publicacion;

import java.util.ArrayList;

public class AdapterGridPublicaciones extends RecyclerView.Adapter<AdapterGridPublicaciones.ViewHolderGridAdapter> {

    ArrayList<Publicacion> lstPublicaciones;

    public AdapterGridPublicaciones(ArrayList<Publicacion> lstPublicaciones){
        this.lstPublicaciones = lstPublicaciones;
    }

    @NonNull
    @Override
    public ViewHolderGridAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_grid_fotos, null, false);
        return new ViewHolderGridAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGridAdapter viewHolderGridAdapter, int i) {
        viewHolderGridAdapter.asignarDatos(lstPublicaciones.get(i));
    }

    @Override
    public int getItemCount() {
        return lstPublicaciones.size();
    }

    public class ViewHolderGridAdapter extends RecyclerView.ViewHolder {
        ImageView iv_publicacion_grid;
        Context context;
        public ViewHolderGridAdapter(@NonNull View itemView) {
            super(itemView);
            iv_publicacion_grid = (ImageView) itemView.findViewById(R.id.imageView_Publicacion_Grid);
            context = itemView.getContext();
        }

        public void asignarDatos(Publicacion publicacion){
            Glide.with(context).load(publicacion.getUrlImage()).centerCrop().centerInside().into(iv_publicacion_grid);
        }

    }
}
