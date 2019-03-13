package com.example.photogram.interfaces;

import com.example.photogram.BuscarFragment;
import com.example.photogram.ChatsFragment;
import com.example.photogram.HomeFragment;
import com.example.photogram.MiPerfilFragment;

public interface FragmentsInterface extends HomeFragment.OnFragmentInteractionListener, ChatsFragment.OnFragmentInteractionListener,
        BuscarFragment.OnFragmentInteractionListener, MiPerfilFragment.OnFragmentInteractionListener {
}
