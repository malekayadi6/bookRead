package com.malek.books.fragmentDetailler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.malek.books.R;
import com.malek.books.entity.Livre;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLivreDetailler extends Fragment {
   private TextView auteur,langue,disc,pages;
    private Livre livre;

    public FragmentLivreDetailler() {
        // Required empty public constructor
    }

    public FragmentLivreDetailler(Livre livre) {
        this.livre = livre;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_livre_detailler, container, false);
          auteur= (TextView) view.findViewById(R.id.auteur_txt);
          langue= (TextView) view.findViewById(R.id.langue_txt);
          pages=(TextView)view.findViewById(R.id.pages_txt);
           pages.setText(livre.getNbrPage());
          auteur.setText(livre.getAuteur());
          langue.setText(livre.getLangue());

        return view;
    }

}
