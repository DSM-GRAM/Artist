package gram_zico.artist.Connect;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by root1 on 2017. 9. 20..
 */

public interface APIInterface {

    @GET("/hello")
    Call<Void> hello();
}
