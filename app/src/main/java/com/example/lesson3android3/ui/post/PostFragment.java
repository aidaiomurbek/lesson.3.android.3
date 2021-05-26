package com.example.lesson3android3.ui.post;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lesson3android3.R;
import com.example.lesson3android3.data.model.Post;
import com.example.lesson3android3.data.remote.PostStorage;
import com.example.lesson3android3.interfaces.DeletePost;
import com.example.lesson3android3.interfaces.GetPosts;
import com.example.lesson3android3.ui.recycler.PostsRVAdapter;

import java.util.List;

public class PostFragment extends Fragment {
    private PostsRVAdapter adapter;
    private RecyclerView recyclerPost;
    private NavController navController;
    private TextView textPost;
    private Button btnAdd;
    private int position;
    private Post post;

    public PostFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostsRVAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadPosts();
        setListeners();
    }

    private void setListeners() {
        btnAdd.setOnClickListener(v -> open(null));
        adapter.setPostLongClick(this::alertBuilder);
        adapter.setPostClick((position, post) -> {
            this.position = position;
            open(post);
        });
    }

    private void open(Post post) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("post", post);
        navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.detailFragment, bundle);
    }

    private void init(View view) {
        recyclerPost = view.findViewById(R.id.recyclerPosts);
        btnAdd = view.findViewById(R.id.btnAdd);
    }

    private void loadPosts() {
        PostStorage.getPosts(new GetPosts() {
            @Override
            public void onSuccess(List<Post> posts) {
                adapter.addList(posts);
                recyclerPost.setAdapter(adapter);
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    private void alertBuilder(int position, Post post) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.alert_layout, null);

        Button delete = view.findViewById(R.id.delete);
        Button cancel = view.findViewById(R.id.cancel);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext())
                .setView(view);
        final AlertDialog dialog = alert.create();

        delete.setOnClickListener(v -> {
            PostStorage.deletePost(post.getId(), new DeletePost() {
                @Override
                public void onSuccess(Post post) {
                    Post post1 = adapter.getPost(position);
                    adapter.deletePost(post1);
                }

                @Override
                public void onFailure(String error) {
                }
            });
            dialog.dismiss();
        });

        cancel.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}