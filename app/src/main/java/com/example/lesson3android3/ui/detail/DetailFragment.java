package com.example.lesson3android3.ui.detail;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.lesson3android3.R;
import com.example.lesson3android3.data.model.Post;
import com.example.lesson3android3.data.remote.PostStorage;
import com.example.lesson3android3.interfaces.CreatePost;
import com.example.lesson3android3.interfaces.UpdatePost;
import com.google.android.material.textfield.TextInputLayout;
public class DetailFragment extends Fragment {
    private ProgressButton progressButton;
    private TextInputLayout editTitle;
    private TextInputLayout editContent;
    private TextInputLayout editUser;
    private TextInputLayout editGroup;
    private NavController navController;
    private Post post;
    private View btnSend;

    public DetailFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { post = (Post) getArguments().getSerializable("post");}
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setListeners();
    }

    @SuppressLint("SetTextI18n")
    private void init(View view) {
        progressButton = new ProgressButton(requireContext(), view);
        btnSend = view.findViewById(R.id.btnSendPost);
        editTitle = view.findViewById(R.id.editTitle);
        editContent = view.findViewById(R.id.editContent);
        editUser = view.findViewById(R.id.editUser);
        editGroup = view.findViewById(R.id.editGroup);

        if (post != null){
            editTitle.getEditText().setText(post.getTitle());
            editContent.getEditText().setText(post.getContent());
            editUser.getEditText().setText(post.getUser().toString());
            editGroup.getEditText().setText(post.getGroup().toString());
        }
    }

    private void setListeners() {
        btnSend.setOnClickListener(v -> {
            if (post != null) {
                postSet();
                PostStorage.updatePost(post.getId(), post, new UpdatePost() {
                    @Override
                    public void onSuccess(Post post) { close();}

                    @Override
                    public void onFailure(String error){}
                });
            } else {
                post = new Post();
                postSet();
                PostStorage.createPost(post, new CreatePost() {
                    @Override
                    public void onSuccess(Post post) {close();}

                    @Override
                    public void onFailure(String error){}
                });
            }
        });
    }

    private void postSet() {
        progressButton.buttonActivated();
        post.setTitle(editTitle.getEditText().getText().toString());
        post.setContent(editContent.getEditText().getText().toString());
        Integer user = Integer.parseInt(editUser.getEditText().getText().toString());
        Integer group = Integer.parseInt(editGroup.getEditText().getText().toString());
        post.setUser(user);
        post.setGroup(group);
    }

    private void close() {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            progressButton.buttonActivated();
            Activity activity = getActivity();
            if (activity != null) {
                navController = Navigation.findNavController(requireActivity(),
                        R.id.nav_host_fragment);
                navController.navigateUp();
            }}, 1000);
    }
}