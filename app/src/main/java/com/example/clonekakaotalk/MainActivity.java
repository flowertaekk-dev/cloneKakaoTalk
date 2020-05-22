package com.example.clonekakaotalk;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.utils.expandableListView.ExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listView
        expandableListView = findViewById(R.id.birthday_friends_list);

        // preparing list data
        prepareListData();

        expandableListAdapter = new ExpandableListViewAdapter(this, listDataHeader, listDataChild);

        expandableListView.setAdapter(expandableListAdapter);
    }

    /**
     * create dummy data for now.
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Friends with Birthdays");

        // Adding child data
        List<String> firendsWithBirthDays = new ArrayList<String>();
        firendsWithBirthDays.add("dummy friends"); // it should be profile component

        listDataChild.put(listDataHeader.get(0), firendsWithBirthDays); // Header, Child data
    }
}
