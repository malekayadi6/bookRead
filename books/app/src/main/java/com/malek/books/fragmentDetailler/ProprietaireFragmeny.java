package com.malek.books.fragmentDetailler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.malek.books.Adapters.AdapterBooksUsers;
import com.malek.books.Adapters.MyAdapter;
import com.malek.books.R;
import com.malek.books.entity.Livre;
import com.malek.books.entity.Userr;
import com.malek.books.request.MyRequest;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProprietaireFragmeny extends Fragment {
    private Userr userr;
    private TextView nomPrenom,email,tel;
    private RecyclerView recyclerView;
    private AdapterBooksUsers myAdapter;


    public ProprietaireFragmeny() {
        // Required empty public constructor
    }

    public ProprietaireFragmeny(Userr userr) {
        this.userr = userr;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_proprietaire_fragmeny, container, false);
       /* nomPrenom= (TextView) view.findViewById(R.id.nom_prenom_txt);
        nomPrenom.setText(userr.getNom()+" "+userr.getPrenom());
        email.setText(userr.getEmail());*/
        Log.d("app","user"+userr.getEmail());
        recyclerView = (RecyclerView) view.findViewById(R.id.rcl_books_user);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.scrollToPosition(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        myAdapter=new AdapterBooksUsers(getActivity().getApplication().getApplicationContext(),userr.getLivres());
        recyclerView.setAdapter(myAdapter);
        Log.d("app","test"+userr.getLivres().size());

        return view;
    }

}
