package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.expandableListView.ProfileExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String PROFILE_FROM_MAIN_ACTIVITY = "PROFILE_FROM_MAIN_ACTIVITY";

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
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding child data
        listDataHeader.add("Friends with Birthdays"); // TODO is it better to make enum or something..?
        listDataHeader.add("Favorites");
        listDataHeader.add("Channel");
        listDataHeader.add("Friends");

        // Adding child data
        List<Profile> friendsWithBirthDays = new ArrayList<>();
        friendsWithBirthDays.add(Profile.builder().profileName("birthday friend1").nickname(("Hello world!")).build());
        friendsWithBirthDays.add(Profile.builder().profileName("birthday friend2").nickname(("Hello world!")).build());

        List<Profile> favorites = new ArrayList<>();
        favorites.add(Profile.builder().profileName("favorite friend").nickname(("Happy birthday")).build());

        List<Profile> channel = new ArrayList<>();
        channel.add(Profile.builder().profileName("Channel dummy").nickname(("Welcome!")).build());

        List<Profile> friends = new ArrayList<>();
        friends.add(Profile.builder().profileName("friend1").nickname(("Stay humble!")).build());
        friends.add(Profile.builder().profileName("friend2").nickname(("move move!")).build());
        friends.add(Profile.builder().profileName("friend3").nickname(("Cheer up!")).build());

        listDataChild.put(listDataHeader.get(0), friendsWithBirthDays); // Header, Child data
        listDataChild.put(listDataHeader.get(1), favorites); // Header, Child data
        listDataChild.put(listDataHeader.get(2), channel); // Header, Child data
        listDataChild.put(listDataHeader.get(3), friends); // Header, Child data
    }

    /**
     * Move to Profile view.
     */
    public void profileClicked(View view) {
        TextView myNickNameView = view.findViewById(R.id.my_nickname);
        TextView myProfileNameView = view.findViewById(R.id.my_profile_name);

        String clickedProfileName = myProfileNameView.getText().toString();
        String clickedNickName = myNickNameView.getText().toString();

        Profile clickedProfile = Profile.builder()
                .profileName(clickedProfileName)
                .nickname(clickedNickName)
                .build();

        Intent intent = new Intent(this, ProfileDetailActivity.class);
        intent.putExtra(PROFILE_FROM_MAIN_ACTIVITY, clickedProfile);
        startActivity(intent);
    }
}
