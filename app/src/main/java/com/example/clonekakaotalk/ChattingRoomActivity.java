package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;

public class ChattingRoomActivity extends AppCompatActivity {

    // Init
    private Profile _selectedProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);

        // init
        Intent intent = getIntent();
        _initProfileFromParcel(intent);
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
            _selectedProfile = bundle.getParcelable(ParcelKeys.CURRNET_SELECTED_PROFILE.name());

            if (_selectedProfile != null) {
                TextView roomNameView = findViewById(R.id.chatting_room_room_name);
                roomNameView.setText(_selectedProfile.getNickname());
            }
        }

    }
}