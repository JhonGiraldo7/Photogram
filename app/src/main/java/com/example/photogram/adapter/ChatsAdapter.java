package com.example.photogram.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.photogram.R;
import com.example.photogram.model.Usuario;

import java.util.ArrayList;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ViewHolderChats> {
    ArrayList<Usuario> lstContactos;

    public ChatsAdapter(ArrayList<Usuario> lstContactos){
        this.lstContactos = lstContactos;
    }

    @NonNull
    @Override
    public ViewHolderChats onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_chats, null, false);
        return new ViewHolderChats(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderChats viewHolderChats, int i) {

        viewHolderChats.asignarDatos(lstContactos.get(i));
    }

    @Override
    public int getItemCount() {
        return lstContactos.size();
    }

    public class ViewHolderChats extends RecyclerView.ViewHolder {
        ImageView iv_Foto_Perfil_Chats;
        TextView tv_nombre_Chat;
        TextView tv_mensaje_Chat;
        Context context;
        public ViewHolderChats(@NonNull View itemView) {
            super(itemView);

            iv_Foto_Perfil_Chats = (ImageView) itemView.findViewById(R.id.imageView_perfil_chats);
            tv_nombre_Chat = (TextView) itemView.findViewById(R.id.textView_nombre_chats);
            tv_mensaje_Chat = (TextView) itemView.findViewById(R.id.textView_mensaje_chats);
            context = itemView.getContext();
        }

        public void asignarDatos(Usuario contacto){
            if(contacto != null) {
                Glide.with(context).load(contacto.getRutaFotoPerfil()).circleCrop().into(iv_Foto_Perfil_Chats);
                tv_nombre_Chat.setText(contacto.getNombreUsuario());
                tv_mensaje_Chat.setText("Inicia una conversación");
                tv_mensaje_Chat.setTextColor(Color.rgb(0, 173, 75));
            }

        }

    }

}
