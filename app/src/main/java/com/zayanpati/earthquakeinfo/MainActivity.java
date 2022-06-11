package com.zayanpati.earthquakeinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<Info> information = new ArrayList<>();
//
//        information.add(new Info("San Francisco", "7.2", "Feb 2, 2016"));
//        information.add(new Info("London", "6.1", "July 10, 2015"));
//        information.add(new Info("Tokyo", "3.9", "Nov 10, 2014"));
//        information.add(new Info("Mexico City", "5.4", "May 3, 2014"));
//        information.add(new Info("Moscow", "2.8", "Jan 31, 2013"));
//        information.add(new Info("Rio De Janeiro", "4.9", "Aug 19, 2012"));
//        information.add(new Info("Paris", "1.6", "Oct 30, 2011"));

        ArrayList<Info> earthquakes = QueryUtils.extractEarthquakes();
        ListView earthquakeListView = findViewById(R.id.list);

        CustomAdapter adapter = new CustomAdapter(this, earthquakes);
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Info currentInfo = adapter.getItem(position);

                Intent intent = new Intent();
                Uri infoUri = Uri.parse(currentInfo.getUrl());
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(infoUri);
                startActivity(intent);

            }
        });
    }
}