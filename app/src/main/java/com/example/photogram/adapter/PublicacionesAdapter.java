package com.example.photogram.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.photogram.MainActivity;
import com.example.photogram.R;
import com.example.photogram.model.Publicacion;

import java.util.ArrayList;


public class PublicacionesAdapter extends RecyclerView.Adapter<PublicacionesAdapter.ViewHolderPublicaciones> {

    ArrayList<Publicacion> lstPublicaciones;

    public PublicacionesAdapter(ArrayList<Publicacion> lstPublicaciones){
        this.lstPublicaciones = lstPublicaciones;
    }

    @NonNull
    @Override
    public ViewHolderPublicaciones onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardviewpublicaciones, null, false);
        return new ViewHolderPublicaciones(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPublicaciones viewHolderPublicaciones, int i) {
        viewHolderPublicaciones.agisnarCamposPublicacion(lstPublicaciones.get(i));
    }

    @Override
    public int getItemCount() {
        return lstPublicaciones.size();
    }

    public class ViewHolderPublicaciones extends RecyclerView.ViewHolder {

        ImageView iv_Publicacion;
        CheckBox checkBox_likes_publicacion;
        TextView tv_likes_publicacion;
        TextView tv_nombre_publicacion;
        //Este view es para poder obtener el contexto al cargar la imagen con Glide
        View view;

        private boolean estado=false;

        public ViewHolderPublicaciones(@NonNull View itemView) {
            super(itemView);

            iv_Publicacion = (ImageView) itemView.findViewById(R.id.imageView_Publicacion);
            checkBox_likes_publicacion = (CheckBox) itemView.findViewById(R.id.checkbox_likes_publicacion);
            tv_likes_publicacion = (TextView) itemView.findViewById(R.id.textview_likes_publicacion);
            tv_nombre_publicacion = (TextView) itemView.findViewById(R.id.textview_nombre_publicacion);

            view = itemView;
            checkBox_likes_publicacion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        checkBox_likes_publicacion.setButtonDrawable(R.drawable.heart_full);
                        estado=true;
                    }else{
                        checkBox_likes_publicacion.setButtonDrawable(R.drawable.heart);
                        estado=false;
                    }
                }
            });

        }

        public void agisnarCamposPublicacion(Publicacion publicacion){
            if(estado){
                publicacion.setContLikes(publicacion.getContLikes()+1);
            }else{
                if(publicacion.getContLikes()>0) {
                    publicacion.setContLikes(publicacion.getContLikes() - 1);
                }
            }
            Glide.with(view.getContext()).load(publicacion.getUrlImage()).centerInside().into(iv_Publicacion);
            tv_likes_publicacion.setText(String.valueOf(publicacion.getContLikes()));
            tv_nombre_publicacion.setText(publicacion.getNombrePersona());
        }

    }
}
