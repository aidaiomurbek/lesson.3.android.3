package com.example.lesson3android3.ui.recycler;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson3android3.R;
import com.example.lesson3android3.data.model.Post;
import com.example.lesson3android3.interfaces.OnPostClick;
import com.example.lesson3android3.interfaces.OnPostLongClick;

import java.util.ArrayList;
import java.util.List;

public class PostsRVAdapter extends RecyclerView.Adapter<PostsRVAdapter.ViewHolder>{
    private List<Post> list = new ArrayList<>();
    private OnPostClick postClick;
    private OnPostLongClick postLongClick;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() { return list.size();}

    public void addList(List<Post> listPost) {
        this.list = listPost;
        notifyDataSetChanged();
    }

    public void setPostClick(OnPostClick postClick) { this.postClick = postClick; }

    public void setPostLongClick(OnPostLongClick postLongClick) { this.postLongClick = postLongClick; }

    public void deletePost(Post post) {
        list.remove(post);
        notifyDataSetChanged();
    }

    public Post getPost(int position) { return list.get(position); }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textTitle;
        private final TextView textContent;
        private final TextView textUser;
        private final TextView textGroup;

        public ViewHolder(@NonNull CardView itemView) {
            super(itemView);
            itemView.setOnLongClickListener(v -> {
                postLongClick.postLong(getAdapterPosition(), list.get(getAdapterPosition()));
                return true;
            });
            itemView.setOnClickListener(v -> postClick.postClick(getAdapterPosition(),list.get(getAdapterPosition())));
            textTitle = itemView.findViewById(R.id.textTitle);
            textContent = itemView.findViewById(R.id.textContent);
            textUser = itemView.findViewById(R.id.textUser);
            textGroup = itemView.findViewById(R.id.textGroup);
        }

        @SuppressLint("SetTextI18n")
        public void onBind(Post post) {
            textTitle.setText(post.getTitle());
            textContent.setText(post.getContent());
            textUser.setText(post.getUser().toString());
            textGroup.setText(post.getGroup().toString());
        }
    }
}
