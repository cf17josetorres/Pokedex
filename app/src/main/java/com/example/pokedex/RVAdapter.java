package com.example.pokedex;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private ArrayList<Pokemonn> array;
    private Context context;

    public RVAdapter(Context con, ArrayList<Pokemonn> arrN) {
        array = arrN;
        context = con;
    }

    public RVAdapter(Context context) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView etiquetaname;
        TextView etiquetaurl;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaname = itemView.findViewById(R.id.itemlistadoname);
            etiquetaurl = itemView.findViewById(R.id.itemlistadourl);
            layout = itemView.findViewById(R.id.layout);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.etiquetaname.setText(array.get(position).getName());
        holder.etiquetaurl.setText(array.get(position).getUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                DetallePokemon activi =new DetallePokemon();
                Bundle bundle = new Bundle();
                bundle.putString("ITEM_NAME", array.get(position).getName());
                bundle.putString("ITEM_URL",array.get(position).getUrl());

                activi.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rvmuestra, activi).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
}