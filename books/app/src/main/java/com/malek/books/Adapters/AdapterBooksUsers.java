package com.malek.books.Adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.malek.books.R;
import com.malek.books.entity.Categorie;
import com.malek.books.entity.Livre;
import com.malek.books.request.MyRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by malek on 21/07/2016.
 */
public class AdapterBooksUsers extends RecyclerView.Adapter {
    private Context context;
    private List<Livre> livres;

    public AdapterBooksUsers(Context context, List<Livre> livres) {
        this.context = context;
        this.livres = livres;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exemple_row,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Livre item=livres.get(position);
        ( (MyViewHolder)holder).textView.setText(item.getNom());
        Picasso.with(context).load(MyRequest.DOMAINE+"/android/img/"+item.getImg()).into(((MyViewHolder)holder).img);
    }




    @Override
    public int getItemCount() {
        return livres.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt);
            img = (ImageView) itemView.findViewById(R.id.img);
        }

    }

}
