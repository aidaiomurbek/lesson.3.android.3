package com.example.lesson3android3.interfaces;

import com.example.lesson3android3.data.model.Post;

import java.util.List;

public interface GetPosts {
    void onSuccess(List<Post> posts);
    void onFailure(String error);
}
