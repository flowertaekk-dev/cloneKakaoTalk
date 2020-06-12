package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.clonekakaotalk.R;

public class FragmentChattingRoomEmoticonMenu extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View emoticonMenuView = inflater.inflate(R.layout.fragment_chatting_room_emoticon_menu, container, false);

        _initEmoticonCategories(emoticonMenuView);
        _iniCurrentEmoticon(emoticonMenuView);

        return emoticonMenuView;
    }

    // ---------------------------------------------------------------------------------------------
    // INIT

    private void _iniCurrentEmoticon(View emoticonMenuView) {
        // retrieve container layout
        LinearLayout container = emoticonMenuView.findViewById(R.id.chatting_room_emoticon_menu_container);

        // create two lines for emoticon as dummy
        LinearLayout emoticonRow = new LinearLayout(getContext());
        emoticonRow.setOrientation(LinearLayout.HORIZONTAL);

        for (int j=0; j<4; j++) {
            ImageButton imageButton = new ImageButton(getContext());
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageButton.setImageResource(R.drawable.fragment_chatting_list_icon);

            imageButton.setLayoutParams(ChattingRoomMenuUtil.getParamForImageButton(getResources()));

            emoticonRow.addView(imageButton);
        }

        container.addView(emoticonRow);
    }

    private void _initEmoticonCategories(View emoticonMenuView) {
        // retrieve container view
        LinearLayout container = emoticonMenuView.findViewById(R.id.chatting_room_emoticon_categories_inner_container);

        // create dummy for now. ;D
        for (int i=0; i<10; i++) {
            ImageButton imageButton = new ImageButton(getContext());
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageButton.setImageResource(R.drawable.fragment_chatting_list_icon);

            container.addView(imageButton);
        }

    }
}