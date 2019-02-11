package com.example.renad.exchangeit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;


public class myPHotoAdapter extends RecyclerView.Adapter<myPHotoAdapter.ViewHolder> {

    private Context context ;
    private List<Product> myProducts ;

    public myPHotoAdapter(Context context, List<Product> myProducts) {
        this.context = context;
        this.myProducts = myProducts;
    }

    @NonNull
    @Override
    public myPHotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_item,parent,false);

        return new myPHotoAdapter.ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull myPHotoAdapter.ViewHolder holder, int position) {

        Product product = myProducts.get(position);
        Glide.with(context).load(product.getPath()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return myProducts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.products_user);
        }

    }

};







