package com.example.clonekakaotalk;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.clonekakaotalk.domain.Profile;
import com.example.clonekakaotalk.utils.defs.ParcelKeys;
import com.example.clonekakaotalk.utils.defs.Transport;
import com.example.clonekakaotalk.utils.footer.chattingRoom.FragmentChattingRoomBottomMenu;
import com.example.clonekakaotalk.utils.footer.chattingRoom.FragmentChattingRoomEmoticonMenu;
import com.example.clonekakaotalk.utils.footer.chattingRoom.HashTagSearchEditText;
import com.example.clonekakaotalk.utils.footer.chattingRoom.SharedChattingRoomViewData;
import com.example.clonekakaotalk.utils.listViewAdapter.ChatListViewAdapter;
import com.example.clonekakaotalk.utils.sqlite.ChattingDatabaseManager;
import com.example.clonekakaotalk.utils.windowSoftware.WindowSoft;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ChattingRoomActivity extends AppCompatActivity {

    // Init
    private Profile _selectedProfile;
    private ImageButton _chattingRoomFooterMenuBtn;
    private ImageButton _chattingRoomEmoticonMenuBtn;
    private ImageButton _searchWithHashTagBtn;
    private ImageButton _sendMessageBtn;
    private ImageButton _hashTagIcon;
    private HashTagSearchEditText _chattingInputEditText;
    private DrawerLayout _mainLayout;
    private ImageButton _sideBarBtn;
    private ListView _chattingList;

    private ChattingDatabaseManager _chattingDatabaseManager;
    private List<Chat> _chatList;
    private ChatListViewAdapter _chattingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting_room);

        // init
        initializeView();

        Intent intent = getIntent();
        _initProfileFromParcel(intent);

        _initChattingMenuButtonClicked();
        _initEmoticonMenuButtonClicked();
        _initSearchWithHashTagButtonClicked();
        _initChattingInputEditText();
        _initSideBarBtn();
        _initSideBar();
        _initSendMessageButton();

        // Database
        this._chattingDatabaseManager = ChattingDatabaseManager.getInstance(this);

        initializeChattingList();

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

    /**
     * Initialize views
     */
    private void initializeView() {
        _chattingRoomFooterMenuBtn = findViewById(R.id.chatting_room_footer_menu_btn);
        _chattingRoomEmoticonMenuBtn = findViewById(R.id.chatting_room_footer_emoticon_btn);
        _searchWithHashTagBtn = findViewById(R.id.chatting_room_footer_search_with_hashtag_btn);
        _sendMessageBtn = findViewById(R.id.chatting_room_footer_send_message_btn);
        _chattingInputEditText = findViewById(R.id.chatting_room_footer_chatting_input);
        _hashTagIcon = findViewById(R.id.chatting_room_footer_hashtag_icon);
        _mainLayout = findViewById(R.id.chatting_room_main_container);
        _sideBarBtn = findViewById(R.id.chatting_room_side_bar_btn);


        ChattingObjectStorage.setContext(getApplicationContext());
        ChattingObjectStorage.setFragmentManager(getSupportFragmentManager());
        ChattingObjectStorage.setDrawerLayout(_mainLayout);
        ChattingObjectStorage.setChattingInputEditText(_chattingInputEditText);
        ChattingObjectStorage.setChattingRoomEmoticonMenuBtn(_chattingRoomEmoticonMenuBtn);
        ChattingObjectStorage.setSearchWithHashTagBtn(_searchWithHashTagBtn);
        ChattingObjectStorage.setHashTagIcon(_hashTagIcon);
    }

    /**
     * Initialize chattingRoom <br />
     * Because chatting room need to be set with data, it is handled separately.
     */
    private void initializeChattingList() {
        _chattingList = findViewById(R.id.chatting_room_chatting);
        _chatList = new ArrayList<>();

        String [] columns = {"username", "message"};

        Cursor cursor = _chattingDatabaseManager.query(columns, null, null, null);

        if(cursor != null) {
            while (cursor.moveToNext()) {
                _chatList.add(
                        new Chat(
                                cursor.getString(0),
                                cursor.getString(1)));
            }
        }

        _chattingListAdapter = new ChatListViewAdapter(getLayoutInflater(), _chatList);
        _chattingList.setAdapter(_chattingListAdapter);
    }

    // ---------------------------------------------------------------------------------------------
    // onClickEventListener

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        // remove BottomMenu
        if (Buttons.isAnyMenuActivated()) {
            Buttons.removeAllMenu(ChattingObjectStorage.fragmentTransaction());
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
        this._chattingRoomFooterMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedChattingRoomViewData.isSearchWithHashOn()) {
                    ChattingObjectStorage.switchHashTagSearchMode(false);
                }
                WindowSoft.hideKeyboardFrom(getApplicationContext(), _chattingInputEditText);
                Buttons.BOTTOM_MENU_BUTTON.showAndRemoveMenuByClickingButton();
            }
        });
    }

    /**
     * Slide up and down the Emoticon menu
     */
    private void _initEmoticonMenuButtonClicked() {
        this._chattingRoomEmoticonMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowSoft.hideKeyboardFrom(getApplicationContext(), _chattingInputEditText);
                Buttons.EMOTICON_MENU_BUTTON.showAndRemoveMenuByClickingButton();
            }
        });
    }

    /**
     * Open Hash tag search block.
     */
    private void _initSearchWithHashTagButtonClicked() {
        this._searchWithHashTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttons.removeAllMenu(ChattingObjectStorage.fragmentTransaction());

                SharedChattingRoomViewData.setSearchWithHashOnFlag(true);
                ChattingObjectStorage.switchHashTagSearchMode(true);

                // request focus
                _chattingInputEditText.requestFocus();
                WindowSoft.showKeyboardFrom(getApplicationContext(), _chattingInputEditText);
            }
        });
    }

    /**
     * Send message
     */
    private void _initSendMessageButton() {
        _sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = _selectedProfile.getNickname();
                String message = _chattingInputEditText.getText().toString();

                // save message
                updateChatting(nickname, message);

                // dummy for fun
                switch (message) {
                    case "야":
                        updateChatting("friend", "호");
                        break;
                    case "너":
                    case "용":
                        updateChatting("friend", "구리");
                        break;
                    case "hello":
                        updateChatting("android", "world");
                        break;
                    default:
                        // do nothing
                        break;
                }

                // init input(EditText)
                _chattingInputEditText.setText("");
            }
        });
    }

    // ---------------------------------------------------------------------------------------------
    // OnTouchListener

    private void _initChattingInputEditText() {
        this._chattingInputEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // remove BottomMenu
                if (Buttons.isAnyMenuActivated()) {
                    Buttons.removeAllMenu(ChattingObjectStorage.fragmentTransaction());
                    _chattingInputEditText.requestFocus();
                }
                return false;
            }
        });

        this._chattingInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // do nothing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // visualize send button
                if (s.toString().length() > 0) {
                    _searchWithHashTagBtn.setVisibility(View.GONE);
                    _sendMessageBtn.setVisibility(View.VISIBLE);

                // visualize hash tag button
                } else {
                    _searchWithHashTagBtn.setVisibility(View.VISIBLE);
                    _sendMessageBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    // ---------------------------------------------------------------------------------------------
    // SIDE BAR LISTENER

    private void _initSideBarBtn() {
        _sideBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Buttons.SIDE_BAR_BUTTON.showAndRemoveMenuByClickingButton();
            }
        });
    }

    private void _initSideBar() {
        // to stop opening by swipe
        _mainLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    // ---------------------------------------------------------------------------------------------
    // UTIL

    /**
     * Update Chatting
     */
    private void updateChatting(String nickname, String message) {
        ContentValues addMessage = new ContentValues();

        addMessage.put("username", nickname);
        addMessage.put("message", message);

        _chattingDatabaseManager.insert(addMessage);
        _chatList.add(new Chat(nickname, message));

        // update list
        _chattingListAdapter.notifyDataSetChanged();
    }

    /**
     * ObjectStorage for handling buttons in ChattingRoom
     */
    public static class ChattingObjectStorage {
        private static Context _applicationContext;
        private static FragmentManager _fragmentManager;
        private static DrawerLayout _mainLayout;
        private static EditText _chattingInputEditText;
        private static ImageButton _chattingRoomEmoticonMenuBtn;
        private static ImageButton _searchWithHashTagBtn;
        private static ImageButton _hashTagIcon;

        private static void setContext(Context context) {
            _applicationContext = context;
        }
        public static Context applicationContext() {
            return _applicationContext;
        }

        private static void setFragmentManager(FragmentManager fragmentManager) {
            _fragmentManager = fragmentManager;
        }
        public static FragmentTransaction fragmentTransaction() {
            return _fragmentManager.beginTransaction();
        }

        private static void setDrawerLayout(DrawerLayout drawerLayout) {
            _mainLayout = drawerLayout;
        }
        public static DrawerLayout mainLayout() {
            return _mainLayout;
        }

        private static void setChattingInputEditText(EditText chattingInputEditText) {
            _chattingInputEditText = chattingInputEditText;
        }
        public static EditText chattingInputEditText() {
            return _chattingInputEditText;
        }

        private static void setChattingRoomEmoticonMenuBtn(ImageButton chattingRoomEmoticonMenuBtn) {
            _chattingRoomEmoticonMenuBtn = chattingRoomEmoticonMenuBtn;
        }
        public static ImageButton ChattingRoomEmoticonMenuBtn() {
            return _chattingRoomEmoticonMenuBtn;
        }

        private static void setSearchWithHashTagBtn(ImageButton searchWithHashTagBtn) {
            _searchWithHashTagBtn = searchWithHashTagBtn;
        }
        public static ImageButton searchWithHashTagBtn() {
            return _searchWithHashTagBtn;
        }

        private static void setHashTagIcon(ImageButton hashTagIcon) {
            _hashTagIcon = hashTagIcon;
        }
        public static ImageButton hashTagIcon() {
            return _hashTagIcon;
        }

        public static void switchHashTagSearchMode(boolean isOn) {
            if (isOn) {
                // hide emoticon and hash tag btn
                _chattingRoomEmoticonMenuBtn.setVisibility(View.GONE);
                _searchWithHashTagBtn.setVisibility(View.GONE);

                // show hash tag icon in front of Edittext
                _hashTagIcon.setVisibility(View.VISIBLE);
            } else {
                _chattingRoomEmoticonMenuBtn.setVisibility(View.VISIBLE);
                _searchWithHashTagBtn.setVisibility(View.VISIBLE);

                _hashTagIcon.setVisibility(View.GONE);
            }
        }
    }

    @Getter
    private enum Buttons {
        BOTTOM_MENU_BUTTON (false, new FragmentChattingRoomBottomMenu()) {
            @Override
            public void showAndRemoveMenuByClickingButton() {
                updateButtonStatus(this);
                FragmentTransaction transaction = ChattingObjectStorage.fragmentTransaction();

                if (isOn()) {
                    transaction.replace(R.id.chatting_room_footer_bottom_menu, getFragment()).commitAllowingStateLoss();
                } else {
                    transaction.remove(getFragment()).commitAllowingStateLoss();
                }
            }
        },
        EMOTICON_MENU_BUTTON (false, new FragmentChattingRoomEmoticonMenu()) {
            @Override
            public void showAndRemoveMenuByClickingButton() {
                updateButtonStatus(this);
                FragmentTransaction transaction = ChattingObjectStorage.fragmentTransaction();

                if (isOn()) {
                    transaction.replace(R.id.chatting_room_footer_bottom_menu, getFragment()).commitAllowingStateLoss();
                } else {
                    transaction.remove(getFragment()).commitAllowingStateLoss();
                }
            }
        },
        // SEARCH_WITH_HASH_BUTTON (false, null), It's not handling Fragment
        SIDE_BAR_BUTTON(false, null) {
            @Override
            void showAndRemoveMenuByClickingButton() {
                // updateButtonStatus(this); // it doesn't seem to work..?

                if (SharedChattingRoomViewData.isSearchWithHashOn()) {
                    ChattingObjectStorage.switchHashTagSearchMode(false);
                }
                WindowSoft.hideKeyboardFrom(ChattingObjectStorage.applicationContext(), ChattingObjectStorage.chattingInputEditText());
                Buttons.removeAllMenu(ChattingObjectStorage.fragmentTransaction());
                ChattingObjectStorage.mainLayout().openDrawer(Gravity.RIGHT);
                SharedChattingRoomViewData.setSideBarOn(true);
            }
        }
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
         * Activate and deactivate menus <br />
         * It is only work with Fragment
         */
        abstract void showAndRemoveMenuByClickingButton();

        /**
         * Update buttons' status <br />
         *
         * <ul>
         *     <li>clicked button -> ON</li>
         *     <li>other buttons -> OFF</li>
         * </ul>
         */
        private static void updateButtonStatus(Buttons self) {
            for (Buttons button : Buttons.values()) {
                // update clicked button to On
                if (button == self) {
                    button.setButtonStatus(true);
                    continue;
                }

                // set others to false
                button.setButtonStatus(false);
            }
        }

        /**
         * Deactivate all menus
         */
        public static void removeAllMenu(FragmentTransaction transaction) {

            if (transaction == null)
                return;

            if (SharedChattingRoomViewData.isSideBarOn()) {
                ChattingObjectStorage.mainLayout().closeDrawer(Gravity.RIGHT);
                SharedChattingRoomViewData.setSideBarOn(false);
            }

            for (Buttons button : values()) {
                if (button.isOn()) {
                    transaction.remove(button.getFragment()).commitAllowingStateLoss();
                    button.setButtonStatus(false);
                }
            }
        }

        /**
         * Check if any of menu is activated
         */
        public static boolean isAnyMenuActivated() {

            if (SharedChattingRoomViewData.isOn()) {
                return true;
            }

            for (Buttons button : values()) {
                if (button.isOn()){
                    return true;
                }
            }
            return false;
        }

    }

    // ---------------------------------------------------------------------------------------------
    // CHATTING

    public class Chat {
        private String _nickName;
        private String _message;

        private Chat(String nickName, String message) {
            this._nickName = nickName;
            this._message = message;
        }

        public String getNickName() {
            return _nickName;
        }

        public String getMessage() {
            return _message;
        }
    }

}