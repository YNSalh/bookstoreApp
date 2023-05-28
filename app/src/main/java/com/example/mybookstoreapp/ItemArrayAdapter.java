package com.example.mybookstoreapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemArrayAdapter extends RecyclerView.Adapter<ItemArrayAdapter.ViewHolder> {
    public ArrayList<MyProduct>itemArrayList;
    public ItemArrayAdapter(ArrayList<MyProduct>itemArrayList){
        this.itemArrayList=itemArrayList;

    }
    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder vh=new ViewHolder(view);
        return vh;

    }
    @Override
    public void  onBindViewHolder(@NonNull ViewHolder holder,int position){
        holder.txtpid.setText(itemArrayList.get(position).pid);
        holder.txtpname.setText(itemArrayList.get(position).pname);
        holder.txtqty.setText(itemArrayList.get(position).pquantity);
        holder.txtprice.setText(itemArrayList.get(position).price);
    }
    @Override
    public  int getItemCount(){
        if (itemArrayList==null)
            return  0;
        else
            return  itemArrayList.size();
    }
    class  ViewHolder extends  RecyclerView.ViewHolder{
        public TextView txtpid,txtpname,txtqty,txtprice;
        public ImageButton image;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtpid=itemView.findViewById(R.id.textView30);
            txtpname=itemView.findViewById(R.id.textView31);
            txtqty=itemView.findViewById(R.id.textView32);
            txtprice=itemView.findViewById(R.id.textView33);
            image=itemView.findViewById(R.id.imageButton2);


            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle b= new Bundle();
                    b.putString("PrpductID",txtpid.getText().toString());
                    b.putString("productname",txtpname.getText().toString());
                    b.putString("productprice",txtprice.getText().toString());
                    b.putString("productavailable",txtqty.getText().toString());

                    Intent i = new Intent(v.getContext(),OrderActivity.class);
                    i.putExtras(b);
                    v.getContext().startActivity(i);

                }
            });

        }
    }


}
