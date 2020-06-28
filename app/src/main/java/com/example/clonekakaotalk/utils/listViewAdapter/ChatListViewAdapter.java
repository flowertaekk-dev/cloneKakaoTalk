package com.example.clonekakaotalk.utils.listViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.clonekakaotalk.ChattingRoomActivity;
import com.example.clonekakaotalk.R;

import java.util.List;

public class ChatListViewAdapter extends BaseAdapter {

    private LayoutInflater _inflater;
    private List<ChattingRoomActivity.Chat> _chatList;

    public ChatListViewAdapter(LayoutInflater layoutInflater, List<ChattingRoomActivity.Chat> chatList) {
        this._inflater = layoutInflater;
        this._chatList = chatList;
    }

    @Override
    public int getCount() {
        return _chatList.size();
    }

    @Override
    public Object getItem(int position) {
        return _chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = _inflater.inflate(R.layout.chatting_component, parent, false);

            viewHolder = new ViewHolder();

            viewHolder.picture = convertView.findViewById(R.id.chatting_component_picture);
            viewHolder.chattingContainer = convertView.findViewById(R.id.chatting_component_container);
            viewHolder.myChat = convertView.findViewById(R.id.my_chat);
            viewHolder.nickname = convertView.findViewById(R.id.chatting_component_nickname);
            viewHolder.message = convertView.findViewById(R.id.chatting_component_message);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set data
        if (_chatList.get(position).getNickName().equals(";")) {

            viewHolder.picture.setVisibility(View.GONE);
            viewHolder.chattingContainer.setVisibility(View.GONE);
            viewHolder.myChat.setVisibility(View.VISIBLE);

            viewHolder.myChat.setText(_chatList.get(position).getMessage());

        } else {
            viewHolder.picture.setVisibility(View.VISIBLE);
            viewHolder.chattingContainer.setVisibility(View.VISIBLE);
            viewHolder.myChat.setVisibility(View.GONE);

            viewHolder.nickname.setText(_chatList.get(position).getNickName());
            viewHolder.message.setText(_chatList.get(position).getMessage());
        }

        return convertView;
    }

    // ---------------------------------------------------------------------------------------------
    // View holder

    public class ViewHolder {
        ImageView picture;
        LinearLayout chattingContainer;
        TextView myChat;
        TextView nickname;
        TextView message;
    }
}
