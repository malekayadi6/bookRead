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
import com.malek.books.request.MyRequest;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by malek on 20/07/2016.
 */
public class AdapterCategorie extends RecyclerView.Adapter {
    private Context context;
    private List<Categorie> categories;

    public AdapterCategorie(Context context, List<Categorie> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.exemple_row,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Categorie item=categories.get(position);
        ( (MyViewHolder)holder).textView.setText(item.getNom());
        Picasso.with(context).load(MyRequest.DOMAINE+"/android/img/categorie/"+item.getImage()).into(((MyViewHolder)holder).img);
    }




    @Override
    public int getItemCount() {
        return categories.size();
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
