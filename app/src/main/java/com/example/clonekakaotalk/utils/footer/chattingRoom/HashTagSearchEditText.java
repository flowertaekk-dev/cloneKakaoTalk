package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

public class HashTagSearchEditText extends androidx.appcompat.widget.AppCompatEditText {

    public HashTagSearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (SharedChattingRoomViewData.isSearchWithHashOn()) {

                ImageButton chattingRoomEmoticonMenuBtn = SharedChattingRoomViewData.chattingRoomEmoticonMenuBtn();
                ImageButton searchWithHashTagBtn = SharedChattingRoomViewData.searchWithHashTagBtn();
                ImageButton hashTagIcon = SharedChattingRoomViewData.hashTagIcon();

                // show emoticon and hash tag btn
                chattingRoomEmoticonMenuBtn.setVisibility(View.VISIBLE);
                searchWithHashTagBtn.setVisibility(View.VISIBLE);

                // hide hash tag icon in front of Edittext
                hashTagIcon.setVisibility(View.GONE);

                SharedChattingRoomViewData.setSearchWithHashOnFlag(false);
                return true;
            }
        }

        return false;
    }
}
