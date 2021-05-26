package com.example.lesson3android3.ui.state;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lesson3android3.data.model.User;

import java.util.List;

public class UserStateAdapter extends FragmentStateAdapter {

    private final List<User> userList;

    public UserStateAdapter(@NonNull FragmentActivity fragmentActivity, List<User> users) {
        super(fragmentActivity);
        this.userList = users;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return UserFragment.newInstance(
                userList.get(position).getName(),
                userList.get(position).getToken());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
