package com.example.navigationdrawerpractica.Fragments;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
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
import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    LineChart chart;
    private LineChart mLineChart;
    private Button mButtonDays, mButtonWeeks, mButtonMonths;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        chart =  view.findViewById(R.id.chart);
        setupChart(chart);
        loadChartData(chart);
        return view;



    }

    private void setupChart(LineChart chart) {
        // Configurar aspectos visuales del gráfico
        chart.setDrawGridBackground(false);
        Description description = new Description();
        description.setText("");
        chart.setDescription(description);
        chart.getLegend().setEnabled(false);
        chart.setTouchEnabled(false);

        chart.getLegend().setEnabled(true); // Habilitar la leyenda
        chart.getLegend().setTextSize(12f); // Tamaño del texto de la leyenda
        chart.getLegend().setFormSize(10f); // Tamaño de los iconos de la leyenda
        chart.getLegend().setForm(Legend.LegendForm.CIRCLE); // Estilo de los iconos de la leyenda (en este caso, círculos)

        // Animación de entrada
        chart.animateY(1000, Easing.EaseInOutQuad);

        // Configurar ejes
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setDrawGridLines(false); // Quitar líneas de cuadrícula en el eje X
        chart.getAxisLeft().setAxisMinimum(0f);
        chart.getAxisLeft().setDrawGridLines(false); // Quitar líneas de cuadrícula en el eje Y
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setTextColor(Color.BLACK);
        chart.getXAxis().setTextColor(Color.BLACK);
    }

    private void loadChartData(LineChart chart) {
        // Datos de ejemplo (valores por día de la semana)
        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0f, 50f));
        entries1.add(new Entry(1f, 70f));
        entries1.add(new Entry(2f, 60f));
        entries1.add(new Entry(3f, 80f));
        entries1.add(new Entry(4f, 90f));
        entries1.add(new Entry(5f, 75f));
        entries1.add(new Entry(6f, 65f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0f, 30));
        entries2.add(new Entry(1f, 30));
        entries2.add(new Entry(2f, 30));
        entries2.add(new Entry(3f, 30));
        entries2.add(new Entry(4f, 30));
        entries2.add(new Entry(5f, 30));
        entries2.add(new Entry(6f, 30));

        // Configurar etiquetas de los días de la semana
        String[] labels = new String[]{"Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"};

        // Crear conjunto de datos de línea
        LineDataSet dataSet = new LineDataSet(entries1, "Ventas");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // Establecer el modo de línea suave
        dataSet.setCubicIntensity(0.2f); // Controlar la suavidad de las curvas
        dataSet.setDrawCircles(true); // Ocultar los círculos en los puntos
        dataSet.setColor(Color.rgb(252, 96, 17)); // Color de la línea

        // Configurar relleno de área bajo la línea
        dataSet.setDrawFilled(true); // Rellenar área debajo de la línea
        dataSet.setFillFormatter(new DefaultFillFormatter()); // Utilizar relleno predeterminado
        dataSet.setFillColor(Color.rgb(252, 96, 17)); // Color del área
        dataSet.setFillAlpha(100); // Transparencia del área


        // Crear conjunto de datos de línea para la serie 2
        LineDataSet dataSet2 = new LineDataSet(entries2, "Presupuesto dia");
        dataSet2.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        dataSet2.setCubicIntensity(0.1f);
        dataSet2.setDrawCircles(false);
        dataSet2.setColor(Color.rgb(0, 0, 0));
        //dataSet2.setFillColor(Color.rgb(255, 0, 0));
        dataSet2.setFillAlpha(100);

        // Combinar los conjuntos de datos
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);
        dataSets.add(dataSet2);

        // Crear objeto LineData y establecer los conjuntos de datos de línea
        LineData lineData = new LineData(dataSets);
        chart.setData(lineData);

        // Configurar etiquetas de los días de la semana en el eje X
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
        chart.getXAxis().setLabelCount(labels.length);
        chart.invalidate(); // Refrescar el gráfico

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
