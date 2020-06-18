package com.example.clonekakaotalk.utils.footer.chattingRoom;

import android.widget.ImageButton;

public class SharedChattingRoomViewData {

    private static ImageButton _chattingRoomEmoticonMenuBtn;
    private static ImageButton _searchWithHashTagBtn;
    private static ImageButton _hashTagIcon;
    private static boolean _isSearchWithHashOn;

    public static void setChattingRoomEmoticonMenuBtn(ImageButton chattingRoomEmoticonMenuBtn) {
        SharedChattingRoomViewData._chattingRoomEmoticonMenuBtn = chattingRoomEmoticonMenuBtn;
    }
    public static ImageButton chattingRoomEmoticonMenuBtn() {
        return _chattingRoomEmoticonMenuBtn;
    }

    public static void setSearchWithHashTagBtn(ImageButton searchWithHashTagBtn) {
        SharedChattingRoomViewData._searchWithHashTagBtn = searchWithHashTagBtn;
    }
    public static ImageButton searchWithHashTagBtn() {
        return _searchWithHashTagBtn;
    }

    public static void setHashTagIcon(ImageButton hashTagIcon) {
        SharedChattingRoomViewData._hashTagIcon = hashTagIcon;
    }
    public static ImageButton hashTagIcon() {
        return _hashTagIcon;
    }

    public static boolean isSearchWithHashOn() {
        return _isSearchWithHashOn;
    }
    public static void setSearchWithHashOnFlag(boolean isSearchWithHashOn) {
        SharedChattingRoomViewData._isSearchWithHashOn = isSearchWithHashOn;
    }
}
