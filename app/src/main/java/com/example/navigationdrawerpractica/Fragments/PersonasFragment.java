package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Adaptadores.AdapterPersonas;
import com.example.navigationdrawerpractica.ConSQL;
import com.example.navigationdrawerpractica.DataBase.DBHelper;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Vendedor;
import com.example.navigationdrawerpractica.Interfaces.MainActivity;
import com.example.navigationdrawerpractica.Interfaces.iComunicaFragments;
import com.example.navigationdrawerpractica.Login;
import com.example.navigationdrawerpractica.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonasFragment extends Fragment {

    //private OnFragmentInteractionListener mListener;
    String nombre_DB = "DB_MOBILE";
    Vendedor vendedor;
    AdapterPersonas adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<Persona> listaPersonas;
    ArrayList<String> listainformacion;
    Toolbar toolbar;

    //EditText txtnombre;
    TextInputEditText txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    DBHelper conn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personas_fragment,container,false);
        txtnombre = view.findViewById(R.id.txtnombre);

        toolbar = view.findViewById(R.id.toolbar);
        vendedor = new Vendedor();
        recyclerViewPersonas = view.findViewById(R.id.recyclerView);


        conn=new DBHelper(this.getContext(),"userclientes",null,1);
        listaPersonas = new ArrayList<>();

        //cargarBDclientes();

        consultarclientes();

        //cargarLista();
        mostrarData();
        return view;
    }

    public void consultarclientes(){

        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM userclientes",null);

        while (cursor.moveToNext()){
            listaPersonas.add(new Persona(cursor.getString(1).toString(),cursor.getString(2).toString(),R.drawable.ic_round_person_pin_24));
        }
    }



    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterPersonas(getContext(), listaPersonas);
       // adapterPersonas = new AdapterPersonas(getContext(), listainformacion);
        recyclerViewPersonas.setAdapter(adapterPersonas);

        adapterPersonas.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nombre = listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre();
               txtnombre.setText(nombre);
               Toast.makeText(getContext(), "Seleccionó: "+listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
                interfaceComunicaFragments.enviarPersona(listaPersonas.get(recyclerViewPersonas.getChildAdapterPosition(view)));
                //luego en el mainactivity se hace la implementacion de la interface para implementar el metodo enviarpersona
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
        //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


}
