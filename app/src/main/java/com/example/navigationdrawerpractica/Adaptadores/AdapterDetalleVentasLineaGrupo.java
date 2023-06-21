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
import com.example.navigationdrawerpractica.Entidades.DetalleVentaLineaGrupo;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterDetalleVentasLineaGrupo extends RecyclerView.Adapter<AdapterDetalleVentasLineaGrupo.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<DetalleVentaLineaGrupo> model;
    ArrayList<DetalleVentaLineaGrupo> Original;
    SearchView texto_buscar;

    private View.OnClickListener listener;

    public AdapterDetalleVentasLineaGrupo(Context context, ArrayList<DetalleVentaLineaGrupo> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        Original = new ArrayList<>();
       // Original.addAll(model);
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
        holder.grupo.setText(grupo);
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
        TextView grupo;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            grupo = itemView.findViewById(R.id.txt_grupo);

        }

    }

}
