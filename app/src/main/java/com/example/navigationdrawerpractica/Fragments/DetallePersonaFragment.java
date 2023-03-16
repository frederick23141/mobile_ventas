package com.example.navigationdrawerpractica.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.R;

public class DetallePersonaFragment extends Fragment {
    TextView nombre;
    TextView nit;
    TextView direccion;
    TextView telefono;
    TextView email;
    TextView estado;
    TextView cupo;
    TextView descuento;
    TextView lista_precios;
    TextView lista_precios2;
    TextView usado;
    TextView condicion;
    TextView notas;

    ImageView imagen;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_persona_fragment,container,false);
        nombre = view.findViewById(R.id.client_name);
        nit = view.findViewById(R.id.nit_client);
        direccion = view.findViewById(R.id.direccion_detalle);
        telefono = view.findViewById(R.id.telefono_detalle);
        email = view.findViewById(R.id.email_detalle);
        estado = view.findViewById(R.id.estado_detalle);
        cupo = view.findViewById(R.id.cupo_detalle);
        descuento = view.findViewById(R.id.descuento_detalle);
        lista_precios = view.findViewById(R.id.lista_precios_detalle);
        lista_precios2 = view.findViewById(R.id.lista_precios2_detalle);
        usado = view.findViewById(R.id.usado_detalle);
        condicion = view.findViewById(R.id.condicion_detalle);
        notas   = view.findViewById(R.id.notas_detalle);
      //  imagen = view.findViewById(R.id.imagen_detalleid);
        //Crear bundle para recibir el objeto enviado por parametro.
        Bundle objetoPersona = getArguments();
        Persona persona = null;;
        //validacion para verificar si existen argumentos para mostrar
        if(objetoPersona !=null){
            persona = (Persona) objetoPersona.getSerializable("objeto");
            //imagen.setImageResource(persona.getImagenid());
            nombre.setText(persona.getNombre());
            nit.setText(persona.getNit());
            direccion.setText(persona.getDirecion());
            telefono.setText(persona.getTelefonos());
            email.setText(persona.getEmail());
            estado.setText(persona.getEstado());
            cupo.setText(persona.getCupo());
            descuento.setText(persona.getDescuento());
            condicion.setText(persona.getCondicion());
            lista_precios.setText(persona.getLista_precios());
            lista_precios2.setText(persona.getLista_precios2());
            usado.setText(persona.getUsado());
            notas.setText(persona.getNotas());
        }
        return view;
    }
}
