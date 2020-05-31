package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.footer.FragmentFriendsList;

public class ProfileDetailActivity extends AppCompatActivity {

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
            Profile profile = bundle.getParcelable(FragmentFriendsList.PROFILE_FROM_FRIENDS_LIST_ACTIVITY);

            if (intent != null) {
                TextView profileNickNameView = findViewById(R.id.profile_detail_profile_nickname);
                profileNickNameView.setText(profile.getNickname());
            }
        }
    }
}
