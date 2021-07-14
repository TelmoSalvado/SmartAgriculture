package pt.ipg.smartagriculture;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("/channels/1424295/feed.json?results=400")
    Call<Feeds> getAllFeeds();
}
