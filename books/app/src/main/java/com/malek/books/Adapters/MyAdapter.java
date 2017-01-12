package com.malek.books.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.malek.books.LivreDetailler;
import com.malek.books.R;
import com.malek.books.entity.Livre;
import com.malek.books.entity.Userr;
import com.malek.books.request.MyRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by malek on 15/07/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private List<Livre> livres;
    private RequestQueue queue;
    private MyRequest request;


    public MyAdapter(Context context, List<Livre> livres,RequestQueue queue,MyRequest request) {
        this.context = context;
        this.livres = livres;
        this.queue=queue;
        this.request=request;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.exemple_row,parent,false);
        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
       final Livre item=livres.get(position);
        ( (MyViewHolder)holder).textView.setText(item.getNom());
        Picasso.with(context).load(MyRequest.DOMAINE+"/android/img/"+item.getImg()).into(((MyViewHolder)holder).img);

        ( (MyViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View view1=view;
                final Intent intent=new Intent(context, LivreDetailler.class);

                   request.getUser(item.getIdUser(), new MyRequest.GetUserCallBack() {
                       @Override
                       public void onSuccess(Userr user) {
                           intent.putExtra("user",user);
                           intent.putExtra("livre",item);
                           Log.d("app","livre==="+item.getNom());
                           Log.d("app","mrigel");

                           view1.getContext().startActivity(intent);

                       }

                       @Override
                       public void onErreur(String msg) {
                          Log.d("app","errrer");
                       }
                   });



            }
        });
    }

    @Override
    public int getItemCount() {
        return livres.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView=(TextView) itemView.findViewById(R.id.txt);
            img=(ImageView) itemView.findViewById(R.id.img);

        }
    }

}
