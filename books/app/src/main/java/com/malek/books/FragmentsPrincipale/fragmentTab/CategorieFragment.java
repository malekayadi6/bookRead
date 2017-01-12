package com.malek.books.FragmentsPrincipale.fragmentTab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.malek.books.Adapters.AdapterCategorie;
import com.malek.books.MySingleton;
import com.malek.books.R;
import com.malek.books.entity.Categorie;
import com.malek.books.request.MyRequest;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategorieFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private RequestQueue queue;
    private MyRequest request;
    private AdapterCategorie adapterCategorie;
    public CategorieFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_categorie2, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.rcl_categorie);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);
        queue= MySingleton.getInstance(getActivity().getApplication().getApplicationContext()).getRequestQueue();
        request=new MyRequest(getContext(),queue);

                request.getCategire(new MyRequest.CategorieCallBack() {
                    @Override
                    public void onSuccess(List<Categorie> categories) {
                        adapterCategorie=new AdapterCategorie(getContext(),categories);
                        recyclerView.setAdapter(adapterCategorie);
                    }

                    @Override
                    public void onErreur(String msg) {

                    }
                });


        return view;
    }

}
