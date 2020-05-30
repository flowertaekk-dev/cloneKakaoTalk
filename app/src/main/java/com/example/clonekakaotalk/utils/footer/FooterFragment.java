package com.example.clonekakaotalk.utils.footer;

import android.widget.TextView;

public enum FooterFragment {

    FRIENDS_LIST("Friends"),
    CHATTING_LIST("Chats"),
    NEWS(null),
    MORE("More"),
    ;

    private String title;

    FooterFragment(String title) {
        this.title = title;
    }

    public void setTitleOnHeader(TextView titleView) {
        titleView.setText(getTitle());
    }

    public String getTitle() {
        return this.title;
    }

}
