package com.example.photogram;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.photogram.adapter.PublicacionesAdapter;
import com.example.photogram.model.Publicacion;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private static int VISIBILIDAD = View.VISIBLE;

    //@BindView(R.id.recyclerViewInicio_Publicaciones)
    RecyclerView recyclerView_Publicaciones;
    PublicacionesAdapter adapter;
    FloatingActionButton fabAddPublicacion;

    ArrayList<Publicacion> listPublicaciones = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        fabAddPublicacion = (FloatingActionButton) view.findViewById(R.id.fab_addPublicacion);
        recyclerView_Publicaciones = (RecyclerView) view.findViewById(R.id.recyclerViewInicio_Publicaciones);
        recyclerView_Publicaciones.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //Inicializar el ArrarList, no puede faltar
        llenarLista();
        adapter = new PublicacionesAdapter(listPublicaciones);
        recyclerView_Publicaciones.setAdapter(adapter);

        fabAddPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Agregar Publicacion", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView_Publicaciones.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    fabAddPublicacion.show();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy < 0 || dy > 0 && fabAddPublicacion.isShown()){
                    fabAddPublicacion.hide();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;

    }

    private void llenarLista() {
        listPublicaciones.add(new Publicacion("Jhon Giraldo", "https://firebasestorage.googleapis.com/v0/b/aplicacion-de-chat.appspot.com/o/Images%2F5bb26d3251b25.jpeg?alt=media&token=f6e72024-cf3d-47aa-a226-e421f22752bb",
                5));
        listPublicaciones.add(new Publicacion("Juan Giraldo", "https://firebasestorage.googleapis.com/v0/b/aplicacion-de-chat.appspot.com/o/Images%2FCara-Delevingne-2017-HD-Mobile-Wallpaper-950x1520.jpg?alt=media&token=6a87aefd-78cc-41e8-9526-18d394743be5",
                2));
        listPublicaciones.add(new Publicacion("Johan Giraldo", "https://firebasestorage.googleapis.com/v0/b/aplicacion-de-chat.appspot.com/o/Images%2Fcamila1.jpg?alt=media&token=8f1a8f8e-0342-4b02-ae8d-7b251ebbc6a1",
                15));
        listPublicaciones.add(new Publicacion("Yadira Cuartas", "https://firebasestorage.googleapis.com/v0/b/aplicacion-de-chat.appspot.com/o/Images%2F48421699_226421624941862_6208610289928110080_n.jpg?alt=media&token=76dddbcc-17b5-4e0e-9bce-87f99ebfcc04", 2));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
