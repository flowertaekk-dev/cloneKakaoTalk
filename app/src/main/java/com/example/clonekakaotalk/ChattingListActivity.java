package com.example.clonekakaotalk;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.utils.onclickhandler.FooterOnclickHandler;

public class ChattingListActivity extends AppCompatActivity {

    TextView headerText;
    ImageButton addChatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_list);

        //  set header title
        headerText = findViewById(R.id.header_title);
        headerText.setText("Chats");

        // change 'ADD USER' to 'ADD CHAT'
        // TODO seems to need to think about button id. 'header_add_user_btn' might cause confusion.
        addChatButton = findViewById(R.id.header_add_user_btn);

        String chatUri = "@android:drawable/sym_action_chat";
        int chatImageResource = getResources().getIdentifier(chatUri, null, getPackageName());
        Drawable chatImageDrawable = getResources().getDrawable(chatImageResource);

        addChatButton.setImageDrawable(chatImageDrawable);

        initFooterImage();

    }

    // TODO DRY!!!!!! How?
    private void initFooterImage() {
        // footer image change. (I do not want to waste of my energy finding the image. so I just use number.
        Button friendsListButton = findViewById(R.id.friends_list_button);
        Button chattingListButton = findViewById(R.id.chatting_list_button);
        Button newsButton = findViewById(R.id.news_button);
        Button moreButton = findViewById(R.id.more_button);

        FooterOnclickHandler.FooterButton
                .CHATTING_LIST_BUTTON
                .updateFooterImage(friendsListButton, chattingListButton, newsButton, moreButton);
    }
}
