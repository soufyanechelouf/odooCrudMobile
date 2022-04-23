package com.example.odooapp.Controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.odooapp.Model.Fournisseur;
import com.example.odooapp.R;
import com.example.odooapp.UI.EditData;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<com.example.odooapp.Controller.Adapter.MyViewHolder> {

    private Context context;
    private List<Fournisseur> fournisseurList;


    public Adapter(Context context, List<Fournisseur> fournisseurList) {
        this.context = context;
        this.fournisseurList = fournisseurList;
    }

    @NonNull
    @Override
    public com.example.odooapp.Controller.Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_content,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.odooapp.Controller.Adapter.MyViewHolder holder, final int position) {
        final Fournisseur fournisseur = fournisseurList.get(position);
        holder.name.setText(fournisseur.getName());
        holder.phone.setText(fournisseur.getPhone());
        holder.mobile.setText(fournisseur.getMobile());
        holder.email.setText(fournisseur.getEmail());
        holder.website.setText(fournisseur.getWebsite());

        // edit

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditData.class);

                intent.putExtra("id",fournisseur.getId());
                intent.putExtra("name",fournisseur.getName());
                intent.putExtra("phone",fournisseur.getPhone());
                intent.putExtra("email",fournisseur.getEmail());
                intent.putExtra("mobile",fournisseur.getMobile());
                intent.putExtra("website",fournisseur.getWebsite());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return fournisseurList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, phone,mobile,email,website;
        public ImageView edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTextView2);
            phone = itemView.findViewById(R.id.phoneTextView);
            mobile=itemView.findViewById(R.id.mobileTextView);
            website=itemView.findViewById(R.id.refTextView);
            email=itemView.findViewById(R.id.emailTextView);

            edit = itemView.findViewById(R.id.imageViewEdit);
        }
    }





}
