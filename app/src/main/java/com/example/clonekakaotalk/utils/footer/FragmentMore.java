package com.example.clonekakaotalk.utils.footer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clonekakaotalk.R;

public class FragmentMore extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View moreView = inflater.inflate(R.layout.fragment_more, container, false);

        // set title on Header
        TextView headerTitleView = moreView.findViewById(R.id.header_title);
        FooterFragment.MORE.setTitleOnHeader(headerTitleView);

        return moreView;
    }

}
