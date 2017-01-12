package com.malek.books.FragmentsPrincipale.fragmentTab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.malek.books.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuteurFragment extends Fragment {


    public AuteurFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_auteur2, container, false);
    }

}
