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

public class FragmentChattingRoomBottomMenu extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View chattingRoomBottomMenu = inflater.inflate(R.layout.fragment_chatting_room_bottom_menu, container, false);

        // retrieve container layout
        LinearLayout parent = chattingRoomBottomMenu.findViewById(R.id.chatting_room_bottom_menu_container);

        // create two lines for menu as dummy
        for (int i=0; i<2; i++) {
            // layout for one line
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int j=0; j<4; j++) {
                ImageButton imageButton = new ImageButton(getContext());
                imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

                if (i == 0){
                    imageButton.setImageResource(R.drawable.fragment_friends_list_icon);
                }
                else {
                    imageButton.setImageResource(R.drawable.fragment_chatting_list_icon);
                }

                imageButton.setLayoutParams(ChattingRoomMenuUtil.getParamForImageButton(getResources()));

                linearLayout.addView(imageButton);
            }

            parent.addView(linearLayout);
        }

        return chattingRoomBottomMenu;
    }

    // ---------------------------------------------------------------------------------------------
    // BOTTOM MENU

}