package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawerpractica.Entidades.Vendedor;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class ConfigFragment extends Fragment {

    Switch swuser;
    Switch swclient;
    Switch swrut;
    Switch swmaestroinventrari;
    Switch swcarter;
    Switch swdetallecarter;
    Switch swstoc;
    Switch swpresupuest;
    Switch swsendclient;
    Switch swsendrut;
    Switch swsendpedid;
    Button procesar;

    Vendedor vendedor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.config_fragment,container,false);

        swuser = (Switch) view.findViewById(R.id.swusuarios);
        swclient = (Switch) view.findViewById(R.id.swclientes);
        swrut = (Switch) view.findViewById(R.id.swrutas);
        swmaestroinventrari = (Switch) view.findViewById(R.id.swmaestroinventario);
        swcarter = (Switch) view.findViewById(R.id.swcartera);
        swdetallecarter = (Switch) view.findViewById(R.id.swdetallecartera);
        swstoc = (Switch) view.findViewById(R.id.swstock);
        swpresupuest = (Switch) view.findViewById(R.id.swpresupuesto);
        swsendclient = (Switch) view.findViewById(R.id.swclienteenviar);
        swsendrut = (Switch) view.findViewById(R.id.swrutasenviar);
        swsendpedid = (Switch) view.findViewById(R.id.swpedidosenviar);

        procesar = (Button) view.findViewById(R.id.btnprocesar);

        procesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procesar_sincronizacion();

            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public void procesar_sincronizacion(){
        if(swuser.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar usuario", Toast.LENGTH_SHORT).show();
        }

        if(swclient.isChecked()){
            Toast.makeText(this.getContext(), "Sincronizar cliente", Toast.LENGTH_SHORT).show();
        }
    }

}
