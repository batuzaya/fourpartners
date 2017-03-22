package com.charpixel.fourthpartnerenergy.PlantDetailModule;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.databinding.PlantArchiveDetailBinding;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType1;
import com.charpixel.fourthpartnerenergy.Models.GraphDataType2;
import com.charpixel.fourthpartnerenergy.Models.InverterArray;
import com.charpixel.fourthpartnerenergy.Models.InverterGraph;
import com.charpixel.fourthpartnerenergy.PlantListModule.Dialogs.DatePicker;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import static android.graphics.Color.rgb;
import static com.charpixel.baseandroidproject.R.id.month;
import static com.charpixel.baseandroidproject.R.id.year;

/**
 * Created by ashu on 24-12-2016.
 */

public class PlantArchiveDetailFragment extends BaseFragment implements PlantGraphContract.View {


    PlantArchiveDetailBinding binding;
    private String TAG = this.getClass().getSimpleName();


    Calendar cal = Calendar.getInstance();
    Calendar cal2 = Calendar.getInstance();

    enum DateTypeSelector {YEAR , MONTH , LIFETIME }


    DateTypeSelector dateTypeSelector = DateTypeSelector.MONTH;

    int DateType = Calendar.MONTH;
    String DateTypeString = "month";







    @Inject
    PlantGraphPresenter presenter;

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.plant_archive_detail,container,false);


        binding.energyPrChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

                Log.e(TAG, "onValueSelected: "+h.getAxis().toString()+","+e.getX() );
              //  String xAxisValue = chart.getData().getXVals().get(e.getXIndex());
            }

            @Override
            public void onNothingSelected() {

            }
        });

        binding.next2.setOnClickListener(view -> {
            presenter.getPlantIrridationData(getCurrentDate(cal2,Calendar.DATE,+1));
            changeDateIrridation();
        });
        binding.prev2.setOnClickListener(view -> {

            presenter.getPlantIrridationData(getCurrentDate(cal2,Calendar.DATE,-1));
            changeDateIrridation();
        });



        binding.next1.setOnClickListener(view -> {

            changeDate();
            presenter.getPlantEnergyData(getCurrentDate(cal,DateType,+1),DateTypeString);
            changeDate();

        });
        binding.prev1.setOnClickListener(view -> {
            changeDate();
            presenter.getPlantEnergyData(getCurrentDate(cal,DateType,-1),DateTypeString);
            changeDate();
        });


        binding.radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {

            switch (i){
                case month:
                    dateTypeSelector = DateTypeSelector.MONTH;
                    DateType = Calendar.MONTH;
                    DateTypeString = "month";
                    presenter.getPlantEnergyData(getCurrentDate(cal,DateType,0),DateTypeString);
                    break;
                case year:
                    dateTypeSelector = DateTypeSelector.YEAR;
                    DateType = Calendar.YEAR;
                    DateTypeString = "year";
                    presenter.getPlantEnergyData(getCurrentDate(cal,DateType,0),DateTypeString);
                    break;
                case R.id.lifetime:
                    dateTypeSelector = DateTypeSelector.LIFETIME;
                    DateTypeString = "lifetime";
                    presenter.getPlantEnergyData(getCurrentDate(cal,DateType,0),DateTypeString);
                    break;
            }
            changeDate();

        });


        binding.headerTextOne.setOnClickListener(view -> {
            DatePicker newFragment = new DatePicker();
            newFragment.setListener((date, year , month , day) -> {




                cal.set(Calendar.DAY_OF_MONTH , day);
                cal.set(Calendar.MONTH , month);
                cal.set(Calendar.YEAR , year);
                changeDate();

                presenter.getPlantIrridationData(getCurrentDate(cal,Calendar.DATE,0));


//                this.year = year;
//                this.month = month;
//                this.day = day;
//
//                binding.currentDate.setText(getDate());

                Log.v(TAG,"dateSelected   : "+date);
//                presenter.setDate(date);
//                presenter.populatePlants();

            });

            newFragment.setDate(cal.get(Calendar.YEAR),-1,-1);

            newFragment.show(getFragmentManager(), "datePicker");
        });


        binding.headerTextTwo.setOnClickListener(view -> {
            DatePicker newFragment = new DatePicker();
            newFragment.setListener((date, year , month , day) -> {




                cal2.set(Calendar.DAY_OF_MONTH , day);
                cal2.set(Calendar.MONTH , month);
                cal2.set(Calendar.YEAR , year);
                changeDateIrridation();

                presenter.getPlantIrridationData(getCurrentDate(cal2,Calendar.DATE,0));


//                this.year = year;
//                this.month = month;
//                this.day = day;
//
//                binding.currentDate.setText(getDate());

                Log.v(TAG,"dateSelected   : "+date);
//                presenter.setDate(date);
//                presenter.populatePlants();

            });

            newFragment.setDate(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONTH),cal2.get(Calendar.DAY_OF_MONTH));
            newFragment.show(getFragmentManager(), "datePicker");
        });









//        @Override
//        public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//
//
//        }

        presenter.attachView(this);


        presenter.getPlantIrridationData(getCurrentDate(cal,Calendar.MONTH,0));
        presenter.getPlantEnergyData(getCurrentDate(cal,Calendar.MONTH,0),"month");
        changeDate();
        changeDateIrridation();

        return binding.getRoot();
    }

    @Override
    public void showLoader(boolean isShowLoader) {

    }







    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((Application) getActivity().getApplication()).getNetComponent().inject(this);
        super.onCreate(savedInstanceState);
    }



    public String getCurrentDate(Calendar cal,int type , int offset){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        cal.add(type, offset);

      return  sdf.format(cal.getTime());
    }




    void changeDate(){

        SimpleDateFormat sdf = null;

        String date = "";

        switch (dateTypeSelector){
            case MONTH:
                sdf = new SimpleDateFormat("MMM yyyy", Locale.US);
                break;
            case YEAR:

                sdf = new SimpleDateFormat("yyyy", Locale.US);
                break;
            case LIFETIME:

                break;
        }


        if( sdf == null){
            date = "LifeTime";
        }else {
          date =  sdf.format(cal.getTime());
        }


        binding.headerTextOne.setText(date);

    }


    void changeDateIrridation(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.US);
        binding.headerTextTwo.setText(sdf.format(cal2.getTime()));

    }

    @Override
    public void refresh() {

    }

    @Override
    public void refreshPlantEnergyData(GraphDataType1 graphData) {

        float groupSpace = 0.16f;
        float barSpace = 0.08f; // x4 DataSet
        float barWidth = 0.4f; // x4 DataSet

        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"


        binding.energyPrChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        Legend l = binding.energyPrChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);


        YAxis rightAxis = binding.energyPrChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        rightAxis.setValueFormatter((value, axis) -> {
            return value+" %";
        });

        YAxis leftAxis = binding.energyPrChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setValueFormatter((value, axis) -> {
            return value+" kWh";
        });

        XAxis xAxis = binding.energyPrChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                Log.e(TAG, "getFormattedValue: "+value);

                if(value < 0)
                {
                    return  "-";
                }

                if(graphData.getDate().size() > (int) value)
                {
                    return graphData.getDate().get((int) value);
                }else{
                    return value+"";
                }

            }
        });

        xAxis.setLabelRotationAngle(-45);

        CombinedData data = new CombinedData();
        LineData d = new LineData();


        ArrayList<Entry> prEntry = new ArrayList<Entry>();

        ArrayList<ArrayList<BarEntry>> stackedBars = new ArrayList<>();

        ArrayList<BarDataSet> barDataSets = new ArrayList<>();

        BarDataSet set1 ;
        BarDataSet set2 ;



        ArrayList<BarEntry> set1barEntry = new ArrayList<BarEntry>();
        ArrayList<BarEntry> set2barEntry = new ArrayList<BarEntry>();

       for (InverterArray i : graphData.getInverterArray()){
           stackedBars.add(new ArrayList<BarEntry>());

       }

        ArrayList<BarEntry> meterKwhImport = new ArrayList<BarEntry>();
        ArrayList<BarEntry> meterKwhTotal = new ArrayList<BarEntry>();



        for (int i = 0;i < graphData.getDate().size();i++ ){

            Log.e(TAG, "refreshPlantEnergyData: "+i );

           float[] f =  new float[stackedBars.size()+2];

            for(int inv = 0 ; inv <  stackedBars.size(); inv++){
                stackedBars.get(inv).add(new BarEntry(i,graphData.getInverterArray().get(inv).getData().get(i).floatValue()));
                f[inv] = graphData.getInverterArray().get(inv).getData().get(i).floatValue();

            }

            f[ stackedBars.size()] = graphData.getMeterKwhImport().get(i);
            f[ stackedBars.size()+1] = graphData.getMeterKwhTotal().get(i);



            set2barEntry.add(new BarEntry(i,graphData.getMeters().get(i).floatValue()));


            meterKwhImport.add(new BarEntry(i,graphData.getMeterKwhImport().get(i)));
            meterKwhTotal.add(new BarEntry(i,graphData.getMeterKwhTotal().get(i)));

            prEntry.add(new Entry(i,graphData.getPr().get(i).floatValue()));

            set1barEntry.add(new BarEntry(i, f));

        }

        set1 = new BarDataSet(set1barEntry, "set1");

        set2 = new BarDataSet(set2barEntry,"set2");


        int[] colors = new int[stackedBars.size()+2];
        String[] lables = new String[stackedBars.size()+2];


        for(int inv = 0 ; inv <  stackedBars.size(); inv++){
           barDataSets.add(new BarDataSet(stackedBars.get(inv),graphData.getInverterArray().get(inv).getName()));
            int color = rgb(getRandom(120, 240), getRandom(50, 240), getRandom(20, 240));

            lables[inv] = graphData.getInverterArray().get(inv).getName();
            colors[inv] = color;

            barDataSets.get(inv).setColor(color);

           // barData.addDataSet(barDataSets.get(inv));

        }




        BarDataSet meterKwhImportDataSet = new BarDataSet(meterKwhImport,"meter kwh import");
        BarDataSet meterKwhTotalDataSet = new BarDataSet(meterKwhImport,"meter kwh total");

        int meterKwhImportDataSetColor = rgb(44, 21, 157);
        int meterKwhTotalDataSetColor = rgb(237, 61, 69);

        lables[ stackedBars.size()] = "meter kwh import";
        lables[ stackedBars.size()+1] = "meter kwh total";


        colors[ stackedBars.size()] = meterKwhImportDataSetColor;
        colors[ stackedBars.size()+1] = meterKwhTotalDataSetColor;




        meterKwhImportDataSet.setColor(meterKwhImportDataSetColor);
        meterKwhTotalDataSet.setColor(meterKwhTotalDataSetColor);

       // barData.addDataSet(meterKwhImportDataSet);
      //  barData.addDataSet(meterKwhTotalDataSet);

        set1.setColors(colors);
        set1.setStackLabels(lables);


        set2.setColor(rgb(247, 163, 92));
        set2.setStackLabels(new String[]{"Energy (Meters)"});

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set2);
        dataSets.add(set1);



      //  barData.addDataSet(dataSets);
      //  barData.addDataSet(set2);

        BarData barData = new BarData(dataSets);
        barData.groupBars(0,groupSpace,barSpace);

        barData.setBarWidth(barWidth);

       // barData.setBarWidth(barWidth);



        LineDataSet prSet = new LineDataSet(prEntry, "PR");
        prSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        prSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

        d.addDataSet(prSet);

        data.setData(d);
        data.setData(barData);

      //  binding.energyPrChart.getBarData().setBarWidth(barWidth);
        binding.energyPrChart.getXAxis().setAxisMaximum(graphData.getDate().size()+1);
        binding.energyPrChart.setData(data);
        binding.energyPrChart.invalidate();



    }

    @Override
    public void refreshPlantIrridationData(GraphDataType2 graphData) {




        //binding.combinedChart.setDrawOrder();


        binding.combinedChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });




        Legend l = binding.combinedChart.getLegend();
        l.setWordWrapEnabled(true);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);


        YAxis rightAxis = binding.combinedChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        rightAxis.setValueFormatter((value, axis) -> {
            return value+" w/m2";
        });

        YAxis leftAxis = binding.combinedChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setValueFormatter((value, axis) -> {
            return value+" kW";
        });

        XAxis xAxis = binding.combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return graphData.getDate().get((int) value);
            }
        });
        xAxis.setLabelRotationAngle(-45);



//        xAxis.setAxisMinimum(0f);
//        xAxis.setGranularity(1f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return mMonths[(int) value % mMonths.length];
//            }
//        });

        List<Entry> entries = new ArrayList<Entry>();


        CombinedData data = new CombinedData();

        LineData d = new LineData();




        ArrayList<Entry> irridationEntries = new ArrayList<Entry>();

        ArrayList<ArrayList<Entry>> inverters =  new ArrayList<>();
        ArrayList<LineData> lineDatas =  new ArrayList<>();

        for(InverterGraph i : graphData.getInverters()){
            inverters.add(new ArrayList<Entry>());
            lineDatas.add(new LineData());
        }






        for(int i = 0 ; i <  graphData.getDate().size(); i++){

            Log.e(TAG, "refreshPlantIrridationData: "+graphData.getIrradiationKwh().get(i) );
            irridationEntries.add(new Entry(i,graphData.getIrradiationKwh().get(i).floatValue()));


            for(int inv = 0 ; inv <  inverters.size(); inv++){
                inverters.get(inv).add(new Entry(i,graphData.getInverters().get(inv).getValue().get(i).floatValue()));
            }

        }


        LineDataSet irridationSet = new LineDataSet(irridationEntries, "irridation");
        irridationSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        irridationSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

        d.addDataSet(irridationSet);

       // data.setData(d);

        ArrayList<LineDataSet> lineDataSetInverterArray = new ArrayList<>();

        for(int inv = 0 ; inv <  inverters.size(); inv++){

            LineDataSet tempSet = new LineDataSet(inverters.get(inv), graphData.getInverters().get(inv).getKey());
            tempSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            tempSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            int color = rgb(getRandom(120, 240), getRandom(50, 240), getRandom(20, 240));

            tempSet.setColor(color);
            tempSet.setCircleColor(color);
            tempSet.setFillColor(color);
            tempSet.setValueTextColor(color);

            lineDataSetInverterArray.add(tempSet);


          //  LineData d2 = new LineData(lineDataSetInverterArray.get(inv),lineDataSetInverterArray.get(inv),lineDataSetInverterArray.get(inv));

           // lineDatas.get(inv).addDataSet();

            d.addDataSet(lineDataSetInverterArray.get(inv));




        }

        data.setData(d);


        binding.combinedChart.setData(data);
        binding.combinedChart.invalidate();



//        LineDataSet dataSet = new LineDataSet(entries, "Label");
//        dataSet.setHighlightEnabled(true); // allow highlighting for DataSet
//
//        // set this to false to disable the drawing of highlight indicator (lines)
//        dataSet.setDrawHighlightIndicators(true);
        // dataSet.setHighlightColor(Color.BLACK); // color for highlight indicator

//        LineData lineData = new LineData(dataSet);
//        binding.chart.setData(lineData);
//        binding.chart.invalidate();

    }


    int getRandom(int minimum,int maximum){
      return    minimum + (int)(Math.random() * maximum);
    }

    void refreshPlantEnergyGraph(){

    }
}
