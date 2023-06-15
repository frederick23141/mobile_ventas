package com.example.navigationdrawerpractica.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Entidades.ListaPrecio;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterListaPrecios extends RecyclerView.Adapter<AdapterListaPrecios.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<ListaPrecio> model;
    ArrayList<ListaPrecio> Original;
    SearchView texto_buscar;

    private View.OnClickListener listener;

    public AdapterListaPrecios(Context context, ArrayList<ListaPrecio> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
        Original = new ArrayList<>();
        Original.addAll(model);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_precios, parent, false);
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
        String descripcion = model.get(position).getDescripcion();
        String codigo = model.get(position).getCodigo();
        String distribuidor = model.get(position).getDistribuidor();
        String mayorista = model.get(position).getMayorista();
        String detallista = model.get(position).getDetallista();


        holder.grupo.setText(grupo + "   -   " + subgrupo );
       // holder.subgrupo.setText(subgrupo);
        holder.codigo.setText(codigo);
        holder.descripcion.setText(descripcion);
        holder.distribuidor.setText(distribuidor);
        holder.mayorista.setText(mayorista);
        holder.detallista.setText(detallista);


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
                List<ListaPrecio> collection = model.stream().
                        filter(i -> i.getDescripcion().toLowerCase().contains(txtbuscar.toLowerCase()))
                        .collect(Collectors.toList());
                model.clear();
                model.addAll(collection);
            }else {
                for (ListaPrecio c: Original) {
                    if (c.getDescripcion().toLowerCase().contains(txtbuscar.toLowerCase())){
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
        TextView grupo, subgrupo,codigo,descripcion,distribuidor,mayorista,detallista;



        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            grupo = itemView.findViewById(R.id.grupo);
           // subgrupo = itemView.findViewById(R.id.subgrupo);
            codigo = itemView.findViewById(R.id.codigo);
            descripcion = itemView.findViewById(R.id.descripcion);
            distribuidor = itemView.findViewById(R.id.txt_distribuidor);
            mayorista = itemView.findViewById(R.id.txt_mayorista);
            detallista = itemView.findViewById(R.id.txt_detallista);
        }

    }

}
