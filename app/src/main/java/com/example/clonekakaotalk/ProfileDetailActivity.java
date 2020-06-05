package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;

public class ProfileDetailActivity extends AppCompatActivity {

    // Init
    private Profile _selectedProfile;

    // Button
    private ImageButton _goBackButton;
    private LinearLayout _freeChatButton; // work as button.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        // init
        Intent intent = getIntent();
        _initProfileDataFromIntent(intent);

        _initGoBackButton();            // Go to previous screen
        _initGoToFreeChatButton();      // Go to FreeChat
    }

    // ---------------------------------------------------------------------------------------------
    // Init

    /**
     * initialize profile data with parcel.
     */
    private void _initProfileDataFromIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            _selectedProfile = bundle.getParcelable(ParcelKeys.CURRNET_SELECTED_PROFILE.name());

            if (_selectedProfile != null) {
                TextView profileNickNameView = findViewById(R.id.profile_detail_profile_nickname);
                profileNickNameView.setText(_selectedProfile.getNickname());
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // OnClickedHandler

    /**
     * Go back to previous screen.
     */
    private void _initGoBackButton() {
        _goBackButton = findViewById(R.id.profile_detail_return_button);
        _goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * Move to chatting room
     */
    private void _initGoToFreeChatButton() {
        _freeChatButton = findViewById(R.id.profile_detail_chatting_container);
        _freeChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChattingRoomActivity.class);
                intent.putExtra(ParcelKeys.CURRNET_SELECTED_PROFILE.name(), _selectedProfile);
                startActivity(intent);
            }
        });
    }
}
