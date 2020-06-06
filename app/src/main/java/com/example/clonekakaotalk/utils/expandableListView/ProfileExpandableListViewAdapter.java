package com.example.clonekakaotalk.utils.expandableListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clonekakaotalk.ProfileDetailActivity;
import com.example.clonekakaotalk.R;
import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;

import java.util.HashMap;
import java.util.List;

public class ProfileExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Profile>> _listDataChild;

    public ProfileExpandableListViewAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Profile>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public Profile getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Profile profile = getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView textViewNickName = convertView.findViewById(R.id.my_nickname);
        textViewNickName.setText(profile.getNickname());

        TextView textViewProfileName = convertView.findViewById(R.id.my_profile_name);
        textViewProfileName.setText(profile.getProfileName());

        // setOnClickListener event
        LinearLayout profileContainer = convertView.findViewById(R.id.profile_container);
        profileContainer.setOnClickListener(new ExpandableListView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ProfileDetailActivity.class);
                intent.putExtra(ParcelKeys.CURRENT_SELECTED_PROFILE.name(), profile);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                _context.startActivity(intent);
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        String currentHeader = this._listDataHeader.get(groupPosition);

        // if there is not a friend registered
        if (this._listDataChild.get(currentHeader) == null) {
            return 0;
        }

        return this._listDataChild.get(currentHeader).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.list_view_header);
        // lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
