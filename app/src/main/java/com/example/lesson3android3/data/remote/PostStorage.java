package com.example.lesson3android3.data.remote;


import com.example.lesson3android3.data.model.Post;
import com.example.lesson3android3.interfaces.CreatePost;
import com.example.lesson3android3.interfaces.DeletePost;
import com.example.lesson3android3.interfaces.GetPosts;
import com.example.lesson3android3.interfaces.UpdatePost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostStorage {
    public static void getPosts(GetPosts getPosts) {
        RetrofitBuilder
                .getInstance()
                .getPosts()
                .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            getPosts.onSuccess(response.body());
                        } else {
                            getPosts.onFailure("Response is not successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        getPosts.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void deletePost(String id, DeletePost deletePost) {
        RetrofitBuilder
                .getInstance()
                .deletePost(id)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            deletePost.onSuccess(response.body());
                        } else {
                            deletePost.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        deletePost.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void createPost(Post post, CreatePost createPost) {
        RetrofitBuilder.getInstance()
                .createPost(post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            createPost.onSuccess(response.body());
                        } else {
                            createPost.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        createPost.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void updatePost(String id, Post post, UpdatePost updatePost) {
        RetrofitBuilder.getInstance()
                .updatePost(id, post)
                .enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            updatePost.onSuccess(response.body());
                        } else {
                            updatePost.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        updatePost.onFailure(t.getLocalizedMessage());
                    }
                });
    }
}

