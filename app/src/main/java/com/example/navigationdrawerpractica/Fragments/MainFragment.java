package com.example.navigationdrawerpractica.Fragments;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

public class MainFragment extends Fragment {

    GraphView grafica;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);


        grafica = (GraphView) view.findViewById(R.id.grafica);

        BarGraphSeries<DataPoint> seriesb = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 5000000),
                new DataPoint(1, 3000000),
                new DataPoint(2, 13000000),
                new DataPoint(3, 20000000),
                new DataPoint(4, 100000000),
                new DataPoint(5, 60000000),
                new DataPoint(6, 80000000),
                new DataPoint(7, 3000000),
                new DataPoint(8, 11000000),
                new DataPoint(9, 3000000),
                new DataPoint(10, 6000000),
                new DataPoint(11, 4000000),
                new DataPoint(12, 120000000),
        });

        grafica.addSeries(seriesb);
        // DIBUJANDO LOS VALORES
        seriesb.setTitle("Valor vendido");
        seriesb.setDrawValuesOnTop(true);
        seriesb.setValuesOnTopColor(Color.BLACK);
        grafica.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        seriesb.setSpacing(30);
        seriesb.setValuesOnTopSize(17);



        // DATOS DE LAS BARRAS
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 5000000),
                new DataPoint(1, 3000000),
                new DataPoint(2, 13000000),
                new DataPoint(3, 20000000),
                new DataPoint(4, 100000000),
                new DataPoint(5, 60000000),
                new DataPoint(6, 80000000),
                new DataPoint(7, 3000000),
                new DataPoint(8, 11000000),
                new DataPoint(9, 3000000),
                new DataPoint(10, 6000000),
                new DataPoint(11, 4000000),
                new DataPoint(12, 120000000),
        });

        grafica.addSeries(series);
        series.setTitle("Tendencia");
        series.setColor(Color.RED);
        series.setDrawDataPoints(true);
        grafica.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        series.setDataPointsRadius(5);

        grafica.setTitle("ESTADISTICA DE VENTAS");



/*
        // ESTILO
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb(0, 147, 255);
            }
        });

        series.setSpacing(10);



*/




        // use static labels for horizontal and vertical labels
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(grafica);


        staticLabelsFormatter.setHorizontalLabels(new String[] {"01-09-2022", "02-09-2022", "03-09-2022", "03-09-2022"
                , "04-09-2022", "05-09-2022", "06-09-2022", "07-09-2022", "08-09-2022"
                , "09-09-2022", "10-09-2022", "11-09-2022", "12-09-2022"});
        grafica.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);



            LineGraphSeries<DataPoint> seriesl = new LineGraphSeries<>(new DataPoint[] {
                    new DataPoint(0, 35000000),
                    new DataPoint(1, 35000000),
                    new DataPoint(2, 35000000),
                    new DataPoint(3, 35000000),
                    new DataPoint(4, 35000000),
                    new DataPoint(5, 35000000),
                    new DataPoint(6, 35000000),
                    new DataPoint(7, 35000000),
                    new DataPoint(8, 35000000),
                    new DataPoint(9, 35000000),
                    new DataPoint(10, 35000000),
                    new DataPoint(11, 35000000),
                    new DataPoint(12, 35000000)
            });
            grafica.addSeries(seriesl);
        seriesl.setColor(Color.rgb(174,0,255));
        seriesl.setDrawDataPoints(true);
        seriesl.setTitle("Venta minima diaria");


        grafica.getGridLabelRenderer().setVerticalLabelsVisible(false);
        grafica.getGridLabelRenderer().setHorizontalLabelsVisible(true);

        grafica.getGridLabelRenderer().setHighlightZeroLines(false);
        grafica.getGridLabelRenderer().setVerticalLabelsAlign(Paint.Align.LEFT);
        grafica.getGridLabelRenderer().setLabelVerticalWidth(100);
        grafica.getGridLabelRenderer().setTextSize(20);
        grafica.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.HORIZONTAL);
        grafica.getGridLabelRenderer().setHorizontalLabelsAngle(90);
        grafica.getGridLabelRenderer().reloadStyles();

        series.setDrawBackground(true);
        series.setBackgroundColor(Color.argb(100, 0, 147, 255));

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setPathEffect(new DashPathEffect(new float[]{8, 5}, 0));
        series.setDrawAsPath(true);
        series.setCustomPaint(paint);

        //grafica.getViewport().setScalable(true);
        // enable scrolling
        grafica.getViewport().setScrollable(true);

        grafica.getLegendRenderer().setFixedPosition(150, 0);
        grafica.getLegendRenderer().setVisible(true);



        // register tap on series callback
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(grafica.getContext(), "Series1: On Data Point clicked: "+ series.getValues(seriesl.getLowestValueX(), series.getHighestValueX()), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
