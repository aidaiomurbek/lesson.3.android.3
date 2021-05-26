package com.example.lesson3android3.interfaces;

import com.example.lesson3android3.data.model.Post;

public interface CreatePost {
    void onSuccess(Post post);
    void onFailure(String error);
}
