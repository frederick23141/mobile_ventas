package com.example.navigationdrawerpractica.Fragments;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ProgressBar;
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

    private SeekBar seekBarX, seekBarY;
    private TextView tvX, tvY;

   // LineChart chart;
    PieChart chart;
    LineChart chartL;
    private LineChart mLineChart;
    private Button mButtonDays, mButtonWeeks, mButtonMonths;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        chart =  view.findViewById(R.id.chart);
        chartL =  view.findViewById(R.id.chart2);
       // setTitle("PieChartActivity");
        //setupChart(chart);
        //loadChartData(chart);



/*        tvX = findViewById(R.id.tvXMax);
        tvY = findViewById(R.id.tvYMax);

        seekBarX = findViewById(R.id.seekBar1);
        seekBarY = findViewById(R.id.seekBar2);

        seekBarX.setOnSeekBarChangeListener(this);
        seekBarY.setOnSeekBarChangeListener(this);*/

        //chart = findViewById(R.id.chart1);
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);

        chart.setDragDecelerationFrictionCoef(0.95f);

       /* chart.setCenterTextTypeface();
        chart.setCenterText(generateCenterSpannableText());*/

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.TRANSPARENT);

        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);

       // chart.setHoleRadius(58f);
        chart.setHoleRadius(48f);
        chart.setTransparentCircleRadius(61f);

        chart.setDrawCenterText(true);

        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);

         //chart.setUnit(" €");
        // chart.setDrawUnitsInChart(true);

        // add a selection listener
        //chart.setOnChartValueSelectedListener((OnChartValueSelectedListener) this);

       // seekBarX.setProgress(4);
       // seekBarY.setProgress(10);

        chart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        chart.setEntryLabelColor(Color.rgb(0,0,0));
       // chart.setEntryLabelTypeface(tfRegular);
        chart.setEntryLabelTextSize(12f);
        setData(5,20);
        stilo_grafica_dia();
        setDataLine(5,20);
        return view;

    }

    private void stilo_grafica_dia()
    {

        {   // // Chart Style // //

            // background color
            chartL.setBackgroundColor(Color.WHITE);

            // disable description text
            chartL.getDescription().setEnabled(false);

            // enable touch gestures
            chartL.setTouchEnabled(true);

            // set listeners
           // chartL.setOnChartValueSelectedListener(this);
            chartL.setDrawGridBackground(false);

            // create marker to display box when values are selected
        //    MyMarkerView mv = new MyMarkerView(this, R.layout.custom_marker_view);

            // Set the marker to the chart
           // mv.setChartView(chartL);
         //   chartL.setMarker(mv);

            // enable scaling and dragging
            chartL.setDragEnabled(true);
            chartL.setScaleEnabled(true);
            // chart.setScaleXEnabled(true);
            // chart.setScaleYEnabled(true);

            // force pinch zoom along both axis
            chartL.setPinchZoom(true);
        }

        XAxis xAxis;
        {   // // X-Axis Style // //
            xAxis = chartL.getXAxis();

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
            yAxis.setAxisMaximum(200f);
            yAxis.setAxisMinimum(-50f);
        }


        {   // // Create Limit Lines // //
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);
          //  llXAxis.setTypeface(tfRegular);

            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);
           // ll1.setTypeface(tfRegular);

            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);
           // ll2.setTypeface(tfRegular);

            // draw limit lines behind data instead of on top
            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

            // add limit lines
            yAxis.addLimitLine(ll1);
            yAxis.addLimitLine(ll2);
            //xAxis.addLimitLine(llXAxis);
        }

        // add data
       // seekBarX.setProgress(45);
      //  seekBarY.setProgress(180);
       // setData(45, 180);

        // draw points over time
        chartL.animateX(1500);

        // get the legend (only possible after setting data)
        Legend l = chartL.getLegend();

        // draw legend entries as lines
        l.setForm(Legend.LegendForm.LINE);
    }

    private void setData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        final String[] parties = new String[] {
                "Alambre brillante", "Puas", "Alambre galvanizado", "Tornillos", "Clavos"
        };


        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5),
                    parties[i % parties.length],
                    getResources().getDrawable(R.drawable.gohan_cara1)));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        //dataSet.setSelectionShift(5f);
        dataSet.setSelectionShift(15f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.rgb(0,0,0));
        //*data.setValueTypeface(tfLight);
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    private void setDataLine(int count, float range) {

        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            float val = (float) (Math.random() * range) / 2;
            values.add(new Entry(i, val, getResources().getDrawable(R.drawable.gohan_cara1)));
        }

        LineDataSet set1;

        if (chartL.getData() != null &&
                chartL.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chartL.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chartL.getData().notifyDataChanged();
            chartL.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

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
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
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

            // create a data object with the data sets
            LineData data = new LineData(dataSets);

            // set data
            chartL.setData(data);
        }
    }

   /* private void setupChart(LineChart chart) {
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
        entries1.add(new Entry(6f, 22));
        entries1.add(new Entry(7f, 90));
        entries1.add(new Entry(8f, 45));
        entries1.add(new Entry(9f, 10));
        entries1.add(new Entry(10f, 80));
        entries1.add(new Entry(11f, 63));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0f, 68));
        entries2.add(new Entry(1f, 68));
        entries2.add(new Entry(2f, 68));
        entries2.add(new Entry(3f, 68));
        entries2.add(new Entry(4f, 68));
        entries2.add(new Entry(5f, 68));
        entries2.add(new Entry(6f, 68));
        entries2.add(new Entry(7f, 68));
        entries2.add(new Entry(8f, 68));
        entries2.add(new Entry(9f, 68));
        entries2.add(new Entry(10f, 68));
        entries2.add(new Entry(11f, 68));

        // Configurar etiquetas de los días de la semana
        String[] labels = new String[]{"D1", "D1", "D3", "D4", "D5", "D6", "D7","D8","D9","D10","D11","D12"};

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
        dataSet2.setDrawValues(false);
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

    }*/



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
