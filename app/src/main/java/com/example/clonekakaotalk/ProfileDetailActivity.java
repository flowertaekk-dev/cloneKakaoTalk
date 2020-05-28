package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.domain.Profile;

public class ProfileDetailActivity extends AppCompatActivity {

    public static String PROFILE_FROM_MAIN_ACTIVITY = "PROFILE_FROM_MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);

        Intent intent = getIntent();
        initProfileDataFromIntent(intent);
    }

    /**
     * initialize profile data with parcel.
     */
    private void initProfileDataFromIntent(Intent intent) {
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            Profile profile = bundle.getParcelable(PROFILE_FROM_MAIN_ACTIVITY);

            if (intent != null) {
                TextView profileNickNameView = findViewById(R.id.profile_detail_profile_nickname);
                profileNickNameView.setText(profile.getProfileName());
            }
        }
    }
}
