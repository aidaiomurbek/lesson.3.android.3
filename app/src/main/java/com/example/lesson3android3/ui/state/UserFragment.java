package com.example.lesson3android3.ui.state;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lesson3android3.R;

public class UserFragment extends Fragment {

    private static final String ARG_NAME = "param1";
    private static final String ARG_TOKEN = "param2";

    private String userName;
    private String userToken;

    public UserFragment(){}

    public static  UserFragment newInstance(String userName, String userToken) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, userName);
        args.putString(ARG_TOKEN, userToken);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userName = getArguments().getString(ARG_NAME);
            userToken = getArguments().getString(ARG_TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView textName = view.findViewById(R.id.textUserNameF);
        TextView textToken = view.findViewById(R.id.textTokenF);

        textName.setText(userName);
        textToken.setText(userToken);
    }
}