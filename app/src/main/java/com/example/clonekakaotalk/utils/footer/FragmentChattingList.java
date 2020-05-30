package com.example.clonekakaotalk.utils.footer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clonekakaotalk.R;

public class FragmentChattingList extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View chattingListView = inflater.inflate(R.layout.fragment_chatting_list, container, false);

        // set title on Header
        TextView headerTitleView = chattingListView.findViewById(R.id.header_title);
        FooterFragment.CHATTING_LIST.setTitleOnHeader(headerTitleView);

        return chattingListView;
    }
    
}
