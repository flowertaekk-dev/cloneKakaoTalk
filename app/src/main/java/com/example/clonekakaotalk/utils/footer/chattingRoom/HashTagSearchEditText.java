package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.example.clonekakaotalk.ChattingRoomActivity;

public class HashTagSearchEditText extends androidx.appcompat.widget.AppCompatEditText {

    public HashTagSearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (SharedChattingRoomViewData.isSearchWithHashOn()) {
                ChattingRoomActivity.ObjectStorage.switchHashTagSearchMode(false);
                SharedChattingRoomViewData.setSearchWithHashOnFlag(false);
                return false;
            }
        }

        return false;
    }
}
