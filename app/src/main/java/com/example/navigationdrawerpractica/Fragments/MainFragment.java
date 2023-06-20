package com.example.navigationdrawerpractica.Fragments;
import static androidx.core.content.res.ResourcesCompat.getColor;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigationdrawerpractica.Adaptadores.AdapterDetalleVentasLinea;
import com.example.navigationdrawerpractica.Adaptadores.AdapterPersonas;
import com.example.navigationdrawerpractica.Entidades.DetalleVentaLinea;
import com.example.navigationdrawerpractica.custom.MyMarkerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.DefaultFillFormatter;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
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
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import pl.pawelkleczkowski.customgauge.CustomGauge;


public class MainFragment extends Fragment implements SeekBar.OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    GraphView grafica;
    String nombre_DB = "DB_MOBILE";
    String presupuestobd;
    String valor_ventas;
    String por_cumplir;
    TextView presupuestotext;
    TextView pendientetext;
    TextView ventastext;
    TextView porcentajeventas;
    TextView porc_ventas;
    TextView porc_kilos;
    TextView porc_rec;
    Presupuesto ppto;
    Vendedor vend;
    Double valorppto;
    int valor_venta_dia;
    int p;
    int v;

    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;

   // LineChart chart;
    PieChart chart;
    LineChart chartL;
    private LineChart mLineChart;
    private Button mButtonDays, mButtonWeeks, mButtonMonths;

    private CustomGauge gauge1;
    private CustomGauge gauge2;
    private CustomGauge gauge3;

    private RatingBar ratingventas;

    ArrayList<Entry> values = new ArrayList<>();
    ArrayList<String> xAxisValues = new ArrayList<>();
//    int orientation = getResources().getConfiguration().orientation;
    int orientation ;
    RecyclerView recyclerViewDetalleVentasLinea;

    ArrayList<DetalleVentaLinea> DetalleVentaLinea;
    AdapterDetalleVentasLinea adapterdetalleventalinea;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        orientation = getResources().getConfiguration().orientation;
        View view;
        vend = new Vendedor();
        ppto = new Presupuesto();
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Cargar el diseño para la orientación horizontal
            //setContentView(R.layout.activity_main_land);
             view = inflater.inflate(R.layout.main_fragmen_land, container, false);
            chartL =  view.findViewById(R.id.chart2);
            stilo_grafica_dia();
            setDataLine(12,300000000);
            gauge1 = view.findViewById(R.id.gauge1);
            gauge2 = view.findViewById(R.id.gauge2);
            gauge3 = view.findViewById(R.id.gauge3);
            porc_ventas = view.findViewById(R.id.txt_proc_ventas);
            porc_kilos = view.findViewById(R.id.txt_porc_kilos);
            porc_rec = view.findViewById(R.id.txt_porc_rec);

            cargar_gauge();
        } else {
            // Cargar el diseño para la orientación vertical
            //setContentView(R.layout.activity_main);
             view = inflater.inflate(R.layout.main_fragment, container, false);
            chart =  view.findViewById(R.id.chart);
            ratingventas = view.findViewById(R.id.ratingBar4);
            stilo_grafica_pie_ventas();
            setDataVEntas(5,180);
            recyclerViewDetalleVentasLinea = view.findViewById(R.id.recicler_detalle_ventas);
            DetalleVentaLinea = new ArrayList<>();
            consultar_detalle();
            try {
                ratingventas.setMax(100);
                ratingventas.setProgress( vend.getVenta() / ppto.getPresupuesto() * 100 );
            }catch (Exception a){

            }

        }

        ventastext = view.findViewById(R.id.txtventas);
        presupuestotext = view.findViewById(R.id.txtpptoventas);
        pendientetext = view.findViewById(R.id.txtpendiente);
        porcentajeventas = view.findViewById(R.id.txtporcentajeventa);
        consultarventas();
        consultarpresupuesto();
        return view;
    }

    public void consultar_detalle(){

        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM ventas_detalle",null);
        while (cursor.moveToNext()){
            try {
                DetalleVentaLinea.add(new DetalleVentaLinea(cursor.getString(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }
        mostrarData();
    }

    private void mostrarData(){
        recyclerViewDetalleVentasLinea.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterdetalleventalinea = new AdapterDetalleVentasLinea(getContext(), DetalleVentaLinea);
        // adapterPersonas = new AdapterPersonas(getContext(), listainformacion);
        recyclerViewDetalleVentasLinea.setAdapter(adapterdetalleventalinea);

        adapterdetalleventalinea.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = DetalleVentaLinea.get(recyclerViewDetalleVentasLinea.getChildAdapterPosition(view)).getGrupo();
                //txtnombre.s(nombre);
                Toast.makeText(getContext(), "Seleccionó: "+DetalleVentaLinea.get(recyclerViewDetalleVentasLinea.getChildAdapterPosition(view)).getGrupo(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
            }
        });
    }
    public void cargar_gauge(){

                                gauge1.setValue(90);
                                gauge2.setValue(80);
                                gauge3.setValue(110);
                                porc_ventas.setText(String.format(Locale.getDefault(), "%1d/%2d", gauge1.getValue(), gauge1.getEndValue()));
                                porc_kilos.setText(String.format(Locale.getDefault(), "%1d/%2d", gauge2.getValue(), gauge2.getEndValue()));
                                porc_rec.setText(String.format(Locale.getDefault(), "%1d/%2d", gauge3.getValue(), gauge3.getEndValue()));

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        try {
            if (e == null) {
                System.out.println("Valor null XD");
                return;
            }else {
                Log.i("VAL SELECTED",
                        "Value: " + e.getY() + ", index: " + h.getX()
                                + ", DataSet index: " + h.getDataSetIndex());
                DecimalFormat formato = new DecimalFormat("#,###");
                //System.out.println(formatoNumero.format(numero));
                int val = (int) e.getY();
                String valorFormateado = formato.format(Double.parseDouble(String.valueOf(val)));
                ventastext.setText("Ventas : $ " +  formato.format(Integer.parseInt(String.valueOf(val))));
            }
        }catch (Exception ex){
            System.out.println("Error en el valor seleccionado");
        }
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    private void stilo_grafica_pie_ventas(){
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);

        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

       //* chart.setCenterTextTypeface();


        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.TRANSPARENT);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

        // chart.setHoleRadius(58f);
        chart.setHoleRadius(38f);
        //chart.setTransparentCircleRadius(61f);
        chart.setTransparentCircleRadius(45f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);


        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setTextColor(Color.WHITE);
        l.setTextSize(12f);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.rgb(0,0,0));
        // chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
    }

    private void stilo_grafica_dia()
    {

        {   // // Chart Style // //

            // background color
            chartL.setBackgroundColor(Color.TRANSPARENT);

            // disable description text
            chartL.getDescription().setEnabled(false);

            // enable touch gestures
            chartL.setTouchEnabled(true);

            // set listeners
            //chartL.setOnChartValueSelectedListener(this.getContext());
            chartL.setDrawGridBackground(false);

            // create marker to display box when values are selected
            MyMarkerView mv = new MyMarkerView(this.getContext(), R.layout.custom_market_view);

            // Set the marker to the chart
            mv.setChartView(chartL);
            chartL.setMarker(mv);

            // enable scaling and dragging
            chartL.setDragEnabled(true);
            chartL.setScaleEnabled(true);
            chartL.setScaleXEnabled(true);
            chartL.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chartL.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chartL.getXAxis();
            xAxis.setLabelRotationAngle(45f);
            // vertical grid lines
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {   // // Y-Axis Style // //
            yAxis = chartL.getAxisLeft();

            // disable dual axis (only use LEFT axis)
            chartL.getAxisRight().setEnabled(false);

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f);

            // axis range
            yAxis.setAxisMaximum(300000000f);
            yAxis.setAxisMinimum(0f);
        }


        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
          //  llXAxis.setTypeface(tfRegular);

            //consultar limite ventas dia
            DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
            /*Abrimos la base de datos para escritura*/
            SQLiteDatabase db=admin.getWritableDatabase();
            Cursor cursor=db.rawQuery("SELECT distinct dias_habiles FROM presupuestoventas",null);
            int dias_ha ;
            double limite = 0;
            while (cursor.moveToNext()){
                try {
                    dias_ha =cursor.getInt(0);
                   limite = ppto.getPresupuesto() / dias_ha;

                }catch (Exception e){
                    Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            //LimitLine ll1 = new LimitLine(150f, "Upper Limit");
            LimitLine ll1 = new LimitLine((float) limite, "Venta por dia");
            ll1.setLineWidth(2f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
           // ll1.setTypeface(tfRegular);


            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);

            //xAxis.addLimitLine(llXAxis);
        }

        // draw points over time
        chartL.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chartL.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.CIRCLE);
    }
    private void setDataVEntas(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        double[] datos_parties = new double[]{};
        String[] parties = new String[] {};

//        final String[] parties = new String[] {
//                "Alambre brillante", "Puas", "Alambre galvanizado", "Tornillos", "Clavos"
//        };
//        final String[] parties = new String[] {};

//        final double[] datos_parties = new double[]{
//          2500,3500,5000,16000,9800
//        };
        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT subgrupo, sum (valor) as valor FROM ventas_detalle group by subgrupo",null);
        Integer pos = 0;
        datos_parties = new double[cursor.getCount()];
        parties = new String[cursor.getCount()];
        while (cursor.moveToNext()){
            try {
                double porc ;
                DecimalFormat formato = new DecimalFormat("#,###");
                String grupo = cursor.getString(0);
                Integer kilos = cursor.getInt(1);
                //String valorFormateado = formato.format(Integer.parseInt(val));

                datos_parties[pos] = kilos;
                parties[pos] = grupo;

                //entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                entries.add(new PieEntry((float) datos_parties[pos],
                        parties[pos % parties.length],
                        getResources().getDrawable(R.drawable.gohan_cara1)));

                pos++;
//                porcentajeventas.setText(formato.format(Double.parseDouble(String.valueOf(porc))) + "%");
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }


        PieDataSet dataSet = new PieDataSet(entries, "Linea de producción");

        dataSet.setDrawIcons(false);
        chart.setCenterTextColor(Color.WHITE);
        chart.setCenterTextSize(56f);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        //dataSet.setSelectionShift(5f);
        dataSet.setSelectionShift(30f);
        // add a lot of colors
        ArrayList<Integer> colors = new ArrayList<>();

        final String[] Colores = new String[] {
                "#cc063e","#e83535","#fd9407","#e2d9c2","#10898b","#8fbe00","#aee239","#f9f2e7","#40c0cb","#00a8c6"
        };

        for (int i = 0 ; i < count; i ++){
            colors.add(Color.parseColor(Colores[i]));
        }
        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.rgb(255,255,255));
        //data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    private void setDataLine(int count, float range) {

        /*ArrayList<Entry> values = new ArrayList<>();
        ArrayList<String> xAxisValues = new ArrayList<>();*/

//SE COMENTA EL FOR HASTA EL LINEDATASET SET 1 PARA INTENTAR LLENAR CON LOS DATOS DE LA BASE DE DATOS REAL
 /*      for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) ;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.gohan_cara1)));
           xAxisValues.add("Lun");
        }
*/
       cargar_ventas_dia();


        LineDataSet set1;

        if (chartL.getData() != null &&
                chartL.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chartL.getData().getDataSetByIndex(0);
            set1.setValues(values);
            XAxis xAxis = chartL.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
            set1.notifyDataSetChanged();
            chartL.getData().notifyDataChanged();
            chartL.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "Ventas Diarias");

            set1.setDrawIcons(false);

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f);

            // black lines and points
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);

            // line thickness and point size
            set1.setLineWidth(1f);
            set1.setCircleRadius(3f);

            // draw points as solid circles
            set1.setDrawCircleHole(false);

            // customize legend entry
            set1.setFormLineWidth(1f);
           // set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{0f, 0f}, 0f));
            set1.setFormSize(15.f);

            // text size of values
            set1.setValueTextSize(9f);

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f);

            // set the filled area
            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chartL.getAxisLeft().getAxisMinimum();
                }
            });

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this.getContext(), R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the data sets

            XAxis xAxis = chartL.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));
            xAxis.setDrawLabels(true);


            // create a data object with the data sets
            LineData data = new LineData(dataSets);
            // Configurar el eje X con los valores de los días de la semana

            set1.setMode(LineDataSet.Mode.STEPPED);


            // set data
            chartL.setData(data);

        }
    }


    public void consultarpresupuesto(){
        int orientation = getResources().getConfiguration().orientation;
        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM presupuestoventas",null);
        while (cursor.moveToNext()){
            try {
                double porc ;
                DecimalFormat formato = new DecimalFormat("#,###");
                int val = cursor.getInt(1);
                //String valorFormateado = formato.format(Integer.parseInt(val));
                int valorFormateado = val;
                ppto.setPresupuesto(valorFormateado);
                //p = Integer.parseInt(valorFormateado);
//                presupuestotext.setText("Pto:    $ " +  formato.format(Integer.parseInt(String.valueOf(valorFormateado))));
                int pendiente = ppto.getPresupuesto() - vend.getVenta() ;

                String vta = String.valueOf(vend.getVenta());
                String ppt = String.valueOf(ppto.getPresupuesto());
                porc = (Double.parseDouble(vta) / Double.parseDouble(ppt))*100;
                double por = porc;
//                pendientetext.setText("Pte:    $ " + formato.format(Integer.parseInt(String.valueOf(pendiente))));
//                /chart.setCenterText(formato.format(Double.parseDouble(String.valueOf(porc))) + "%");

                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    pendientetext.setText("Pte:    $ " + formato.format(Integer.parseInt(String.valueOf(pendiente))));
                    presupuestotext.setText("Pto:    $ " +  formato.format(Integer.parseInt(String.valueOf(valorFormateado))));
                } else {
                    chart.setCenterText(formato.format(Double.parseDouble(String.valueOf(porc))) + "%");
                    pendientetext.setText(" $ " + formato.format(Integer.parseInt(String.valueOf(pendiente))));
                    presupuestotext.setText(" $ " +  formato.format(Integer.parseInt(String.valueOf(valorFormateado))));
                }

//                porcentajeventas.setText(formato.format(Double.parseDouble(String.valueOf(porc))) + "%");
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void cargar_ventas_dia(){
        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM ventasvendedor",null);
        int i = 0;
        while (cursor.moveToNext()){
            try {
                DecimalFormat formato = new DecimalFormat("#,###");
                values.add(new Entry( i, cursor.getInt(2), getResources().getDrawable(R.drawable.gohan_cara1)));
               xAxisValues.add(cursor.getString(1));
                i++;
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void consultarventas(){
        DBHelper admin=new DBHelper(this.getContext(),nombre_DB,null,1);
        /*Abrimos la base de datos para escritura*/
        SQLiteDatabase db=admin.getWritableDatabase();
//        Cursor cursor=db.rawQuery("SELECT * FROM ventastot",null);
        Cursor cursor=db.rawQuery("SELECT SUM(valor) AS vta FROM ventas_detalle",null);
        while (cursor.moveToNext()){
            try {
                DecimalFormat formato = new DecimalFormat("#,###");
                //System.out.println(formatoNumero.format(numero));
                int val = cursor.getInt(0);
                //String valorFormateado = formato.format(Double.parseDouble(val));
                //vend.setVentas(valorFormateado);
                vend.setVentas(val);
                //v = Integer.parseInt(valorFormateado);
                //ventastext.setText("Ventas : $ " +  valorFormateado);
                if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    ventastext.setText("Vtas:    $ " +  formato.format(Integer.parseInt(String.valueOf(val))));
                } else {
                    ventastext.setText(" $ " +  formato.format(Integer.parseInt(String.valueOf(val))));
                }
//                ventastext.setText("Vtas:    $ " +  formato.format(Integer.parseInt(String.valueOf(val))));
            }catch (Exception e){
                Toast.makeText(getContext(), "Error: "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
