package com.malek.books.FragmentsPrincipale;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.malek.books.InscriptionActivity;
import com.malek.books.MySingleton;
import com.malek.books.R;
import com.malek.books.entity.Livre;
import com.malek.books.request.InscrireConx;
import com.malek.books.request.MyRequest;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    private Button b1;
    private MyRequest myRequest;
    private RequestQueue queue;
    private int PICK_IMAGE_REQUEST = 1;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_blank, container, false);
        b1= (Button) view.findViewById(R.id.b1);
        queue= MySingleton.getInstance(getContext()).getRequestQueue();
        myRequest=new MyRequest(getContext(),queue);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),InscriptionActivity.class);
                startActivity(intent);
            }
        });

    return view;}

}
