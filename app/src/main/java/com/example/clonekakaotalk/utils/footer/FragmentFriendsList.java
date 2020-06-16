package com.example.clonekakaotalk.utils.footer;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clonekakaotalk.ProfileDetailActivity;
import com.example.clonekakaotalk.R;
import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;
import com.example.clonekakaotalk.utils.expandableListView.ProfileExpandableListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentFriendsList extends Fragment {

    public static String CURRNET_SELECTED_PROFILE = "PROFILE_FROM_FRIENDS_LIST_ACTIVITY";

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

        // init my profile
        _initializeMyProfile(friendsListView);

        // get Friends list view
        _profileExpandableListView = friendsListView.findViewById(R.id.friends_list);

        _initializeFriendsList(friendsListView);

        return friendsListView;
    }

    /**
     * Initialize my profile
     */
    private void _initializeMyProfile(View friendsListView) {

        // create dummy for now
        Profile profile = Profile.builder()
                .nickname(";")
                .profileName("Code Poet")
                .build();

        TextView textViewNickName = friendsListView.findViewById(R.id.my_nickname);
        textViewNickName.setText(profile.getNickname());

        TextView textViewProfileName = friendsListView.findViewById(R.id.my_profile_name);
        textViewProfileName.setText(profile.getProfileName());

        // setOnClickListener event
        LinearLayout profileContainer = friendsListView.findViewById(R.id.friends_list_my_profile);
        profileContainer.setOnClickListener(new ExpandableListView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProfileDetailActivity.class);
                intent.putExtra(ParcelKeys.CURRENT_SELECTED_PROFILE.name(), profile);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                getContext().startActivity(intent);
            }
        });
    }

    /**
     * Initialize Friends list
     */
    private void _initializeFriendsList(View friendsListView) {

        // set indicator to right side.
        _setIndicatorToRightSide();

        // preparing list data
        _prepareListData();

        _profileExpandableListAdapter = new ProfileExpandableListViewAdapter(friendsListView.getContext(), _listDataHeader, _listDataChild);

        _profileExpandableListView.setAdapter(_profileExpandableListAdapter);
    }

    /**
     * set ExpandableListView's indicator to right side.
     */
    private void _setIndicatorToRightSide() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Resources r = getResources();
        int px = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics());
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            _profileExpandableListView.setIndicatorBounds(width - px, width);
        } else {
            _profileExpandableListView.setIndicatorBoundsRelative(width - px, width);
        }
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
        friendsWithBirthDays.add(Profile.builder().profileName("Hello world!").nickname(("birthday friend1")).build());
        friendsWithBirthDays.add(Profile.builder().profileName("Hello world!").nickname(("birthday friend2")).build());

        List<Profile> favorites = new ArrayList<>();
        favorites.add(Profile.builder().profileName("Happy birthday").nickname(("favorite friend")).build());

        List<Profile> channel = new ArrayList<>();
        channel.add(Profile.builder().profileName("Welcome!").nickname(("Channel dummy")).build());

        List<Profile> friends = new ArrayList<>();
        friends.add(Profile.builder().profileName("Stay humble!").nickname(("friend1")).build());
        friends.add(Profile.builder().profileName("move move!").nickname(("friend2")).build());
        friends.add(Profile.builder().profileName("Cheer up!").nickname(("friend3")).build());

        _listDataChild.put(_listDataHeader.get(0), friendsWithBirthDays); // Header, Child data
        _listDataChild.put(_listDataHeader.get(1), favorites); // Header, Child data
        _listDataChild.put(_listDataHeader.get(2), channel); // Header, Child data
        _listDataChild.put(_listDataHeader.get(3), friends); // Header, Child data
    }

}
