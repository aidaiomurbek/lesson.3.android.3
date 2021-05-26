package com.example.lesson3android3.data.remote;

import com.example.lesson3android3.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AndroidApi {

    @GET(EndPoints.END_POINT)
    Call<List<Post>> getPosts();

    @POST(EndPoints.END_POINT)
    Call<Post> createPost(@Body Post post);

    @DELETE(EndPoints.END_POINT_ID)
    Call<Post> deletePost(@Path("id") String id);

    @PUT(EndPoints.END_POINT_ID)
    Call<Post> updatePost(@Path("id") String id, @Body Post post);
}

