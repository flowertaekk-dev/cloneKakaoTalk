package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.LinearLayout;

import com.example.clonekakaotalk.R;

public class ChattingRoomMenuUtil {

    /**
     * Return param for ImageButton for bottom menu
     */
    public static LinearLayout.LayoutParams getParamForImageButton(Resources resources) {
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        param.weight = 1;
        param.width = R.attr.actionBarSize;

        // set height in dp
        int iconHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 90, resources.getDisplayMetrics());
        param.height = iconHeight;

        return param;
    }
}
