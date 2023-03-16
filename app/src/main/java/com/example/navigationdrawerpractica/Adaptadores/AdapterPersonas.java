package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class AdapterPersonas extends RecyclerView.Adapter<AdapterPersonas.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Persona> model;

    private View.OnClickListener listener;

    public AdapterPersonas(Context context, ArrayList<Persona> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_personas, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombres = model.get(position).getNombre();
        String nit = model.get(position).getNit();
        holder.nombres.setText(nombres);
        holder.nit.setText(nit);

    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombres, nit;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.nombres);
            nit = itemView.findViewById(R.id.nit);
        }

    }

}
