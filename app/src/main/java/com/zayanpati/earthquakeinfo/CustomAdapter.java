package com.zayanpati.earthquakeinfo;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;


public class CustomAdapter extends ArrayAdapter<Info> {

    public static final String LOCATION_SEPERATOR = " of ";
    public CustomAdapter(Context context, ArrayList<Info> information) {
        super(context, 0, information);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_info, parent, false);
        Info currentInfo = getItem(position);

        String fullLocation = currentInfo.getLocation();
        String locationHalf1;
        String locationHalf2;

        if(fullLocation.contains(LOCATION_SEPERATOR)){
            String [] parts = fullLocation.split(" of ");
            locationHalf1 = parts[0] + LOCATION_SEPERATOR;
            locationHalf2 = parts[1];
        }
        else{
            locationHalf1 = getContext().getString(R.string.near_the);
            locationHalf2 = fullLocation;
        }

        TextView locationOffset = listItemView.findViewById(R.id.locationOffset);
        locationOffset.setText(locationHalf1);

        TextView location = listItemView.findViewById(R.id.location);
        location.setText(locationHalf2);

        TextView magnitude = listItemView.findViewById(R.id.magnitude);
        String formattedMagnitude = formatMagnitude(currentInfo.getMagnitude());
        magnitude.setText(formattedMagnitude);

        Date dateObject = new Date(currentInfo.getTimeInMilliSeconds());

        TextView date = listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView time =  listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        time.setText(formattedTime);

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentInfo.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);



        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
