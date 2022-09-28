package com.example.navigationdrawerpractica.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

    Vendedor vendedor;
    AdapterPersonas adapterPersonas;
    RecyclerView recyclerViewPersonas;
    ArrayList<Persona> listaPersonas;
    Toolbar toolbar;

    //EditText txtnombre;
    TextInputEditText txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personas_fragment,container,false);
        txtnombre = view.findViewById(R.id.txtnombre);

        toolbar = view.findViewById(R.id.toolbar);
        vendedor = new Vendedor();
        recyclerViewPersonas = view.findViewById(R.id.recyclerView);
        listaPersonas = new ArrayList<>();
        cargarBDclientes();
        //cargarLista();
        mostrarData();
        return view;
    }

    public void cargarBDclientes(){
        //CREAMOS LA CONEXION A LA BASE DE DATOS REAL, BORRAMOS LA TABLA DE USUARIOS Y AGREGAMOS ESTE NUEVO USUARIO

        ConSQL c = new ConSQL();
        Connection connection = c.conclass();
        String vend = vendedor.getVendedor();
        if(c != null){
            //logear en real
            try {
                Toast.makeText(actividad.getApplicationContext(), "el vendedor es " + vend, Toast.LENGTH_SHORT).show();
                String sqlstatement = "select nit,nombres,direccion,celular,mail,bloqueo,cupo_credito,lista,descuento_fijo,condicion,notas\n" +
                        "from terceros where vendedor = ? ";
                PreparedStatement preparedStatement = connection.prepareStatement(sqlstatement);
                preparedStatement.setString(1, vend);

                ResultSet set = preparedStatement.executeQuery();

                while (set.next()){
                    Toast.makeText(actividad.getApplicationContext(), "Felicidades. encontrado", Toast.LENGTH_SHORT).show();
                    listaPersonas.add(new Persona(set.getString(1).toString(),set.getString(2).toString(),R.drawable.ic_round_person_pin_24));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            //loguar en local
            Toast.makeText(actividad.getApplicationContext(), "Error", Toast.LENGTH_SHORT);
        }




    }

    public void cargarLista(){
        listaPersonas.add(new Persona("Gohan","31-05-1994",R.drawable.ic_round_person_pin_24));
        listaPersonas.add(new Persona("Goku","31-05-1994",R.drawable.ic_round_person_pin_24));
        listaPersonas.add(new Persona("Goten","31-05-1994",R.drawable.ic_round_person_pin_24));
        listaPersonas.add(new Persona("Krilin","31-05-1994",R.drawable.ic_round_person_pin_24));
        listaPersonas.add(new Persona("Picoro","31-05-1994",R.drawable.ic_round_person_pin_24));
        listaPersonas.add(new Persona("Trunks","31-05-1994",R.drawable.ic_round_person_pin_24));
        listaPersonas.add(new Persona("Vegueta","31-05-1994",R.drawable.ic_round_person_pin_24));
    }
    private void mostrarData(){
        recyclerViewPersonas.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPersonas = new AdapterPersonas(getContext(), listaPersonas);
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

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
