package com.example.clonekakaotalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;
import com.example.clonekakaotalk.utils.defs.Transport;
import com.example.clonekakaotalk.utils.footer.chattingRoom.FragmentChattingRoomBottomMenu;
import com.example.clonekakaotalk.utils.footer.chattingRoom.FragmentChattingRoomEmoticonMenu;
import com.example.clonekakaotalk.utils.windowSoftware.WindowSoft;

import lombok.Getter;

public class ChattingRoomActivity extends AppCompatActivity {

    // Init
    private Profile _selectedProfile;
    private ImageButton _chattingRoomFooterMenuBtn;
    private EditText _chattingInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);

        // init
        Intent intent = getIntent();
        _initProfileFromParcel(intent);

        _initChattingMenuButtonClicked();
        _initEmoticonMenuButtonClicked();

        _initChattingInputEditText();
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

            if (bundle != null) {
                _selectedProfile = bundle.getParcelable(ParcelKeys.CURRENT_SELECTED_PROFILE.name());

                if (_selectedProfile != null) {
                    TextView roomNameView = findViewById(R.id.chatting_room_room_name);
                    roomNameView.setText(_selectedProfile.getNickname());
                }
            }
        }
    }

    // ---------------------------------------------------------------------------------------------
    // onClickEventListener

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        // remove BottomMenu
        if (Buttons.isAnyMenuActivated()) {
            Buttons.removeAllMenu(getSupportFragmentManager().beginTransaction());
            return;
        }

        // go to ChattingList
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
                WindowSoft.hideKeyboardFrom(getApplicationContext(), _chattingInputEditText);
                Buttons.BOTTOM_MENU_BUTTON.showAndRemoveMenuByClickingButton(getSupportFragmentManager().beginTransaction());
            }
        });
    }

    /**
     * Slide up and down the Emoticon menu
     */
    private void _initEmoticonMenuButtonClicked() {
        _chattingRoomFooterMenuBtn = findViewById(R.id.chatting_room_footer_emoticon_btn);
        _chattingRoomFooterMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowSoft.hideKeyboardFrom(getApplicationContext(), _chattingInputEditText);
                Buttons.EMOTICON_MENU_BUTTON.showAndRemoveMenuByClickingButton(getSupportFragmentManager().beginTransaction());
            }
        });
    }


    // ---------------------------------------------------------------------------------------------
    // OnTouchListener

    private void _initChattingInputEditText() {
        _chattingInputEditText = findViewById(R.id.chatting_room_footer_chatting_input);

        _chattingInputEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // remove BottomMenu
                if (Buttons.isAnyMenuActivated()) {
                    Buttons.removeAllMenu(getSupportFragmentManager().beginTransaction());
                    _chattingInputEditText.requestFocus();
                }
                return false;
            }
        });
    }

    // ---------------------------------------------------------------------------------------------
    // UTIL

    @Getter
    private enum Buttons {
        BOTTOM_MENU_BUTTON (false, new FragmentChattingRoomBottomMenu()),
        EMOTICON_MENU_BUTTON (false, new FragmentChattingRoomEmoticonMenu()),
        ;

        private boolean isOn;           // isClicked?
        private Fragment fragment;
        Buttons(boolean isOn, Fragment fragment) {
            this.isOn = isOn;
            this.fragment = fragment;
        }

        private void setButtonStatus(boolean on) {
            isOn = on;
        }

        /**
         * Update buttons' status <br />
         *
         * <ul>
         *     <li>clicked button -> ON</li>
         *     <li>other buttons -> OFF</li>
         * </ul>
         */
        private void updateButtonStatus() {

            // If the same button clicked
            if (isOn()) {
                setButtonStatus(false);
                return;
            }

            for (Buttons button : Buttons.values()) {
                // update clicked button to On
                if (button == this) {
                    setButtonStatus(true);
                    continue;
                }

                // set others to false
                button.setButtonStatus(false);
            }
        }

        /**
         * Activate and deactivate menus
         */
        public void showAndRemoveMenuByClickingButton(FragmentTransaction transaction) {
            updateButtonStatus();

            if (isOn()) {
                transaction.replace(R.id.chatting_room_footer_bottom_menu, getFragment()).commitAllowingStateLoss();
            } else {
                transaction.remove(getFragment()).commitAllowingStateLoss();
            }

        }

        /**
         * Deactivate all menus
         */
        public static void removeAllMenu(FragmentTransaction transaction) {

            if (transaction == null)
                return;

            for (Buttons button : values()) {
                if (button.isOn()){
                    transaction.remove(button.getFragment()).commitAllowingStateLoss();
                }
            }
        }

        /**
         * Check if any of menu is activated
         */
        public static boolean isAnyMenuActivated() {
            for (Buttons button : values()) {
                if (button.isOn()){
                    return true;
                }
            }
            return false;
        }

    }

}