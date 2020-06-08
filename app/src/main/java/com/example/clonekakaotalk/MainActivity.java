package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clonekakaotalk.utils.defs.Transport;
import com.example.clonekakaotalk.utils.footer.FragmentChattingList;
import com.example.clonekakaotalk.utils.footer.FragmentFriendsList;
import com.example.clonekakaotalk.utils.footer.FragmentMore;
import com.example.clonekakaotalk.utils.footer.FragmentNews;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // FOOTER
    private FragmentManager _fragmentManager = getSupportFragmentManager();
    private FragmentFriendsList _fragmentFriendsList = new FragmentFriendsList();
    private FragmentChattingList _fragmentChattingList = new FragmentChattingList();
    private FragmentNews _fragmentNews = new FragmentNews();
    private FragmentMore _fragmentMore = new FragmentMore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize
        FragmentTransaction transaction = _fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame_layout, _fragmentFriendsList).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_footer);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        bottomNavigationView.setItemIconTintList(null); // To stop changing color when clicked
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initWithChatsScreen(getIntent()); // When comes from ChattingRoom
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        // kill this app completely (with three of them)
        moveTaskToBack(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAndRemoveTask();
        }
        Process.killProcess(Process.myPid());
    }

    // ---------------------------------------------------------------------------------------------
    // init
    private void initWithChatsScreen(Intent intent) {
        FragmentTransaction transaction = _fragmentManager.beginTransaction();
        if (intent != null) {
            boolean isFromChattingRoom = intent.getBooleanExtra(Transport.FROM_CHATTING_ROOM.name(), false);
            if (isFromChattingRoom) {
                transaction.replace(R.id.main_frame_layout, _fragmentChattingList).commitAllowingStateLoss();
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // FOOTER

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = _fragmentManager.beginTransaction();

            switch (menuItem.getItemId()) {
                case R.id.friends_list_item:
                    transaction.replace(R.id.main_frame_layout, _fragmentFriendsList).commitAllowingStateLoss();
                    break;
                case R.id.chatting_list_item:
                    transaction.replace(R.id.main_frame_layout, _fragmentChattingList).commitAllowingStateLoss();
                    break;
                case R.id.news_item:
                    transaction.replace(R.id.main_frame_layout, _fragmentNews).commitAllowingStateLoss();
                    break;
                case R.id.more_item:
                    transaction.replace(R.id.main_frame_layout, _fragmentMore).commitAllowingStateLoss();
                    break;
            }

            return true;
        }
    }
}