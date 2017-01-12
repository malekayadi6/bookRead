package com.malek.books.FragmentsPrincipale;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.malek.books.Adapters.MyAdapter;
import com.malek.books.MainActivity;
import com.malek.books.MySingleton;
import com.malek.books.R;
import com.malek.books.entity.Livre;
import com.malek.books.request.MyRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fr1 extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private MyRequest request;
    private RequestQueue queue;


    public Fr1() {
        // Required empty public constructor
    }

    public Fr1(MyRequest request, RequestQueue queue) {
        this.request = request;
        this.queue = queue;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fr1, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcl_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setHasFixedSize(true);

          request.getLivres(new MyRequest.LivreCallBack() {
              @Override
              public void onSuccess(List<Livre> livres) {
                  myAdapter=new MyAdapter(getActivity().getApplication().getApplicationContext(),livres,queue,request);
                  recyclerView.setAdapter(myAdapter);
              }

              @Override
              public void onErruer(String msg) {
                  Toast.makeText(getActivity().getApplicationContext(),msg,Toast.LENGTH_LONG).show();

              }
          });


        return view;
    }

}
