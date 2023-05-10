package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterPersonas extends RecyclerView.Adapter<AdapterPersonas.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Persona> model;
    ArrayList<Persona> Original;
    SearchView texto_buscar;

    private View.OnClickListener listener;

    public AdapterPersonas(Context context, ArrayList<Persona> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        Original = new ArrayList<>();
        Original.addAll(model);
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
        String estado = model.get(position).getEstado();
        holder.nombres.setText(nombres);
        holder.nit.setText(nit);
        holder.estado.setText(estado);
    }

//METODO PARA BUSCAR BORRAR
    public void filtrado(final String txtbuscar){
        int longitud = txtbuscar.length();
        if (longitud == 0){
           /* model.clear();
            model.addAll(Original);*/
            model = Original;
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Persona> collection = model.stream().
                        filter(i -> i.getNombre().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                model.clear();
                model.addAll(collection);
            }else {
                for (Persona c: Original) {
                    if (c.getNombre().toLowerCase().contains(txtbuscar.toLowerCase())){
                        model.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
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
        TextView nombres, nit,estado;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.nombres);
            nit = itemView.findViewById(R.id.nit);
            estado = itemView.findViewById(R.id.statustextview);

        }

    }

}
