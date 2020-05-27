package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

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
        setTitle("Friends");

        // get the listView
        profileExpandableListView = findViewById(R.id.friends_list);

        // get current device's width
        int width = getCurrentScreenWidth();

        // set indicator to right side.
        profileExpandableListView.setIndicatorBounds(width - getDipsFromPixel(25), width - getDipsFromPixel(5));

        // preparing list data
        prepareListData();

        profileExpandableListAdapter = new ProfileExpandableListViewAdapter(this, listDataHeader, listDataChild);

        profileExpandableListView.setAdapter(profileExpandableListAdapter);
    }

    /**
     * Retrieve current device's width.
     */
    private int getCurrentScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    //Convert pixel to dip

    /**
     * Convert pixel to dip
     */
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
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
        firendsWithBirthDays.add(new Profile("birthday friend1", "Hello world!"));
        firendsWithBirthDays.add(new Profile("birthday friend2", "Hola world!"));

        List<Profile> favorites = new ArrayList<>();
        favorites.add(new Profile("favorite friend", "Happy birthday"));

        List<Profile> channel = new ArrayList<>();
        channel.add(new Profile("Channel dummy", "Welcome!"));

        List<Profile> friends = new ArrayList<>();
        friends.add(new Profile("friend1", "Stay humble"));
        friends.add(new Profile("friend2", "move move"));
        friends.add(new Profile("friend3", "Cheer up!"));

        listDataChild.put(listDataHeader.get(0), firendsWithBirthDays); // Header, Child data
        listDataChild.put(listDataHeader.get(1), favorites); // Header, Child data
        listDataChild.put(listDataHeader.get(2), channel); // Header, Child data
        listDataChild.put(listDataHeader.get(3), friends); // Header, Child data
    }

    /**
     * Move to Profile view.
     */
    public void profileClicked(View view) {
        TextView textView = view.findViewById(R.id.my_nickname);
        Toast.makeText(this, "Profile clicked : " + textView.getText().toString(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, ProfileDetailActivity.class);
        startActivity(intent);
    }
}
