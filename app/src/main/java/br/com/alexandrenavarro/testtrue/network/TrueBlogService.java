package br.com.alexandrenavarro.testtrue.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by alexa on 11/03/2018.
 */

public interface TrueBlogService {

    @GET("2018/01/22/life-as-an-android-engineer")
    Call<ResponseBody> getHtml();
}