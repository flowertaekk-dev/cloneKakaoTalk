package com.example.clonekakaotalk.utils.footer.chattingRoom;

public class SharedChattingRoomViewData {

    private static boolean _isSearchWithHashOn;
    private static boolean _isSideBarOn;

    public static boolean isSearchWithHashOn() {
        return _isSearchWithHashOn;
    }
    public static void setSearchWithHashOnFlag(boolean isSearchWithHashOn) {
        SharedChattingRoomViewData._isSearchWithHashOn = isSearchWithHashOn;
    }

    public static boolean isSideBarOn() {
        return _isSideBarOn;
    }
    public static void setSideBarOn(boolean isSideBarOn) {
        SharedChattingRoomViewData._isSideBarOn = isSideBarOn;
    }

    public static boolean isOn() {
        return _isSearchWithHashOn || _isSideBarOn;
    }
}
