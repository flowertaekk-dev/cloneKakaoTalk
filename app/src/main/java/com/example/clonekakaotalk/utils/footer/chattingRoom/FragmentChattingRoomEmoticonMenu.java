package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.os.Bundle;
import android.util.TypedValue;
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

        // retrieve container layout
        LinearLayout parent = emoticonMenuView.findViewById(R.id.chatting_room_emoticon_menu_container);

        // create two lines for emoticon as dummy
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        for (int j=0; j<4; j++) {
            ImageButton imageButton = new ImageButton(getContext());
            imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);

            imageButton.setImageResource(R.drawable.fragment_chatting_list_icon);

            imageButton.setLayoutParams(getParamForImageButton());

            linearLayout.addView(imageButton);
        }

        parent.addView(linearLayout);

        return emoticonMenuView;
    }

    /**
     * Return param for ImageButton for bottom menu
     * TODO DRY!!!!!!!!!!!!!!!!!!!!!!!!
     */
    private LinearLayout.LayoutParams getParamForImageButton() {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param.weight = 1;
        param.width = R.attr.actionBarSize;

        // set height in dp
        int iconHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, getResources().getDisplayMetrics());
        param.height = iconHeight;

        return param;
    }
}