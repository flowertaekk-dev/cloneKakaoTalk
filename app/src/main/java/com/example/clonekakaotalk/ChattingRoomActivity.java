package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;
import com.example.clonekakaotalk.utils.defs.Transport;
import com.example.clonekakaotalk.utils.footer.chattingRoom.FragmentChattingRoomBottomMenu;

public class ChattingRoomActivity extends AppCompatActivity {

    // Init
    private Profile _selectedProfile;
    private ImageButton _chattingRoomFooterMenuBtn;
    private FragmentChattingRoomBottomMenu _fragmentChattingRoomBottomMenu = new FragmentChattingRoomBottomMenu();

    // Menu status
    private boolean _isChattingMenuButtonOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);

        // init
        Intent intent = getIntent();
        _initProfileFromParcel(intent);

        _initChattingMenuButtonClicked();
    }

    // ---------------------------------------------------------------------------------------------
    // init

    /**
     * Get selected profile data from parcelable and set Room name
     */
    private void _initProfileFromParcel(Intent intent) {
        // TODO what if null? -> better to make some pattern for DRY??
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            _selectedProfile = bundle.getParcelable(ParcelKeys.CURRENT_SELECTED_PROFILE.name());

            if (_selectedProfile != null) {
                TextView roomNameView = findViewById(R.id.chatting_room_room_name);
                roomNameView.setText(_selectedProfile.getNickname());
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // onClickEventListener

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(Transport.FROM_CHATTING_ROOM.name(), Transport.FROM_CHATTING_ROOM.value());
        startActivity(intent);

    }

    /**
     * Slide up and down the Chatting menu
     */
    private void _initChattingMenuButtonClicked() {
        _chattingRoomFooterMenuBtn = findViewById(R.id.chatting_room_footer_menu_btn);
        _chattingRoomFooterMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Create bottom menu
                if (!_isChattingMenuButtonOn) {
                    _isChattingMenuButtonOn = true;
                    transaction.replace(R.id.chatting_room_footer_bottom_menu, _fragmentChattingRoomBottomMenu).commitAllowingStateLoss();

                // Remove bottom menu
                } else {
                    _isChattingMenuButtonOn = false;
                    transaction.remove(_fragmentChattingRoomBottomMenu).commitAllowingStateLoss();
                }

            }
        });
    }
}