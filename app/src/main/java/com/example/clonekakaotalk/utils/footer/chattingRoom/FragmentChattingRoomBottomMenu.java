package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.clonekakaotalk.R;

public class FragmentChattingRoomBottomMenu extends Fragment {

    public FragmentChattingRoomBottomMenu() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chatting_room_bottom_menu, container, false);
    }
}