package com.example.navigationdrawerpractica.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigationdrawerpractica.Adaptadores.AdapterListaPrecios;
import com.example.navigationdrawerpractica.DataBase.DBHelper;
import com.example.navigationdrawerpractica.Entidades.ListaPrecio;
import com.example.navigationdrawerpractica.Entidades.Vendedor;
import com.example.navigationdrawerpractica.Interfaces.iComunicaListaPerciosFragments;
import com.example.navigationdrawerpractica.R;

import java.util.ArrayList;

public class ListaPreciosFragment extends Fragment implements SearchView.OnQueryTextListener {

    //private OnFragmentInteractionListener mListener;
    String nombre_DB = "DB_MOBILE";
    Vendedor vendedor;
    AdapterListaPrecios adapterListaPrecios;
    RecyclerView recyclerViewListaPrecios;
    ArrayList<ListaPrecio> listaPrecios;
    Toolbar toolbar;

    //EditText txtnombre;
    SearchView txtnombre;

    //Crear referencias para poder realizar la comunicacion entre el fragment detalle
    Activity actividad;


    DBHelper conn;

    @SuppressLint("MissingInflatedId")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_precios_fragment,container,false);
        txtnombre = view.findViewById(R.id.txt_buscar);
        toolbar = view.findViewById(R.id.toolbar);

        recyclerViewListaPrecios = view.findViewById(R.id.recyclerView);
        conn=new DBHelper(this.getContext(),"lista_precios",null,1);
        listaPrecios = new ArrayList<>();
        txtnombre.setOnQueryTextListener(this);
        //cargarBDlistaprecios();
        consultarlista();
        //cargarLista();
        mostrarData();
        return view;
    }

    public void consultarlista(){

        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM lista_precios",null);

        while (cursor.moveToNext()){
            try {
                listaPrecios.add(new ListaPrecio(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
    }



    private void mostrarData(){
        recyclerViewListaPrecios.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterListaPrecios = new AdapterListaPrecios(getContext(), listaPrecios);
       // adapterPersonas = new AdapterPersonas(getContext(), listainformacion);
        recyclerViewListaPrecios.setAdapter(adapterListaPrecios);
        adapterListaPrecios.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //  String nombre = listaPrecios.get(recyclerViewListaPrecios.getChildAdapterPosition(view)).getCodigo();
               //txtnombre.s(nombre);
            //   Toast.makeText(getContext(), "Seleccionó: "+ listaPrecios.get(recyclerViewListaPrecios.getChildAdapterPosition(view)).getDescripcion(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                //se utiliza la interface como puente para enviar el objeto seleccionado
            //    interfaceComunicaFragments.enviarListaPrecio(listaPrecios.get(recyclerViewListaPrecios.getChildAdapterPosition(view)));
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
            //interfaceComunicaFragments= (iComunicaListaPerciosFragments) this.actividad;
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

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapterListaPrecios.filtrado(s);
        return false;
    }
}
