package com.example.clonekakaotalk;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.expandableListView.ProfileExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExpandableListAdapter profileExpandableListAdapter;
    ExpandableListView profileExpandableListView;
    List<String> listDataHeader;
    HashMap<String, List<Profile>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listView
        profileExpandableListView = findViewById(R.id.birthday_friends_list);

        // preparing list data
        prepareListData();

        profileExpandableListAdapter = new ProfileExpandableListViewAdapter(this, listDataHeader, listDataChild);

        profileExpandableListView.setAdapter(profileExpandableListAdapter);
    }

    /**
     * create dummy data for now.
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Profile>>();

        // Adding child data
        listDataHeader.add("Friends with Birthdays"); // TODO is it better to make enum or something..?
        listDataHeader.add("Favorites");
        listDataHeader.add("Channel");
        listDataHeader.add("Friends");

        // Adding child data
        List<Profile> firendsWithBirthDays = new ArrayList<>();
        firendsWithBirthDays.add(new Profile("friend1", "Hello world!"));
        firendsWithBirthDays.add(new Profile("friend2", "Hola world!"));

        listDataChild.put(listDataHeader.get(0), firendsWithBirthDays); // Header, Child data
    }
}
