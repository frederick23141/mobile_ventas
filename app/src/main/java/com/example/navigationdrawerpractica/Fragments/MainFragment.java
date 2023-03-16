package com.example.navigationdrawerpractica.Fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigationdrawerpractica.DataBase.DBHelper;
import com.example.navigationdrawerpractica.Entidades.Persona;
import com.example.navigationdrawerpractica.Entidades.Presupuesto;
import com.example.navigationdrawerpractica.Entidades.Vendedor;
import com.example.navigationdrawerpractica.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainFragment extends Fragment {

    GraphView grafica;
    String nombre_DB = "DB_MOBILE";
    String presupuestobd;
    String valor_ventas;
    String por_cumplir;
    TextView presupuestotext;
    TextView pendientetext;
    TextView ventastext;
    Presupuesto ppto;
    Vendedor vend;
    Double valorppto;
    int valor_venta_dia;
    int p;
    int v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ppto = new Presupuesto();
        vend = new Vendedor();
        presupuestotext = view.findViewById(R.id.presupuesto_t);
        presupuestotext.setText("a");
        consultarpresupuesto();
        presupuestotext.setText(ppto.getPresupuesto());

        ventastext = view.findViewById(R.id.ventas_t);
        ventastext.setText("b");
        consultarventas();
        ventastext.setText(vend.getVentas());

        pendientetext = view.findViewById(R.id.pendiente_t);
        try {

            int t = p - v;
            DecimalFormat formato = new DecimalFormat("#.###");
            String valorFormateado = formato.format(Integer.parseInt(String.valueOf(t)));
            pendientetext.setText(valorFormateado);
        }catch (NumberFormatException e){
            Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //pendientetext.setText(pendiente.toString());

        return view;
    }

    public void consultarpresupuesto(){

        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM presupuestoventas",null);
        while (cursor.moveToNext()){
            try {
                DecimalFormat formato = new DecimalFormat("#.###");
                String val = cursor.getString(1);
                String valorFormateado = formato.format(Integer.parseInt(val));
                ppto.setPresupuesto(valorFormateado);
                p = Integer.parseInt(valorFormateado);
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }


    public void consultarventas(){
        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM ventastot",null);
        while (cursor.moveToNext()){
            try {
                DecimalFormat formato = new DecimalFormat("#.###");
                String val = cursor.getString(1);
                String valorFormateado = formato.format(Double.parseDouble(val));
                vend.setVentas(valorFormateado);
                v = Integer.parseInt(valorFormateado);
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
