package com.example.lesson3android3.ui.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson3android3.R;
import com.example.lesson3android3.data.model.User;

import java.util.List;

public class UserRVAdapter extends RecyclerView.Adapter<UserRVAdapter.UserVH> {

    private final List<User> users;

    public UserRVAdapter(List<User> users) { this.users = users; }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        holder.onBind(users.get(position));
    }

    @Override
    public int getItemCount() { return users.size(); }

    class UserVH extends RecyclerView.ViewHolder {
        private final TextView textUserName;
        private final TextView textUserToken;

        public UserVH(@NonNull View itemView) {
            super(itemView);
            textUserName = itemView.findViewById(R.id.textUserName);
            textUserToken = itemView.findViewById(R.id.textUserToken);
        }

        public void onBind(User user) {
            textUserName.setText(user.getName());
            textUserToken.setText(user.getToken());
        }
    }
}

