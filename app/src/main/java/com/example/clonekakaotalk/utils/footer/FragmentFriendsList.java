package com.example.clonekakaotalk.utils.footer;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clonekakaotalk.R;
import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.expandableListView.ProfileExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentFriendsList extends Fragment {

    public static String PROFILE_FROM_FRIENDS_LIST_ACTIVITY = "PROFILE_FROM_FRIENDS_LIST_ACTIVITY";

    private ExpandableListAdapter _profileExpandableListAdapter;
    private ExpandableListView _profileExpandableListView;
    private List<String> _listDataHeader;
    private HashMap<String, List<Profile>> _listDataChild;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View friendsListView = inflater.inflate(R.layout.fragment_friends_list, container, false);

        // set title on Header
        TextView headerTitleView = friendsListView.findViewById(R.id.header_title);
        FooterFragment.FRIENDS_LIST.setTitleOnHeader(headerTitleView);

        // get Friends list view
        _profileExpandableListView = friendsListView.findViewById(R.id.friends_list);

        _initializeFriendsList(friendsListView);

        return friendsListView;
    }

    /**
     * Initialize Friends list
     */
    private void _initializeFriendsList(View friendsListView) {
        // get current device's width
        int width = _getCurrentScreenWidth();

        // set indicator to right side.
        // _profileExpandableListView.setIndicatorBounds(width - _getDipsFromPixel(25), width - _getDipsFromPixel(5));

        // preparing list data
        _prepareListData();

        _profileExpandableListAdapter = new ProfileExpandableListViewAdapter(friendsListView.getContext(), _listDataHeader, _listDataChild);

        _profileExpandableListView.setAdapter(_profileExpandableListAdapter);
    }

    /**
     * Retrieve current device's width.
     */
    private int _getCurrentScreenWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    //Convert pixel to dip

    /**
     * Convert pixel to dip
     */
    private int _getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    /**
     * create dummy data for now.
     */
    private void _prepareListData() {
        _listDataHeader = new ArrayList<>();
        _listDataChild = new HashMap<>();

        // Adding child data
        _listDataHeader.add("Friends with Birthdays"); // TODO is it better to make enum or something..?
        _listDataHeader.add("Favorites");
        _listDataHeader.add("Channel");
        _listDataHeader.add("Friends");

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

        _listDataChild.put(_listDataHeader.get(0), friendsWithBirthDays); // Header, Child data
        _listDataChild.put(_listDataHeader.get(1), favorites); // Header, Child data
        _listDataChild.put(_listDataHeader.get(2), channel); // Header, Child data
        _listDataChild.put(_listDataHeader.get(3), friends); // Header, Child data
    }

}
