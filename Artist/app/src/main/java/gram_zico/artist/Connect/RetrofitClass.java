package gram_zico.artist.Connect;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root1 on 2017. 9. 19..
 */

public class RetrofitClass {

    private static final RetrofitClass ourInstance = new RetrofitClass();

    public static RetrofitClass getInstance() {
        return ourInstance;
    }

    private Retrofit retrofit;

    private String url = "http://52.79.134.200:5590";

    public APIInterface apiInterface;

    private RetrofitClass() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(APIInterface.class);
    }
}
