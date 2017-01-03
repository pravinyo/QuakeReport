package com.is_great.pro.quakereport;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.graphics.drawable.GradientDrawable;


/**
 * Created by Pravinyo on 12/30/2016.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private final static String STRING_SEPARATOR=" of ";

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes){
        super(context,0,earthquakes);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView=convertView;
        if(listItemView == null){
            listItemView= LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item,parent,false);
        }
        Earthquake currenEarthquake= getItem(position);



        String originalLocaton = currenEarthquake.getPlace();
        String primaryLocation,LocationOffset;

        if(originalLocaton.contains(STRING_SEPARATOR)){
            String[] parts = originalLocaton.split(STRING_SEPARATOR);
            LocationOffset = parts[0]+ STRING_SEPARATOR;
            primaryLocation = parts[1];
        }else{
            LocationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocaton;
        }

        TextView primaryLocationView = (TextView) listItemView.findViewById(R.id.primary_location);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = (TextView) listItemView.findViewById(R.id.location_offset);
        locationOffsetView.setText(LocationOffset);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        Date dateObject = new Date(currenEarthquake.getTimeInMilliSecond());
        String formatedDate = formatDate(dateObject);
        dateTextView.setText(formatedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);


        // Find the TextView with view ID magnitude
        TextView magnitudeView = (TextView) listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = formatMagnitude(currenEarthquake.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currenEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }
    /**
     * definition of formatDate
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
    /**
     * definition of formatTime
     */
    private String formatTime(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    private int getMagnitudeColor(double magnitude){

        int magnitudeResoureId;
        int magnitudeFloor=(int) Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeResoureId=R.color.magnitude1;
                break;
            case 2:
                magnitudeResoureId=R.color.magnitude2;
                break;
            case 3:
                magnitudeResoureId=R.color.magnitude3;
                break;
            case 4:
                magnitudeResoureId=R.color.magnitude4;
                break;
            case 5:
                magnitudeResoureId=R.color.magnitude5;
                break;
            case 6:
                magnitudeResoureId=R.color.magnitude6;
                break;
            case 7:
                magnitudeResoureId=R.color.magnitude7;
                break;
            case 8:
                magnitudeResoureId=R.color.magnitude8;
                break;
            case 9:
                magnitudeResoureId=R.color.magnitude9;
                break;

            default:
                magnitudeResoureId=R.color.magnitude10plus;

        }

        return ContextCompat.getColor(getContext(),magnitudeResoureId);
    }
    /**
     * Return the formatted magnitude string showing 1 decimal place (i.e. "3.2")
     * from a decimal magnitude value.
     */
    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
}
