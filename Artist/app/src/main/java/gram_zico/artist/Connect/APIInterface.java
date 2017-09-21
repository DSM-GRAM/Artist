package gram_zico.artist.Connect;

import com.google.gson.JsonObject;

import gram_zico.artist.Model.CategoryCountModel;
import gram_zico.artist.Model.CategoryIDModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by root1 on 2017. 9. 20..
 */

public interface APIInterface {

    @GET("/category-count")
    Call<CategoryCountModel> getCategoryCount();

    @POST("/new-sample")
    @FormUrlEncoded
    Call<CategoryIDModel> getCategoryID(@Field("category") int categoryNum);

    @POST("/compare/{id}")
    @Multipart
    Call<JsonObject> uploadImage(@Path("id")String id, @Part MultipartBody.Part file1);
}
