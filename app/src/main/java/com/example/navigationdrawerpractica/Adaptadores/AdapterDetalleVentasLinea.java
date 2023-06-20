package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.DetalleVentaLinea;
import com.example.navigationdrawerpractica.Entidades.ListaPrecio;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterDetalleVentasLinea extends RecyclerView.Adapter<AdapterDetalleVentasLinea.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<DetalleVentaLinea> model;
    ArrayList<DetalleVentaLinea> Original;
    SearchView texto_buscar;

    private View.OnClickListener listener;

    public AdapterDetalleVentasLinea(Context context, ArrayList<DetalleVentaLinea> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        Original = new ArrayList<>();
        Original.addAll(model);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.detalle_ventas_ppto, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);

    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String grupo = model.get(position).getGrupo();
        String subgrupo = model.get(position).getSubgrupo();
        Integer kilos = model.get(position).getKilos();
        Integer pesos = model.get(position).getPesos();



        holder.grupo.setText(grupo);
        holder.subgrupo.setText(subgrupo);
        holder.kilos.setText(" "+kilos);
        holder.pesos.setText( " "+ pesos );



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
        TextView grupo, subgrupo,pkilos,ppesos,kilos,pesos;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            grupo = itemView.findViewById(R.id.txt_grupo);
            subgrupo = itemView.findViewById(R.id.txt_subgrupo);
            kilos   = itemView.findViewById(R.id.txt_ventas_kilos);
            pesos   = itemView.findViewById(R.id.txt_ventas);
        }

    }

}
