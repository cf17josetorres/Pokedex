package com.example.pokedex;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class DetallePokemon extends Fragment {

    public DetallePokemon() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.detalle_pokemon, container, false);
        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //RVAdapter adapter = new RVAdapter(getContext(), IncidenciaDBHelper.listado(sqLiteDatabase));
        //recyclerView.setAdapter(adapter);
        return view;
    }
}