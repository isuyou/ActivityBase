package com.example.isuyo_000.activities.Graph;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.isuyo_000.activities.R;
import com.example.isuyo_000.activities.UserData.User;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by McLovin on 9/7/2017.
 */

public class GraphAdapter  extends ArrayAdapter<Coordinates> {

    //selectedIndex default value -1 represents no Index is selected
    Integer selectedIndex = -1;
    Integer lastSelectedIndex = -1;
    Integer minX = 0;
    Integer minY = 0;
    Integer range = 1;
    Integer domain = -1;
    Float rangeDivisor = 100f;
    Float domainDivisor = 50f;


    //storage internal data
    List<Coordinates> data;
    Context context;
    GraphView graph;
    View result;

    public GraphAdapter(List<Coordinates> data, Context context){
        super(context, R.layout.channel_line, data);
        this.data = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Coordinates points = getItem(position);

        result = convertView;

        if(convertView == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            result = vi.inflate(R.layout.channel_line, null);
        }

        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        TextView userNum = (TextView) result.findViewById(R.id.userChannelID);
        userNum.setText("" + (position + 1));



        if(points != null){
            graph = (GraphView) result.findViewById(R.id.graphPiece);
            LineGraphSeries lineGraph = new LineGraphSeries<DataPoint>();
            DataPoint currentPoint;


            //insert points

            double x;
            double y;

            for (int i = 0; i <= 5; i++) {
                x = i;
                y = (Math.sin(x) + 1) /2;
                currentPoint = new DataPoint(x, y);
                dataPoints.add(currentPoint);
                lineGraph.appendData(currentPoint, true, 500);
                x = x + 1;
                if(i > domain)
                    domain = i;
            }
            //draw points
            drawPoints(lineGraph);
        }

        return result;
    }

    /**draw points onto graph
     *  @param lineGraph : the series of data points to draw (specifically for a line graph)
     */
    private void drawPoints(LineGraphSeries lineGraph){
        lineGraph.setDrawDataPoints(true);
        if(graph == null)
            graph = (GraphView) result.findViewById(R.id.graphPiece);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(minX);
        graph.getViewport().setMaxX(domain + minX);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(minY);
        graph.getViewport().setMaxY(range + minY);
        graph.addSeries(lineGraph);
        graph.refreshDrawableState();
    }
}
